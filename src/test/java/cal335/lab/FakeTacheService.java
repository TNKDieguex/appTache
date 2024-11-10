package cal335.lab;

import cal335.lab.dto.TacheDTO;
import cal335.lab.modele.Tache;
import cal335.lab.service.ITacheService;
import cal335.lab.service.TacheService;

import java.util.List;

public class FakeTacheService implements ITacheService /*extends TacheService*/ {

    @Override
    public List<TacheDTO> rechercherTaches() {

        return List.of(
                new TacheDTO("Lessive", "Je n'ai plus de chaussettes propres"),
                new TacheDTO("Épicerie", "Quoi faire à manger pour samedi ?"));
    }

    @Override
    public void ajouterTache(TacheDTO tache) {
        System.out.println("GO ALI");
    }
}
