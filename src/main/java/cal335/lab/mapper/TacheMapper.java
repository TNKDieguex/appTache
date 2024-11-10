package cal335.lab.mapper;

import cal335.lab.dto.TacheDTO;
import cal335.lab.modele.Tache;

import java.util.ArrayList;
import java.util.List;

public class TacheMapper {

    public static Tache convertirDtoVersModele(TacheDTO tacheDTO) {
        if (tacheDTO == null) {
            return null;
        }
        return new Tache(tacheDTO.getNom(), tacheDTO.getDescription(), tacheDTO.isaFaire());
    }

    public static List<Tache> versEntites(List<TacheDTO> tachesDTOs) {
        if (tachesDTOs == null || tachesDTOs.isEmpty()) {
            return new ArrayList<>();
        }

        List<Tache> taches = new ArrayList<>();
        for (TacheDTO tacheDTO : tachesDTOs) {
            taches.add(convertirDtoVersModele(tacheDTO));
        }
       return taches;
    }

    public static TacheDTO versDTO(Tache tache) {
        if (tache == null) {
            return null;
        }

        return new TacheDTO(tache.getNom(), tache.getDescription(), tache.isaFaire());
    }

    public static List<TacheDTO> versDTOs(List<Tache> taches) {
        if (taches == null || taches.isEmpty()) {
            return new ArrayList<>();
        }
        List<TacheDTO> tachesDTOs = new ArrayList<>();
        for (Tache tache : taches) {
            tachesDTOs.add(versDTO(tache));
        }
        return tachesDTOs;
    }

}
