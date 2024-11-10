package cal335.lab.service;

import cal335.lab.dto.TacheDTO;

import java.util.List;

public class FakeTacheService implements ITacheService /*extends TacheService*/ {

    @Override
    public List<TacheDTO> rechercherTaches() {

        return List.of();
//                new TacheDTO("Lessive", "Je n'ai plus de chaussettes propres"),
//                new TacheDTO("Épicerie", "Quoi faire à manger pour samedi ?"));
    }

    @Override
    public List<TacheDTO> rechercherTachesSelonEtat(boolean fait) {
        return List.of();
    }

    @Override
    public void ajouterTache(TacheDTO tache) {
        System.out.println("GO BENJAMIN");
    }
}
