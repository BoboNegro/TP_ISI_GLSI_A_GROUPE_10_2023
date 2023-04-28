package frey.house.ega.mappers;

import frey.house.ega.controller.VirementController;
import frey.house.ega.dto.*;
import frey.house.ega.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntityMapperImpl implements EntityMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Client ClientDtoToClient(ClientDto clientDto) {
        if(clientDto == null){
            return null;
        } return this.modelMapper.map(clientDto, Client.class);
    }

    @Override
    public ClientDto ClientToClientDto(Client client) {
        if(client == null){
            return null;
        } return this.modelMapper.map(client, ClientDto.class);
    }

    @Override
    public Compte CompteDtoToCompte(CompteDto compteDto) {
        if(compteDto == null){
            return null;
        } return this.modelMapper.map(compteDto, Compte.class);
    }

    @Override
    public CompteDto CompteToCompteDto(Compte compte) {
        if(compte == null){
            return null;
        } return this.modelMapper.map(compte, CompteDto.class);
    }

    @Override
    public Retrait RetraitDtoToRetrait(RetraitDto retraitDto) {
        if(retraitDto == null){
            return null;
        } return this.modelMapper.map(retraitDto, Retrait.class);
    }

    @Override
    public RetraitDto RetraitToRetraitDto(Retrait retrait) {
        if(retrait == null){
            return null;
        } return this.modelMapper.map(retrait, RetraitDto.class);
    }

    @Override
    public Versement VersementDtoToVersement(VersementDto versementDto) {
        if(versementDto == null){
            return null;
        } return this.modelMapper.map(versementDto, Versement.class);
    }

    @Override
    public VersementDto VersementToVersementDto(Versement versement) {
        if(versement == null){
            return null;
        } return this.modelMapper.map(versement, VersementDto.class);
    }

    @Override
    public Virement VirementDtoToVirement(VirementDto virementDto) {
        if(virementDto == null){
            return null;
        } return this.modelMapper.map(virementDto, Virement.class);
    }

    @Override
    public VirementDto VirementToVirementDto(Virement virement) {
        if(virement == null){
            return null;
        } return this.modelMapper.map(virement, VirementDto.class);
    }


}
