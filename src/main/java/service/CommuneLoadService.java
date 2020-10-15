package service;

import entity.Commune;
import entity.CommunePart;

import java.util.List;

public interface CommuneLoadService {
    List<Commune> loadCommunes();
    List<CommunePart> loadCommuneParts();
}
