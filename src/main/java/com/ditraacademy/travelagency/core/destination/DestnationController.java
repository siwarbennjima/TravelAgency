package com.ditraacademy.travelagency.core.destination;


import com.ditraacademy.travelagency.core.voyage.Voyage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DestnationController {
    @Autowired
    DestinationServices destinationServices;


    @PostMapping("/destination")
    public ResponseEntity<?> addVoy(@RequestBody Destination destination) {

        return destinationServices.addDest(destination);
    }


    @GetMapping("/destinations")
    public List<Destination> getdests() {
        List<Destination> destinationlist = destinationServices.getDests();
        return destinationlist;
    }


    @PutMapping("/destination/{id}")
    public ResponseEntity<?> updateDest(@PathVariable int id, @RequestBody Destination destination) {
        return destinationServices.updateDest(id, destination);
    }


    @DeleteMapping("destination/{id}")
    public ResponseEntity removeById(@PathVariable int id) {
        return destinationServices.removeById(id);
    }




    @GetMapping("/destination/{id}")
    public ResponseEntity<?> getDestById(@PathVariable int id) {
        return destinationServices.getDestById(id);
    }



    }





