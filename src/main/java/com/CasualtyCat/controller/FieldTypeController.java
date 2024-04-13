//package com.CasualtyCat.controller;
//
//import com.CasualtyCat.entity.FieldType;
//import com.CasualtyCat.service.FieldService;
//import org.springframework.data.domain.Page;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/field")
//public class FieldTypeController {
//    private FieldService fieldService;
//
//    public FieldTypeController(FieldService fieldService) {
//        this.fieldService = fieldService;
//    }
//
//
//    @GetMapping("/types")
//    public Page<FieldType> searchTypes(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String company,
//            @RequestParam(required = false) String email,
//            @RequestParam(required = false) Integer year,
//            @RequestParam(defaultValue = "0") int pageNo,
//            @RequestParam(defaultValue = "10") int pageSize) {
//
//        return fieldService.searchTypes(name,company, email, year != null ? year : 0, pageNo, pageSize);
//    }
//}
