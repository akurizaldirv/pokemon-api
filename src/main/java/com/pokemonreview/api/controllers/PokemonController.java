package com.pokemonreview.api.controllers;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;
import com.pokemonreview.api.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PokemonController {
    private PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/pokemons")
    public ResponseEntity<PokemonResponse> getAllPokemons(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
    ) {
        return new ResponseEntity<PokemonResponse>(pokemonService.getAll(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/pokemon/{id}")
    public ResponseEntity<PokemonDto> getPokemon(@PathVariable Integer id) {
        return new ResponseEntity<PokemonDto>(pokemonService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/pokemon")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto) {
        return new ResponseEntity<PokemonDto>(pokemonService.create(pokemonDto), HttpStatus.CREATED);
    }

    @PutMapping("/pokemon/{id}")
    public ResponseEntity<PokemonDto> updatePokemon(@RequestBody PokemonDto pokemonDto, @PathVariable Integer id) {
        return new ResponseEntity<PokemonDto>(pokemonService.update(pokemonDto, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/pokemon/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletePokemon(@PathVariable Integer id) {
        pokemonService.delete(id);
        return ResponseEntity.noContent().build();
//        return new ResponseEntity<>("Pokemon Deleted", HttpStatus.OK);
    }


}
