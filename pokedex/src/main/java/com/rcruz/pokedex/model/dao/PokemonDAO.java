package com.rcruz.foovar.model.dao;

import com.rcruz.foovar.model.entity.Pokemon;
import org.springframework.data.repository.CrudRepository;

public interface PokemonDAO extends CrudRepository<Pokemon, Long> {



}
