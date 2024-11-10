package cal335.lab.service;

import cal335.lab.dto.TacheDTO;

import java.util.List;
import java.util.Map;

public interface ITacheService {

    List<TacheDTO> rechercherTaches();

    List<TacheDTO> rechercherTachesSelonEtat(boolean aFaire);

    void ajouterTache(TacheDTO tache);
}
//