package org.rb.goodsprice.controller;

import java.util.Date;
import org.rb.goodsprice.model.Good;
import org.rb.goodsprice.repositories.IGoodsCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by raitis on 27-Feb-17.
 */
@RestController
@RequestMapping("/goods")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class GoodsController {

    @Autowired
    private IGoodsCrudRepository crudRepo;

    @RequestMapping(path ="/dummy" ,method = RequestMethod.GET)
    public ResponseEntity<Good> getDummy(){
       return new ResponseEntity<>(new Good(new Date(), "TestGood", "TestShop", 1.0, 0),HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Good>> getAllGoods(){
       return new ResponseEntity<>((List<Good>) crudRepo.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(path = "{id}",method = RequestMethod.GET)
    public ResponseEntity<Good> getGoodById(@PathVariable(value = "id") long id){
        return new ResponseEntity<>((Good) crudRepo.findOne(id), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Good> createGood(@RequestBody Good good){
        return new ResponseEntity<>((Good) crudRepo.save(good), HttpStatus.CREATED);
    }
    @RequestMapping(path = "{id}",method = RequestMethod.PUT)
    public ResponseEntity<Good> modifyGoodById(@PathVariable(value = "id") long id,@RequestBody Good good) {
        if( crudRepo.findOne(id) ==null) {
            return new ResponseEntity<Good>(HttpStatus.NOT_FOUND);
        }else {
            crudRepo.delete(id);
            return new ResponseEntity<>((Good) crudRepo.save(good), HttpStatus.OK);
        }
    }
    @RequestMapping(path = "{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteGoodById(@PathVariable(value = "id") long id) {
        if( crudRepo.findOne(id) ==null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }else {
            crudRepo.delete(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

}
