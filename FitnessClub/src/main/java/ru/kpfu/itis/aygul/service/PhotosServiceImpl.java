package ru.kpfu.itis.aygul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.aygul.model.Photos;
import ru.kpfu.itis.aygul.repository.PhotosRepository;
import ru.kpfu.itis.aygul.service.interfaces.PhotosService;

import java.util.List;

/**
 * Created by Айгуль on 22.04.2016.
 */

@Service
public class PhotosServiceImpl implements PhotosService {

    @Autowired
    PhotosRepository photosRepository;

    @Override
    public Photos getPhotoById(int id) {
        return photosRepository.findById(id);
    }

    @Override
    public Photos getphotoByName(String name) {
        return null;
    }

    @Override
    public List<Photos> getAll() {
        return photosRepository.findAll();
    }

    @Override
    public void addPhotos(Photos photos) {
        photosRepository.save(photos);
    }

}
