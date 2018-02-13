/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import daos.impl.EncryptDecryptAES;
import model.api.user.tracking.GeoLocation;
import services.ApplicationService;
import statics.provider.FileUploader;
import statics.provider.GeoLookup;
import statics.provider.EmailSender;
import statics.provider.StringUtils;

/**
 *
 * @author HUNGCUONG
 */

@Service
public class ApplicationServiceImpl implements ApplicationService {
	
	@Autowired
	EncryptDecryptAES edAES;

	@Override
	public String uploadImage(CommonsMultipartFile commonsMultipartFiles, HttpServletRequest request, ModelMap model, String itemType) {
		return FileUploader.uploadImage(commonsMultipartFiles, request, model, itemType);
	}

	@Override
	public String sendEmail(String message, String sendto, String subject) {
		return EmailSender.sendEmail(message, sendto, subject);
	}

	@Override
	public String removeAccent(String originalString) {
		return StringUtils.removeAccent(originalString);
	}

	@Override
	public void uploadPDF(CommonsMultipartFile commonsMultipartFiles, HttpServletRequest request, ModelMap model) {
		FileUploader.uploadPDF(commonsMultipartFiles, request, model);
	}

	@Override
	public String getPasswordGenerated() {
		return StringUtils.getRandomStringLen16();
	}

	@Override
	public void sendHTMLEmail(String message, String sendto, String subject) {
		EmailSender.sendHTMLEmail(message, sendto, subject);
	}

	@Override
	public String getEncryptPassword(String password) {
		try {
			return edAES.encrypt(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}

	@Override
	public String getDecryptPassword(String pwEncryted) {
		try {
			return edAES.decrypt(pwEncryted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pwEncryted;
	}

	@Override
	public GeoLocation getLocationByIP(String externalIP) {
		return GeoLookup.getLocation(externalIP);
	}
}
