package vn.dshop.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.dshop.dto.product.ProductResponseDTO;
import vn.dshop.entity.Image;
import vn.dshop.entity.Product;
import vn.dshop.service.CategoryService;
import vn.dshop.service.ProductService;
import vn.dshop.transform.ProductTransform;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;
    private DateFormat dateFormat;
    @Value("${file.resource}")
    private String dir;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, DateFormat dateFormat) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.dateFormat = dateFormat;
    }
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll(@RequestParam int position){
        List<ProductResponseDTO> listDTO = new ArrayList<>();
        ProductTransform transform = new ProductTransform(dateFormat);
        List<Product> products = this.productService.getAllProducts(position);
       if(products!=null){
           for(Product p : products){
               ProductResponseDTO resp = transform.apply(p);
               List<String> img = new ArrayList<>();
               for(Image i : this.productService.getAllImageByProduct(p.getProductId())){
                   String imgurl = dir+i.getImageUrl();
                   img.add(imgurl);
               }
               resp.setImages(img);
               listDTO.add(resp);
           }
           return ResponseEntity.ok().body(listDTO);
       } else {
           return ResponseEntity.noContent().build();
       }

    }
    @GetMapping(value = "/{category}")
    public List<ProductResponseDTO> getProductsByCategory(@PathVariable(name = "category") String category){
        String categoryName = category.toUpperCase();
        List<ProductResponseDTO> listDTO = new ArrayList<>();
        ProductTransform transform = new ProductTransform(dateFormat);
        List<Product> products =  this.productService.getProductByCategoryName(categoryName);
        for(Product p : products){
            ProductResponseDTO resp = transform.apply(p);
            List<String> img = new ArrayList<>();
            for(Image i : this.productService.getAllImageByProduct(p.getProductId())){
                String imgurl = dir+i.getImageUrl();
                img.add(imgurl);
            }
            resp.setImages(img);
            listDTO.add(resp);
        }
        return listDTO;
    }

}
