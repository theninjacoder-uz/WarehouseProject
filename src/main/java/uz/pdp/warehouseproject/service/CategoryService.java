package uz.pdp.warehouseproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.dto.receive.CategoryReceiveDto;
import uz.pdp.warehouseproject.entity.Category;
import uz.pdp.warehouseproject.entity.Measurement;
import uz.pdp.warehouseproject.repository.CategoryRepository;
import uz.pdp.warehouseproject.repository.MeasurementRepository;
import uz.pdp.warehouseproject.response.ApiResponse;
import uz.pdp.warehouseproject.service.base.BaseService;

import java.util.List;
import java.util.Optional;

import static uz.pdp.warehouseproject.consts.BaseConstants.EDIT;
import static uz.pdp.warehouseproject.consts.BaseConstants.SAVED;
import static uz.pdp.warehouseproject.response.BaseResponse.DELETED;
import static uz.pdp.warehouseproject.response.BaseResponse.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CategoryService implements BaseService<CategoryReceiveDto, Category> {

    private final CategoryRepository categoryRepository;

    @Override
    public ApiResponse add(CategoryReceiveDto item) {


        Category category = new Category();
        Integer parentCategoryId = item.getParentCategoryId();

        boolean b = categoryRepository.existsByNameAndParentCategoryId(item.getName(), parentCategoryId);
        if(b)
            return new ApiResponse(5, "Category is already exist", null);

        category.setName(item.getName());

        if (parentCategoryId != null) {
            Category parentCategory = checkById(parentCategoryId);
            if (parentCategory == null)
                return NOT_FOUND;

            category.setParentCategory(parentCategory);
        }

        return new ApiResponse(1, "Successfully added", categoryRepository.save(category));
    }

    @Override
    public ApiResponse edit(Integer id, CategoryReceiveDto item) {


        Category category = checkById(id);
        if(category == null)
            return NOT_FOUND;

        Integer parentCategoryId = item.getParentCategoryId();

        //Check if the category is already exist
        boolean b = categoryRepository.existsByNameAndParentCategoryId(item.getName(), parentCategoryId);
        if(b)
            return new ApiResponse(5, "Category is already exist", null);

        if(parentCategoryId != null) {
            Category parentCategory = checkById(parentCategoryId);

            if (parentCategory == null)
                return new ApiResponse(4, "Parent category not found", null);
            category.setParentCategory(parentCategory);
        }
        if(item.getName() != null || !item.getName().isBlank())
        category.setName(item.getName());
        categoryRepository.save(category);

        return new ApiResponse(1, EDIT, category);
    }

    @Override
    public ApiResponse delete(Integer id) {

        Category category = checkById(id);
        if(category == null)
            return NOT_FOUND;
        category.setActive(false);
        categoryRepository.save(category);

        return DELETED;
    }

    @Override
    public ApiResponse getAll() {
        return new ApiResponse(1, "All categories", categoryRepository.findAll());
    }

    @Override
    public ApiResponse getById(Integer id) {

        Category category = checkById(id);
        if(category == null)
            return NOT_FOUND;

        return new ApiResponse(1, "Category", category);
    }

    @Override
    public Category checkById(Integer id) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent() && optionalCategory.get().isActive())
            return optionalCategory.get();

        return null;
    }
}
