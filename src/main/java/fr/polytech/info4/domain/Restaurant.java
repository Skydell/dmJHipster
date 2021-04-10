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
 * A Restaurant.
 */
@Entity
@Table(name = "restaurant")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "adress", nullable = false)
    private String adress;

    @Column(name = "products")
    private String products;

    @OneToOne
    @JoinColumn(unique = true)
    private Compte compte;

    @OneToMany(mappedBy = "restaurant")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Produit> produits = new HashSet<>();

    @OneToMany(mappedBy = "restaurant")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Course> courses = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "restaurants", allowSetters = true)
    private Cooperative cooperative;

    @ManyToMany(mappedBy = "restaurants")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Panier> paniers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Restaurant name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public Restaurant adress(String adress) {
        this.adress = adress;
        return this;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getProducts() {
        return products;
    }

    public Restaurant products(String products) {
        this.products = products;
        return this;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public Compte getCompte() {
        return compte;
    }

    public Restaurant compte(Compte compte) {
        this.compte = compte;
        return this;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    public Restaurant produits(Set<Produit> produits) {
        this.produits = produits;
        return this;
    }

    public Restaurant addProduit(Produit produit) {
        this.produits.add(produit);
        produit.setRestaurant(this);
        return this;
    }

    public Restaurant removeProduit(Produit produit) {
        this.produits.remove(produit);
        produit.setRestaurant(null);
        return this;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public Restaurant courses(Set<Course> courses) {
        this.courses = courses;
        return this;
    }

    public Restaurant addCourse(Course course) {
        this.courses.add(course);
        course.setRestaurant(this);
        return this;
    }

    public Restaurant removeCourse(Course course) {
        this.courses.remove(course);
        course.setRestaurant(null);
        return this;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Cooperative getCooperative() {
        return cooperative;
    }

    public Restaurant cooperative(Cooperative cooperative) {
        this.cooperative = cooperative;
        return this;
    }

    public void setCooperative(Cooperative cooperative) {
        this.cooperative = cooperative;
    }

    public Set<Panier> getPaniers() {
        return paniers;
    }

    public Restaurant paniers(Set<Panier> paniers) {
        this.paniers = paniers;
        return this;
    }

    public Restaurant addPanier(Panier panier) {
        this.paniers.add(panier);
        panier.getRestaurants().add(this);
        return this;
    }

    public Restaurant removePanier(Panier panier) {
        this.paniers.remove(panier);
        panier.getRestaurants().remove(this);
        return this;
    }

    public void setPaniers(Set<Panier> paniers) {
        this.paniers = paniers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Restaurant)) {
            return false;
        }
        return id != null && id.equals(((Restaurant) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Restaurant{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", adress='" + getAdress() + "'" +
            ", products='" + getProducts() + "'" +
            "}";
    }
}
