package com.rcruz.pokedex.model.dao;

import com.rcruz.pokedex.model.entity.Pokemon;
import org.springframework.data.repository.CrudRepository;

public interface PokemonDAO extends CrudRepository<Pokemon, Long> {



}
