/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statics.helper;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import statics.constant.AppData;

/**
 *
 * @author Do Hung Cuong
 */
public class FileUploader {

    public static String uploadImage(CommonsMultipartFile commonsMultipartFiles, HttpServletRequest request, ModelMap model, String itemType) {
        String nameFile = commonsMultipartFiles.getOriginalFilename();
        if (!"".equals(nameFile)) {
            String dirFile = request.getServletContext().getRealPath(AppData.IMAGE_RESOURCES + itemType);
            File fileDir = new File(dirFile);
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }
            try {
                commonsMultipartFiles.transferTo(new File(fileDir + File.separator + nameFile));
                model.put("editResult", AppData.EDITSUCCESS);
                return nameFile;
            } catch (IOException | IllegalStateException e) {
            	nameFile = "";
                model.put("editResult", AppData.ERROR);
            }
        }
        return nameFile;
    }
    
    public static void uploadPDF(CommonsMultipartFile commonsMultipartFiles, HttpServletRequest request, ModelMap model) {
        String nameFile = commonsMultipartFiles.getOriginalFilename();
        if (nameFile.equals("")) {
        	model.put("uploadResult", AppData.NO_FILE_CHOSEN);
        } else if (!nameFile.contains("pdf")) {
        	model.put("uploadResult", AppData.NOT_PDF);
        } else {
            String dirFile = request.getServletContext().getRealPath(AppData.PDF_RESOURCES);
            File fileDir = new File(dirFile);
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }
            try {
                commonsMultipartFiles.transferTo(new File(fileDir + File.separator + "fqa.pdf"));
                model.put("uploadResult", AppData.UPLOAD_SUCCESS);
            } catch (IOException | IllegalStateException e) {
                model.put("uploadResult", AppData.ERROR);
            }
        }
    }
}
