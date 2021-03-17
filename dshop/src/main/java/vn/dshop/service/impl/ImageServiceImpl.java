package vn.dshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.dshop.entity.Image;
import vn.dshop.exception.ImageStorageException;
import vn.dshop.repository.ImageRepository;
import vn.dshop.service.ImageService;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {
    @Value("${file.upload-dir}")
    private String fileDir;

    private ImageRepository imageRepository;
    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    @Transactional
    public void save(Image image, MultipartFile file) {
        storeImage(file);
        this.imageRepository.save(image);
    }

    @Override
    @Transactional
    public void delele(Image image) {
        this.imageRepository.delele(image);
    }

    public String storeImage(MultipartFile image){
        String imageName = StringUtils.cleanPath(image.getOriginalFilename());
        try{
            if(imageName.contains("..")) {
                throw new ImageStorageException("Sorry! Filename contains invalid path sequence " + imageName);
            }
            FileCopyUtils.copy(image.getBytes(), new File(fileDir+imageName));
            return imageName;
        }catch (IOException e){
            throw new ImageStorageException("Could not store image "+imageName);
        }
    }

}
