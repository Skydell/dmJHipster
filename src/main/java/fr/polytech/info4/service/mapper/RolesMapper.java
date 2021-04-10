package fr.polytech.info4.service.mapper;


import fr.polytech.info4.domain.*;
import fr.polytech.info4.service.dto.RolesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Roles} and its DTO {@link RolesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RolesMapper extends EntityMapper<RolesDTO, Roles> {



    default Roles fromId(Long id) {
        if (id == null) {
            return null;
        }
        Roles roles = new Roles();
        roles.setId(id);
        return roles;
    }
}
