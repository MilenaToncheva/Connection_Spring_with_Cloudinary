package bg.softuni.cloudinary.web.models;

import org.springframework.web.multipart.MultipartFile;

public class PictureBindingModel {
    private String title;
    private String description;
    private String author;
    private MultipartFile picture;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }
}
