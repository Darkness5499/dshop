package vn.dshop.repository;

import vn.dshop.entity.Image;

import java.util.List;

public interface ImageRepository {
    void save(Image image);
    void delele(Image image);
    List<Image> getAllImageByProduct(int productid);

}
