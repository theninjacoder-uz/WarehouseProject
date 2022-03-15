package uz.pdp.warehouseproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.warehouseproject.entity.Attachment;
import uz.pdp.warehouseproject.entity.AttachmentContent;
import uz.pdp.warehouseproject.repository.AttachmentContentRepository;
import uz.pdp.warehouseproject.repository.AttachmentRepository;
import uz.pdp.warehouseproject.response.ApiResponse;
import uz.pdp.warehouseproject.service.base.BaseService;

import java.util.Iterator;

import static uz.pdp.warehouseproject.consts.BaseConstants.*;

@Service
@RequiredArgsConstructor
public class AttachmentService implements BaseService<Attachment, Attachment> {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    @Override
    public ApiResponse add(Attachment item) {
        return null;
    }

    @Override
    public ApiResponse edit(Integer id, Attachment item) {
        return null;
    }

    @Override
    public ApiResponse delete(Integer id) {
        return null;
    }

    @Override
    public ApiResponse getAll() {
        return null;
    }

    @Override
    public ApiResponse getById(Integer id) {
        return null;
    }

    public ApiResponse uploadFile(MultipartHttpServletRequest request){
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        //Create attachment and save
        Attachment attachment = new Attachment();

        assert file != null;
        attachment.setContentType(file.getContentType());

        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        Attachment savedAttachment = attachmentRepository.save(attachment);

        //Create attachmentContent and save
        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);

        return new ApiResponse(1, SAVED, savedAttachment.getId());
    }

    @Override
    public Attachment checkById(Integer id) {
        return null;
    }
}
