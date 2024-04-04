package repository;

import model.Cart;
import model.status.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByStatus(CartStatus status);
//    List<Cart> findByStatusAndCustomerId(CartStatus cartStatus, Long customerId);
}
