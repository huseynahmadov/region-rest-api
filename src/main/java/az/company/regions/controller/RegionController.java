package az.company.regions.controller;

import az.company.regions.model.Region;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionController {

    private static final List<Region> regions = new ArrayList<>();

    public RegionController() {
        if (regions.isEmpty()) {
            Region r1 = new Region("99", "Baku");
            Region r2 = new Region("21", "Gadabay");

            regions.add(r1);
            regions.add(r2);
        }
    }

    @GetMapping
    public ResponseEntity<List<Region>> getAllRegions() {

        return new ResponseEntity<>(regions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegion(@PathVariable String id) {

       Region result = getRegionById(id);

       return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Region> createRegion(@RequestBody Region newRegion) {
        regions.add(newRegion);

        return new ResponseEntity<>(newRegion, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateRegion(@PathVariable String id, @RequestBody Region newRegion) {
        Region oldRegion = getRegionById(id);
        oldRegion.setName(newRegion.getName());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable String id) {
        Region region = getRegionById(id);
        regions.remove(region);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Region getRegionById(String id) {
        return regions.stream().filter(region -> region.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Region was not found!"));
    }

}
