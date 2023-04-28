package frey.house.ega.servicesImplementations;

import frey.house.ega.dto.ClientDto;
import frey.house.ega.exceptions.ResourceNotFoundException;
import frey.house.ega.mappers.EntityMapper;
import frey.house.ega.model.Client;
import frey.house.ega.repositories.ClientRepository;
import frey.house.ega.services.ClientInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService implements ClientInterface {
    private final EntityMapper entityMapper;
    private final ClientRepository clientRepository;

    public ClientService(EntityMapper entityMapper, ClientRepository clientRepository) {
        this.entityMapper = entityMapper;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientDto> findAll() {
        List<Client> clients = this.clientRepository.findClientByEtatOrderByIdDesc(true);
        return clients.stream().map(this.entityMapper::ClientToClientDto).collect(Collectors.toList());
    }

    @Override
    public ClientDto findById(Long id) {
        Client client = this.clientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("client","id",id));
        return this.entityMapper.ClientToClientDto(client);
    }

    @Override
    public ClientDto save(ClientDto ClientDto) {
        Client client = this.entityMapper.ClientDtoToClient(ClientDto);
        Client savedclient = this.clientRepository.save(client);
        return this.entityMapper.ClientToClientDto(savedclient);
    }

    @Override
    public ClientDto update(ClientDto clientDto, Long id) {
        Client client = this.clientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Client", "id", id));
        client = this.entityMapper.ClientDtoToClient(clientDto);
        Client updatedClient = this.clientRepository.save(client);
        return this.entityMapper.ClientToClientDto(updatedClient);
    }

    @Override
    public ClientDto delete(ClientDto clientDto, Long id){
        Client client = this.clientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Client", "id", id));
        clientDto.setEtat(false);
        client = this.entityMapper.ClientDtoToClient(clientDto);
        Client updatedClient = this.clientRepository.save(client);
        return this.entityMapper.ClientToClientDto(updatedClient);
    }
}
