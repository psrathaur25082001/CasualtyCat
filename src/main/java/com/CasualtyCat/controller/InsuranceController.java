package com.CasualtyCat.controller;

import com.CasualtyCat.exception.ResourceNotFoundException;
import com.CasualtyCat.payload.InsuranceDto;
import com.CasualtyCat.service.InsuranceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    private InsuranceService insuranceService;

    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> createInsurance(@RequestBody @Valid InsuranceDto dto, BindingResult result){

        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getFieldError().getDefaultMessage());
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(insuranceService.createInsurance(dto));
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInsuranceById(@PathVariable String id) {
        try {
            InsuranceDto insuranceDto = insuranceService.getInsuranceById(id);
            return ResponseEntity.ok(insuranceDto);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/insurances")
    public ResponseEntity<List<InsuranceDto>> getAllInsurances() {
        List<InsuranceDto> insuranceDtos = insuranceService.getAllInsurance();
        return ResponseEntity.ok(insuranceDtos);
    }

    @DeleteMapping("/{id}")// here
    public ResponseEntity<?> deleteInsuranceById(@PathVariable String id) {
        try {
            String deletionMessage = insuranceService.deleteInsuranceById(id);
            return ResponseEntity.ok(deletionMessage);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInsuranceById(@PathVariable String id, @RequestBody @Valid InsuranceDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getFieldError().getDefaultMessage());
        }

        try {
            InsuranceDto updatedInsurance = insuranceService.updateInsuranceById(id, dto);
            return ResponseEntity.ok(updatedInsurance);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}
