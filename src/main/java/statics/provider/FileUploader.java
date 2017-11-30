/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statics.provider;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import statics.AppData;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author Do Hung Cuong
 */
public class FileUploader {

    public static String uploadfile(CommonsMultipartFile commonsMultipartFiles, HttpServletRequest request, ModelMap model, String itemType) {
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
                System.out.println(e.getMessage());
                model.put("editResult", AppData.ERROR);
            }
        }
        return "";
    }
}
