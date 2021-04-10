package fr.polytech.info4.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RolesMapperTest {

    private RolesMapper rolesMapper;

    @BeforeEach
    public void setUp() {
        rolesMapper = new RolesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(rolesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(rolesMapper.fromId(null)).isNull();
    }
}
