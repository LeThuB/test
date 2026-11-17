package TechMini.Model.Dto.Response;

import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long id;
    private String name;
    private String sku;
    private BigDecimal price;
    private int stockQuantity;
}
