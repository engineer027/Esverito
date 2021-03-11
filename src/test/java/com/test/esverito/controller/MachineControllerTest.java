package com.test.esverito.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.esverito.model.dto.MachineRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
class MachineControllerTest {
    public static final String MACHINE_ENDPOINT = "/machine";
    @Autowired
    private MockMvc mockMvc;

    @Test
    void create() throws Exception {
        MachineRequestDto dto = new MachineRequestDto();
        dto.setOwner("Owner");
        dto.setCountry("Country");
        dto.setCurrency("Currency");
        dto.setUrl("Url");
        dto.setSource("Source");
        dto.setPrice(3256.0);
        dto.setId(1L);
        dto.setPhotos("Photos");
        dto.setMachineType("Type");
        dto.setMachineInfo("Info");
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(dto);
        mockMvc.perform(
                post(MACHINE_ENDPOINT)
                .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {
        MachineRequestDto dto = new MachineRequestDto();
        String updateEndpoint = MACHINE_ENDPOINT + 1;
        dto.setOwner("Owner");
        dto.setCountry("Country");
        dto.setCurrency("Currency");
        dto.setUrl("Url");
        dto.setSource("Source");
        dto.setPrice(3256.0);
        dto.setId(1L);
        dto.setPhotos("Photos");
        dto.setMachineType("Type");
        dto.setMachineInfo("Info");
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(dto);
        mockMvc.perform(
                post(updateEndpoint)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    void getById() throws Exception {
        String url = MACHINE_ENDPOINT + "/by-id?id=1";
        MachineRequestDto dto = new MachineRequestDto();
        dto.setOwner("Owner");
        dto.setCountry("usa");
        dto.setCurrency("Currency");
        dto.setUrl("Url");
        dto.setSource("Source");
        dto.setPrice(3256.0);
        dto.setId(1L);
        dto.setPhotos("Photos");
        dto.setMachineType("Type");
        dto.setMachineInfo("Info");
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(dto);
        mockMvc.perform(
                post(MACHINE_ENDPOINT)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        mockMvc.perform(
                get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(content));
    }

    @Test
    void findAllByOwner() throws Exception {
        String content = "[]";
        String str = MACHINE_ENDPOINT + "/owner?owner=Eulalia";
        mockMvc.perform(
                get(str)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(content));
    }

    @Test
    void findAllByCountry() throws Exception {
        String str = MACHINE_ENDPOINT + "/country?country=usa";
        MachineRequestDto dto = new MachineRequestDto();
        dto.setOwner("Owner");
        dto.setCountry("usa");
        dto.setCurrency("Currency");
        dto.setUrl("Url");
        dto.setSource("Source");
        dto.setPrice(3256.0);
        dto.setId(1L);
        dto.setPhotos("Photos");
        dto.setMachineType("Type");
        dto.setMachineInfo("Info");
        ObjectMapper mapper = new ObjectMapper();

        String content = mapper.writeValueAsString(dto);
        mockMvc.perform(
                post(MACHINE_ENDPOINT)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        List<MachineRequestDto> dtos = List.of(dto);
        String responseContent = mapper.writeValueAsString(dtos);
        mockMvc.perform(
                get(str)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(responseContent));
    }
}