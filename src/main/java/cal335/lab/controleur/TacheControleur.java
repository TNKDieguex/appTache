package cal335.lab.controleur;

import cal335.lab.dto.TacheDTO;
import cal335.lab.service.ITacheService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TacheControleur implements HttpHandler {

    private final ITacheService tacheService;
    private final ObjectMapper objectMapper;

    public TacheControleur(ITacheService tacheService) {
        this.tacheService = tacheService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void handle(HttpExchange echange) throws IOException {
        String methode = echange.getRequestMethod();

        if ("GET".equals(methode) && echange.getRequestURI().getPath().equals("/taches/etat")) {
            this.rechercherTachesSelonEtat(echange);
        } else if ("GET".equals(methode) && echange.getRequestURI().getPath().equals("/taches")) {
            this.rechercherTaches(echange);
        }else if ("POST".equals(methode) && echange.getRequestURI().getPath().equals("/taches")) {
            this.ajouterTache(echange);
        } else {
            echange.sendResponseHeaders(404, -1);
        }
    }

    private void rechercherTaches(HttpExchange echange) throws IOException {

        List<TacheDTO> tacheDTOS = tacheService.rechercherTaches();
        String objetsSerialises = objectMapper.writeValueAsString(tacheDTOS);
        echange.getResponseHeaders().set("Content-Type", "application/json");
        echange.sendResponseHeaders(200, objetsSerialises.getBytes(StandardCharsets.UTF_8).length);
        try (OutputStream os = echange.getResponseBody()) {
            os.write(objetsSerialises.getBytes(StandardCharsets.UTF_8));
        }
    }

    private void rechercherTachesSelonEtat(HttpExchange echange) throws IOException {
        boolean tachesAFaire = retrouveIndicateurEtat(echange);
        List<TacheDTO> taches = tacheService.rechercherTachesSelonEtat(tachesAFaire);

        String objetsSerialises = objectMapper.writeValueAsString(taches);
        echange.getResponseHeaders().set("Content-Type", "application/json");
        echange.sendResponseHeaders(200, objetsSerialises.getBytes(StandardCharsets.UTF_8).length);
        try (OutputStream os = echange.getResponseBody()) {
            os.write(objetsSerialises.getBytes(StandardCharsets.UTF_8));
        }
    }

    private boolean retrouveIndicateurEtat(HttpExchange echange) throws IOException {
        boolean indicateurTrouve = false;
        boolean tachesDejaFaites = false;

        String requete = echange.getRequestURI().getQuery();
        if (requete != null) {
            // Avec un boolean dans un paramètre
            Map<String, String> paramsRequete = convertirParametresEnMap(requete);
            if (paramsRequete.containsKey("aFaire")) {
                indicateurTrouve = true;
                tachesDejaFaites = Boolean.parseBoolean(paramsRequete.get("aFaire"));
            }
        }
        if (!indicateurTrouve) {
            // Avec un boolean dans dans le corp de la requete
            tachesDejaFaites = booleanAPartirDuCorpDeRequete(echange);
        }
        return tachesDejaFaites;
    }

    private static Map<String, String> convertirParametresEnMap(String requete) {
        Map<String, String> mapParametres = new HashMap<>();
        String[] paires = requete.split("&");
        for (String paire : paires) {
            String[] cleValeur = paire.split("=");
            if (cleValeur.length == 2) {
                mapParametres.put(cleValeur[0], cleValeur[1]);
            }
        }
        return mapParametres;
    }

    private boolean booleanAPartirDuCorpDeRequete(HttpExchange echange) throws IOException {
        boolean tachesDejaFaites = false;
        if (echange.getRequestBody().available() > 0) {
            Map<String, Boolean> paramsCorpRequete = objectMapper.readValue(echange.getRequestBody(), Map.class);
            if (paramsCorpRequete.containsKey("fait")) {
                tachesDejaFaites = paramsCorpRequete.get("fait");
            }
        }
        return tachesDejaFaites;
    }


    private void ajouterTache(HttpExchange echange) throws IOException {
        TacheDTO tacheDTO = objectMapper.readValue(echange.getRequestBody(), TacheDTO.class);
        tacheService.ajouterTache(tacheDTO);
        echange.sendResponseHeaders(201, -1);
    }

}

