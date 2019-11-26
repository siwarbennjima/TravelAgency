package com.ditraacademy.travelagency.core.voyage;

import com.ditraacademy.travelagency.core.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class VoyageController {
    @Autowired
    VoyageServices voyageServices ;

  @PostMapping ( "/voyage")
    public ResponseEntity <?> addVoy (@RequestBody Voyage voyage) {

      return voyageServices.addVoy(voyage);
  }

    @GetMapping("/voyages")
    public List<Voyage> getvoyages(){
        List<Voyage> Voyagelist=voyageServices.getVoyages();
        return Voyagelist ;
    }
  @PutMapping ("/voyage/{id}")
    public ResponseEntity <?> updateVoy (@PathVariable int id , @RequestBody Voyage voyage ) {
      return  voyageServices.updateVoy(id,voyage);

  }

    @DeleteMapping("voyage/{id}")
    public ResponseEntity deleteUser(@PathVariable int id ){
        return voyageServices.removeById(id);
    }

    @GetMapping("/voyage/{id}")
    public ResponseEntity<?> UserGet(@PathVariable int id){
        return voyageServices.getVoyageById(id);
    }

}
