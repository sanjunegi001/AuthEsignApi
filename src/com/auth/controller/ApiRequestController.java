package com.auth.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.auth.bean.EsignSession;
import com.auth.dao.UserLoginDAO;
import com.auth.service.EsignSessionService;
import com.auth.util.AUAUtilities;
import com.auth.util.ESIGNProperties;
import com.auth.util.Log;
import com.auth.util.paramValidation;
import com.auth.util.signPDF;
import com.google.gson.JsonParser;

/**
 * The Class ApiController.
 */
@Controller
public class ApiRequestController {

	
	/** The user login DAO. */
	@Autowired
	private UserLoginDAO userLogindao;
	
	
	@Autowired
	private EsignSessionService esignservice;
	
	@RequestMapping(value = "/esignApi", method = { RequestMethod.POST , RequestMethod.GET})
	public ModelAndView demographicApiAuthentication(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws Exception {
		
		
		
		
		String propFilePath = "";
		ESIGNProperties.load(propFilePath);
		String UPLOADED_FOLDER = ESIGNProperties.getUploadpath();
		
		Map<String, String> mapheader = new HashMap<String, String>();
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			mapheader.put(key, value);

		}
		
		
		/*Set Request Time*/
		String request_time="";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date reqdate = new Date();
		request_time = dateFormat.format(reqdate);
		
		
		
		/**Global Variables*/
		String esign_data="",requested_by="",aadharcardnumber="",authmode="",name="",location="",reason="",response_url="";
		String txn="";
		String orgip = AUAUtilities.getClientIpAddr(request);
		
		
		if (request.getMethod().toUpperCase().contains("POST")) {
			
				
				/** Request parameter checked */
				Properties propertiesquery = new Properties();
				ClassLoader classloadererrorquery = Thread.currentThread().getContextClassLoader();
				propertiesquery.load(new FileInputStream(new File(classloadererrorquery.getResource("parameter.properties").getFile())));
				ClassLoader classLoader = getClass().getClassLoader();
				
				
				
				
				 if (StringUtils.isNotEmpty(mapheader.get("username"))&& StringUtils.isNotEmpty(mapheader.get("password"))){
					 
					 Log.subaua.info("User Access With::"+mapheader.get("username"));
					 boolean isDemoValidUser = userLogindao.isValidUser(mapheader.get("username"), mapheader.get("password"), session);
					 if (isDemoValidUser == false) {
						    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
							
							JsonObjectBuilder value2 = Json.createObjectBuilder().add("StatusCode", "A102").add("Message", "You are not authorized to make such request. Please contact system administrator.");

							JsonObject dataJsonObject = value2.build();
							model.addAttribute("model", dataJsonObject);
							return new ModelAndView("esignapires");
					 }else{
						
						 esign_data=request.getParameter("data").trim();
						 requested_by=mapheader.get("username").trim();
						 try{
							    
							    Boolean isValid = true;
							    Boolean isAadhaar = true;
							    Boolean isAuthmode = true;
							    Boolean isFilepath = true;
							    Boolean isResponseurl = true;
							    Boolean isTransactionid=true;
							    
							    String fkey = "";
							   
								 JSONObject resobj = new JSONObject(esign_data);
								 Map map = new HashMap();
								 paramValidation pval = new paramValidation();
								 Iterator<?> keys = resobj.keys();
									while (keys.hasNext()) {
									
									fkey = (String) keys.next();
									map.put(fkey, resobj.get(fkey).toString());
									if (propertiesquery.getProperty(fkey) != null) {
										
										/** Checking valid aadhaar number **/
										if (fkey.equalsIgnoreCase("aadhaarnumber")) {

											isAadhaar = pval.isAaadharValid(resobj.get("aadhaarnumber").toString());

										}
										/** Checking valid authmode **/
										if (fkey.equalsIgnoreCase("authmode")) {
											isAuthmode = pval.isAuthmodeValid(resobj.get("authmode").toString());
											
										}
										/** Checking valid filepath **/
										  //System.out.println("sanjay"+request.getParameter("file"));
										    //isFilepath = pval.isFilepathValid(request.getParameter("file"));
										
										/** Checking valid responseurl **/
										if (fkey.equalsIgnoreCase("responseurl")) {
											isResponseurl = pval.isResponseurlValid(resobj.get("responseurl").toString());
										}
										
										
										if (fkey.equalsIgnoreCase("name")) {
											
											name=pval.isNameValid(resobj.get("name").toString())?resobj.get("name").toString():"";
											
										}
										
										if (fkey.equalsIgnoreCase("location")) {
											
											location=pval.isLocationValid(resobj.get("location").toString())?resobj.get("location").toString():"";
											
										}
										
										
										if (fkey.equalsIgnoreCase("reason")) {
											
											reason=pval.isReasonValid(resobj.get("reason").toString())?resobj.get("reason").toString():"";
											
										}
										if (fkey.equalsIgnoreCase("transactionid")) {
											
											isTransactionid = pval.istxnValid(resobj.get("transactionid").toString());
											
										}
										
										
									}else{
										isValid = false;
										break;
										
									}
									
								}
						
								
							if (isValid == true) {
									
								if (isAadhaar == true){
										
									if (isAuthmode == true){
											
										if (isFilepath == true){
												
											if (isResponseurl == true){
													
												if (isTransactionid == true){
													
												  try {
														String inputxml="",extensionOfFileName="";
			
														aadharcardnumber = resobj.get("aadhaarnumber").toString().trim();
														response_url=resobj.get("responseurl").toString().trim();
														txn=resobj.get("transactionid").toString().trim();
														if(resobj.get("authmode").toString().equalsIgnoreCase("otp"))
															authmode="1";
														else if(resobj.get("authmode").toString().equalsIgnoreCase("bio"))
														   authmode="2";
														
													    File file = new File(UPLOADED_FOLDER + File.separator +"unsigned");
														
															if (!file.exists()) {                                                                                                      //if File is Exist remains as it is otherwise create folder 
																if(file.mkdir()){ 
																}
															}
													
														MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
															
														file.setExecutable(true, false);
														file.setReadable(true, false);
														file.setWritable(true, false);
														
														Date dNow = new Date();
														SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd_HHmmss");
														String datetime = ft.format(dNow);
														
														
														Iterator<String> itr = multipartRequest.getFileNames();
														MultipartFile mpf = multipartRequest.getFile(itr.next());
														InputStream inputStream = null;
														String fileName = mpf.getOriginalFilename();
														byte[] bytes = mpf.getBytes();
														extensionOfFileName = fileName.substring(fileName.lastIndexOf(".") + 1);
														
													if (null != extensionOfFileName && extensionOfFileName.equalsIgnoreCase("pdf")) {	
														Path path = Paths
																.get(UPLOADED_FOLDER + File.separator +"unsigned"+File.separator+ mpf.getOriginalFilename().replaceAll(".pdf", "").replaceAll(".PDF", "") + datetime + ".pdf");
														Files.write(path, bytes);
														File inFile = new File(
																UPLOADED_FOLDER + File.separator +"unsigned"+File.separator+ mpf.getOriginalFilename().replaceAll(".pdf", "") + datetime + ".pdf");
														signPDF spdf = new signPDF(authmode, aadharcardnumber);
														
														
														/*Save session value*/
														EsignSession esession=new EsignSession();
														esession.setIpaddress(orgip);
														esession.setRequest_time(new Timestamp(dateFormat.parse(request_time).getTime()));
														esession.setRequested_by(requested_by);
														esession.setTransaction_id(txn);
														esession.setPdf_path(inFile.toString());
														esession.setEsign_name(name);
														esession.setEsign_location(location);
														esession.setEsign_reason(reason);
														esession.setDoc_id(1);
														esession.setDoc_timestamp(datetime);
														esession.setResponse_url(response_url);
														esession.setAuth_mode(Integer.parseInt(authmode));
														esignservice.save(esession);
														
															InputStream targetStream = new FileInputStream(inFile);
															
															inputxml = spdf.getResponseNSDL(targetStream, txn,ESIGNProperties.getResponseURLAPI());
															Log.subaua.info("Request Meta Data::AuthMode:"+resobj.get("authmode")+"::TranscationID:"+txn+
																	"::RequestTime:"+request_time+"::File Name:"+fileName);
															PrintWriter out = response.getWriter();
															out.println("<html><body>");
															out.println(
																	"<form  id='eSignForm'  method='post' action='https://14.142.129.242/EsignAuth/getEkycDetails' target='_blank'>");

															
															 
															out.println("<input type='hidden' name='msg' id='msg' value='"+inputxml+"'/>");
															out.println(
																	"<input type=hidden name='obj' id='obj' value="
																			+ aadharcardnumber + ">");
															out.println("</form>");
															out.println("</body>");
															out.println(
																	"<script type='text/javascript'>");
															
															out.println(
																	"document.getElementById('eSignForm').submit();");
															out.println("</script>");
															out.println("</html>");
															out.close();
													     }else{
													    	 
													    	  Log.subaua.info("Response Meta Data::"+"StatusCode:A111::Message:Invalid pdf file.");
																response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
																JsonObjectBuilder value2 = Json.createObjectBuilder().add("StatusCode", "A111").add("Message", "Invalid Pdf File.");
																JsonObject dataJsonObject = value2.build();
																model.addAttribute("model", dataJsonObject);
													     }	
														}catch(Exception ex){
															Log.subaua.info("Response Meta Data::"+"StatusCode:A117::Message:"+ex.getMessage());
															response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
															JsonObjectBuilder value2 = Json.createObjectBuilder().add("StatusCode", "A117").add("Message", "Something Went Wrong.");
															JsonObject dataJsonObject = value2.build();
															model.addAttribute("model", dataJsonObject);
															
														}
											    	}else{
											    		Log.subaua.info("Response Meta Data::"+"StatusCode:A109::Message:Please Check TransactionID.");
														response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
														JsonObjectBuilder value2 = Json.createObjectBuilder().add("StatusCode", "A109").add("Message", "Please Check TransactionID.");
														JsonObject dataJsonObject = value2.build();
														model.addAttribute("model", dataJsonObject);
													}	
													
												}else{
													Log.subaua.info("Response Meta Data::"+"StatusCode:A103::Message:Please Check Response URL.");
													response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
													JsonObjectBuilder value2 = Json.createObjectBuilder().add("StatusCode", "A103").add("Message", "Please Check Response URL.");
													JsonObject dataJsonObject = value2.build();
													model.addAttribute("model", dataJsonObject);
												}
												
											}else{
												Log.subaua.info("Response Meta Data::"+"StatusCode:A104::Message:Please Check File Path.");
												response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
												JsonObjectBuilder value2 = Json.createObjectBuilder().add("StatusCode", "A104").add("Message", "Please Check File Path.");
												JsonObject dataJsonObject = value2.build();
												model.addAttribute("model", dataJsonObject);
											}
											
											
										}else{
											Log.subaua.info("Response Meta Data::"+"StatusCode:A105::Message:Invalid Auth Mode.");
											response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
											JsonObjectBuilder value2 = Json.createObjectBuilder().add("StatusCode", "A105").add("Message", "Invalid Auth Mode.");
											JsonObject dataJsonObject = value2.build();
											model.addAttribute("model", dataJsonObject);
											
										}
										
									}else{
										Log.subaua.info("Response Meta Data::"+"StatusCode:A106::Message:Aadhaar Number should be of 12 digits.");
										response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
										JsonObjectBuilder value2 = Json.createObjectBuilder().add("StatusCode", "A106").add("Message", "Aadhaar Number should be of 12 digits.");
										JsonObject dataJsonObject = value2.build();
										model.addAttribute("model", dataJsonObject);
						
									}
									
									
								}else{
									Log.subaua.info("Response Meta Data::"+"StatusCode:A107::Message:Please check the parameter.");
									response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
									JsonObjectBuilder value2 = Json.createObjectBuilder().add("StatusCode", "A107").add("Message", "Please check the parameter.");
									JsonObject dataJsonObject = value2.build();
									model.addAttribute("model", dataJsonObject);
								}
								
						 }catch(Exception e){
							    Log.subaua.info("Response Meta Data::"+"StatusCode:A117::Message:"+e.getMessage());
							    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
								JsonObjectBuilder value2 = Json.createObjectBuilder().add("StatusCode", "A117").add("Message", "Something Went Wrong.");
								JsonObject dataJsonObject = value2.build();
								model.addAttribute("model", dataJsonObject);
							 
						 }
						 
						 
					 }
					 
					 
					 
				 }else{
					    Log.subaua.info("Response Meta Data::"+"StatusCode:A100::Message:Bad Request. Please check your headers.");
					    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
						JsonObjectBuilder value2 = Json.createObjectBuilder().add("StatusCode", "A100").add("Message", "Bad Request. Please check your headers.");
						JsonObject dataJsonObject = value2.build();
						model.addAttribute("model", dataJsonObject); 
					 
				 }
	
		}else{
			Log.subaua.info("Response Meta Data::"+"StatusCode:A101::Message:Invalid Request Method");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			JsonObjectBuilder value2 = Json.createObjectBuilder().add("StatusCode", "A101").add("Message","Invalid Request Method");
			JsonObject dataJsonObject = value2.build();
			model.addAttribute("model", dataJsonObject);
			
		}
		
		return new ModelAndView("esignapires");
		
		
		
	}

}
