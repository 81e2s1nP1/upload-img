package com.pa.Controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.pa.entities.Product;
import com.pa.repository.ProductRepository;

@Controller
@RequestMapping(path = "/api")
public class ProductController {
	@Autowired
	private Cloudinary cloudinary;
	
	@Autowired
	private ProductRepository repository;
	
	@PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Sử dụng CloudinaryService để tải lên tệp lên Cloudinary
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

            // Trích xuất URL của tệp đã tải lên
            String fileUrl = (String) uploadResult.get("url");
            Product product = new Product();
            product.setUrl(fileUrl);
            repository.save(product);
            // Trả về URL của tệp đã tải lên
            return fileUrl;
        } catch (IOException e) {
            e.printStackTrace();
            return "Lỗi khi tải lên tệp";
        }
    }
	
	@GetMapping(path = "/")
	public String getImg(Model m) {
		Product p = repository.getById(1);
		m.addAttribute("product", p);
		return "img";
	}
}
