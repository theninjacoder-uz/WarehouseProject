package uz.pdp.warehouseproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.warehouseproject.service.AttachmentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/attachment")
public class AttachmentController {

    private AttachmentService attachmentService;

    @PostMapping
    public ResponseEntity<?> uploadFile(MultipartHttpServletRequest request){
        return ResponseEntity.ok(attachmentService.uploadFile(request));
    }
}
