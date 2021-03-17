package vn.dshop.service;

import org.springframework.web.multipart.MultipartFile;
import vn.dshop.entity.Image;

public interface ImageService {
    void save(Image image, MultipartFile file);
    void delele(Image image);
}
