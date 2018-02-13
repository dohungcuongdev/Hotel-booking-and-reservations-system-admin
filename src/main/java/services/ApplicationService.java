/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import model.api.user.tracking.GeoLocation;

/**
 *
 * @author HUNGCUONG
 */
public interface ApplicationService {

	public String getEncryptPassword(String password);
	public String getDecryptPassword(String pwEncryted);
	public String getPasswordGenerated();
	public GeoLocation getLocationByIP(String externalIP);
	public String uploadImage(CommonsMultipartFile commonsMultipartFiles, HttpServletRequest request, ModelMap model, String itemType);
	public void uploadPDF(CommonsMultipartFile commonsMultipartFiles, HttpServletRequest request, ModelMap model);
	public String sendEmail(String message, String sendto, String subject);
	public void sendHTMLEmail(String message, String sendto, String subject);
	public String removeAccent(String originalString);
}
