package TechMini.Repository;

import TechMini.Model.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    boolean existsBySkuAndIdNot(String sku,Long id);

    boolean existsBySku(String sku);
}
