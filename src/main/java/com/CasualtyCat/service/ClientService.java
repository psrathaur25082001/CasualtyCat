package com.CasualtyCat.service;

import com.CasualtyCat.entity.*;
import com.CasualtyCat.payload.ClientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {
    ClientDto createClient(ClientDto dto);
    ClientDto getClientById(String id);
    List<ClientDto> getAllClient();
    String deleteClientById(String id);
    ClientDto updateClientById(String id,ClientDto dto);


    Page<Client> findByEmailId(String emailId,PageRequest pageable);
    Page<Client> findByCompanyName(String companyName,PageRequest pageable);
    Page<Client> findByCreatedDate(int createdDate,PageRequest pageable);

    Page<Client> findByNameContainig(String name, PageRequest pageable);
}
