package com.example.shopapp.controllers;

import com.example.shopapp.dtos.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createProduct(@Valid @ModelAttribute ProductDTO productDTO, BindingResult bindingResult){
        try{
            if(bindingResult.hasErrors()){
                List<String> errorMessages = bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            List<MultipartFile> files = productDTO.getFiles();
            files = files == null ? new ArrayList<MultipartFile>() : files;
            for (MultipartFile file : files){
                if (file.getSize() == 0){
                    continue;
                }
                // kiem tra kich thuoc file
                if (file.getSize() > 10 * 1024 * 1024){ // kich thuoc lon hon 10MB
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                            .body("File is too large! Maximum size is 10MB");
                }
                // kiem tra dinh dang file
                String contentType = file.getContentType();
                if (contentType == null || !contentType.startsWith("image/")){
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                            .body("File must be an image");
                }
                // Luu file va cap nhat thumbnail trong DTO
                String fileName = storeFile(file);
                // Luu vao doi tuong product trong DB
                //TODO : luw vao bang product_images
            }
            return ResponseEntity.ok("Product created successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    private String storeFile(MultipartFile file) throws IOException{
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        // them UUID vao truoc ten file de dam bao ten file la duy nhat
        String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
        // duong dan den thu muc ma muon luu file
        Path uploadDir = Paths.get("uploads");
        // kiem tra va tao thu muc neu no khong ton tai
        if (!Files.exists(uploadDir)){
            Files.createDirectories(uploadDir);
        }
        // duong dan day du den file
        Path destination = Paths.get(uploadDir.toString(), uniqueFileName);
        // sao chep file vao thu muc dich
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }
    @GetMapping("")
    public ResponseEntity<String> getProducts(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        return ResponseEntity.ok("products here");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable("id") String productId){
        return ResponseEntity.ok("product with ID:" + productId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id){
        return ResponseEntity.ok(String.format("product with id  = %d deleted successfully", id));
    }
}
