package cal335.lab.service;

import cal335.lab.dto.TacheDTO;
import cal335.lab.mapper.TacheMapper;
import cal335.lab.modele.Tache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TacheService implements ITacheService {

    private final Map<Boolean, List<Tache>> mapDesTaches = new HashMap<>();

    public List<TacheDTO> rechercherTaches() {
        List<TacheDTO> result = new ArrayList<>();

        List<Tache> tachesAFaire = this.mapDesTaches.get(true);
        List<TacheDTO> lesFaitesDTO = new ArrayList<>();
        lesFaitesDTO = TacheMapper.versDTOs(tachesAFaire);
        result.addAll(lesFaitesDTO);

        List<Tache> tachesFaites = this.mapDesTaches.get(false);
        List<TacheDTO> tachesFaitesDTO = new ArrayList<>();
        tachesFaitesDTO = TacheMapper.versDTOs(tachesFaites);
        result.addAll(tachesFaitesDTO);

        return result;

    }

    public List<TacheDTO> rechercherTachesSelonEtat(boolean aFaire) {
        return new ArrayList<>(TacheMapper.versDTOs(this.mapDesTaches.get(aFaire)));
    }

    public void ajouterTache(TacheDTO tacheDTO) {
        if (!mapDesTaches.containsKey(tacheDTO.isaFaire())) {
            mapDesTaches.put(tacheDTO.isaFaire(), new ArrayList<>());
        }
        Tache tache = TacheMapper.convertirDtoVersModele(tacheDTO);
        List<Tache> mesTachesExistantes = mapDesTaches.get(tache.isaFaire());
        mesTachesExistantes.add(tache);
        // ou
        // mapDesTaches.get(tache.getaFaire()).add(tache);
    }

}
