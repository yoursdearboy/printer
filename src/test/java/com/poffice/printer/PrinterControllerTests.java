package com.poffice.printer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PrinterControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void responds() throws Exception {
        mockMvc.perform(post("/"))
   	       .andExpect(status().isOk())
	       .andExpect(content().string("Hello, World!"));
    }
}
