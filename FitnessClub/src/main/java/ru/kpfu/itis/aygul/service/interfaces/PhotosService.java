package ru.kpfu.itis.aygul.service.interfaces;

import ru.kpfu.itis.aygul.model.Photos;

import java.util.List;

/**
 * Created by Айгуль on 22.04.2016.
 */
public interface PhotosService {

    Photos getPhotoById(int id);

    Photos getPhotoByName(String name);

    List<Photos> getAll();

    void addPhotos(Photos photos);

}
