package it.mobileapp.app.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
public class BusinessUserTest {

    @Autowired
    private MockMvc mockMvc;

    private String id;
    private String jwt;

    @Before
    public void getJwt() throws Exception {
        /*MvcResult result = this.mockMvc.perform(
                post("/auth/login")
                        .content("{\"username\":\"John\",\"password\":\"1234\"}").
                        contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        jwt = JsonPath.read(result.getResponse().getContentAsString(), "$.token");*/
    }

    @Test
    public void test() throws Exception {
        /*MvcResult result = this.mockMvc.perform(
                 post("/user/create").header("Authorization", "Bearer " + jwt)
                .content("{\"firstName\":\"TEST\", \"lastName\":\"TEST\", \"fiscalCode\":\"TEST\"}").
                         contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().is2xxSuccessful()).andReturn();
        id = JsonPath.read(result.getResponse().getContentAsString(), "$.id").toString();

        result = this.mockMvc.perform(
                get("/user/findById/"+id).header("Authorization", "Bearer " + jwt))
                .andExpect(status().is2xxSuccessful()).andReturn();
        id = JsonPath.read(result.getResponse().getContentAsString(), "$.id").toString();

        result = this.mockMvc.perform(
                put("/user/update").header("Authorization", "Bearer " + jwt)
                        .content("{\"id\":\""+id+"\", \"firstName\":\"TEST2\", \"lastName\":\"TEST2\", \"fiscalCode\":\"TEST2\"}").
                        contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().is2xxSuccessful()).andReturn();
        id = JsonPath.read(result.getResponse().getContentAsString(), "$.id").toString();

        this.mockMvc.perform(
                delete("/user/delete/"+id).header("Authorization", "Bearer " + jwt))
                .andExpect(status().is2xxSuccessful());*/
    }
}
