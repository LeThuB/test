package TechMini.Controrller;

import TechMini.Model.Dto.Request.ProductRequest;

import TechMini.Model.Dto.Response.ApiResponse;
import TechMini.Model.Dto.Response.ProductResponse;

import TechMini.Model.Entity.Product;

import TechMini.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping

    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProduct(){
        List<ProductResponse> lists = productService.getAllProduct();
      ApiResponse<List<ProductResponse>> response = ApiResponse.<List<ProductResponse>>builder()
              .status(200)
              .message("Danh sach sna pham")
              .data(lists)
              .timestamp(LocalDateTime.now())
              .build();
      return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")

    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable Long id){
        ProductResponse productResponse = productService.getById(id);
        ApiResponse<ProductResponse> response = ApiResponse.<ProductResponse>builder()
                .status(200)
                .message("CHi tiet san pham co ID : "+id )
                .data(productResponse)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
//them moi san pham
    @PostMapping
    public ResponseEntity<ApiResponse<Product>> addProduct(@Valid @RequestBody ProductRequest productRequest){
        Product product = productService.createProduct(productRequest);
        ApiResponse<Product> response  = ApiResponse.<Product>builder()
                .status(201)
                .message("Them moi san pham thanh cong")
                .data(product)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
//cap nhat
    @PutMapping
    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Long id,
                                                              @Valid @RequestBody ProductRequest productRequest){
        Product product = productService.updateProduct(id, productRequest);
        ApiResponse<Product> response = ApiResponse.<Product>builder()
                .status(200)
                .message("Cap nhat san pham thanh cong")
                .data(product)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    //xoa

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProduct(@PathVariable Long id){
       productService.deleteProduct(id);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .status(200)
                .message("Xoa san pham thanh cong")
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
