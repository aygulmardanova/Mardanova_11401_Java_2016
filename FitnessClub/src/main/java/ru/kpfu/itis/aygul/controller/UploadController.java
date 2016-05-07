package ru.kpfu.itis.aygul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.aygul.model.Photos;
import ru.kpfu.itis.aygul.service.PhotosServiceImpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by Айгуль on 22.04.2016.
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    /*@Autowired
    PhotosServiceImpl photosServiceImpl;

    @RequestMapping(value = "/photo", method = RequestMethod.GET)
    public String uploadFile(ModelMap model) {
        List<Photos> photosList = photosServiceImpl.getAll();
        model.addAttribute("photosList", photosList);
        return "upload";
    }

    @RequestMapping(value = "/photo", method = RequestMethod.POST)
//    @ResponseBody
    public String uploadFilePost(@RequestParam("file") MultipartFile file, ModelMap model) {

        String name = null;

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                name = file.getOriginalFilename();

                String rootPath = "C:\\folders\\FitnessClub\\target\\fitnessclub-1.0-SNAPSHOT\\images";
                File dir = new File(rootPath + File.separator + "upload");

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();

            } catch (Exception e) {
            }
            model.addAttribute("photo", name);

            Photos photos = new Photos();
            photos.setUpload(name);
            photosServiceImpl.addPhotos(photos);
            List<Photos> photosList = photosServiceImpl.getAll();
            model.addAttribute("photosList", photosList);
        }
        return "upload";
    }*/


}
