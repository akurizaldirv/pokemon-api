package com.pokemonreview.api.services.impl;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;
import com.pokemonreview.api.exceptions.PokemonNotFoundException;
import com.pokemonreview.api.entities.Pokemon;
import com.pokemonreview.api.repositories.PokemonRepository;
import com.pokemonreview.api.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public PokemonDto create(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        Pokemon newPokemon = pokemonRepository.save(pokemon);

        return mapToDto(newPokemon);
    }

    @Override
    public PokemonResponse getAll(Integer pageNo, Integer pageSize) {
//        Add Pagination Conf
        Pageable pageable = PageRequest.of(pageNo, pageSize);
//        Get All Pokemons w/ Conf
        Page<Pokemon> pokemons = pokemonRepository.findAll(pageable);
        List<Pokemon> pokemonList = pokemons.getContent();
        List<PokemonDto> pokemonDtos = pokemonList.stream().map(pokemon -> mapToDto(pokemon)).collect(Collectors.toList());

        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setContent(pokemonDtos);
        pokemonResponse.setPageNo(pokemons.getNumber());
        pokemonResponse.setPageSize(pokemons.getSize());
        pokemonResponse.setTotalPages(pokemons.getTotalPages());
        pokemonResponse.setTotalElements(pokemons.getTotalElements());
        pokemonResponse.setIsLast(pokemons.isLast());

        return pokemonResponse;


    }

    @Override
    public PokemonDto getById(Integer id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon Could not be Found"));
        return mapToDto(pokemon);
    }

    @Override
    public PokemonDto update(PokemonDto pokemonDto, Integer id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon Could not be Updated"));

        pokemon.setType(pokemonDto.getType());
        pokemon.setName(pokemonDto.getName());

        Pokemon updated = pokemonRepository.save(pokemon);

        return mapToDto(updated);
    }

    @Override
    public void delete(Integer id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon Could not be Deleted"));
        pokemonRepository.delete(pokemon);
    }

    private PokemonDto mapToDto(Pokemon pokemon) {
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());

        return pokemonDto;
    }

    private Pokemon mapToEntity(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        return pokemon;
    }
}
