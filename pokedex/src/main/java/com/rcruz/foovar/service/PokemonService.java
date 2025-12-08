package com.rcruz.foovar.service;


import com.rcruz.foovar.model.entity.Pokemon;

import java.util.List;

public interface PokemonService {

    Pokemon get(Long id);
    List<Pokemon> getAll();
    void post(Pokemon pokemon);
    void put(Pokemon pokemon);
    void delete(long id);

}
