package fr.polytech.info4.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.polytech.info4.domain.Roles} entity.
 */
public class RolesDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(min = 1)
    private String role;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RolesDTO)) {
            return false;
        }

        return id != null && id.equals(((RolesDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RolesDTO{" +
            "id=" + getId() +
            ", role='" + getRole() + "'" +
            "}";
    }
}
