package uz.pdp.warehouseproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.dto.receive.InputProductReceiveDto;
import uz.pdp.warehouseproject.dto.receive.OutputProductReceiveDto;
import uz.pdp.warehouseproject.service.InputProductService;
import uz.pdp.warehouseproject.service.OutputProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/input-product")
public class InputProductController {

    private final InputProductService inputProductService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody InputProductReceiveDto inputProductReceiveDto){
        return ResponseEntity.ok(inputProductService.add(inputProductReceiveDto));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(inputProductService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        return ResponseEntity.ok(inputProductService.getById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Integer id, @RequestBody InputProductReceiveDto inputProductReceiveDto){
        return ResponseEntity.ok(inputProductService.edit(id, inputProductReceiveDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok(inputProductService.delete(id));
    }
}
