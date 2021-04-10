package fr.polytech.info4.service.mapper;


import fr.polytech.info4.domain.*;
import fr.polytech.info4.service.dto.CompteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Compte} and its DTO {@link CompteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CompteMapper extends EntityMapper<CompteDTO, Compte> {


    @Mapping(target = "paniers", ignore = true)
    @Mapping(target = "removePanier", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "systemePaiements", ignore = true)
    @Mapping(target = "removeSystemePaiement", ignore = true)
    Compte toEntity(CompteDTO compteDTO);

    default Compte fromId(Long id) {
        if (id == null) {
            return null;
        }
        Compte compte = new Compte();
        compte.setId(id);
        return compte;
    }
}
