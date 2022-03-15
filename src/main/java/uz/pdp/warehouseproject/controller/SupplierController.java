package uz.pdp.warehouseproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.Supplier;
import uz.pdp.warehouseproject.service.SupplierService;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Supplier supplier){
        return ResponseEntity.ok(supplierService.add(supplier));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(supplierService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        return ResponseEntity.ok(supplierService.getById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Integer id, @RequestBody Supplier supplier){
        return ResponseEntity.ok(supplierService.edit(id, supplier));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok(supplierService.delete(id));
    }

}
