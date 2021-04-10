package fr.polytech.info4.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Course.
 */
@Entity
@Table(name = "course")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "clientname", nullable = false)
    private String clientname;

    @NotNull
    @Column(name = "coursiername", nullable = false)
    private String coursiername;

    @NotNull
    @Column(name = "timerequired", nullable = false)
    private Integer timerequired;

    @OneToOne
    @JoinColumn(unique = true)
    private Panier panier;

    @OneToOne
    @JoinColumn(unique = true)
    private Compte compte;

    @ManyToOne
    @JsonIgnoreProperties(value = "courses", allowSetters = true)
    private Restaurant restaurant;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientname() {
        return clientname;
    }

    public Course clientname(String clientname) {
        this.clientname = clientname;
        return this;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getCoursiername() {
        return coursiername;
    }

    public Course coursiername(String coursiername) {
        this.coursiername = coursiername;
        return this;
    }

    public void setCoursiername(String coursiername) {
        this.coursiername = coursiername;
    }

    public Integer getTimerequired() {
        return timerequired;
    }

    public Course timerequired(Integer timerequired) {
        this.timerequired = timerequired;
        return this;
    }

    public void setTimerequired(Integer timerequired) {
        this.timerequired = timerequired;
    }

    public Panier getPanier() {
        return panier;
    }

    public Course panier(Panier panier) {
        this.panier = panier;
        return this;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public Compte getCompte() {
        return compte;
    }

    public Course compte(Compte compte) {
        this.compte = compte;
        return this;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Course restaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        return this;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Course)) {
            return false;
        }
        return id != null && id.equals(((Course) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Course{" +
            "id=" + getId() +
            ", clientname='" + getClientname() + "'" +
            ", coursiername='" + getCoursiername() + "'" +
            ", timerequired=" + getTimerequired() +
            "}";
    }
}
