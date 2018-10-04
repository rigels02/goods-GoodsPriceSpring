package org.rb.goodsprice.repositories;

import org.rb.goodsprice.model.Good;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by raitis on 27-Feb-17.
 */
public interface IGoodsCrudRepository extends CrudRepository<Good,Long> {

}
