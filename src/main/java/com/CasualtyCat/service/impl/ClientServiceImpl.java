package com.CasualtyCat.service.impl;

import com.CasualtyCat.entity.*;
import com.CasualtyCat.exception.ResourceNotFoundException;
import com.CasualtyCat.payload.ClientDto;
import com.CasualtyCat.repository.ClientRepository;
import com.CasualtyCat.repository.DurationRepository;
import com.CasualtyCat.repository.IndustryRepository;
import com.CasualtyCat.repository.TypeRepository;
import com.CasualtyCat.service.ClientService;
import com.CasualtyCat.service.DurationService;
import com.CasualtyCat.service.IndustryService;
import com.CasualtyCat.service.TypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private IndustryService industryService;
    @Autowired
    private DurationService durationService;
    @Autowired
    private TypeService typeService;


    @Override
    public ClientDto createClient(ClientDto dto) {
        if(clientRepository.existsByEmailId(dto.getEmailId())){
            throw new ResourceNotFoundException("email id already registered use different email id");
        }if (clientRepository.existsByMobile(dto.getMobile())){
            throw new ResourceNotFoundException("mobile already registered use different mobile number");
        }else{// creating client using model mapper and before creating checking the email id and mobile number
            Industry industry = industryService.getIndustryById(dto.getIndustryId());
            Type typeById = typeService.getTypeById(dto.getTypeId());
            Duration durationById = durationService.getDurationById(dto.getDurationId());
            if(industry!=null && typeById!=null&&durationById!=null){
                dto.setClientId(UUID.randomUUID().toString());
              //  dto.setCreatedDate(LocalDate.now());
           //     Client map = modelMapper.map(dto, Client.class);
                Client client=new Client();
                System.out.println(client.getCreateDate());
                client.setClientId(dto.getClientId());
                client.setName(dto.getName());
                client.setCompanyName(dto.getCompanyName());
                client.setDurationId(dto.getDurationId());
                client.setEmailId(dto.getEmailId());
                client.setMobile(dto.getMobile());
                client.setIndustryId(dto.getIndustryId());
                client.setTypeId(dto.getTypeId());
                client.setDesignation(dto.getDesignation());

                Client save = clientRepository.save(client);
                return modelMapper.map(save,ClientDto.class);
            }
        }
        return null;
    }

//pagination


    @Override
    public ClientDto getClientById(String id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("client not Found with this id")
        );
        return modelMapper.map(client,ClientDto.class);
    }

    @Override
    public List<ClientDto> getAllClient() {
        List<Client> all=clientRepository.findAll();
        List<ClientDto> collect = all.stream().map(t -> modelMapper.map(t, ClientDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public String deleteClientById(String id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client not found with this ID: " + id)
        );
        clientRepository.deleteById(id);
        return "Client with ID " + id + " has been successfully deleted.";
    }


    @Override
    public ClientDto updateClientById(String id, ClientDto dto) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("client not Found with this id")
        );
        client.setName(dto.getName());
        client.setDesignation(dto.getDesignation());
        client.setDurationId(dto.getDurationId());
        //client.setType(dto.getType());
        client.setCompanyName(dto.getCompanyName());
        client.setEmailId(dto.getEmailId());
        client.setMobile(dto.getMobile());
        client.setIndustryId(dto.getIndustryId());
        client.setTypeId(dto.getTypeId());
        Client save = clientRepository.save(client);
        ClientDto map = modelMapper.map(client, ClientDto.class);
        return map;
    }

    @Override
    public Page<Client> findByEmailId(String emailId, PageRequest pageable) {
     return clientRepository.findByEmailIdContainingIgnoreCase(emailId,pageable);
    }

    @Override
    public Page<Client> findByCompanyName(String companyName, PageRequest pageable) {
        return clientRepository.findByCompanyNameContainingIgnoreCase(companyName,pageable);
    }

    @Override
    public Page<Client> findByCreatedDate(int createdDate, PageRequest pageable) {
        return clientRepository.findByCreateDateYear(createdDate,pageable);
    }


    @Override
    public Page<Client> findByNameContainig(String name, PageRequest pageable) {
       return clientRepository.findByNameContainingIgnoreCase(name,pageable);
    }

}
