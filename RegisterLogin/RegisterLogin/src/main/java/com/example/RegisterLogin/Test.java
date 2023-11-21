package com.example.RegisterLogin;

import java.util.Random;


public class Test {

    public static void main(String[] args) {

        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String num = "0123456789";
        String specialCharacter = "*&%$@#!=+";

        String combination = upper + lower + specialCharacter + num;

        int passLength = 8;
        char[] password = new char[passLength];

        Random random = new Random();

        password[0] = upper.charAt(random.nextInt(upper.length()));         
        password[1] = lower.charAt(random.nextInt(lower.length()));          
        password[2] = specialCharacter.charAt(random.nextInt(specialCharacter.length())); 
        password[3] = num.charAt(random.nextInt(num.length()));              


        for (int i = 4; i < passLength; i++) {
            password[i] = combination.charAt(random.nextInt(combination.length()));
        }
        
       shuffleArray(password);

        System.out.println("Random Password ---> " + new String(password));
    }

   
    private static void shuffleArray(char[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
    }
    
}
