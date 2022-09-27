package ru.netology.geo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GeoServiceImplTest {
    private final GeoServiceImpl geoService = new GeoServiceImpl();

    @Test
    @DisplayName("Тест локального IP")
    void byIPLocal() {

        assertNull(geoService.byIp("127.0.0.1").getCity());
        assertNull(geoService.byIp("127.0.0.1").getCountry());
        assertNull(geoService.byIp("127.0.0.1").getStreet());
        assertEquals(0, geoService.byIp("127.0.0.1").getBuiling());
    }

    @DisplayName("Тест российского IP")
    @Test
    void byIPRussia() {
        assertEquals(Country.RUSSIA, geoService.byIp("172.").getCountry());
    }

    @DisplayName("Тест американского IP")
    @Test
    void shouldBeUSA() {
        assertEquals(Country.USA, geoService.byIp("96.").getCountry());
    }
}
