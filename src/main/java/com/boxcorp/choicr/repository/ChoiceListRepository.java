package com.boxcorp.choicr.repository;

import com.boxcorp.choicr.model.ChoiceList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiceListRepository extends CrudRepository<ChoiceList, String> {

    boolean existsByName(String name);
}
