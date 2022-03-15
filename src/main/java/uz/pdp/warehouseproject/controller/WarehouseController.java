package uz.pdp.warehouseproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.Warehouse;
import uz.pdp.warehouseproject.service.WarehouseService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Warehouse warehouse){
        return ResponseEntity.ok(warehouseService.add(warehouse));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(warehouseService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        return ResponseEntity.ok(warehouseService.getById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Integer id, @RequestBody Warehouse warehouse){
        return ResponseEntity.ok(warehouseService.edit(id, warehouse));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok(warehouseService.delete(id));
    }
}
