package ra.boot.model.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import ra.boot.validation.PhoneUnique;

import java.time.LocalDate;

@Data
public class StudentEditDto {
private Integer id;
    private String name;

    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @PhoneUnique(message = "Số điện thoại đã tồn tại")
    private String phone;

    private MultipartFile file;  // Nếu có file, sẽ cập nhật ảnh

    private Boolean sex;

    private String address;

    private String currentImageUrl; //hiển thị ảnh cũ nếu không sửa
}
