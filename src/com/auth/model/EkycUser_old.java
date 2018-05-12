package com.auth.model;

import java.io.Serializable;

import javax.persistence.Entity;

import org.springframework.web.multipart.MultipartFile;
@Entity
public class EkycUser_old  implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	//Remaining only Image
    //private File upload;	
	
	private String Title;
	private String lastname;//for lastname
	private String middlename;//for lastname
	private String firstname;//for lastname
	private String dateOfBirth;//Dateof Birth
	private String addressline1;
	private String addressline2;
	private String city;
	private String state;
	private String country;
	private String pincode;
	private MultipartFile file;
	
	private String fileName;
    
    public MultipartFile getFile() {
        return file;
    }
 
    public void setFile(MultipartFile file) {
        this.file = file;
    }

	 
	
	
	
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode.trim();
	}
	/*public Integer getPincode() {
		return pincode;
	}
	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}*/
	private String telephone;
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone.trim();
		
		
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno.trim();
	}
	public String getAadharno() {
		return aadharno;
	}
	public void setAadharno(String aadharno) {
		this.aadharno = aadharno.trim();
	}
	private String mobileno;
	private String email;
	private String aadharno;
	
	 
	
	 

	
	/*public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}*/
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname.trim();
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename.trim();
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname.trim();
	}
	
	public String getAddressline1() {
		return addressline1;
	}
	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1.trim();
	}
	public String getAddressline2() {
		return addressline2;
	}
	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2.trim();
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city.trim();
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state.trim();
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	/*public Integer getTelephone() {
		return telephone;
	}
	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}*/
	/*public Integer getMobileno() {
		return mobileno;
	}
	public void setMobileno(Integer mobileno) {
		this.mobileno = mobileno;
	}*/
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/*public Integer getAadharno() {
		return aadharno;
	}
	public void setAadharno(Integer aadharno) {
		this.aadharno = aadharno;
	}*/
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName.trim();
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth.trim();
	}
	
	
	
	
	
	
	
	
	
	
}
