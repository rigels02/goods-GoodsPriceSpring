package org.rb.goodsprice.repositories;

import org.rb.goodsprice.model.Good;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by raitis on 27-Feb-17.
 */
@RepositoryRestResource(collectionResourceRel = "pgoods",path = "/pgoods")
@CrossOrigin(origins = "*")
public interface IGoodsRepositoryPages extends PagingAndSortingRepository<Good,Long> {
    List<Good> findByName(@Param("name") String name);
    List<Good> findByShop(@Param("shop") String shop);
}
