package TechMini.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "miniProduct")
@Builder
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @Column(nullable = false)
    private String name;
   @Column(nullable = false, unique = true)
    private String sku;
   @Column(nullable = false)
    private BigDecimal price;
   @Column(nullable = false)
    private int stockQuantity;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
