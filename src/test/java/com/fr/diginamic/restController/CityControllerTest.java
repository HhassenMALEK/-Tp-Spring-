package com.fr.diginamic.restController;


//import org.junit.jupiter.api.Test;
//
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.List;
//
//
//@SpringBootTest
//@ActiveProfiles("test")
//@AutoConfigureMockMvc
//public class CityControllerTest {
//    @MockitoBean
//    private CityService cityService;
//
//    @Autowired
//    MockMvc mockMvc;
//
//    private final List<Department> departments = List.of(
//            new Department(1L, "Nord", "59"),
//            new Department(2L, "Pas-de-Calais", "62")
//    );
//
//    private final List<City> cities = List.of(
//            new City(10001L, "Lille", 232440, departments.get(0)),
//            new City(10002L, "Roubaix", 98239, departments.get(0)),
//            new City(10003L, "Calais", 74530, departments.get(1))
//    );
//
//    @Test
//    public void TestGetAllCities() throws Exception {
//
//        Mockito.when(cityService.getAllCities()).thenReturn(List.of(cities));
//        this.mockMvc.perform(mockMvcRequestBuilders.get(""))
//
//    }
//}
