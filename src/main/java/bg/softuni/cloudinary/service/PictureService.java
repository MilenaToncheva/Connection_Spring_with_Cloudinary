package bg.softuni.cloudinary.service;

import bg.softuni.cloudinary.service.models.PictureServiceModel;

import java.util.List;

public interface PictureService {
    void add(PictureServiceModel pictureServiceModel);

    List<PictureServiceModel> findAll();
}
