package uz.pdp.warehouseproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.dto.receive.UserReceiveDto;
import uz.pdp.warehouseproject.service.UserService;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody UserReceiveDto user){
        return ResponseEntity.ok(userService.add(user));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Integer id, @RequestBody UserReceiveDto user){
        return ResponseEntity.ok(userService.edit(id, user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok(userService.delete(id));
    }

}
