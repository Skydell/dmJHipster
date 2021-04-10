package fr.polytech.info4.web.rest;

import fr.polytech.info4.DmJHipsterApp;
import fr.polytech.info4.domain.SystemePaiement;
import fr.polytech.info4.repository.SystemePaiementRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SystemePaiementResource} REST controller.
 */
@SpringBootTest(classes = DmJHipsterApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class SystemePaiementResourceIT {

    private static final String DEFAULT_CREDITEUR = "AAAAAAAAAA";
    private static final String UPDATED_CREDITEUR = "BBBBBBBBBB";

    private static final String DEFAULT_DEBITEUR = "AAAAAAAAAA";
    private static final String UPDATED_DEBITEUR = "BBBBBBBBBB";

    private static final String DEFAULT_METHODE = "AAAAAAAAAA";
    private static final String UPDATED_METHODE = "BBBBBBBBBB";

    @Autowired
    private SystemePaiementRepository systemePaiementRepository;

    @Mock
    private SystemePaiementRepository systemePaiementRepositoryMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSystemePaiementMockMvc;

    private SystemePaiement systemePaiement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SystemePaiement createEntity(EntityManager em) {
        SystemePaiement systemePaiement = new SystemePaiement()
            .crediteur(DEFAULT_CREDITEUR)
            .debiteur(DEFAULT_DEBITEUR)
            .methode(DEFAULT_METHODE);
        return systemePaiement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SystemePaiement createUpdatedEntity(EntityManager em) {
        SystemePaiement systemePaiement = new SystemePaiement()
            .crediteur(UPDATED_CREDITEUR)
            .debiteur(UPDATED_DEBITEUR)
            .methode(UPDATED_METHODE);
        return systemePaiement;
    }

    @BeforeEach
    public void initTest() {
        systemePaiement = createEntity(em);
    }

    @Test
    @Transactional
    public void createSystemePaiement() throws Exception {
        int databaseSizeBeforeCreate = systemePaiementRepository.findAll().size();
        // Create the SystemePaiement
        restSystemePaiementMockMvc.perform(post("/api/systeme-paiements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(systemePaiement)))
            .andExpect(status().isCreated());

        // Validate the SystemePaiement in the database
        List<SystemePaiement> systemePaiementList = systemePaiementRepository.findAll();
        assertThat(systemePaiementList).hasSize(databaseSizeBeforeCreate + 1);
        SystemePaiement testSystemePaiement = systemePaiementList.get(systemePaiementList.size() - 1);
        assertThat(testSystemePaiement.getCrediteur()).isEqualTo(DEFAULT_CREDITEUR);
        assertThat(testSystemePaiement.getDebiteur()).isEqualTo(DEFAULT_DEBITEUR);
        assertThat(testSystemePaiement.getMethode()).isEqualTo(DEFAULT_METHODE);
    }

    @Test
    @Transactional
    public void createSystemePaiementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = systemePaiementRepository.findAll().size();

        // Create the SystemePaiement with an existing ID
        systemePaiement.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSystemePaiementMockMvc.perform(post("/api/systeme-paiements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(systemePaiement)))
            .andExpect(status().isBadRequest());

        // Validate the SystemePaiement in the database
        List<SystemePaiement> systemePaiementList = systemePaiementRepository.findAll();
        assertThat(systemePaiementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCrediteurIsRequired() throws Exception {
        int databaseSizeBeforeTest = systemePaiementRepository.findAll().size();
        // set the field null
        systemePaiement.setCrediteur(null);

        // Create the SystemePaiement, which fails.


        restSystemePaiementMockMvc.perform(post("/api/systeme-paiements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(systemePaiement)))
            .andExpect(status().isBadRequest());

        List<SystemePaiement> systemePaiementList = systemePaiementRepository.findAll();
        assertThat(systemePaiementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDebiteurIsRequired() throws Exception {
        int databaseSizeBeforeTest = systemePaiementRepository.findAll().size();
        // set the field null
        systemePaiement.setDebiteur(null);

        // Create the SystemePaiement, which fails.


        restSystemePaiementMockMvc.perform(post("/api/systeme-paiements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(systemePaiement)))
            .andExpect(status().isBadRequest());

        List<SystemePaiement> systemePaiementList = systemePaiementRepository.findAll();
        assertThat(systemePaiementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMethodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = systemePaiementRepository.findAll().size();
        // set the field null
        systemePaiement.setMethode(null);

        // Create the SystemePaiement, which fails.


        restSystemePaiementMockMvc.perform(post("/api/systeme-paiements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(systemePaiement)))
            .andExpect(status().isBadRequest());

        List<SystemePaiement> systemePaiementList = systemePaiementRepository.findAll();
        assertThat(systemePaiementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSystemePaiements() throws Exception {
        // Initialize the database
        systemePaiementRepository.saveAndFlush(systemePaiement);

        // Get all the systemePaiementList
        restSystemePaiementMockMvc.perform(get("/api/systeme-paiements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(systemePaiement.getId().intValue())))
            .andExpect(jsonPath("$.[*].crediteur").value(hasItem(DEFAULT_CREDITEUR)))
            .andExpect(jsonPath("$.[*].debiteur").value(hasItem(DEFAULT_DEBITEUR)))
            .andExpect(jsonPath("$.[*].methode").value(hasItem(DEFAULT_METHODE)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllSystemePaiementsWithEagerRelationshipsIsEnabled() throws Exception {
        when(systemePaiementRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restSystemePaiementMockMvc.perform(get("/api/systeme-paiements?eagerload=true"))
            .andExpect(status().isOk());

        verify(systemePaiementRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllSystemePaiementsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(systemePaiementRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restSystemePaiementMockMvc.perform(get("/api/systeme-paiements?eagerload=true"))
            .andExpect(status().isOk());

        verify(systemePaiementRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getSystemePaiement() throws Exception {
        // Initialize the database
        systemePaiementRepository.saveAndFlush(systemePaiement);

        // Get the systemePaiement
        restSystemePaiementMockMvc.perform(get("/api/systeme-paiements/{id}", systemePaiement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(systemePaiement.getId().intValue()))
            .andExpect(jsonPath("$.crediteur").value(DEFAULT_CREDITEUR))
            .andExpect(jsonPath("$.debiteur").value(DEFAULT_DEBITEUR))
            .andExpect(jsonPath("$.methode").value(DEFAULT_METHODE));
    }
    @Test
    @Transactional
    public void getNonExistingSystemePaiement() throws Exception {
        // Get the systemePaiement
        restSystemePaiementMockMvc.perform(get("/api/systeme-paiements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSystemePaiement() throws Exception {
        // Initialize the database
        systemePaiementRepository.saveAndFlush(systemePaiement);

        int databaseSizeBeforeUpdate = systemePaiementRepository.findAll().size();

        // Update the systemePaiement
        SystemePaiement updatedSystemePaiement = systemePaiementRepository.findById(systemePaiement.getId()).get();
        // Disconnect from session so that the updates on updatedSystemePaiement are not directly saved in db
        em.detach(updatedSystemePaiement);
        updatedSystemePaiement
            .crediteur(UPDATED_CREDITEUR)
            .debiteur(UPDATED_DEBITEUR)
            .methode(UPDATED_METHODE);

        restSystemePaiementMockMvc.perform(put("/api/systeme-paiements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSystemePaiement)))
            .andExpect(status().isOk());

        // Validate the SystemePaiement in the database
        List<SystemePaiement> systemePaiementList = systemePaiementRepository.findAll();
        assertThat(systemePaiementList).hasSize(databaseSizeBeforeUpdate);
        SystemePaiement testSystemePaiement = systemePaiementList.get(systemePaiementList.size() - 1);
        assertThat(testSystemePaiement.getCrediteur()).isEqualTo(UPDATED_CREDITEUR);
        assertThat(testSystemePaiement.getDebiteur()).isEqualTo(UPDATED_DEBITEUR);
        assertThat(testSystemePaiement.getMethode()).isEqualTo(UPDATED_METHODE);
    }

    @Test
    @Transactional
    public void updateNonExistingSystemePaiement() throws Exception {
        int databaseSizeBeforeUpdate = systemePaiementRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSystemePaiementMockMvc.perform(put("/api/systeme-paiements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(systemePaiement)))
            .andExpect(status().isBadRequest());

        // Validate the SystemePaiement in the database
        List<SystemePaiement> systemePaiementList = systemePaiementRepository.findAll();
        assertThat(systemePaiementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSystemePaiement() throws Exception {
        // Initialize the database
        systemePaiementRepository.saveAndFlush(systemePaiement);

        int databaseSizeBeforeDelete = systemePaiementRepository.findAll().size();

        // Delete the systemePaiement
        restSystemePaiementMockMvc.perform(delete("/api/systeme-paiements/{id}", systemePaiement.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SystemePaiement> systemePaiementList = systemePaiementRepository.findAll();
        assertThat(systemePaiementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
