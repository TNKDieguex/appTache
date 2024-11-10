package cal335.lab;

import cal335.lab.controleur.TacheControleur;
import cal335.lab.dto.TacheDTO;
import cal335.lab.modele.Tache;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class TacheControleurTest {

    private TacheControleur controleur;
    private FakeTacheService serviceFactice;

    @BeforeEach
    public void setUp() {
        // Injection de la version factice de TacheService
        serviceFactice = new FakeTacheService();
        controleur = new TacheControleur(serviceFactice);
    }

    @Test
    public void testRechercherTaches() throws IOException {
        // Arrange
        HttpExchange echangeFactice = mock(HttpExchange.class);
        // Prepare URI et endpoint
        when(echangeFactice.getRequestURI()).thenReturn(URI.create("/taches"));
        when(echangeFactice.getRequestMethod()).thenReturn("GET");

        // Entete factice pour les réponses
        Headers reponseHeadersFactices = new Headers();
        when(echangeFactice.getResponseHeaders()).thenReturn(reponseHeadersFactices);

        // Outputstream pour attraper la reponse
        ByteArrayOutputStream corpReponse = new ByteArrayOutputStream();
        when(echangeFactice.getResponseBody()).thenReturn(corpReponse);

        // Act
        controleur.handle(echangeFactice);

        // Assert
        verify(echangeFactice).sendResponseHeaders(eq(200), anyLong());

        ObjectMapper objectMapper = new ObjectMapper();
        List<TacheDTO> tachesAttendues = serviceFactice.rechercherTaches();
        String jsonAttendu = objectMapper.writeValueAsString(tachesAttendues);

        // Vérificiation
        assertEquals(jsonAttendu, corpReponse.toString(StandardCharsets.UTF_8));

        corpReponse.close();
    }
}
