package com.ifi.controller;

import com.ifi.bo.PokemonType;
import com.ifi.repository.PokemonTypeRepository;

import javax.management.Attribute;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.stream.Collectors;

@Controller
public class PokemonTypeController {
    private PokemonTypeRepository repository = new PokemonTypeRepository();

    @RequestMapping(uri = "/pokemons")
    public PokemonType getPokemon(Map<String,String[]> parameters){
        // TODO
        if(parameters == null){
            throw new IllegalArgumentException("parameters should not be empty");
        }

        /**
         * On récupère les attributs du pokemon type
         */
        Field[] attributes = PokemonType.class.getDeclaredFields();

        List<Field> listAttr = new ArrayList<>(Arrays.asList(attributes));
        var list = listAttr.stream().map(field -> field.getName()).collect(Collectors.toList());


        for(Map.Entry<String, String[]> parameter : parameters.entrySet()){
            if(!list.contains(parameter.getKey())){
                throw new IllegalArgumentException("unknown parameter");
            }

            var paramsValue = parameter.getValue();
            if(paramsValue.length == 1){
                var paramValue = paramsValue[0];
                switch (parameter.getKey()){
                    case "name":
                        return repository.findPokemonByName(paramValue);
                    case "id":
                        return repository.findPokemonById(Integer.parseInt(paramValue));
                }
            }
        }
        return null;
    }
}