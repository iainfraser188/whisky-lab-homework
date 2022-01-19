package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DistilleryController {

    @Autowired
    DistilleryRepository distilleryRepository;

//    @GetMapping(value = "/distilleries")
//    public ResponseEntity<List<Distillery>> getAllDistilleries(){
//        return new ResponseEntity<>(distilleryRepository.findAll(), HttpStatus.OK);
//    }


    @GetMapping(value = "/distilleries/{id}")
    public ResponseEntity<Optional<Distillery>> getDistillery(@PathVariable Long id){
        Optional<Distillery> payload = distilleryRepository.findById(id);
        if(payload.isPresent()){
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/distilleries")
    public ResponseEntity<Distillery> postDistillery(@RequestBody Distillery distillery){
        distilleryRepository.save(distillery);
        return new ResponseEntity<>(distillery, HttpStatus.CREATED);
    }

    @GetMapping(value = "/distilleries")
    public ResponseEntity <List<Distillery>>findWhiskysFromCertainYear(
            @RequestParam(name= "region",required = false) String region
    ){
        if(region !=null){
            return new ResponseEntity<>(distilleryRepository.findDistilleryByRegion(region),HttpStatus.OK);

        }
        else{
            return new ResponseEntity<>(distilleryRepository.findAll(),HttpStatus.OK);
        }
    }




}
