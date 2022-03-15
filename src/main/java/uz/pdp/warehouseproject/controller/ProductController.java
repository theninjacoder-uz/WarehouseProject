package uz.pdp.warehouseproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.dto.receive.ProductReceiveDto;
import uz.pdp.warehouseproject.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody ProductReceiveDto productReceiveDto){
        return ResponseEntity.ok(productService.add(productReceiveDto));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        return ResponseEntity.ok(productService.getById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Integer id, @RequestBody ProductReceiveDto productReceiveDto){
        return ResponseEntity.ok(productService.edit(id, productReceiveDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok(productService.delete(id));
    }
}
