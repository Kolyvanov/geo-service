package ru.netology.sender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;
import static ru.netology.sender.MessageSenderImpl.IP_ADDRESS_HEADER;

@ExtendWith(MockitoExtension.class)
public class MessageSenderImplTest {
    @Mock
    private GeoService geoService;

    @Mock
    private LocalizationService localizationService;

    private MessageSenderImpl messageSenderImpl;


    @BeforeEach
    void setUp() {
        geoService = Mockito.mock(GeoService.class);
        localizationService = Mockito.mock(LocalizationService.class);
        messageSenderImpl = new MessageSenderImpl(geoService, localizationService);
        System.out.println();
    }

    @Test
    @DisplayName("Тест российского сегмента IP")
    void shouldBeRussia() {
        Mockito.when(geoService.byIp("172.")).thenReturn(new Location("Moscow", RUSSIA, null, 0));
        Mockito.when(localizationService.locale(RUSSIA)).thenReturn("Добро пожаловать");
        Map<String, String> headers = Map.of(IP_ADDRESS_HEADER, "172.");
        assertEquals("Добро пожаловать", messageSenderImpl.send(headers));
    }

    @Test
    @DisplayName("Тест американского сегмента IP")
    void shouldBeEnglish() {
        Mockito.when(geoService.byIp("96.")).thenReturn(new Location("New York", USA, null, 0));
        Mockito.when(localizationService.locale(USA)).thenReturn("Welcome");
        Map<String, String> headers = Map.of(IP_ADDRESS_HEADER, "96.");
        assertEquals("Welcome", messageSenderImpl.send(headers));
    }

}
