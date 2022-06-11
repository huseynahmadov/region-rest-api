package az.company.regions.repository;

import az.company.regions.model.Region;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends MongoRepository<Region, String> {

    List<Region> findAllByName(String name);
    Optional<Region> findByName(String name);
}
