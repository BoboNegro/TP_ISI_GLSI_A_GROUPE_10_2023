package frey.house.ega.services;

import frey.house.ega.dto.ClientDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientInterface {

    List<ClientDto> findAll();
    ClientDto findById(Long id);
    ClientDto save(ClientDto clientDto);
    ClientDto update(ClientDto clientDto,Long id);
    ClientDto delete(ClientDto clientDto, Long id);
}
