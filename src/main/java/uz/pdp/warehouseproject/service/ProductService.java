package uz.pdp.warehouseproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.dto.receive.ProductReceiveDto;
import uz.pdp.warehouseproject.entity.*;
import uz.pdp.warehouseproject.repository.AttachmentRepository;
import uz.pdp.warehouseproject.repository.CategoryRepository;
import uz.pdp.warehouseproject.repository.MeasurementRepository;
import uz.pdp.warehouseproject.repository.ProductRepository;
import uz.pdp.warehouseproject.response.ApiResponse;
import uz.pdp.warehouseproject.service.base.BaseService;

import java.util.Optional;

import static uz.pdp.warehouseproject.consts.BaseConstants.EDIT;
import static uz.pdp.warehouseproject.consts.BaseConstants.SAVED;
import static uz.pdp.warehouseproject.response.BaseResponse.DELETED;
import static uz.pdp.warehouseproject.response.BaseResponse.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductService implements BaseService<ProductReceiveDto, Product> {

    private final CategoryRepository categoryRepository;
    private final AttachmentRepository attachmentRepository;
    private final MeasurementRepository measurementRepository;
    private final ProductRepository productRepository;

    @Override
    public ApiResponse add(ProductReceiveDto item) {

        Integer categoryId = item.getCategoryId();

        boolean exists = productRepository.existsByNameAndCategoryId(item.getName(), categoryId);
        if(exists)
            return new ApiResponse(5, "Product name already exist in this category", null);

        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if(optionalCategory.isEmpty())
            return new ApiResponse(4, "Category not found", null);

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(item.getMeasurementId());
        if(optionalMeasurement.isEmpty())
            return new ApiResponse(4, "Measurement not found", null);

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(item.getPhotoId());
        if(optionalAttachment.isEmpty())
            return new ApiResponse(4, "Photo not found", null);

        Product product = new Product();
        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setPhoto(optionalAttachment.get());
        product.setName(item.getName());
        product.setCode("1");

        return new ApiResponse(1, SAVED,  productRepository.save(product));
    }

    @Override
    public ApiResponse edit(Integer id, ProductReceiveDto item) {

        Product product = checkById(id);
        Integer categoryId = item.getCategoryId();

        if(product == null)
            return NOT_FOUND;

        boolean exists = productRepository.existsByNameAndCategoryId(item.getName(), categoryId);
        if(exists)
            return new ApiResponse(5, "Product name already exist in this category", null);

        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if(optionalCategory.isEmpty())
            return new ApiResponse(4, "Category not found", null);

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(item.getMeasurementId());
        if(optionalMeasurement.isEmpty())
            return new ApiResponse(4, "Measurement not found", null);

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(item.getPhotoId());
        if(optionalAttachment.isEmpty())
            return new ApiResponse(4, "Photo not found", null);

        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setPhoto(optionalAttachment.get());
        product.setName(item.getName());
        product.setCode("1");

        return new ApiResponse(1, EDIT, productRepository.save(product));
    }

    @Override
    public ApiResponse delete(Integer id) {
        Product product = checkById(id);
        if(product == null)
            return NOT_FOUND;

        product.setActive(false);
        productRepository.save(product);

        return DELETED;
    }

    @Override
    public ApiResponse getAll() {
        return new ApiResponse(1, "All products", productRepository.findAll());
    }

    @Override
    public ApiResponse getById(Integer id) {
        Product product = checkById(id);
        if(product == null)
            return NOT_FOUND;
        return new ApiResponse(1, "Product", product);
    }

    @Override
    public Product checkById(Integer id) {
        Optional<Product> optional = productRepository.findById(id);
        return optional.orElse(null);
    }


}
