package com.yl;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Cookie {
    List<String>cookie = null;

    public void readCookieFile(String fileName) throws IOException{
        // instantiate the cookies collection object
        cookie = new LinkedList<>();


        // use a File object to pass the fileName
        File fileObj = new File(fileName);

        // use FileReader and BufferReader for reading from cookie file
        BufferedReader br = new BufferedReader(new FileReader(fileObj));
        String cookieList = "";

        // while loop to loop through the file
        while((cookieList = br.readLine())!= null){
            // read each line and add into the cookies collection object
            cookie.add(cookieList);
        }

        // close the BufferedReader in reverse order

        br.close();
        }


    public String getRandomCookie(){

        // use Math random function to get random item from cookies collection object
        Random random = new Random();

        //check if cookies collection are loaded, else get random cookie
        if (cookie.isEmpty()){
            return "There are no cookies in the jar.";
        }else{
            String randomCookie = cookie.get(random.nextInt(cookie.size()));
            return randomCookie;
        }
            
        
    
    }

            

}
            



    






