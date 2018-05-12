package com.auth.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.hibernate.mapping.Map;

import com.auth.model.EsignResp;
import com.auth.util.XMLConverter;
import com.auth.util.signPDF;
import com.nsdl.esign.preverifiedNo.service.EsignService;


public class Main {

	public static void main(String args[]) throws Exception{
		
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\esign.txt"))); 
		String str1;
		 StringBuffer sb=new StringBuffer();
		while((str1 = br1.readLine()) != null){
			sb.append(str1);
			
		}
		byte []pdf = null;
		signPDF spdf = new signPDF(sb.toString());
		XMLConverter XMLconverter = new XMLConverter();
		EsignResp esignresp = new EsignResp();
		
		esignresp = (EsignResp) XMLconverter.convertFromXMLToObject(esignresp, sb.toString());
		for (int i = 0; i < esignresp.getSignatures().getDocSignature().length; i++) {
			
			pdf = esignresp.getSignatures().getDocSignature()[i].getValue();
		}
		
		
		HashMap<Integer,byte[]>mp=new HashMap<Integer,byte[]>();
		String timestapm=new EsignAuthController().generateTimeStamp().toString();
		System.out.println("sanjay"+timestapm);
		mp.put(1, pdf);
		File inFile = new File("D:\\samplpdf\\CSEdd - CSE_VI.pdf");
		
		File outFile = new File("D:\\samplpdf\\CSEdd - CSE_VI_sined.pdf");
		PDDocument doc = PDDocument.load(inFile);

		FileInputStream fis = new FileInputStream(inFile);
		FileOutputStream fos = new FileOutputStream(outFile);
		int c;
		byte[] buffer = new byte[8 * 1024];
		while ((c = fis.read(buffer)) != -1) {
			fos.write(buffer, 0, c);
		}
		fis.close();
		fis = new FileInputStream(outFile);
		List<Long> li=new ArrayList<Long>();
		li.add((long) 1);
		
		SimpleDateFormat l_simpleDateFormater = new SimpleDateFormat("yyyyMMdd_HHmmss");
		
		
		List<String> lo=new ArrayList<String>();
		lo.add(l_simpleDateFormater.format(new Date()));
		
		
		
		EsignService dfd=new EsignService();
		dfd.getSignOnPdf(mp, li,lo, "D:\\samplpdf\\CM7C17.docx.pdf","D:\\workspace\\esignV4\\WebContent\\tickimage\\test4.png",
				10, 1,"sanjay", "", "", 40, 60,160 ,50, "");
		//spdf.signDetached(doc, fos, null, fis, outFile,"sanjay singh negi","abcddd","reasoneany");
		
	}
	
}
