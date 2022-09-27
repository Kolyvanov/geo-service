package ru.netology.i18n;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceImplTest {
    private final LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

    @Test
    @DisplayName("Тест сообщения  при российском IP")
    void localeRUSSIA() {
        assertEquals("Добро пожаловать", localizationService.locale(Country.RUSSIA));
    }

    @Test
    @DisplayName("Тест сообщения  при зарубежном IP")
    void localeForeign() {
        assertEquals("Welcome", localizationService.locale(Country.BRAZIL));
        assertEquals("Welcome", localizationService.locale(Country.GERMANY));
        assertEquals("Welcome", localizationService.locale(Country.USA));
    }
}
