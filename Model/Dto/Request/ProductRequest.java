package TechMini.Model.Dto.Request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
   @NotBlank(message = "Ten san pham khong duoc de trong")
   @Size(max = 200)
    private String name;
   @NotBlank(message = "SKU khong duoc de trong")
    private String sku;
   @NotNull(message = "Gia san pham khong duoc de trong")
   @Min(1000)
    private BigDecimal price;
   @NotNull(message = "So luong ton kho khong duoc de trong")
   @Min(0)
    private int stockQuantity;
    private String description;
}
