package az.company.regions.service;

import az.company.regions.exception.RegionAlreadyExistsException;
import az.company.regions.exception.RegionNotFoundException;
import az.company.regions.model.Region;
import az.company.regions.repository.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        Optional<Region> regionByName = regionRepository.findByName(newRegion.getName());
        if (regionByName.isPresent()) {
            throw new RegionAlreadyExistsException("Region already exists with name:" + newRegion.getName());
        }
        return regionRepository.save(newRegion);
    }

    public void deleteRegion(String id) {
        regionRepository.deleteById(id);
    }

    public Region getRegionById(String id) {
        return regionRepository.findById(id)
                .orElseThrow(() -> new RegionNotFoundException("Region not found with id:" + id));
    }

    public void updateRegion(String id, Region newRegion) {
        Region oldRegion = getRegionById(id);
        oldRegion.setName(newRegion.getName());
        regionRepository.save(oldRegion);
    }
}
