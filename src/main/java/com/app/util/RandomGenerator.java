/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.util;

import java.util.Random;

/**
 *
 * @author brainplusplus
 */
public class RandomGenerator {
     private static final String CHAR_LIST =
        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
     
    /**
     * This method generates random string
     * @return
     */
    public String generateRandomString(int LENGTH){
        
        StringBuffer randStr = new StringBuffer();
        for(int i=0; i<LENGTH; i++){
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }
     
    /**
     * This method generates random numbers
     * @return int
     */
    private int getRandomNumber() {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }
}
