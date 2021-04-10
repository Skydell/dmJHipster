package fr.polytech.info4.service;

import fr.polytech.info4.domain.Compte;
import fr.polytech.info4.repository.CompteRepository;
import fr.polytech.info4.service.dto.CompteDTO;
import fr.polytech.info4.service.mapper.CompteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Compte}.
 */
@Service
@Transactional
public class CompteService {

    private final Logger log = LoggerFactory.getLogger(CompteService.class);

    private final CompteRepository compteRepository;

    private final CompteMapper compteMapper;

    public CompteService(CompteRepository compteRepository, CompteMapper compteMapper) {
        this.compteRepository = compteRepository;
        this.compteMapper = compteMapper;
    }

    /**
     * Save a compte.
     *
     * @param compteDTO the entity to save.
     * @return the persisted entity.
     */
    public CompteDTO save(CompteDTO compteDTO) {
        log.debug("Request to save Compte : {}", compteDTO);
        Compte compte = compteMapper.toEntity(compteDTO);
        compte = compteRepository.save(compte);
        return compteMapper.toDto(compte);
    }

    /**
     * Get all the comptes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CompteDTO> findAll() {
        log.debug("Request to get all Comptes");
        return compteRepository.findAll().stream()
            .map(compteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the comptes where Restaurant is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<CompteDTO> findAllWhereRestaurantIsNull() {
        log.debug("Request to get all comptes where Restaurant is null");
        return StreamSupport
            .stream(compteRepository.findAll().spliterator(), false)
            .filter(compte -> compte.getRestaurant() == null)
            .map(compteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the comptes where Course is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<CompteDTO> findAllWhereCourseIsNull() {
        log.debug("Request to get all comptes where Course is null");
        return StreamSupport
            .stream(compteRepository.findAll().spliterator(), false)
            .filter(compte -> compte.getCourse() == null)
            .map(compteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one compte by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CompteDTO> findOne(Long id) {
        log.debug("Request to get Compte : {}", id);
        return compteRepository.findById(id)
            .map(compteMapper::toDto);
    }

    /**
     * Delete the compte by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Compte : {}", id);
        compteRepository.deleteById(id);
    }
}
