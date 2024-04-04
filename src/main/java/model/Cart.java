package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import model.abstr.AbstractEntity;
import model.status.CartStatus;

@Getter
@Setter
@NoArgsConstructor(force = true)
@Entity
@Table(name = "carts")
public class Cart extends AbstractEntity {

    @ManyToOne
    private final Customer customer;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CartStatus status;

    public Cart(Customer customer, @NotNull CartStatus status) {
        this.customer = customer;
        this.status = status;
    }


}
