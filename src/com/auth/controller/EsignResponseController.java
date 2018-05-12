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
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.auth.bean.Esign;
import com.auth.bean.EsignSession;
import com.auth.dao.EsignDao;
import com.auth.dao.EsignSessionDAO;
import com.auth.dao.UserLoginDAO;
import com.auth.model.EsignResp;
import com.auth.model.GenericResponce;
import com.auth.service.EsignSessionService;
import com.auth.util.AUAUtilities;
import com.auth.util.ESIGNProperties;
import com.auth.util.Log;
import com.auth.util.XMLConverter;
import com.auth.util.signPDF;
import com.nsdl.esign.preverifiedNo.service.EsignService;

/**
 * 
 * @author sanjay.negi
 * @controller for handling the response from NSDL
 */

@Controller
public class EsignResponseController {

	public String UPLOADED_FOLDER="";
	public String tickimagepath="";
	public String signedpdfpath="";
	public String authmode="";
	
	

	@Autowired
	private EsignDao esignDAO;
	
	@Autowired
	private EsignSessionService esignsessionService;
	
	
	
	/** The user login DAO. */
	@Autowired
	private UserLoginDAO userLogindao;

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
	@RequestMapping(value = { "/response" }, method = RequestMethod.POST)
	public String resps(@RequestParam("msg") String eSignResp, @RequestParam("obj") String aadhaar,
			HttpServletRequest request, HttpSession session, ModelMap model) throws IOException, SignatureException {
		
		String propFilePath = "";
		ESIGNProperties.load(propFilePath);
		
		/** Set Response Time **/
		String response_time = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date reqdate = new Date();
		response_time = dateFormat.format(reqdate);
		
		/**Initialize variables**/
		EsignService esingservice=new EsignService();
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
		
		
		try {

			esignresp = (EsignResp) XMLconverter.convertFromXMLToObject(esignresp, eSignResp);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			request.getSession().setAttribute("esigntransactionnm","");
			request.getSession().setAttribute("error", "A2011");
			request.getSession().setAttribute("message", "Something Went Wrong");
			return "esignError";
			

		}

		
		Esign esign = null;
		esign = new Esign();
		
	try{	
		
		EsignSession esession=esignsessionService.getByTxn(esignresp.getTxn());
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
			authmode=(esession.getAuth_mode()==1?"OTP":"BIO");
			
			esingservice.getSignOnPdf(mapsign, listdocid, listdoctimestamp, signedpdfpath, 
					tickimagepath, 10, 1, esession.getEsign_name(), esession.getEsign_location(), esession.getEsign_reason(),
					40, 60, 160, 50, "");
			

				Log.subaua.info("Response Meta Data::AuthMode:"+authmode+"::TranscationID:"+esignresp.getTxn()+
						"::ResponseTime:"+response_time+"::Message:Esign Process Successfully Done");
				esign.setEsign_status(1);
				esign.setEsign_aadhaar(aadhaar);
				esign.setEsign_auth_type(authmode);
				esign.setEsign_file_path(outFile.getAbsolutePath().replaceAll(".pdf", "_signedFinal.pdf"));
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
				request.getSession().setAttribute("esigntransactionnm", esignresp.getTxn());
				request.getSession().setAttribute("message", "Esign Process Successfully Done");
				return "esignSuccess";
			

		}else {
			
			Log.subaua.info("Response Meta Data::AuthMode:"+session.getAttribute("authmode")+"::TranscationID:"+esignresp.getTxn()+
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
			request.getSession().setAttribute("esigntransactionnm", esignresp.getTxn());
			request.getSession().setAttribute("error", esignresp.getErrCode());
			request.getSession().setAttribute("message", esignresp.getErrMsg());
			return "esignError";
		}
		
	}catch(Exception e){
		Log.subaua.info("Response Meta Data::AuthMode:"+session.getAttribute("authmode")+"::TranscationID:"+""+
				"::ResponseTime:"+response_time+"::Message:"+e.getMessage());
		request.getSession().setAttribute("esigntransactionnm","");
		request.getSession().setAttribute("error", "A201");
		request.getSession().setAttribute("message", "Something Went Wrong");
		return "esignError";
		
	}	

		

	}
	
	
	

}
