//package ra.jpa.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.math.BigDecimal;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//@Getter
//@Entity
//@Table(name = "shopping_cart")
//public class ShoppingCart {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    private int orderQuantity;
//}
