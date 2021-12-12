package bg.softuni.cloudinary.service.impl;

import bg.softuni.cloudinary.model.entity.PictureEntity;
import bg.softuni.cloudinary.model.repository.PictureRepository;
import bg.softuni.cloudinary.service.PictureService;
import bg.softuni.cloudinary.service.models.PictureServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {

    private final ModelMapper modelMapper;
    private final PictureRepository pictureRepository;

    public PictureServiceImpl(ModelMapper modelMapper, PictureRepository pictureRepository) {
        this.modelMapper = modelMapper;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void add(PictureServiceModel pictureServiceModel) {
        this.pictureRepository.save(this.modelMapper.map(pictureServiceModel, PictureEntity.class));
    }

    @Override
    public List<PictureServiceModel> findAll() {
       List<PictureServiceModel>pictures=Arrays.stream(this.modelMapper.map(this.pictureRepository.findAll(), PictureServiceModel[].class))
                .collect(Collectors.toList());
       return pictures;
    }
}
