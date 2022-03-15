package uz.pdp.warehouseproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.dto.receive.InputReceiveDto;
import uz.pdp.warehouseproject.dto.receive.OutputReceiveDto;
import uz.pdp.warehouseproject.service.InputService;
import uz.pdp.warehouseproject.service.OutputService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/input")
public class InputController {

    private final InputService inputService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody InputReceiveDto inputReceiveDto){
        return ResponseEntity.ok(inputService.add(inputReceiveDto));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(inputService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        return ResponseEntity.ok(inputService.getById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Integer id, @RequestBody InputReceiveDto inputReceiveDto){
        return ResponseEntity.ok(inputService.edit(id, inputReceiveDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok(inputService.delete(id));
    }
}
