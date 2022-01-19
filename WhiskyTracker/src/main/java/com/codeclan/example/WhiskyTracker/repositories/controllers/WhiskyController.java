package com.codeclan.example.WhiskyTracker.repositories.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class WhiskyController {

    @Autowired
     WhiskyRepository whiskyRepository;

//    @GetMapping(value="/whiskies")
//    public ResponseEntity<List<Whisky>>getAllWhiskies(){
//        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
//    }

    @GetMapping(value ="/whiskies/{id}")
    public ResponseEntity<Optional<Whisky>>getWhisky(@PathVariable Long id){
        Optional<Whisky> payload = whiskyRepository.findById(id);
        if(payload.isPresent()){
            return new ResponseEntity<>(payload,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(payload,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/whiskies")
    public ResponseEntity <List<Whisky>>findWhiskysFromCertainYear(
            @RequestParam(name= "year",required = false) Integer year
    ){
        if(year !=null){
            return new ResponseEntity<>(whiskyRepository.findWhiskyByYear(year),HttpStatus.OK);

        }
        else{
            return new ResponseEntity<>(whiskyRepository.findAll(),HttpStatus.OK);
        }
    }



    @GetMapping(value = "/whiskies/distilleries")
    public ResponseEntity <List<Whisky>>findWhiskiesFromCertainDistilleryAndYear(
            @RequestParam(name= "Id",required = false) Long id,
            @RequestParam(name= "year",required = false) Integer year
    ){
        if(year !=null){
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByDistilleryIdAndYear(id,year),HttpStatus.OK);

        }
        else{
            return new ResponseEntity<>(whiskyRepository.findAll(),HttpStatus.OK);
        }
    }


    @GetMapping(value = "/whiskies/region")
    public ResponseEntity<List<Whisky>> findWhiskiesFromRegion(
            @RequestParam(name= "region",required= false) String region
    ) {
        if (region != null) {
            return new ResponseEntity<>(whiskyRepository.findWhiskyByDistilleryRegion(region), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);

        }
    }
}
