package ra.boot.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ra.boot.model.dto.StudentCreateDto;
import ra.boot.model.dto.StudentEditDto;
import ra.boot.model.entity.Student;
import ra.boot.service.IStudentService;

import java.util.Optional;
import java.io.IOException;

@Controller
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @GetMapping
    public String list(Model model){
        model.addAttribute("list",studentService.findAll());
        return "student/list";
    }
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("studentAdd", new StudentCreateDto() );
        return "student/add";
    }
    @PostMapping("/add")
    public String doAdd(@Valid @ModelAttribute("studentAdd") StudentCreateDto request, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("studentAdd",request);
            return "student/add";
        }
        studentService.add(request);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable("id") Integer id, Model model) {
        Student student = studentService.findById(id).orElse(null);

        if (student != null) {
            StudentEditDto dto = new StudentEditDto();
            dto.setName(student.getName());
            dto.setPhone(student.getPhone());
            dto.setSex(student.getSex());
            dto.setDateOfBirth(student.getDateOfBirth());
            dto.setAddress(student.getAddress());
            dto.setCurrentImageUrl(student.getImageUrl());

            model.addAttribute("studentEdit", dto);
            return "student/edit";
        }

        return "redirect:/students"; // Nếu không tìm thấy sinh viên, chuyển hướng về danh sách
    }

    @PostMapping("/edit/{id}")
    public String updateStudent(@PathVariable("id") Integer id,
                                @ModelAttribute("studentEdit") StudentEditDto dto,
                                BindingResult result, Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("studentEdit", dto);
            return "student/edit"; // Nếu có lỗi, trả về form chỉnh sửa
        }

        // Giữ giá trị cũ cho những trường không thay đổi
        String imageUrl = dto.getCurrentImageUrl();  // Giữ URL ảnh cũ nếu không có ảnh mới

        // Nếu có file ảnh mới, chúng ta sẽ lưu lại và cập nhật URL ảnh mới
        if (dto.getFile() != null && !dto.getFile().isEmpty()) {
            imageUrl = studentService.saveImage(dto.getFile()); // Lưu ảnh mới và lấy URL
        }

        // Cập nhật thông tin sinh viên với những trường đã thay đổi
        StudentEditDto updatedDto = new StudentEditDto();
        updatedDto.setName(dto.getName() != null ? dto.getName() : dto.getName());
        updatedDto.setPhone(dto.getPhone() != null ? dto.getPhone() : dto.getPhone());
        updatedDto.setSex(dto.getSex() != null ? dto.getSex() : dto.getSex());
        updatedDto.setDateOfBirth(dto.getDateOfBirth() != null ? dto.getDateOfBirth() : dto.getDateOfBirth());
        updatedDto.setAddress(dto.getAddress() != null ? dto.getAddress() : dto.getAddress());
        updatedDto.setCurrentImageUrl(imageUrl);  // Cập nhật ảnh mới (hoặc giữ ảnh cũ)

        // Gọi service để cập nhật dữ liệu
        studentService.update(id, updatedDto);

        // Sau khi cập nhật thành công, chuyển hướng về danh sách sinh viên
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        studentService.delete(id);
        return "redirect:/";
    }
}
