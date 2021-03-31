package vn.dshop.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.dshop.entity.Image;
import vn.dshop.entity.Product;
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
    private static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp|jpeg))$)";
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
    public List<Product> getAllProducts(int position, int pageSize) {
        return this.productRepository.getAllProducts(position,pageSize);
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
            this.imageRepository.save(image);
        }
    }

    @Override
    @Transactional
    public void update(Product p) {
        if(p.getProductId()>0){
            this.productRepository.update(p);
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        Product product = this.productRepository.getProductById(id);
        if(product!=null){
            for(Image i : product.getImages()){
                this.imageRepository.delele(i);
            }
        }
        this.productRepository.delete(product);
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
    public boolean storeImage(MultipartFile image){
        String imageName = StringUtils.cleanPath(image.getOriginalFilename());
        try{
            if(!validate(imageName)) {
                System.out.println("Could not save "+ imageName);
                return false;
            } else {
                FileCopyUtils.copy(image.getBytes(), new File(fileDir+imageName));
                return true;
            }
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
    //validate image file
    public boolean validate(final String image) {
        pattern = Pattern.compile(IMAGE_PATTERN);
        matcher = pattern.matcher(image);
        return matcher.matches();
    }
}
