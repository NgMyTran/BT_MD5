package ra.jpa.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder

@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(length = 100)
    private String username;
    @Column(unique = true)
    private String email;
    private String fullName;
    private String password;
    private String avatar;
    private String phone;
    private String address;
    private boolean status;
    private Date createdAt;
    private Date updateAt;
    private boolean isDeleted;
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @ManyToMany
    @JoinTable(
            name = "shopping_cart", // Tên bảng kết hợp
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
//
//    @OneToMany
//    private List<Product> products;
//
//    @OneToMany(mappedBy = "user")
//    private List<ShoppingCart> shoppingCarts;
}
