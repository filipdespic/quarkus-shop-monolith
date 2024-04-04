package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import model.abstr.AbstractEntity;
import model.status.PaymentStatus;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "payments")
public class Payment extends AbstractEntity {
    @Column(name = "paypal_payment_id")
    private String paypalPaymentId;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status;
    @NotNull
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    public Payment(String paypalPaymentId, @NotNull PaymentStatus status,
                   @NotNull BigDecimal amount) {
        this.paypalPaymentId = paypalPaymentId;
        this.status = status;
        this.amount = amount;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(paypalPaymentId, payment.paypalPaymentId);
    }
    @Override
    public int hashCode() { return Objects.hash(paypalPaymentId); }
}
