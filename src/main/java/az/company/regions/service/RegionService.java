package az.company.regions.service;

import az.company.regions.model.Region;
import az.company.regions.repository.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;
    public List<Region> getAllRegions(String name) {

        if (name == null) {
            return regionRepository.findAll();
        }
        else {
            return regionRepository.findAllByName(name);
        }
    }

    public Region createRegion(Region newRegion) {
        return regionRepository.save(newRegion);
    }

    public void deleteRegion(String id) {
        regionRepository.deleteById(id);
    }

    public Region getRegionById(String id) {
        return regionRepository.findById(id).orElseThrow(() -> new RuntimeException("Error"));
    }

    public void updateRegion(String id, Region newRegion) {
        Region oldRegion = getRegionById(id);
        oldRegion.setName(newRegion.getName());
        regionRepository.save(oldRegion);
    }
}
