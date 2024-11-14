package ra.securityweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ra.securityweb.config.UploadService;

import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    private UploadService uploadService;
    @GetMapping
    public String home(){
        return  "index";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "redirect:/error=404";
        }
     String url = uploadService.uploadFileToDrive(file);
        System.out.println(url);
        return "redirect:/message=success" + url;
    }
}
