/*******************************************************************************
 * DISCLAIMER: The sample code or utility or tool described herein
 *    is provided on an "as is" basis, without warranty of any kind.
 *    UIDAI does not warrant or guarantee the individual success
 *    developers may have in implementing the sample code on their
 *    environment. 
 *    
 *    UIDAI does not warrant, guarantee or make any representations
 *    of any kind with respect to the sample code and does not make
 *    any representations or warranties regarding the use, results
 *    of use, accuracy, timeliness or completeness of any data or
 *    information relating to the sample code. UIDAI disclaims all
 *    warranties, express or implied, and in particular, disclaims
 *    all warranties of merchantability, fitness for a particular
 *    purpose, and warranties related to the code, or any service
 *    or software related thereto. 
 *    
 *    UIDAI is not responsible for and shall not be liable directly
 *    or indirectly for any direct, indirect damages or costs of any
 *    type arising out of use or any action taken by you or others
 *    related to the sample code.
 *    
 *    THIS IS NOT A SUPPORTED SOFTWARE.
 ******************************************************************************/
package com.auth.util;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;

import com.auth.model.Auth;
import com.auth.model.Bios;
import com.auth.model.DataType;
import com.auth.model.LocationType;
import com.auth.model.Meta;
import com.auth.model.Pid;
import com.auth.model.Pv;
import com.auth.model.Skey;
import com.auth.model.Tkn;
import com.auth.model.Uses;
import com.auth.model.UsesFlag;
import com.auth.util.CustomBase64;
import com.auth.util.HashGenerator;
import com.auth.util.XMLConverter;
import com.auth.util.ESIGNProperties;

/**
 * This class provides a method to collate all the data that needs to sent from
 * Auth Client to AUA server.
 * 
 * @author UIDAI
 * 
 */
public class AuthAUADataCreator {

	private static final int AES_256_KEY_SIZE = 32;
	private Encrypter encrypter;
	// private HashGenerator hashGenerator;

	private static final String RANDOM_ALGORITH_NAME = "SHA1PRNG";

	private Map<String, SynchronizedKey> skeyMap = new HashMap<String, SynchronizedKey>();

	private long expiryTime = 10 * 60 * 1000; // 10 minutes for testing purpose.

	private SecureRandom secureSeedGenerator;
	private boolean useSSK = false;

	/**
	 * Constructor
	 * 
	 * @param encrypter
	 *            For encryption of Pid
	 * @param useSynchronizedSesionKey
	 *            Flag indicating whether synchronized sesssion key should be
	 *            used.
	 */
	private static Logger log = Logger.getLogger(AuthAUADataCreator.class);

	public AuthAUADataCreator(Encrypter encrypter, boolean useSynchronizedSesionKey) {
		log.info(" Inside AuthAUADataCreator class   AuthAUADataCreator method begin");
		// this.hashGenerator = new HashGenerator();
		this.encrypter = encrypter;
		this.useSSK = useSynchronizedSesionKey;

		try {
			log.info("inside try block");
			this.secureSeedGenerator = SecureRandom.getInstance(RANDOM_ALGORITH_NAME);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occured for :" + e + "error :" + e.getMessage());

		}
	}

	/**
	 * Creates an instance of {@link AuthDataFromDeviceToAUA} that represents
	 * all the data that an Auth client will send to AUA server.
	 * 
	 * @param uid
	 * @param terminalId
	 * @param meta
	 * @param pid
	 * @param dataType
	 * @return
	 */
	public String prepareAUAData(String otp, Bios BIOS, String uid, String terminalId, String timestamp,
			String FromKYC) {

		String propFilePath = "";
		ESIGNProperties.load(propFilePath);

		System.out.println(" Inside AuthAUADataCreator class prepareAUAData method begin");
		CustomBase64 base64 = new CustomBase64();
		// EsignWebController readproperty = new EsignWebController();
		XMLConverter xmlConverter = null;
		try {
			System.out.println(" Inside AuthAUADataCreator class prepareAUAData inside try block");
			Uses uses = new Uses();
			Skey skey = new Skey();
			Meta meta = new Meta();
			Tkn tkn = new Tkn();
			/*
			 * String pidXml = "<Pid ts=\""+OTPapi.generateTimeStamp()+
			 * "\" ver=\"1.6\"> <Pv otp=\"" + otp + "\" pin=\"y\"/></Pid>";
			 */
			Pid pidxml = new Pid();
			if (null == BIOS) {

				log.info("inside if");
				Pv pv = new Pv();
				pv.setOtp(otp);
				pidxml.setPv(pv);
				pidxml.setTs(timestamp);

				uses.setBio(UsesFlag.N);
				uses.setOtp(UsesFlag.Y);
				uses.setBt("");
				meta.setFdc(ESIGNProperties.getFDC());
				meta.setIdc(ESIGNProperties.getIDC());

			} else {
				System.out.println("HelloBIOS" + BIOS);
				log.info("inside else");
				pidxml.setBios(BIOS);
				pidxml.setTs(timestamp);
				uses.setBio(UsesFlag.Y);
				uses.setOtp(UsesFlag.N);
				uses.setBt("FMR");
				meta.setFdc(ESIGNProperties.getFDC());
				meta.setIdc(ESIGNProperties.getIDC());

			}

			xmlConverter = new XMLConverter();
			String pidXml = xmlConverter.convertFromObjectToXML(pidxml);
			// String pidXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"
			// standalone=\"yes\"?><ns2:Pid ts=\""+timestamp+"\"
			// xmlns:ns2=\"http://www.uidai.gov.in/authentication/uid-auth-request-data/1.0\"><Pv
			// otp=\""+ otp +"\"/></ns2:Pid>";
			// <?xml version="1.0" encoding="UTF-8" standalone="yes"?><ns2:Pid
			// ts="2016-09-08T16:09:14"
			// xmlns:ns2="http://www.uidai.gov.in/authentication/uid-auth-request-data/1.0"><pv
			// otp="385565"/></ns2:Pid>
			byte[] pidXmlBytes = pidXml.getBytes();
			byte[] sessionKey = null;
			byte[] newRandom = new byte[20];
			SynchronizedKey synchronizedKey = null;
			byte[] encryptedSessionKey = null;

			if (this.useSSK) {

				log.info("inside useSSk if block");
				synchronizedKey = this.skeyMap.get("X");

				if (synchronizedKey == null
						|| synchronizedKey.getSeedCreationDate().getTime() - System.currentTimeMillis() > expiryTime) {
					synchronizedKey = new SynchronizedKey(this.encrypter.generateSessionKey(),
							UUID.randomUUID().toString(), new Date());
					this.skeyMap.put("X", synchronizedKey);
					sessionKey = synchronizedKey.getSeedSkey();
					encryptedSessionKey = this.encrypter.encryptUsingPublicKey(sessionKey);

				} else {
					log.info("inside synchronizedKey else block");
					byte[] seed = secureSeedGenerator.generateSeed(20);

					SecureRandom random = SecureRandom.getInstance(RANDOM_ALGORITH_NAME);
					random.setSeed(seed);
					random.nextBytes(newRandom);

					sessionKey = Arrays.copyOf(
							this.encrypter.encryptUsingSessionKey(synchronizedKey.getSeedSkey(), newRandom),
							AES_256_KEY_SIZE);
					encryptedSessionKey = newRandom;
				}
			} else {
				log.info("inside useSSk else block");
				this.skeyMap.clear();
				sessionKey = this.encrypter.generateSessionKey();
				encryptedSessionKey = this.encrypter.encryptUsingPublicKey(sessionKey);
			}
			byte[] encXMLPIDData = this.encrypter.encryptUsingSessionKey(sessionKey, pidXmlBytes);
			byte[] hmac = HashGenerator.generateSha256Hash(pidXmlBytes);
			byte[] encryptedHmacBytes = this.encrypter.encryptUsingSessionKey(sessionKey, hmac);

			@SuppressWarnings("unused")
			String certificateIdentifier = this.encrypter.getCertificateIdentifier();

			Auth authXml = new Auth();

			Auth.Data data = new Auth.Data();
			authXml.setXmlns(ESIGNProperties.getAuthXmlns());
			authXml.setUid(uid);
			authXml.setTid(terminalId);
			authXml.setVer(ESIGNProperties.getVer());
			authXml.setTxn(generateTXN());

			uses.setPi(UsesFlag.N);
			uses.setPa(UsesFlag.N);
			uses.setPfa(UsesFlag.N);
			uses.setPi(UsesFlag.N);
			uses.setPin(UsesFlag.N);

			// <Meta udc=\"UKC:SampleClient\" pip=\"127.0.0.1\" fdc=\"NC\"
			// idc=\"NA\" lot=\"P\" lov=\"560103\"/>
			meta.setUdc(ESIGNProperties.getUDC());
			meta.setPip(ESIGNProperties.getPIP());

			meta.setLot(LocationType.P);
			meta.setLov(ESIGNProperties.getLOV());

			// <Skey
			// ci=\"20171105\">"+DatatypeConverter.printBase64Binary(encryptedSessionKey)+"</Skey>
			skey.setCi(ESIGNProperties.getCI());
			skey.setValue(DatatypeConverter.printBase64Binary(encryptedSessionKey));
			// <Data
			// type=\"X\">"+DatatypeConverter.printBase64Binary(encXMLPIDData)+"</Data>
			data.setValue(DatatypeConverter.printBase64Binary(encXMLPIDData));
			data.setType(DataType.X);
			// <Hmac>"+DatatypeConverter.printBase64Binary(encryptedHmacBytes)+"</Hmac>

			if (FromKYC.equals("Y")) // Request comming from kyc
			{
				log.info("inside FromKYC if  block");
				authXml.setAc(ESIGNProperties.getACforKYC());
				authXml.setSa(ESIGNProperties.getSAforKYC());
				authXml.setLk(ESIGNProperties.getK());
				authXml.setUses(uses);
			} else {
				log.info("inside FromKYC Else  block");
				authXml.setAc(ESIGNProperties.getAC()); // No need
														// to
														// send,
														// It
														// whould
														// be
														// blank
				authXml.setSa(ESIGNProperties.getSA()); // No need
														// to
														// send,
														// It
														// whould
														// be
														// blank
			}

			authXml.setData(data);
			// authXml.setTkn(tkn);
			authXml.setMeta(meta);
			authXml.setSkey(skey);
			authXml.setHmac(DatatypeConverter.printBase64Binary(encryptedHmacBytes));

			XMLConverter XMLconverter = new XMLConverter();
			
			
			String auaData = XMLconverter.convertFromObjectToXML(authXml);
			// String auaData1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"
			// standalone=\"yes\"?><Auth
			// xmlns=\"http://www.uidai.gov.in/authentication/uid-auth-request/1.0\"
			// uid=\""+uid+"\" tid=\""+terminalId+"\" ver=\"1.6\"
			// txn=\""+OTPapi.generateTXN()+"\" ac=\"AUANSDL001\"
			// sa=\"NSDLSK0004\"
			// lk=\"MPsXCYn_eB4AQ6G_kZe6ZZONdWgcTtNsDAuEmy2p8sthSvFpRwJzUmo\"
			// ><Uses pi=\"n\" pa=\"n\" pfa=\"n\" bio=\"n\" bt=\"\" pin=\"n\"
			// otp=\"y\"/><Meta udc=\"UKC:SampleClient\" pip=\"127.0.0.1\"
			// fdc=\"NC\" idc=\"NA\" lot=\"P\" lov=\"560103\"/><Skey
			// ci=\"20171105\">"+DatatypeConverter.printBase64Binary(encryptedSessionKey)+"</Skey><Data
			// type=\"X\">"+DatatypeConverter.printBase64Binary(encXMLPIDData)+"</Data><Hmac>"+DatatypeConverter.printBase64Binary(encryptedHmacBytes)+"</Hmac></Auth>";
			// System.out.println("auaData1 base64.encode String "+auaData1);
			// auaData =
			// DatatypeConverter.printBase64Binary(auaData.getBytes());
			// System.out.println("auaData base64.encode String" + auaData);
			// System.out.println("auaData base64.encode String" +
			// DatatypeConverter.printBase64Binary(auaData1.getBytes()));
			return auaData;
			// ac="AUANSDL001" , txn="UKC:" sa="NSDLSK0004" NK=""
		} catch (Exception e) {

			System.out.println("Exception in creating Auth xml" + e);
			e.printStackTrace();
			log.error("Exception in creating Auth xml:" + e + "error :" + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public static String generateTXN()

	{
		log.info(" Inside OTPapi class   generateTXN() method  ");
		int randomPIN = (int) (Math.random() * 9000) + 1000; // Its create
		// randomPIN
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS"); // Date&TimeFormat
		// yyyyMMddhhmmssSSS
		String txn = "UKC:eSign:" + randomPIN + ":" + dateFormat.format(new Date());
		return String.valueOf(txn);
	}

	public void restSkeyMap() {
		log.info("restSkeyMap method");
		this.skeyMap.clear();
	}

	public static class SynchronizedKey {
		byte[] seedSkey;
		String keyIdentifier;
		Date seedCreationDate;

		public SynchronizedKey(byte[] seedSkey, String keyIdentifier, Date seedCreationDate) {
			super();
			this.seedSkey = seedSkey;
			this.keyIdentifier = keyIdentifier;
			this.seedCreationDate = seedCreationDate;
		}

		public String getKeyIdentifier() {
			return keyIdentifier;
		}

		public Date getSeedCreationDate() {
			return seedCreationDate;
		}

		public byte[] getSeedSkey() {
			return seedSkey;
		}
	}

}
