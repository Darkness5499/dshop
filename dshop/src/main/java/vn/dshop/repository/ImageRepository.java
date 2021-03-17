package vn.dshop.repository;

import org.springframework.web.multipart.MultipartFile;
import vn.dshop.entity.Image;

public interface ImageRepository {
    void save(Image image);
    void delele(Image image);

}
