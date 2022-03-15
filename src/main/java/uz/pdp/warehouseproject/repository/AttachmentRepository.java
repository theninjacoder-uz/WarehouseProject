package uz.pdp.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouseproject.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {

}
