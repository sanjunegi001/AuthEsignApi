package com.auth.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
//import org.apache.pdfbox.exceptions.COSVisitorException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
//import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
//import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureInterface;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureOptions;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDVisibleSigProperties;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDVisibleSignDesigner;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDSignatureField;
import org.apache.pdfbox.util.Matrix;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.auth.model.Bios;
import com.auth.model.EsignResp;
import com.auth.model.GenericResponce;
import com.auth.util.Log;
import com.itextpdf.text.pdf.TSAClient;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.auth.controller.EsignAuthController;
import com.auth.controller.SaveIncrementalSignObject;

public class signPDF implements SignatureInterface {

	public static String OTP = null;
	public static String Aadhaar = "";
	public static String eKYCDATA = null;
	public static String TimeStamp = null;
	public static Bios BIOS = null;
	public static String AUTHMODE = "";
	public static int Responce = 1;
	public static GenericResponce gr = null;
	public SignatureOptions options = null;
	private static Logger log = Logger.getLogger(signPDF.class);
	public static Integer mapId = 0;
	public static Integer SignetureId = 0;
	public static String msgbody=null;
	public static String imagepath="";
	
	
	public signPDF(String msg){
		msgbody=msg;
		
	}

	public signPDF(String otp, String aadhaar, Bios bios, String eKycData) throws KeyStoreException,
			UnrecoverableKeyException, NoSuchAlgorithmException, CertificateException, IOException {
		Aadhaar = aadhaar; // Value is setting from the constructur
		OTP = otp;
		BIOS = bios;
		eKYCDATA = eKycData;

	}

	public signPDF(String otp, String aadhaar, Bios bios, String eKycData, String AuthMode) throws KeyStoreException,
			UnrecoverableKeyException, NoSuchAlgorithmException, CertificateException, IOException {
		Aadhaar = aadhaar; // Value is setting from the constructur
		OTP = otp;
		BIOS = bios;
		eKYCDATA = eKycData;
		AUTHMODE = AuthMode;
	}

	public signPDF(String authmode, String aadhaar) throws KeyStoreException,
			UnrecoverableKeyException, NoSuchAlgorithmException, CertificateException, IOException {
		Aadhaar = aadhaar; // Value is setting from the constructur
		AUTHMODE=authmode;

	}

	

	
	/**
	 * method to get input xml for redirect URL
	 * @param content
	 * @param txn
	 * @return
	 * @throws IOException
	 */
	public String getResponseNSDL(InputStream content,String txn,String responseURL) throws IOException {
		
		

		EsignAuthController Esignauth = new EsignAuthController();
		String inputxml = null;
		if(AUTHMODE.equals("1")){
			
			inputxml=Esignauth.Esigndoc("1",Aadhaar, content,txn,responseURL);
			
		}else if(AUTHMODE.equals("2")){
			inputxml=Esignauth.Esigndoc("2",Aadhaar, content, txn,responseURL);
			
		}
		
		
		return inputxml;

	}

	@Override
	public byte[] sign(InputStream content) throws IOException {

		// TODO Auto-generated method stub
		byte[] pdf = null;
		String body=msgbody;
		System.out.println("samkau::"+body);
		try {
		    
				XMLConverter XMLconverter = new XMLConverter();
				EsignResp responce = new EsignResp();
				try {

					responce = (EsignResp) XMLconverter.convertFromXMLToObject(responce, body);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}	

			gr = new GenericResponce();

		

			if (responce.getStatus().equalsIgnoreCase("1")) {

				Responce = 1;

				for (int i = 0; i < responce.getSignatures().getDocSignature().length; i++) {
					
					pdf = responce.getSignatures().getDocSignature()[i].getValue();
				}
				
				gr.setStatus(responce.getErrMsg());
				gr.setStatuscode(responce.getStatus());
				gr.setReasoncode(responce.getErrCode());
				gr.setReason(responce.getResCode());
				gr.setTransactionid(responce.getTxn());
				gr.setTimestamp(responce.getTs());

			} else {

				Responce = 0;
				pdf = "".getBytes();
				gr.setStatus(responce.getErrMsg());
				gr.setStatuscode(responce.getStatus());
				gr.setReasoncode(responce.getErrCode());
				gr.setReason(responce.getResCode());
				gr.setTransactionid(responce.getTxn());
				gr.setTimestamp(responce.getTs());

				return pdf;

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return pdf;
	}

	public static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}
	
    public static void saveImage(String name,String location,String reason) throws IOException{
    	
    	File input = new File(imagepath+"test.png");
    	File output = new File(imagepath+"tick.jpeg");
    	String Line="Digitally Signed by:| | |";
    	String Line1="Name:"+name+"| | |";
    	String Line2="Location:"+location+"| | |";
    	String Line3="Reason:"+reason;
  
   	// overlay settings
	        String text = Line+Line1+Line2+Line3+"| | |On Date:"+new Date();
	     
	        

	        // adding text as overlay to an image
	        addTextWatermark(text, "jpg", imagepath, output);
    	
    }	
    
    private static void addTextWatermark(String text, String type, String source, File destination) throws IOException {
        BufferedImage image = ImageIO.read(new File(source));

        // determine image type and handle correct transparency
        int imageType = "png".equalsIgnoreCase(type) ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;
        BufferedImage watermarked = new BufferedImage(image.getWidth(), image.getHeight(), imageType);

        // initializes necessary graphic properties
        Graphics2D w = (Graphics2D) watermarked.getGraphics();
        w.drawImage(image, 0, 0, null);
        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);
        w.setComposite(alphaChannel);
        w.setColor(Color.BLACK);
        w.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
        FontMetrics fontMetrics = w.getFontMetrics();
        Rectangle2D rect = fontMetrics.getStringBounds(text, w);

        // calculate center of the image
        int centerX = (image.getWidth() - (int) rect.getWidth()) / 2;
        int centerY = image.getHeight() / 2;

        Graphics g = image.getGraphics();
        g.setFont(g.getFont().deriveFont(30f));
        int lineHeight = g.getFontMetrics().getHeight();
        
        // add text overlay to the image
        String [] lines = text.split("\\|");
        
        for(int lineCount = 0; lineCount < lines.length; lineCount ++){ //lines from above
            int xPos = 100;
            int yPos = 100 + lineCount * lineHeight;
            String line = lines[lineCount];
            w.drawString(line, xPos, yPos);
            ImageIO.write(watermarked, type, destination);
        }
        //w.drawString(text, centerX, centerY);
        
        w.dispose();
    }
}
