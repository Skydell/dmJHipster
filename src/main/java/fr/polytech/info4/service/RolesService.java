package fr.polytech.info4.service;

import fr.polytech.info4.domain.Roles;
import fr.polytech.info4.repository.RolesRepository;
import fr.polytech.info4.service.dto.RolesDTO;
import fr.polytech.info4.service.mapper.RolesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Roles}.
 */
@Service
@Transactional
public class RolesService {

    private final Logger log = LoggerFactory.getLogger(RolesService.class);

    private final RolesRepository rolesRepository;

    private final RolesMapper rolesMapper;

    public RolesService(RolesRepository rolesRepository, RolesMapper rolesMapper) {
        this.rolesRepository = rolesRepository;
        this.rolesMapper = rolesMapper;
    }

    /**
     * Save a roles.
     *
     * @param rolesDTO the entity to save.
     * @return the persisted entity.
     */
    public RolesDTO save(RolesDTO rolesDTO) {
        log.debug("Request to save Roles : {}", rolesDTO);
        Roles roles = rolesMapper.toEntity(rolesDTO);
        roles = rolesRepository.save(roles);
        return rolesMapper.toDto(roles);
    }

    /**
     * Get all the roles.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RolesDTO> findAll() {
        log.debug("Request to get all Roles");
        return rolesRepository.findAll().stream()
            .map(rolesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one roles by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RolesDTO> findOne(Long id) {
        log.debug("Request to get Roles : {}", id);
        return rolesRepository.findById(id)
            .map(rolesMapper::toDto);
    }

    /**
     * Delete the roles by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Roles : {}", id);
        rolesRepository.deleteById(id);
    }
}
