package exercise.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import exercise.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // BEGIN
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN %:min% AND %:max%")
    List<Product> findByProductBetween(@Param("min") Integer min, @Param("max") Integer max);
    List<Product> findByPriceBetween(Integer startPrice, Integer endPrice, Sort sort);
    // END
}
