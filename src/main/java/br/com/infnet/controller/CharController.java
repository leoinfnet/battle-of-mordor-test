package br.com.infnet.controller;

import br.com.infnet.model.Character;
import br.com.infnet.service.CharacterService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/character")
public class CharController {
    @Inject
    CharacterService characterService;

    @GET
    public Uni<List<String>> getAllKeys(){
        return characterService.keys();
    }
    @GET
    @Path("/{name}")
    public Character getByName(String name){
        Long pontuacao = characterService.get(name).get();
        return new Character(name, pontuacao);
    }
    @GET
    @Path("/create")
    public void create(){
        characterService.createChars();
    }
}
