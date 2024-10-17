package ra.webservice.student.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String name;
    private double price;
    private int stock;
    private boolean status=true;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}

