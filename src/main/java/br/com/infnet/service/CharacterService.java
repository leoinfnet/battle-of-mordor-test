package br.com.infnet.service;

import br.com.infnet.model.Character;
import com.github.javafaker.Faker;
import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.keys.ReactiveKeyCommands;
import io.quarkus.redis.datasource.value.ValueCommands;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@ApplicationScoped
public class CharacterService {
    private ValueCommands<String,Long> commands;
    private ReactiveKeyCommands<String> keyCommands;

    public CharacterService(RedisDataSource ds, ReactiveRedisDataSource reactive) {
        this.commands = ds.value(Long.class);
        this.keyCommands = reactive.key();
    }
    public Optional<Long> get(String charName){
        Long score = commands.get(charName);
        if(score == null ) return Optional.empty();
        else return Optional.of(score);
    }
    public void set(String charName, Long pontuacao){
        commands.set(charName,pontuacao);
    }
    public Uni<List<String>> keys(){
        return keyCommands.keys("*");
    }
    public void createChars(){
/*
        for(int i=0;i< 200;i++){
            Faker faker = new Faker();
            String charName = faker.lordOfTheRings().character();
            set(charName,new Random().nextLong(3000));
        }
*/

    }





}
