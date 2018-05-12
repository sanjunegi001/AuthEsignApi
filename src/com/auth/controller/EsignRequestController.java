package com.auth.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureOptions;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.auth.bean.Esign;
import com.auth.dao.EsignDao;
import com.auth.dao.UserLoginDAO;
import com.auth.model.Bios;
import com.auth.service.EsignSessionService;
import com.auth.util.Log;

import com.auth.util.AUAUtilities;
import com.auth.util.ESIGNProperties;
import com.auth.util.signPDF;
import com.nsdl.esign.preverifiedNo.service.EsignService;

import com.auth.bean.EsignSession;

@Controller
public class EsignRequestController {

	public static HttpServletRequest requestStatic = null;
	private KeyStore.PrivateKeyEntry keyEntry;
	private static final String KEY_STORE_TYPE = "PKCS12";
	private static final String MEC_TYPE = "DOM";
	private static final String WHOLE_DOC_URI = "";
	public SignatureOptions options = null;

	@Autowired
	private EsignDao esignDAO;
	
	@Autowired
	private EsignSessionService esignservice;

	/** The user login DAO. */
	@Autowired
	private UserLoginDAO userLogindao;

	@RequestMapping(value = "/download ", method = RequestMethod.GET)
	public void Download(HttpServletRequest request, HttpServletResponse response) {

	}

	/*
	 * @Controller for prepare the input xml for NSDL Send input xml to Redirect
	 * URL
	 * 
	 */
	@RequestMapping(value = "/otpdocsign", method = RequestMethod.POST)
	public @ResponseBody String otpdocsign(HttpSession session,HttpServletRequest request,
			HttpServletResponse response) throws Exception {

	try{	
		String propFilePath = "";
		ESIGNProperties.load(propFilePath);
		String UPLOADED_FOLDER = ESIGNProperties.getUploadpath();
		
		/** Set Global Variables **/
		String aadhaarno="",authmode="",response_time = "",request_time="",requestXML="",requested_by="",
				name="",location="",reason="";
		
		String orgip = AUAUtilities.getClientIpAddr(request);
		
		
		/*Set Request Time*/
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date reqdate = new Date();
		request_time = dateFormat.format(reqdate);
		
		int access = userLogindao.isAcessDetails(session.getAttribute("user_login_name").toString(), session);

		if (session.getAttribute("user_login_name") != null && access == 1) {

			aadhaarno = request.getParameter("aadhaarno");
			authmode = request.getParameter("authmode");
			name=request.getParameter("name");
			location=request.getParameter("location");
			reason=request.getParameter("reason");
			requested_by=(String) session.getAttribute("user_login_name");
			List<MultipartFile> files = ((MultipartHttpServletRequest) request)
				    .getFiles("file");
			
			/** Set Transaction Id **/
			String txn = EsignAuthController.generateTXN();
			String fileName="";
			String extensionOfFileName="";
			JSONObject jesign = new JSONObject();
			
			
			for (MultipartFile mpf:files) {
				
				fileName=mpf.getOriginalFilename();
				extensionOfFileName = fileName.substring(fileName.lastIndexOf(".") + 1);
				if (null != extensionOfFileName && extensionOfFileName.equalsIgnoreCase("pdf")) {
	    	
					Date dNow = new Date();
					SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd_HHmmss");
					String datetime = ft.format(dNow);
					
					
					
					signPDF spdf = new signPDF(authmode, aadhaarno);
					
					/*upload the pdf files */
					byte[] bytes = mpf.getBytes();
					Path path = Paths
							.get(UPLOADED_FOLDER + File.separator +"unsigned"+File.separator+ mpf.getOriginalFilename().replaceAll(".pdf", "").replaceAll(".PDF", "") + datetime + ".pdf");
					Files.write(path, bytes);
					
					/*read the file*/
					File inFile = new File(
							UPLOADED_FOLDER + File.separator +"unsigned"+File.separator+ mpf.getOriginalFilename().replaceAll(".pdf", "").replaceAll(".PDF", "") + datetime + ".pdf");
					
					
					/*Set Session Values*/
					session.setAttribute("request_time", request_time);
					if (authmode.equals("1"))
						session.setAttribute("authmode", "OTP");
					else if (authmode.equals("2"))
						session.setAttribute("authmode", "BIO");
					else
						session.setAttribute("authmode", "IRIS");
					
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
					esession.setAuth_mode(Integer.parseInt(authmode));
					esignservice.save(esession);
					
					try {
						EsignService requestxml=new EsignService();
						InputStream targetStream = new FileInputStream(inFile);
						
						
						/**Get inputxml for NSDL*/
						
						Log.subaua.info("Request Meta Data::AuthMode:"+session.getAttribute("authmode")+"::TranscationID:"+txn+
								"::RequestTime:"+request_time+"::File Name:"+fileName);
						requestXML = spdf.getResponseNSDL(targetStream, txn,ESIGNProperties.getResponseURL());
						
						
						if (!requestXML.isEmpty() && requestXML != null) {
							Log.subaua.info("User Redirected to NSDL Page");
							jesign.put("statuscode", "1");
							jesign.put("inputxml", requestXML);
							jesign.put("addharno", aadhaarno);
							return jesign.toString();

						} else {
							Log.subaua.info("Invalid input xml");
							jesign.put("statuscode", "2");
							jesign.put("error", "Invalid input xml");
							return jesign.toString();
						}
						
					}catch(Exception ex){
						Log.subaua.info("Exception Occure"+ex.getMessage());
						jesign.put("statuscode", "3");
						jesign.put("message", "There is some Technical issue please contact technical support Team");
						System.out.println(ex.getMessage());
						return jesign.toString();
					}
					
					
				}else{
					Log.subaua.info("Invalid PDF files");
					jesign.put("statuscode", "4");
					return jesign.toString();
					
				}
		
			}
		
			
			return jesign.toString();

		} else {
			return ("redirect:/login");

		}
		
	  }catch(Exception ex){
		    JSONObject jesign = new JSONObject();
		    jesign.put("statuscode", "3");
			jesign.put("message", "There is some Technical issue please contact technical support Team");
			System.out.println(ex.getMessage());
			return jesign.toString();
	  }

	}

}
