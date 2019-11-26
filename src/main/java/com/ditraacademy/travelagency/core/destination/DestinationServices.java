package com.ditraacademy.travelagency.core.destination;

import Utils.ErrorResponseModel;
import com.ditraacademy.travelagency.core.voyage.Voyage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationServices {
    @Autowired
    DestinationRepository destinationRepository ;

    public ResponseEntity<?> addDest ( Destination destination ) {

        if (destination.getTitre() == null)
            return new ResponseEntity<>(new ErrorResponseModel("voyage titre required"), HttpStatus.BAD_REQUEST);

        if (destination.getTitre().length() < 4)
            return new ResponseEntity<>(new ErrorResponseModel("voyage titre required"), HttpStatus.BAD_REQUEST);


        if (destination.getDescription() == null)
            return new ResponseEntity<>(new ErrorResponseModel("voyage description required"), HttpStatus.BAD_REQUEST);

        if (destination.getDescription().length() < 20)
            return new ResponseEntity<>(new ErrorResponseModel("voyage description required"), HttpStatus.BAD_REQUEST);


        destinationRepository.save(destination);
        return new ResponseEntity<>(destination, HttpStatus.OK);

    }



    public List<Destination> getDests () {
        List<Destination> destinationlist  =destinationRepository.findAll();
        return destinationlist  ;
    }

    public ResponseEntity<?> updateDest (int id , Destination newDest ) {

        Optional<Destination> destinationOptional = destinationRepository.findById(id);
        if (!destinationOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseModel("wrong destination id "), HttpStatus.BAD_REQUEST);
        Destination oldDest = destinationOptional.get();

        if (newDest.getTitre() != null)
            if (newDest.getTitre().length() < 4)
                return new ResponseEntity<>(new ErrorResponseModel("destination titre required "), HttpStatus.BAD_REQUEST);
            else
                oldDest.setTitre(oldDest.getTitre());

        if (newDest.getDescription().length() < 20)
            return new ResponseEntity<>(new ErrorResponseModel("description shouled be more then 20 letters"), HttpStatus.BAD_REQUEST);
        else
            oldDest.setDescription(newDest.getDescription());


       destinationRepository.save(oldDest);
        return new ResponseEntity<>(HttpStatus.OK);
    }




    public ResponseEntity<?> removeById (int id) {

        Optional<Destination> destinationOptional = destinationRepository.findById(id);
        if (!destinationOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseModel("wrong destination id "), HttpStatus.BAD_REQUEST);


    destinationRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




    public ResponseEntity<?> getDestById (int id ){
        Optional<Destination> destinationOptional = destinationRepository.findById(id);
        if (destinationOptional.isPresent()){
            Destination destination = destinationOptional.get();
            return new ResponseEntity<>(destination, HttpStatus.OK);
        }
        else {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("wrong user id");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }
    }

}
