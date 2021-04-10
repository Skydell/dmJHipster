package fr.polytech.info4.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Panier.
 */
@Entity
@Table(name = "panier")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Panier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nbelements", nullable = false)
    private Integer nbelements;

    @Column(name = "listelements")
    private String listelements;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "panier_restaurant",
               joinColumns = @JoinColumn(name = "panier_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "restaurant_id", referencedColumnName = "id"))
    private Set<Restaurant> restaurants = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "panier_produit",
               joinColumns = @JoinColumn(name = "panier_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "produit_id", referencedColumnName = "id"))
    private Set<Produit> produits = new HashSet<>();

    @OneToOne(mappedBy = "panier")
    @JsonIgnore
    private Course course;

    @ManyToOne
    @JsonIgnoreProperties(value = "paniers", allowSetters = true)
    private Compte compte;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNbelements() {
        return nbelements;
    }

    public Panier nbelements(Integer nbelements) {
        this.nbelements = nbelements;
        return this;
    }

    public void setNbelements(Integer nbelements) {
        this.nbelements = nbelements;
    }

    public String getListelements() {
        return listelements;
    }

    public Panier listelements(String listelements) {
        this.listelements = listelements;
        return this;
    }

    public void setListelements(String listelements) {
        this.listelements = listelements;
    }

    public Integer getPrice() {
        return price;
    }

    public Panier price(Integer price) {
        this.price = price;
        return this;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public Panier restaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
        return this;
    }

    public Panier addRestaurant(Restaurant restaurant) {
        this.restaurants.add(restaurant);
        restaurant.getPaniers().add(this);
        return this;
    }

    public Panier removeRestaurant(Restaurant restaurant) {
        this.restaurants.remove(restaurant);
        restaurant.getPaniers().remove(this);
        return this;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    public Panier produits(Set<Produit> produits) {
        this.produits = produits;
        return this;
    }

    public Panier addProduit(Produit produit) {
        this.produits.add(produit);
        produit.getPaniers().add(this);
        return this;
    }

    public Panier removeProduit(Produit produit) {
        this.produits.remove(produit);
        produit.getPaniers().remove(this);
        return this;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    public Course getCourse() {
        return course;
    }

    public Panier course(Course course) {
        this.course = course;
        return this;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Compte getCompte() {
        return compte;
    }

    public Panier compte(Compte compte) {
        this.compte = compte;
        return this;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Panier)) {
            return false;
        }
        return id != null && id.equals(((Panier) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Panier{" +
            "id=" + getId() +
            ", nbelements=" + getNbelements() +
            ", listelements='" + getListelements() + "'" +
            ", price=" + getPrice() +
            "}";
    }
}
