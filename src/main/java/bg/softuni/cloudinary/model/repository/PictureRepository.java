package bg.softuni.cloudinary.model.repository;

import bg.softuni.cloudinary.model.entity.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<PictureEntity,String> {
void deleteByPublicId(String publicId);
}
