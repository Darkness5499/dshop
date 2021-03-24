package vn.dshop.service.impl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.dshop.entity.Image;
import vn.dshop.entity.Product;
import vn.dshop.exception.ImageStorageException;
import vn.dshop.repository.ImageRepository;
import vn.dshop.repository.ProductRepository;
import vn.dshop.service.ProductService;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProductServiceImpl implements ProductService {
    private Pattern pattern;
    private Matcher matcher;
    private static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";
    @Value("${file.upload-dir}")
    private String fileDir;

    private ProductRepository productRepository;
    private ImageRepository imageRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.getAllProducts();
    }

    @Override
    @Transactional
    public void save(Product p, List<MultipartFile> files) {
        this.productRepository.save(p);
        for(MultipartFile file: files){
            storeImage(file);
            Image image = new Image();
            String imageName = StringUtils.cleanPath(file.getOriginalFilename());
            image.setImageUrl(imageName);
            image.setContentType(file.getContentType());
            image.setProduct(p);
        }
    }
    @Override
    @Transactional
    public void delete(int id) {
        this.productRepository.delete(id);
    }

    @Override
    public Product getProductById(int id) {
        return this.productRepository.getProductById(id);
    }

    @Override
    public List<Product> getProductsByCategory(int categoryId) {
        return this.productRepository.getProductsByCategory(categoryId);
    }

    @Override
    public List<Product> getProductByCategoryName(String categoryName) {
        return this.productRepository.getProductByCategoryName(categoryName);
    }

    @Override
    public List<Image> getAllImageByProduct(int productid) {
        return this.imageRepository.getAllImageByProduct(productid);
    }

    //store image
    public String storeImage(MultipartFile image){
        String imageName = StringUtils.cleanPath(image.getOriginalFilename());
        try{
            if(!validate(imageName)) {
                throw new ImageStorageException("Sorry! Filename contains invalid path sequence " + imageName);
            }
            FileCopyUtils.copy(image.getBytes(), new File(fileDir+imageName));
            return imageName;
        }catch (IOException e){
            throw new ImageStorageException("Could not store image "+imageName);
        }
    }
    //validate image file
    public boolean validate(final String image) {
        pattern = Pattern.compile(IMAGE_PATTERN);
        matcher = pattern.matcher(image);
        return matcher.matches();
    }
}
