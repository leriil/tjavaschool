package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.Role;
import com.tsystems.tshop.repositories.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("RoleService")
@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    private RoleService roleService;
    @Mock
    private RoleRepository roleRepository;
    private Role role;
    private String name;


    @BeforeEach
    void init() {

        MockitoAnnotations.initMocks(roleRepository);

        roleService = new RoleService(roleRepository);
        name = "bill";
        role = new Role();
        role.setRoleId(1L);
        role.setName("CLIENT");

    }

    @Test
    void givenValidName_WhenGetRole_ThenSucceed() {

        when(roleRepository.getByName(role.getName())).thenReturn(role);
        roleService.getRoleByName(role.getName());
        Mockito.verify(roleRepository).getByName(role.getName());

    }

}
