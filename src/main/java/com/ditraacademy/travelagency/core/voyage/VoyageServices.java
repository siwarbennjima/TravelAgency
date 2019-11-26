package com.ditraacademy.travelagency.core.voyage;

import Utils.ErrorResponseModel;
import com.ditraacademy.travelagency.core.destination.Destination;
import com.ditraacademy.travelagency.core.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VoyageServices {
    @Autowired
    VoyageRepository voyageRepository;

    public ResponseEntity<?> addVoy(Voyage voyage) {


        if (voyage.getTitre() == null)
            return new ResponseEntity<>(new ErrorResponseModel("voyage titre required"), HttpStatus.BAD_REQUEST);


        if (voyage.getTitre().length() < 4)
            return new ResponseEntity<>(new ErrorResponseModel("voyage titre required"), HttpStatus.BAD_REQUEST);



        if (voyage.getDate() == null)
            return new ResponseEntity<>(new ErrorResponseModel("wrong  Date"), HttpStatus.BAD_REQUEST);

        Date currentd = new Date();
        if (voyage.getDate().compareTo(currentd) < 0)
            return new ResponseEntity<>(new ErrorResponseModel("Date should be in the future"), HttpStatus.BAD_REQUEST);

        if (voyage.getDate().compareTo(currentd) == 0)
            return new ResponseEntity<>(new ErrorResponseModel("Date should be in the future"), HttpStatus.BAD_REQUEST);


        if (voyage.getDescription() == null)
            return new ResponseEntity<>(new ErrorResponseModel("voyage description required"), HttpStatus.BAD_REQUEST);

        if (voyage.getDescription().length() < 20)
            return new ResponseEntity<>(new ErrorResponseModel("voyage description required"), HttpStatus.BAD_REQUEST);

        if (voyage.getNbPlaces() == null)
            return new ResponseEntity<>(new ErrorResponseModel("voyagen NbPlaces required"), HttpStatus.BAD_REQUEST);

        if (voyage.getNbPlaces() <= 0)
            return new ResponseEntity<>(new ErrorResponseModel("voyagen NbPlaces required"), HttpStatus.BAD_REQUEST);

        if (voyage.getPrix() < 0)
            return new ResponseEntity<>(new ErrorResponseModel("voyage NbPlaces required"), HttpStatus.BAD_REQUEST);

        voyageRepository.save(voyage);
        return new ResponseEntity<>(voyage, HttpStatus.OK);

     /*   Optional<Destination> destinationOptional = destinationRepository.findById(voyage.getDestination().getId());
        if (!destinationOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseModel("wrong voyage id "), HttpStatus.BAD_REQUEST);
        Destination destination = destinationOptional.get();
        voyage.setDestination(destination);
        Voyage newVoyage = voyageRepository.save(voyage);
        List<Voyage> listVoyage = destination.getVoyages();
        listVoyage.add(newVoyage);
        destination.setVoyages(listVoyage);
        destinationRepository.save(destination);
        return new ResponseEntity<>(voyage, HttpStatus.OK);*/
    }

    public List<Voyage> getVoyages() {
        List<Voyage> voyagelist =voyageRepository.findAll();
        return voyagelist ;
    }


    public ResponseEntity updateVoy(int id, Voyage newVoyage) {

        Optional<Voyage> voyageOptional = voyageRepository.findById(id);
        if (!voyageOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseModel("wrong voyage id "), HttpStatus.BAD_REQUEST);
        Voyage oldVoyage = voyageOptional.get();

        if (newVoyage.getTitre() != null)
            if (newVoyage.getTitre().length() < 4)
                return new ResponseEntity<>(new ErrorResponseModel("voyage titre required "), HttpStatus.BAD_REQUEST);
            else
                oldVoyage.setTitre(newVoyage.getTitre());

        if (newVoyage.getDate() == null)
            return new ResponseEntity<>(new ErrorResponseModel("wrong Date"), HttpStatus.BAD_REQUEST);
        Date currentd = new Date();
        if (newVoyage.getDate().compareTo(currentd) < 0)
            return new ResponseEntity<>(new ErrorResponseModel("Date should be in the future"), HttpStatus.BAD_REQUEST);
        if (newVoyage.getDate().compareTo(currentd) == 0)
            return new ResponseEntity<>(new ErrorResponseModel("Date should be in the future"), HttpStatus.BAD_REQUEST);
        else
            oldVoyage.setDate(newVoyage.getDate());

        if (newVoyage.getDescription().length() < 20)
            return new ResponseEntity<>(new ErrorResponseModel("voyage description required"), HttpStatus.BAD_REQUEST);
        else
            oldVoyage.setDescription(newVoyage.getDescription());


        if (newVoyage.getNbPlaces() < 0)
            return new ResponseEntity<>(new ErrorResponseModel("voyagen NbPlaces required"), HttpStatus.BAD_REQUEST);
        else
            oldVoyage.setNbPlaces(newVoyage.getNbPlaces());


        if (newVoyage.getPrix() < 0)
            return new ResponseEntity<>(new ErrorResponseModel("voyage NbPlaces required"), HttpStatus.BAD_REQUEST);
        else
            oldVoyage.setPrix(newVoyage.getPrix());


        voyageRepository.save(oldVoyage);
        return new ResponseEntity<>(HttpStatus.OK);


    }

    public ResponseEntity<?> removeById (int id) {

        Optional<Voyage> voyageOptional = voyageRepository.findById(id);
        if (!voyageOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseModel("wrong voyage id "), HttpStatus.BAD_REQUEST);


        voyageRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public ResponseEntity<?> getVoyageById (int id ){
        Optional<Voyage> voyageOptional = voyageRepository.findById(id);
        if (voyageOptional.isPresent()){
           Voyage voyage = voyageOptional.get();
            return new ResponseEntity<>(voyage, HttpStatus.OK);
        }
        else {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("wrong user id");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }
    }
}

