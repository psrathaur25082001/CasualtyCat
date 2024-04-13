package com.CasualtyCat.controller;

import com.CasualtyCat.exception.ResourceNotFoundException;
import com.CasualtyCat.payload.InsurerDto;
import com.CasualtyCat.service.InsurerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insurer")
public class InsurerController {
    @Autowired
    private InsurerService insurerService;

    public InsurerController(InsurerService insurerService) {
        this.insurerService = insurerService;
    }
    @PostMapping("/save")
    public ResponseEntity<?> createInsurer(@RequestBody @Valid InsurerDto dto, BindingResult result){


        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getFieldError().getDefaultMessage());
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(insurerService.createInsurer(dto));
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getInsurerById(@PathVariable String id) {
        try {
            InsurerDto insurerDto = insurerService.getInsurerById(id);
            return ResponseEntity.ok(insurerDto);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/insurers")
    public ResponseEntity<List<InsurerDto>> getAllInsurer() {
        List<InsurerDto> insurerDtos = insurerService.getAllInsurer();
        return ResponseEntity.ok(insurerDtos);
    }

    @DeleteMapping("/{id}")// here
    public ResponseEntity<?> deleteInsurerById(@PathVariable String id) {
        try {
            String deletionMessage = insurerService.deleteInsurerById(id);
            return ResponseEntity.ok(deletionMessage);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInsurerById(@PathVariable String id, @RequestBody @Valid InsurerDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getFieldError().getDefaultMessage());
        }

        try {
            InsurerDto updatedInsurer = insurerService.updateInsurerById(id, dto);
            return ResponseEntity.ok(updatedInsurer);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
