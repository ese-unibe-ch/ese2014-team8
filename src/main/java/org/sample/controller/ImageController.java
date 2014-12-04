package org.sample.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public abstract class ImageController {
	
	
	protected String saveImage(MultipartFile file, String name, String directory) {
		if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
 
                // Creating the directory to store file
                
                File dir = new File("src" + File.separator +"main" + File.separator + "webapp" + File.separator + directory);
                if (!dir.exists())
                    dir.mkdirs();
 
                // Create the file on server
                String extension = FilenameUtils.getExtension(file.getOriginalFilename());
                if(extension.equals("jpg") || extension.equals("jpeg") ){
                	File serverFile = new File(dir.getAbsolutePath() + File.separator + name + ".jpg" );
                	BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                	stream.write(bytes);
                	stream.close();
                	return "You successfully uploaded the image" ;
                }
                else{
                	return "Image must be jpg format";
                }
                
            } catch (Exception e) {
                return "You failed to upload the image => " + e.getMessage();
            }
        } else {
            return "You failed to upload the image because the file was empty.";
        }
	}

}
