package ra.jpa.entity;

import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "serial_number", nullable = false, unique = true)
    private String serialNumber;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @Column(name = "note")
    private String note;

    @Column(name = "receive_name")
    private String receiveName;

    @Column(name = "receive_address")
    private String receiveAddress;

    @Column(name = "receive_phone")
    private String receivePhone;

    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "received_at")
    private LocalDate receivedAt;

    // Khóa ngoại cho User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
//    @ManyToMany
//    @JoinTable(
//            name = "order_detail",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
//    private List<Product> products = new ArrayList<>();
}
