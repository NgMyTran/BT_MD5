package ra.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "sku", columnDefinition = "varchar(100)", unique = true)
    private String sku;

    @Column(name = "product_name", columnDefinition = "varchar(100)", nullable = false, unique = true)
    private String productName;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "price", columnDefinition = "decimal(10,2)")
    private BigDecimal price;

    @Column(name = "stock")
    private int stock;

    @Column(name = "image", columnDefinition = "varchar(255)")
    private String image;

    @Column(name = "created_at", columnDefinition = "date")
    private Date createdAt;

    @Column(name = "updated_at", columnDefinition = "date")
    private Date updatedAt;

    @Column(columnDefinition = "boolean default true")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
//
//    @OneToMany(mappedBy = "product")
//    private List<ShoppingCart> shoppingCarts;
//
//    @OneToMany(mappedBy = "product")
//    private List<OrderDetail> orderDetails;
}
