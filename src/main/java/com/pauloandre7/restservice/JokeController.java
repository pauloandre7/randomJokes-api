package com.pauloandre7.restservice;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JokeController {

    @GetMapping("/random-joke")
    public Joke getRandomJoke() {
        int id = new Random().nextInt(10000);
        String[] joke = {"Nao foi possivel encontrar a piada"};
        
        try{
            InputStream is = getClass().getResourceAsStream("/com/pauloandre7/resources/shortjokes.csv");
            Reader reader = new InputStreamReader(is);
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(0).build();
            
            for(int i = 0; i<=10000; i++){
                joke = csvReader.readNext();
                if(id == i)
                    break;
            }

            return new Joke(id, joke[1]);
        }
        catch(IOException ioe){
            return null;
        }
        catch(CsvException csve){
            return null;
        }
        
    }
}
