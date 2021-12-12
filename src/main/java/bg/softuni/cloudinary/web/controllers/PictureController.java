package bg.softuni.cloudinary.web.controllers;

import bg.softuni.cloudinary.service.CloudinaryService;
import bg.softuni.cloudinary.service.PictureService;
import bg.softuni.cloudinary.service.models.CloudinaryImage;
import bg.softuni.cloudinary.service.models.PictureServiceModel;
import bg.softuni.cloudinary.web.models.PictureBindingModel;
import bg.softuni.cloudinary.web.models.PictureViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/pictures")
public class PictureController {
    private final CloudinaryService cloudinaryService;
    private final PictureService pictureService;
    private final ModelMapper modelMapper;

    public PictureController(CloudinaryService cloudinaryService, PictureService pictureService, ModelMapper modelMapper) {
        this.cloudinaryService = cloudinaryService;
        this.pictureService = pictureService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute(name = "pictureModel")
    public PictureBindingModel pictureBindingModel() {
        return new PictureBindingModel();
    }

    @GetMapping("/add")
    public String addPicture() {

        return "add-picture";
    }

    @PostMapping("/add")
    public String addPictureConfirm(@ModelAttribute("pictureModel") PictureBindingModel pictureModel) throws IOException {
        PictureServiceModel pictureServiceModel = this.createPictureServiceModel(
                pictureModel.getPicture(), pictureModel.getTitle(), pictureModel.getAuthor(), pictureModel.getDescription());
        this.pictureService.add(pictureServiceModel);
        return "redirect:/pictures/all";
    }

    private PictureServiceModel createPictureServiceModel(MultipartFile file, String title, String author, String description) throws IOException {
        final CloudinaryImage upload = this.cloudinaryService.upload(file);
        PictureServiceModel pictureServiceModel = new PictureServiceModel();
        pictureServiceModel.setAuthor(author);
        pictureServiceModel.setTitle(title);
        pictureServiceModel.setDescription(description);
        pictureServiceModel.setUrl(upload.getUrl());
        pictureServiceModel.setPublicId(upload.getPublicId());
        return pictureServiceModel;
    }
@Transactional
    @DeleteMapping("/delete")
    public String delete(@RequestParam("public_id") String publicId) {
        if (cloudinaryService.delete(publicId)) {
            this.pictureService.delete(publicId);
        }
        return "redirect:/pictures/all";
    }

    @GetMapping("/all")
    public String getAllPictures(Model model) {
        List<PictureViewModel> pictures = Arrays.stream(this.modelMapper.map(this.pictureService.findAll(), PictureViewModel[].class))
                .collect(Collectors.toList());
        model.addAttribute("pictures", pictures);
        return "all-pictures";
    }
}
