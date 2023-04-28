package frey.house.ega.servicesImplementations;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Service
public class GenerateNumeroService {

    public static String generateAccountNumber(Date dateCreation) {
        String accountNumber = "";

        // Générer 5 caractères alphanumériques aléatoires
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int randInt = random.nextInt(36); // 36 est le nombre de caractères alphanumériques possibles
            char randChar = (char) (randInt < 10 ? randInt + '0' : randInt - 10 + 'A'); // Convertir le nombre en caractère alphanumérique
            accountNumber += randChar;
        }

        // Concaténer l'année de création du compte
        accountNumber += Calendar.getInstance().get(Calendar.YEAR);
//        System.out.println(Calendar.getInstance().get(Calendar.YEAR));



        return accountNumber;
    }

}
