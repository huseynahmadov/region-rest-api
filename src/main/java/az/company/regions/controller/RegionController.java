package az.company.regions.controller;

import az.company.regions.exception.RegionAlreadyExistsException;
import az.company.regions.exception.RegionNotFoundException;
import az.company.regions.model.Region;
import az.company.regions.service.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/regions")
@AllArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @GetMapping
    public ResponseEntity<List<Region>> getAllRegions(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(regionService.getAllRegions(name), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegion(@PathVariable String id) {

       return new ResponseEntity<>(getRegionById(id), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Region> createRegion(@RequestBody Region newRegion) {
        return new ResponseEntity<>(regionService.createRegion(newRegion), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateRegion(@PathVariable String id, @RequestBody Region newRegion) {
        regionService.updateRegion(id, newRegion);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable String id) {
        regionService.deleteRegion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Region getRegionById(String id) {
        return regionService.getRegionById(id);
    }

    @ExceptionHandler(RegionNotFoundException.class)
    public ResponseEntity<String> handleRegionNotFoundException(RegionNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RegionAlreadyExistsException.class)
    public ResponseEntity<String> handleRegionAlreadyExistsException(RegionAlreadyExistsException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
}
