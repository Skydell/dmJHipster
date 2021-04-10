package fr.polytech.info4.web.rest;

import fr.polytech.info4.service.CompteService;
import fr.polytech.info4.web.rest.errors.BadRequestAlertException;
import fr.polytech.info4.service.dto.CompteDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link fr.polytech.info4.domain.Compte}.
 */
@RestController
@RequestMapping("/api")
public class CompteResource {

    private final Logger log = LoggerFactory.getLogger(CompteResource.class);

    private static final String ENTITY_NAME = "compte";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CompteService compteService;

    public CompteResource(CompteService compteService) {
        this.compteService = compteService;
    }

    /**
     * {@code POST  /comptes} : Create a new compte.
     *
     * @param compteDTO the compteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new compteDTO, or with status {@code 400 (Bad Request)} if the compte has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/comptes")
    public ResponseEntity<CompteDTO> createCompte(@Valid @RequestBody CompteDTO compteDTO) throws URISyntaxException {
        log.debug("REST request to save Compte : {}", compteDTO);
        if (compteDTO.getId() != null) {
            throw new BadRequestAlertException("A new compte cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CompteDTO result = compteService.save(compteDTO);
        return ResponseEntity.created(new URI("/api/comptes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /comptes} : Updates an existing compte.
     *
     * @param compteDTO the compteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated compteDTO,
     * or with status {@code 400 (Bad Request)} if the compteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the compteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/comptes")
    public ResponseEntity<CompteDTO> updateCompte(@Valid @RequestBody CompteDTO compteDTO) throws URISyntaxException {
        log.debug("REST request to update Compte : {}", compteDTO);
        if (compteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CompteDTO result = compteService.save(compteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, compteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /comptes} : get all the comptes.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of comptes in body.
     */
    @GetMapping("/comptes")
    public List<CompteDTO> getAllComptes(@RequestParam(required = false) String filter) {
        if ("restaurant-is-null".equals(filter)) {
            log.debug("REST request to get all Comptes where restaurant is null");
            return compteService.findAllWhereRestaurantIsNull();
        }
        if ("course-is-null".equals(filter)) {
            log.debug("REST request to get all Comptes where course is null");
            return compteService.findAllWhereCourseIsNull();
        }
        log.debug("REST request to get all Comptes");
        return compteService.findAll();
    }

    /**
     * {@code GET  /comptes/:id} : get the "id" compte.
     *
     * @param id the id of the compteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the compteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/comptes/{id}")
    public ResponseEntity<CompteDTO> getCompte(@PathVariable Long id) {
        log.debug("REST request to get Compte : {}", id);
        Optional<CompteDTO> compteDTO = compteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(compteDTO);
    }

    /**
     * {@code DELETE  /comptes/:id} : delete the "id" compte.
     *
     * @param id the id of the compteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/comptes/{id}")
    public ResponseEntity<Void> deleteCompte(@PathVariable Long id) {
        log.debug("REST request to delete Compte : {}", id);
        compteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
