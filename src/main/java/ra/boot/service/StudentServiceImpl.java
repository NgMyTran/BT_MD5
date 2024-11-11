package ra.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ra.boot.model.dto.StudentCreateDto;
import ra.boot.model.dto.StudentEditDto;
import ra.boot.model.entity.Student;
import ra.boot.repository.IStudentRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService{
//    @Autowired
//    private UploadService uploadService;
@Value("${file.upload-dir}")
private String uploadDir;
    @Autowired
    private IStudentRepository studentRepository;

    private static final String UPLOAD_DIR = "uploads/";

    @Override
    public String saveImage(MultipartFile file) {
        // Đảm bảo thư mục tồn tại
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();  // Nếu thư mục chưa có thì tạo mới
        }

        // Tạo tên tệp duy nhất để tránh trùng lặp (ví dụ sử dụng thời gian hiện tại + tên gốc của file)
        String originalFilename = file.getOriginalFilename();
        String newFilename = System.currentTimeMillis() + "-" + originalFilename;

        // Lưu ảnh vào thư mục
        try {
            Path path = Path.of(uploadDir, newFilename);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return path.toString();  // Trả về đường dẫn ảnh đã lưu
        } catch (IOException e) {
            throw new RuntimeException("Không thể lưu ảnh", e);
        }
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void add(StudentCreateDto request) {
//        String url = uploadService.uploadFileToServer(request.getFile());
        String url = null;
        if (request.getFile() != null && !request.getFile().isEmpty()) {
            try {
                // Tạo đường dẫn lưu file
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Tạo tên file duy nhất
                String fileName = System.currentTimeMillis() + "_" + request.getFile().getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);

                // Lưu file vào thư mục
                request.getFile().transferTo(filePath.toFile());

                // Lưu đường dẫn URL của file ảnh
                url = filePath.toString();  // Đường dẫn tuyệt đối
                // Nếu bạn cần URL tương đối cho ứng dụng, bạn có thể chỉ lấy tên file hoặc đường dẫn tương đối
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Student entity = Student.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .sex(request.getSex())
                .dateOfBirth(request.getDateOfBirth())
                .imageUrl(url)
                .build();
        studentRepository.save(entity);
    }

    @Override
    public Optional<Student> findById(Integer id) {
        return studentRepository.findById(id);
    }

    @Override
    public void update(Integer id, StudentEditDto dto) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();

            // Cập nhật thông tin học sinh
            student.setName(dto.getName() != null ? dto.getName() : student.getName());
            student.setPhone(dto.getPhone() != null ? dto.getPhone() : student.getPhone());
            student.setSex(dto.getSex() != null ? dto.getSex() : student.getSex());
            student.setDateOfBirth(dto.getDateOfBirth() != null ? dto.getDateOfBirth() : student.getDateOfBirth());
            student.setAddress(dto.getAddress() != null ? dto.getAddress() : student.getAddress());

            // Xử lý ảnh nếu có thay đổi
            String imageUrl = dto.getCurrentImageUrl();  // Giữ URL ảnh cũ nếu không có ảnh mới
            if (dto.getFile() != null && !dto.getFile().isEmpty()) {
                imageUrl = saveImage(dto.getFile()); // Lưu ảnh mới và lấy URL
            }

            // Cập nhật ảnh
            student.setImageUrl(imageUrl);

            // Lưu đối tượng đã được cập nhật vào cơ sở dữ liệu
            studentRepository.save(student);
        }
    }

    @Override
    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }
}
