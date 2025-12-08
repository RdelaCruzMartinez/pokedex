package com.rcruz.foovar.service;

import com.rcruz.foovar.model.dao.PokemonDAO;
import com.rcruz.foovar.model.entity.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonServiceImpl implements PokemonService {


    @Autowired
    private PokemonDAO pokemonDAO;

    @Override
    public Pokemon get(Long id) {
        return pokemonDAO.findById(id).get();
    }

    @Override
    public List<Pokemon> getAll() {
        return (List<Pokemon>) pokemonDAO.findAll();
    }

    @Override
    public void post(Pokemon pokemon) {
        pokemonDAO.save(pokemon);
    }

    @Override
    public void put(Pokemon pokemon) {
        pokemonDAO.findById(pokemon.getId()).ifPresent((x) -> {
            pokemonDAO.save(pokemon);
        });
    }


    @Override
    public void delete(long id) {
        pokemonDAO.deleteById(id);
    }
}
