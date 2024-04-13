package com.CasualtyCat.controller;

import com.CasualtyCat.entity.*;
import com.CasualtyCat.exception.ResourceNotFoundException;
import com.CasualtyCat.payload.ClientDto;
import com.CasualtyCat.repository.IndustryRepository;
import com.CasualtyCat.service.ClientService;
import com.CasualtyCat.service.DurationService;
import com.CasualtyCat.service.IndustryService;
import com.CasualtyCat.service.TypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private DurationService durationService;

    @Autowired
    private IndustryService industryService;
    @Autowired
    private TypeService typeService;


    @PostMapping("/save")
    public ResponseEntity<?> createClient(@RequestBody @Valid ClientDto dto, BindingResult result){

        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getFieldError().getDefaultMessage());
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(dto));
        }

    }
    // Industries
    @PostMapping("/createIndustry")
    public ResponseEntity<Industry> createIndustry (@RequestBody Industry industry){
        return ResponseEntity.status(HttpStatus.CREATED).body(industryService.createIndustry(industry));
    }
    @GetMapping("/getIndustryList")
    public ResponseEntity<List<Industry>> getIndustryList(){
        return ResponseEntity.ok(industryService.getIndustryList());
    }
    @GetMapping("getIndustryById/{id}")
    public ResponseEntity<Industry> getIndustryById(@PathVariable("id")String durationId){
        return ResponseEntity.status(HttpStatus.OK).body(industryService.getIndustryById(durationId));
    }
    //Durations

    @PostMapping("/createDuration")
    public ResponseEntity<Duration> createDuration (@RequestBody Duration duration){
        return ResponseEntity.status(HttpStatus.CREATED).body(durationService.createDuration(duration));
    }

    @GetMapping("/getDurationList")
    public ResponseEntity<List<Duration>> getDurationList(){
        return ResponseEntity.ok(durationService.getDurationList());
    }
    @GetMapping("getDurationById/{id}")
    public ResponseEntity<Duration> getDurationById(@PathVariable("id")String durationId){
        return ResponseEntity.status(HttpStatus.OK).body(durationService.getDurationById(durationId));
    }
    //Types
    @PostMapping("/type")
    public Type addType(@RequestBody Type type) {
        return typeService.createType(type);
    }
    @GetMapping("/getTypeList")
    public List<Type> getAllType() {
        return typeService.getTypeList();
    }
    @GetMapping("/getTypeById/{id}")
    public ResponseEntity<Type> getTypeById(@PathVariable("id")String id){
        return ResponseEntity.status(HttpStatus.OK).body(typeService.getTypeById(id));
    }
    //pagination

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable String id) {
        try {
            ClientDto clientDto = clientService.getClientById(id);
            return ResponseEntity.ok(clientDto);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/getClientList")
    public ResponseEntity<List<ClientDto>> getAllClients() {
        List<ClientDto> clientDtos = clientService.getAllClient();
        return ResponseEntity.ok(clientDtos);
    }

    @DeleteMapping("/byId/{id}")
    public ResponseEntity<?> deleteClientById(@PathVariable String id) {
        try {
            String deletionMessage = clientService.deleteClientById(id);
            return ResponseEntity.ok().body(deletionMessage);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<?> updateClientById(@PathVariable String id, @RequestBody @Valid ClientDto dto, BindingResult result) {
        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        }

        try {
            ClientDto updatedClient = clientService.updateClientById(id, dto);
            return ResponseEntity.ok(updatedClient);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }


    @GetMapping("/getClientsFilter/{fieldType}")
    public Page<Client> getClientsFilter(@PathVariable("fieldType")String fieldType,
                                         @RequestParam (required = false)String fieldItem,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size){
        if(fieldType.equalsIgnoreCase("name")){
            PageRequest pageable = PageRequest.of(page, size);
            return clientService.findByNameContainig(fieldItem,pageable);
        } else if (fieldType.equalsIgnoreCase("company")) {
            PageRequest pageable = PageRequest.of(page, size);
            return clientService.findByCompanyName(fieldItem,pageable);
        } else if (fieldType.equalsIgnoreCase("email")) {
            PageRequest pageable = PageRequest.of(page, size);
            return clientService.findByEmailId(fieldItem,pageable);
        }else if (fieldType.equalsIgnoreCase("year")){
            Integer i;
            if(fieldItem==null){
                 i  = LocalDate.now().getYear();
            }else {
                i = Integer.valueOf(fieldItem);
            }
            PageRequest pageable = PageRequest.of(page, size);
            return clientService.findByCreatedDate(i,pageable);

        }
      return null;
    }


}
