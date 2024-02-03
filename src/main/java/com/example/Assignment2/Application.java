package com.example.Assignment2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        int number = randomNumber();
        pokemonName(number);
        System.exit(0);
        }
        
        
      public static int randomNumber() {
        Random random = new Random();
        return random.nextInt(1302) + 1;
      }
    

    /**
     * Get a random pokemon name and print to console
     */
    public static void pokemonName(int number) {
        try {
            String url = "https://pokeapi.co/api/v2/pokemon/" + number;
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonFact = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonFact);

            String name = root.findValue("name").asText();
            
            System.out.println("**********POKEMON NAME********");
            System.out.println("Name: " + name);

        } catch (JsonProcessingException ex) {
            Logger.getLogger(Application.class.getName()).log(
                    Level.SEVERE,
                    null, ex);
            System.out.println("error in pokemonName");

        }
    }
}
    
