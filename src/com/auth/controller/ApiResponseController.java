package com.auth.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SignatureException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.auth.bean.Esign;
import com.auth.bean.EsignSession;
import com.auth.dao.EsignDao;
import com.auth.model.EsignResp;
import com.auth.service.EsignSessionService;
import com.auth.util.AUAUtilities;
import com.auth.util.ESIGNProperties;
import com.auth.util.Log;
import com.auth.util.XMLConverter;
import com.auth.util.signPDF;

import com.nsdl.esign.preverifiedNo.service.EsignService;

@Controller
public class ApiResponseController {

	
	public String UPLOADED_FOLDER="";
	public String tickimagepath="";
	public String signedpdfpath="";
	public String authmode="";
	public String directorypath="https://i-pass.in/AuthEsign/resources/UPLOADPDFFILE";
	//public String directorypath="http://localhost/esignV4/UPLOADPDFFILE";
	
	
	@Autowired
	private EsignDao esignDAO;
	
	@Autowired
	private EsignSessionService esignsessionService;
	
	
	/**
	 * 
	 * @method for handle response from NSDL
	 * @param eSignResp
	 * @param aadhaar
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws SignatureException
	 */
	@RequestMapping(value = { "/responseapi" }, method = RequestMethod.POST)
	public String resps(@RequestParam("msg") String eSignResp, @RequestParam("obj") String aadhaar,
			HttpServletRequest request, HttpSession session,HttpServletResponse response, ModelMap model) throws IOException, SignatureException {
		
		
	
		String propFilePath = "";
		ESIGNProperties.load(propFilePath);
		
		/** Set Response Time **/
		String response_time = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date reqdate = new Date();
		response_time = dateFormat.format(reqdate);
		
		/**Initialize variables**/
		EsignService esingservice=new EsignService();
		String redirect_url="",filepath="";
		String ipaddress = AUAUtilities.getClientIpAddr(request);
		signPDF spdf = new signPDF(eSignResp);
		File inFile = null;
		File outFile = null;
		byte []pdf = null;
		XMLConverter XMLconverter = new XMLConverter();
		EsignResp esignresp = new EsignResp();
		HashMap<Integer,byte[]>mapsign=new HashMap<Integer,byte[]>();
		List<Long> listdocid=new ArrayList<Long>();
		List<String> listdoctimestamp=new ArrayList<String>();
		tickimagepath=ESIGNProperties.getTickimagepath();
		UPLOADED_FOLDER=ESIGNProperties.getUploadpath();
		PrintWriter out = response.getWriter();
		
		try {
			
			esignresp = (EsignResp) XMLconverter.convertFromXMLToObject(esignresp, eSignResp);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.subaua.info("Response Meta Data::"+"StatusCode:A117::Message:"+e.getMessage());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			JsonObjectBuilder value2 = Json.createObjectBuilder().add("StatusCode", "A117").add("Message", "Something Went Wrong.");
			JsonObject dataJsonObject = value2.build();
			model.addAttribute("model", dataJsonObject);
	
		}

		
		Esign esign = null;
		esign = new Esign();
		
	try{	
		
		EsignSession esession=esignsessionService.getByTxn(esignresp.getTxn());
		authmode=(esession.getAuth_mode()==1?"OTP":"BIO");
		redirect_url=esession.getResponse_url();
	
		if (esignresp.getStatus().equalsIgnoreCase("1")) {
			
			for (int i = 0; i < esignresp.getSignatures().getDocSignature().length; i++) {
				
				pdf = esignresp.getSignatures().getDocSignature()[i].getValue();
			}
			
			
			inFile = new File(esession.getPdf_path());
			FileInputStream fis = new FileInputStream(inFile);
			
			
			/**write output file**/
			File file = new File(UPLOADED_FOLDER + File.separator + "signed");
			if (!file.exists()) { // if File is Exist remains as it is
									// otherwise create folder
				if (file.mkdir()) {

				}
			}
			
			
			outFile = new File(UPLOADED_FOLDER+File.separator+"signed"+File.separator+inFile.getName());
			FileOutputStream fos = new FileOutputStream(outFile);

			// below the block used for spacing in output sign document
			int c;
			byte[] buffer = new byte[8 * 1024];
			while ((c = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, c);
			}
			fis.close();
			signedpdfpath=UPLOADED_FOLDER+File.separator+"signed"+File.separator+inFile.getName();
			
			
			
			mapsign.put(1, pdf);
			listdocid.add((long) 1);
			listdoctimestamp.add(esession.getDoc_timestamp());
			
			
			esingservice.getSignOnPdf(mapsign, listdocid, listdoctimestamp, signedpdfpath, 
					tickimagepath, 10, 1, esession.getEsign_name(), esession.getEsign_location(), esession.getEsign_reason(),
					40, 60, 160, 50, "");
			
			   filepath=outFile.getAbsolutePath().replaceAll("^(.*?)UPLOADPDFFILE", directorypath)
					   .replaceAll(".pdf","_signedFinal.pdf");
			   
				Log.subaua.info("Response Meta Data::AuthMode:"+authmode+"::TranscationID:"+esignresp.getTxn()+
						"::ResponseTime:"+response_time+"::Message:Esign Process Successfully Done");
				esign.setEsign_status(1);
				esign.setEsign_aadhaar(aadhaar);
				esign.setEsign_auth_type(authmode);
				esign.setEsign_file_path(filepath);
				esign.setEsign_reason(esignresp.getResCode());
				esign.setEsign_reasoncode(esignresp.getErrCode());
				esign.setEsign_timestamp(esignresp.getTs());
				esign.setEsign_transaction_id(esignresp.getTxn());
				esign.setEsign_request_on(esession.getRequest_time());
				esign.setEsign_response_on(new Timestamp(dateFormat.parse(response_time).getTime()));
				esign.setEsign_env("PREPROD");
				esign.setEsign_ip_address(ipaddress);
				esign.setEsign_request_by(esession.getRequested_by());
				esign.setEsign_aspid(ESIGNProperties.getAspId());
				esign.setEsign_api("2.0");
				int esignID = esignDAO.save(esign);
				
				JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
				jsonBuilder.add("TransactionID", esignresp.getTxn());
				jsonBuilder.add("StatusCode", "A200");
				jsonBuilder.add("FilePath", filepath);
				jsonBuilder.add("Message", "Esign Process Successfully Done.");
				JsonObject dataJsonObject = jsonBuilder.build();
				
				
				out.println("<html><body>");
				out.println("<form  id='frmtest' name='frmtest' method='post' action='" + redirect_url
						+ "'>");
				out.println("<input type=hidden name='esigndata' id='esigndata' value='" +new String(Base64.getEncoder().encode(dataJsonObject.toString().getBytes()))
						+ "'>");
				out.println("</form>");
				out.println("</body>");
				out.println("<script type='text/javascript'>");
				out.println("document.forms['frmtest'].submit();");
				out.println("</script>");
				out.println("</html>");
				out.close();

				// *****//
			

		}else {
			
			Log.subaua.info("Response Meta Data::AuthMode:"+authmode+"::TranscationID:"+esignresp.getTxn()+
					"::ResponseTime:"+response_time+"::Message:"+esignresp.getErrMsg());
			esign.setEsign_status(0);
			esign.setEsign_aadhaar(aadhaar);
			esign.setEsign_auth_type(authmode);
			esign.setEsign_file_path("");
			esign.setEsign_reason(esignresp.getResCode());
			esign.setEsign_reasoncode(esignresp.getErrCode());
			esign.setEsign_timestamp(esignresp.getTs());
			esign.setEsign_transaction_id(esignresp.getTxn());
			esign.setEsign_request_on(esession.getRequest_time());
			esign.setEsign_response_on(new Timestamp(dateFormat.parse(response_time).getTime()));
			esign.setEsign_env("PREPROD");
			esign.setEsign_ip_address(ipaddress);
			esign.setEsign_request_by(esession.getRequested_by());
			esign.setEsign_aspid(ESIGNProperties.getAspId());
			esign.setEsign_api("2.0");
			int esignID = esignDAO.save(esign);
			
			
			JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
			jsonBuilder.add("TransactionID", esignresp.getTxn());
			jsonBuilder.add("StatusCode", "A201");
			jsonBuilder.add("Error", esignresp.getErrCode());
			jsonBuilder.add("Message", esignresp.getErrMsg());
			JsonObject dataJsonObject = jsonBuilder.build();
			
			
			out.println("<html><body>");
			out.println("<form  id='frmtest' name='frmtest' method='post' action='" + redirect_url
					+ "'>");
			out.println("<input type=hidden name='esigndata' id='esigndata' value='" +new String(Base64.getEncoder().encode(dataJsonObject.toString().getBytes()))
					+ "'>");
			out.println("</form>");
			out.println("</body>");
			out.println("<script type='text/javascript'>");
			out.println("document.forms['frmtest'].submit();");
			out.println("</script>");
			out.println("</html>");
			out.close();
		}
		
	}catch(Exception e){
		    Log.subaua.info("Response Meta Data::"+"StatusCode:A117::Message:"+e.getMessage());
		    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			JsonObjectBuilder value2 = Json.createObjectBuilder().add("StatusCode", "A117").add("Message", "Something Went Wrong.");
			JsonObject dataJsonObject = value2.build();
			model.addAttribute("model", dataJsonObject);
		
	}
	return "esignapires";	

		

	}
	
}
