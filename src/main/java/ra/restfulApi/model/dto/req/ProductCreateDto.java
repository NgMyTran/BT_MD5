package ra.restfulApi.model.dto.req;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductCreateDto {
    @NotBlank(message = "k đc bỏ trống")
    private String name;
    @NotBlank(message = "k đc bỏ trống")
    private String description;
    @NotNull(message = "k đc bỏ trống")
    @Min(value = 20,message = "k đc dưới 10")
    private Integer price;
    @NotBlank
    private String image;
    @NotNull(message = "k đc bỏ trống")
    @Min(value = 10,message = "k đc dưới 20")
    private Integer stock;
}
