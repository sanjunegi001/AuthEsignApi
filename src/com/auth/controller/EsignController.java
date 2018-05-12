package com.auth.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.KeyStore;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.auth.dao.EsignDao;
import com.auth.dao.UserLoginDAO;
import com.auth.util.Log;
import com.auth.util.XMLConverter;
import com.auth.model.OTP;
import com.auth.model.OTPResp;
import com.auth.util.ESIGNProperties;
import com.auth.bean.Esign;

/**
 * controller for esign download,delete
 * @author sanjay.negi
 *
 */
@Controller
public class EsignController {

	@Autowired
	private EsignDao esignDAO;
	

	/** The user login DAO. */
	@Autowired
	private UserLoginDAO userLogindao;

	private KeyStore.PrivateKeyEntry keyEntry;
	private static final String KEY_STORE_TYPE = "PKCS12";
	private static final String MEC_TYPE = "DOM";
	private static final String WHOLE_DOC_URI = "";

	private static final int BUFFER_SIZE = 4096;

	/**
	 * mathod for delete file
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/esignDelete", method = RequestMethod.POST)
	public @ResponseBody String EsignDelete(HttpServletRequest request, HttpSession session) throws Exception {

		String propFilePath = "";
		ESIGNProperties.load(propFilePath);

		int access = userLogindao.isAcessDetails(session.getAttribute("user_login_name").toString(), session);
		if (session.getAttribute("user_login_name") != null && access == 1) {

			int delete_id = Integer.parseInt(request.getParameter("esignseid"));

			Log.subaua.info(
					"PDF DELETE:" + "User::" + session.getAttribute("user_login_name") + "Delete ID::" + delete_id);

			// int delete_id = 20;

			JSONObject jo = new JSONObject();
			try {
				int dstatuc = esignDAO.delete(delete_id);
				jo.put("status", "1");

			} catch (Exception e) {
				jo.put("status", "0");

			}
			return jo.toString();

		} else {
			return ("redirect:/login");

		}

	}

	
	
	/**
	 * mathod for esign display Form
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/document", method = RequestMethod.GET)
	public ModelAndView Esigndocument(HttpSession session) throws Exception {
		
		try{
			int access = userLogindao.isAcessDetails(session.getAttribute("user_login_name").toString(), session);

			

			if (session.getAttribute("user_login_name") != null && access == 1) {
				

				String propFilePath = "";
				ESIGNProperties.load(propFilePath);
				

				List<Esign> esigndata = esignDAO
						.getAllEsignu(session.getAttribute("user_login_name").toString());
				
				Map<String, List<String>> map = new HashMap<String, List<String>>();
				List<String> docs = new ArrayList<String>();
				List<String> date = new ArrayList<String>();

				ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

				for (Esign authdetails : esigndata) {
					HashMap<String, Object> map1 = new HashMap<String, Object>();
					
					File f = new File(authdetails.getEsign_file_path());
					double bytes = f.length();
					
					Integer kilobytes = (int) (bytes / 1024);
					map1.put("id", authdetails.getId());
					map1.put("name", f.getName().replaceAll("_signedFinal", ""));
					map1.put("docs", authdetails.getEsign_file_path());
					map1.put("fsize", kilobytes);
					map1.put("date", authdetails.getEsign_response_on());
					data.add(map1);
				}

				Map<String, Object> model = new HashMap<String, Object>();
				model.put("employee", data);
				return new ModelAndView("document", "model", model);
			} else {
				return new ModelAndView("redirect:/login");

			}
			}catch(Exception e){
				return new ModelAndView("redirect:/login");
				
			}
	}
	
	/**
	 * mathod for esign display Form
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/esign", method = RequestMethod.GET)
	public ModelAndView Esign(HttpSession session) throws Exception {

		
		try{
		int access = userLogindao.isAcessDetails(session.getAttribute("user_login_name").toString(), session);

		

		if (session.getAttribute("user_login_name") != null && access == 1) {

			String propFilePath = "";
			ESIGNProperties.load(propFilePath);
			return new ModelAndView("esign");
		} else {
			return new ModelAndView("redirect:/login");

		}
		}catch(Exception e){
			return new ModelAndView("redirect:/login");
			
		}
	}

	/**
	 * mathod for download the singed files
	 * @param request
	 * @param session
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = { "/download", "/download" }, method = RequestMethod.GET)
	public void doDownload(HttpServletRequest request, HttpSession session, HttpServletResponse response)
			throws Exception {

		int access = userLogindao.isAcessDetails(session.getAttribute("user_login_name").toString(), session);
		if (session.getAttribute("user_login_name") != null && access == 1) {

			String propFilePath = "";
			ESIGNProperties.load(propFilePath);

			Log.subaua.info("PDF DOWNLOAD:" + "User::" + session.getAttribute("user_login_name"));

			String fpath = request.getParameter("dfpath");
		
			ServletContext context = request.getServletContext();
			File f = new File(fpath);
			File downloadFile = new File(fpath);
			FileInputStream inputStream = new FileInputStream(downloadFile);
			String mimeType = context.getMimeType(fpath);
			if (mimeType == null) { // This File Type used MIME
				// set to binary type if MIME mapping not found
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					f.getName().replaceAll(".pdf", "") + ".pdf");
			response.setHeader(headerKey, headerValue);
			response.setHeader("X-Download-Options", "noopen");
			OutputStream outStream = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			// write bytes read from the input stream into the output stream
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			inputStream.close();
			outStream.close();
		}

	}

	/**
	 * mathod for otp generation
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/otpesignGeneration", method = RequestMethod.POST)
	public @ResponseBody String otpesignGeneration(HttpSession session, HttpServletRequest request) throws Exception {

		JSONObject otp = new JSONObject();
		
		otp.put("status", "1");
		otp.put("message", "Otp generation Successful!");
		return otp.toString();
		
//		String propFilePath = "";
//		ESIGNProperties.load(propFilePath);
//
//		int access = userLogindao.isAcessDetails(session.getAttribute("user_login_name").toString(), session);
//
//		if (session.getAttribute("user_login_name") != null && access == 1) {
//
//			ESIGNProperties.load(propFilePath);
//			 Log.subaua.info("Otp Generation MetaData:" + "User::" +
//			 session.getAttribute("user_login_name") + "ASPID::"
//			 + ESIGNProperties.getAspId() + "TIMESTAMP::" +
//			 generateTimeStamp().toString() + "TRANSACTION ID::"
//			 + generateTXN() + "Aadhaar number" +
//			 request.getParameter("aadharnumber") + "OTP VERSION"
//			 + ESIGNProperties.getVer());
//
//			OTP otpxml = new OTP();
//			otpxml.setAspId(ESIGNProperties.getAspId());
//			otpxml.setTs(generateTimeStamp().toString());
//			otpxml.setTxn(generateTXN());
//			otpxml.setUid(request.getParameter("aadharnumber"));
//			otpxml.setVer(ESIGNProperties.getVer());
//
//			XMLConverter XMLconverter = new XMLConverter();
//
//			String Inputxml = XMLconverter.convertFromObjectToXML(otpxml);
//
//			Inputxml = (new EsignController()).signXML(Inputxml, true, request);
//
//			System.out.println("Inputxml" + Inputxml);
//
//			 Log.subaua.info("Otp Generation XML:" + "User::" +
//			 session.getAttribute("user_login_name") + "REQUEST XML::"
//			 + Inputxml);
//
//			String response = "";
//			OTPResp otpResp = new OTPResp();
//			try {
//				response = com.auth.util.ConnectHttps.getResponseFromHttps(ESIGNProperties.geteSignOTP(),
//						Inputxml);
//
//				 Log.subaua.info("Otp RESPONSE XML:" + "User::" +
//				 session.getAttribute("user_login_name")
//				 + "RESPONSE XML::" + response);
//				String content = response;
//				otpResp = (OTPResp) XMLconverter.convertFromXMLToObject(otpResp, content);
//
//			} catch (Exception e) {
//				System.out.println("Message" + e.getMessage());
//
//			}
//
//			JSONObject otp = new JSONObject();
//			if (otpResp.getStatus() != null) {
//				if (otpResp.getStatus().equals("1")) {
//					otp.put("status", otpResp.getStatus());
//					otp.put("message", "Otp generation Successful!");
//
//				} else {
//
//					otp.put("status", otpResp.getStatus());
//					otp.put("message", "Invalid OTP Generation Request" + "Error Code" + otpResp.getErrCode());
//
//				}
//			} else {
//
//				otp.put("status", "2");
//				otp.put("message", "There are something technical error");
//			}
//
//			return otp.toString();
//
//		} else {
//			return ("redirect:/login.html");
//		}

	}

	public String signXML(String xmlDocument, boolean includeKeyInfo, HttpServletRequest request) throws IOException {

		System.out.println("xmlDocument" + xmlDocument);

		// Log.subaua.info("Signed XML:");

		String propFilePath = "";
		ESIGNProperties.load(propFilePath);

		{
			Security.addProvider(new BouncyCastleProvider());
		}

		String a = "";

		a = ESIGNProperties.getP12Certificate();

		System.out.println("P12 Certificate" + a);

		FileInputStream keyFileStream = null;

		try {

			keyFileStream = new FileInputStream(new File(a));

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		this.keyEntry = getKeyFromKeyStore(keyFileStream, (ESIGNProperties.getPwd()).toCharArray(),
				ESIGNProperties.getAlias());

		// System.out.println("Keyentry" + this.keyEntry);

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			Document inputDocument = dbf.newDocumentBuilder().parse(new InputSource(new StringReader(xmlDocument)));

			Document signedDocument = sign(inputDocument, includeKeyInfo);

			System.out.println("inputDocument" + inputDocument);
			System.out.println("includeKeyInfo" + includeKeyInfo);

			StringWriter stringWriter = new StringWriter();
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer trans = tf.newTransformer();
			trans.transform(new DOMSource(signedDocument), new StreamResult(stringWriter));

			return stringWriter.getBuffer().toString();
		} catch (Exception e) {
			throw new RuntimeException("Error while digitally signing the XML document", e);

		}

	}

	public static String generateTimeStamp() {

		Calendar cal = GregorianCalendar.getInstance();
		int month = (cal.get(Calendar.MONTH) + 1);
		String sa = (month < 10) ? ("0" + month) : ("" + month);

		int DAY_OF_MONTH = (cal.get(Calendar.DAY_OF_MONTH));
		String DAY_OF_MONTH1 = (DAY_OF_MONTH < 10) ? ("0" + DAY_OF_MONTH) : ("" + DAY_OF_MONTH);

		int HOUR_OF_DAY = (cal.get(Calendar.HOUR_OF_DAY));
		String HOUR_OF_DAY1 = (HOUR_OF_DAY < 10) ? ("0" + HOUR_OF_DAY) : ("" + HOUR_OF_DAY);

		int MINUTE = (cal.get(Calendar.MINUTE));
		String MINUTE1 = (MINUTE < 10) ? ("0" + MINUTE) : ("" + MINUTE);

		cal.add(Calendar.SECOND, -55);

		int SECOND = (cal.get(Calendar.SECOND));
		String SECOND1 = (SECOND < 10) ? ("0" + SECOND) : ("" + SECOND);

		String as = cal.get(Calendar.YEAR) + "-" + sa + "-" + DAY_OF_MONTH1 + "T" + HOUR_OF_DAY1 + ":" + MINUTE1 + ":"
				+ SECOND1;
		return as;

	}

	/*
	 * 1.Document Signing using SHA1 2.Sign Document with using X509Certificate
	 * 3.Added Signature method and Signature Context 4.Returning own Documents
	 */
	private Document sign(Document xmlDoc, boolean includeKeyInfo) throws Exception {
		// Log.subaua.info("Signed Document Start");
		String propFilePath = "";
		ESIGNProperties.load(propFilePath);

		if (System.getenv("SKIP_DIGITAL_SIGNATURE") != null) {
			return xmlDoc;
		}

		XMLSignatureFactory fac = XMLSignatureFactory.getInstance(MEC_TYPE);
		Reference ref = fac.newReference(WHOLE_DOC_URI, fac.newDigestMethod(DigestMethod.SHA1, null),
				Collections.singletonList(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)), null,
				null);

		// Create the SignedInfo.
		SignedInfo sInfo = fac.newSignedInfo(
				fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null),
				fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null), Collections.singletonList(ref));

		if (keyEntry == null) {
			throw new RuntimeException(
					"Key could not be read for digital signature. Please check value of signature alias and signature password, and restart the Auth Client");
		}

		X509Certificate x509Cert = (X509Certificate) keyEntry.getCertificate();

		KeyInfo kInfo = getKeyInfo(x509Cert, fac);
		DOMSignContext dsc = new DOMSignContext(this.keyEntry.getPrivateKey(), xmlDoc.getDocumentElement());
		XMLSignature signature = fac.newXMLSignature(sInfo, includeKeyInfo ? kInfo : null);
		signature.sign(dsc);

		Node node = dsc.getParent();
		return node.getOwnerDocument();

	}

	@SuppressWarnings("unchecked")
	private KeyInfo getKeyInfo(X509Certificate cert, XMLSignatureFactory fac) {

		// Create the KeyInfo containing the X509Data.
		KeyInfoFactory kif = fac.getKeyInfoFactory();
		List x509Content = new ArrayList();
		x509Content.add(cert.getSubjectX500Principal().getName());
		x509Content.add(cert);
		X509Data xd = kif.newX509Data(x509Content);
		return kif.newKeyInfo(Collections.singletonList(xd));
	}

	private KeyStore.PrivateKeyEntry getKeyFromKeyStore(FileInputStream keyFileStream, char[] keyStorePassword,
			String alias) {
		// Load the KeyStore and get the signing key and certificate.

		try {

			KeyStore ks = KeyStore.getInstance(KEY_STORE_TYPE);
			ks.load(keyFileStream, keyStorePassword);
			KeyStore.PrivateKeyEntry entry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias,
					new KeyStore.PasswordProtection(keyStorePassword));
			return entry;

		} catch (Exception e) {
			return null;
		} finally {
			if (keyFileStream != null) {
				try {
					keyFileStream.close();
				} catch (IOException e) {
				}
			}
		}

	}

	public static String generateTXN() {

		int randomPIN = (int) (Math.random() * 9000) + 1000;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		String txn = "UKC:eSign:" + randomPIN + ":" + dateFormat.format(new Date());
		return String.valueOf(txn);
	}

}
