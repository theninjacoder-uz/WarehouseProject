package uz.pdp.warehouseproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.dto.receive.CategoryReceiveDto;
import uz.pdp.warehouseproject.entity.Category;
import uz.pdp.warehouseproject.entity.Measurement;
import uz.pdp.warehouseproject.service.CategoryService;
import uz.pdp.warehouseproject.service.MeasurementService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody CategoryReceiveDto category){
        return ResponseEntity.ok(categoryService.add(category));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Integer id, @RequestBody CategoryReceiveDto category){
        return ResponseEntity.ok(categoryService.edit(id, category));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok(categoryService.delete(id));
    }
}

