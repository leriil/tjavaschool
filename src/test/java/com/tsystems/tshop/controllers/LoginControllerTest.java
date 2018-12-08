package com.tsystems.tshop.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@DisplayName("LoginControllerTest")
@ExtendWith(MockitoExtension.class)
//@ExtendWith({SpringExtension.class})
//@ContextConfiguration("classpath:test-application-context.xml")
class LoginControllerTest {

    MockMvc mockMvc;

    @BeforeEach
    public void init() {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        this.mockMvc = MockMvcBuilders.standaloneSetup(LoginController.class).setViewResolvers(viewResolver).build();
    }

    @Test
    void givenLoginUrl_whenMockMvc_thenReturnLoginJSPViewName() throws Exception {

        this.mockMvc.perform(get("/login")).andExpect(view().name("login"));

    }

//    @Test
//    public void givenHomePageURI_whenMockMVC_thenReturnsIndexJSPViewName() throws Exception {
//        this.mockMvc.perform(get("/homePage")).andExpect(view().name("index"));
//    }
}