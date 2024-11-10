package cal335.lab;

import cal335.lab.controleur.TacheControleur;
import cal335.lab.service.FakeTacheService;
import cal335.lab.service.ITacheService;
import cal335.lab.service.TacheService;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ApplicationTacheServeur {
    public static void main(String[] args) throws IOException {

        HttpServer serveur = HttpServer.create(new InetSocketAddress(8080), 0);

        ITacheService tacheService = new TacheService();

        TacheControleur controleur = new TacheControleur(tacheService);

        serveur.createContext("/taches", controleur);

        serveur.setExecutor(null);

        serveur.start();

        System.out.println("Serveur démarré sur le port 8080");
    }
}