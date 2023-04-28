package frey.house.ega.mappers;

import frey.house.ega.controller.VirementController;
import frey.house.ega.dto.*;
import frey.house.ega.model.*;

public interface EntityMapper {

    Client ClientDtoToClient(ClientDto clientDto);
    ClientDto ClientToClientDto(Client client);

    //Compte
    Compte CompteDtoToCompte(CompteDto compteDto);
    CompteDto CompteToCompteDto(Compte compte);

    Retrait RetraitDtoToRetrait(RetraitDto retraitDto);
    RetraitDto RetraitToRetraitDto(Retrait retrait);

    Versement VersementDtoToVersement(VersementDto versementDto);
    VersementDto VersementToVersementDto(Versement versement);

    Virement VirementDtoToVirement(VirementDto virementDto);
    VirementDto VirementToVirementDto(Virement virement);
}
