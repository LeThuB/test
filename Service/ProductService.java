package TechMini.Service;

import TechMini.Exception.CustomException.DulicateResourceException;
import TechMini.Exception.CustomException.ResourceNotFoundException;
import TechMini.Model.Dto.Request.ProductRequest;
import TechMini.Model.Dto.Response.ApiResponse;
import TechMini.Model.Dto.Response.ProductResponse;
import TechMini.Model.Entity.Product;
import TechMini.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    //xem danh sach san pham

    public List<ProductResponse> getAllProduct(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> new ProductResponse(
                product.getId(),
                product.getName(),
                product.getSku(),
                product.getPrice(),
                product.getStockQuantity())).toList();
    }

    //xem sp theo id

    public ProductResponse getById(Long id){
       Product product = productRepository.findById(id).orElseThrow(()->
               new ResourceNotFoundException("Khong tim thay san pham co ID : "+ id));
        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .build();
        return productResponse;
    }

    //tao moi san pham
    public Product createProduct(ProductRequest productRequest) {
        if (productRepository.existsBySku(productRequest.getSku())) {
            throw new DulicateResourceException("SKU da ton tai");
        }
        Product product = Product.builder()
                .name(productRequest.getName())
                .sku(productRequest.getSku())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .stockQuantity(productRequest.getStockQuantity())
                .createdAt(LocalDateTime.now())
                .build();
        return productRepository.save(product);

    }

    //cap nhat san pham

    public Product updateProduct(Long id ,ProductRequest productRequest){
       Product product = productRepository.findById(id).orElseThrow(()->
               new ResourceNotFoundException("Khong tim thay san pham co ID : "+ id));
       if (productRepository.existsBySkuAndIdNot(productRequest.getSku(), id)){
           throw new DulicateResourceException("Sku da ton tai ");
       }
       Product updateProduct = Product.builder()
               .id(product.getId())
               .name(productRequest.getName())
               .sku(productRequest.getSku())
               .description(productRequest.getDescription())
               .price(productRequest.getPrice())
               .stockQuantity(productRequest.getStockQuantity())
               .createdAt(product.getCreatedAt())
               .updateAt(LocalDateTime.now())
               .build();
       return productRepository.save(updateProduct);
    }
//xoa
    public void deleteProduct(Long id){
        Product deleteProduct = productRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Khong tim thay san pham co id :"+ id));
        productRepository.delete(deleteProduct);
    }

}
