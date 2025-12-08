package com.rcruz.foovar.controller;

import com.rcruz.foovar.model.entity.Pokemon;
import com.rcruz.foovar.service.PokemonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@RequestMapping(PokemonController.ROOT_ENDPOINT)
public class PokemonController {

    private static final Logger LOGGER = Logger.getLogger(PokemonController.class.getName());
    private static final ResourceBundle rb = ResourceBundle.getBundle("messages");
    protected static final String ROOT_ENDPOINT = "/pokedex";
    private static final String CHILD_ENDPOINT = "/pokemons";
    private static final String ID = "/{id}";

    @Autowired
    PokemonServiceImpl pokemonServiceImpl;


    @GetMapping(CHILD_ENDPOINT)
    public ResponseEntity<List<Pokemon>> getAll() {
        LOGGER.log(Level.FINE, "Call to PokemonController.getAll");
        try {
            List<Pokemon> pokemons = pokemonServiceImpl.getAll();
            if (!pokemons.isEmpty()) {
                LOGGER.log(Level.FINE, rb.getString("get.all.ok"));
                return ResponseEntity.ok().body(pokemons);
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, rb.getString("error.not.found"), ex);
        }

    }

    @GetMapping(CHILD_ENDPOINT + ID)
    public ResponseEntity<Pokemon> get(@PathVariable(value = "id") long id) {
        LOGGER.log(Level.FINE, "Call to PokemonController.getOne");
        try {
            Pokemon pokemon = pokemonServiceImpl.get(id);
            return ResponseEntity.ok().body(pokemon);
        } catch (NoSuchElementException ex) {
            LOGGER.log(Level.WARNING, rb.getString("get.not.found") + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, rb.getString("error.not.found"), ex);
        }
    }

    @PostMapping(CHILD_ENDPOINT)
    public ResponseEntity<Void> post(Pokemon pokemon) {
        LOGGER.log(Level.FINE, "Call to PokemonController.post");
        try {
            pokemonServiceImpl.post(pokemon);
            LOGGER.log(Level.FINE, rb.getString("post.ok") + pokemon.toString());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(CHILD_ENDPOINT + ID)
    public ResponseEntity<Void> put(Pokemon pokemon) {
        LOGGER.log(Level.FINE, "Call to PokemonController.put");
        try {
            if (pokemonServiceImpl.get(pokemon.getId()) != null) {
                LOGGER.log(Level.FINE, rb.getString("found.updating"));
                pokemonServiceImpl.put(pokemon);
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NoSuchElementException ex) {
            LOGGER.log(Level.WARNING, rb.getString("update.not.found"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(CHILD_ENDPOINT + ID)
    public ResponseEntity<Void> delete(@PathVariable(value = "id") long id) {
        LOGGER.log(Level.FINE, "Call to PokemonController.put");
        try {
            if (pokemonServiceImpl.get(id) != null) {
                pokemonServiceImpl.delete(id);
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NoSuchElementException ex) {
            LOGGER.log(Level.WARNING, rb.getString("delete.not.found"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
