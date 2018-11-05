package org.rb.goodsprice.controller;

import java.util.Date;
import java.util.List;
import org.rb.goodsprice.model.Good;
import org.rb.goodsprice.model.UpdateTime;
import org.rb.goodsprice.repositories.IGoodsCrudRepository;
import org.rb.goodsprice.repositories.IUpdateTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by raitis on 27-Feb-17.
 */
@RestController
@RequestMapping("/goods")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class GoodsController {
    private final static String CHKTEXT="GoodsController#16-Oct-2018";
    
    @Autowired
    private IGoodsCrudRepository crudRepo;
    @Autowired
    private IUpdateTimeRepository timeRepo;

    @RequestMapping(path ="/dummy" ,method = RequestMethod.GET)
    public ResponseEntity<Good> getDummy(){
       return new ResponseEntity<>(new Good(new Date(), "TestGood", "TestShop", 1.0, 0),HttpStatus.OK);
    }
    
    @GetMapping("/chk")
    public ResponseEntity<String> checkAvailability(){
        return ResponseEntity.ok(CHKTEXT);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Good>> getAllGoods(){
       return new ResponseEntity<>((List<Good>) crudRepo.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(path = "{id}",method = RequestMethod.GET)
    public ResponseEntity<Good> getGoodById(@PathVariable(value = "id") long id){
        return new ResponseEntity<>(crudRepo.findOne(id), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Good> createGood(@RequestBody Good good){
        updateTimeStamp();
        return new ResponseEntity<>(crudRepo.save(good), HttpStatus.CREATED);
    }
    @RequestMapping(path = "{id}",method = RequestMethod.PUT)
    public ResponseEntity<Good> modifyGoodById(@PathVariable(value = "id") long id,@RequestBody Good good) {
        if( crudRepo.findOne(id) ==null) {
            return new ResponseEntity<Good>(HttpStatus.NOT_FOUND);
        }else {
            crudRepo.delete(id);
            updateTimeStamp();
            return new ResponseEntity<>(crudRepo.save(good), HttpStatus.OK);
        }
    }
    @RequestMapping(path = "{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteGoodById(@PathVariable(value = "id") long id) {
        if( crudRepo.findOne(id) ==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            crudRepo.delete(id);
            updateTimeStamp();
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAll(){
            crudRepo.deleteAll();
            updateTimeStamp();
            return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------- UpdateTime methods -------------
    private void updateTimeStamp() {
        timeRepo.deleteAll();
        timeRepo.save(new UpdateTime(new Date()));
       
    }
    
    @GetMapping("/date")
    public ResponseEntity<Date> getModifiedTime(){
        Iterable<UpdateTime> times = timeRepo.findAll();
        if( !times.iterator().hasNext() )
        {
            return ResponseEntity.notFound().build();
        }
        UpdateTime mtime = times.iterator().next();
        return ResponseEntity.ok(mtime.getUpdateTime());
    }

    
}
