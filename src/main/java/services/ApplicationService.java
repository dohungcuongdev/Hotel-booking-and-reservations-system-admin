/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author HUNGCUONG
 */
public interface ApplicationService {

	public String uploadfile(CommonsMultipartFile commonsMultipartFiles, HttpServletRequest request, ModelMap model, String itemType);
	public String sendEmail(String message, String sendto, String subject);
	public String removeAccent(String originalString);
}
