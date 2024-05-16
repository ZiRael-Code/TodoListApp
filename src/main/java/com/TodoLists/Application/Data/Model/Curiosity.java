package com.TodoLists.Application.Data.Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Curiosity {

    public static void main(String[] args) throws Exception {
//        String fileName =  "C:\\Users\\Israel\\MyFile\\TodoLists\\src\\main\\java\\com\\TodoLists\\Application\\Data\\Model\\test.java";
//        String fileName = "C:\\Users\\Israel\\MyFile\\TodoLists\\src\\main\\java\\com\\TodoLists\\Application\\test";
//        Files.createFile(Path.of(fileName));
//
//        String fileName2 = "C:\\Users\\Israel\\MyFile\\TodoLists\\src\\main\\java\\com\\TodoLists\\Application\\Config\\test1";

                Scanner scanner = new Scanner(System.in);
                ArrayList<Integer> scores = new ArrayList<>();
                for (int i = 0; i < 10; i++){
                    System.out.print("enter score : ");
                    int score = scanner.nextInt();
                    scores.add(score);
                }
                for (int i = 0; i < 10; i++){
                    if (i % 2 == 0){

                    }
                }



//        try(FileOutputStream fileInputStream = new FileOutputStream(fileName)){
//           fileInputStream.write(Files.createFile(Path.of(fileName2)).getNameCount());
//        }catch (IOException e){
//
//        }
        //
//        String fileInfo = null;
//        try( FileInputStream fileInputStream = new FileInputStream(fileName)){
//            fileInfo = new String(fileInputStream.readAllBytes()).replace('}', ' ')+',';
//        }catch (IOException e){
//
//        }
//
//        try(FileOutputStream fileOutputStream = new FileOutputStream(fileName)){
//            String toWrite = fileInfo + "\n\tDAILY_TASK}";
////                        System.out.println(toWrite);
//            fileOutputStream.write(toWrite.getBytes());
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//            String testing = "Discription";

//        System.out.println(arrangingHint(testing));

    String lol = "Hello world i know";
    ArrayList<String> stringsBack = new ArrayList<>();
    StringBuilder whimpCode = new StringBuilder();
        for (int i = 0; i < lol.length(); i++) {
            char let = lol.charAt(i);
            if (let == ' '){
                whimpCode.append(let);
                stringsBack.add(whimpCode.toString());
                whimpCode = new StringBuilder();
                continue;
            }
                whimpCode.append(let);
        }
        stringsBack.add(whimpCode+" ");
        String finals  = "";
        for (int i =stringsBack.size()-1; i >= 0; i--) {
           finals  += stringsBack.get(i);
        }
        System.out.println(finals);

    }

    private static String arrangingHint(String testing) {
        char[] lowers = testing.toLowerCase().toCharArray();
        List<String>values = new ArrayList<>();
        for (int i = 0; i < lowers.length; i++) {
            char chars = lowers[i];
            if (i > 0){
                if (lowers[i-1] == ' '){
                    values.add(String.valueOf(chars).toUpperCase());
                continue;
                }
            }
            values.add(String.valueOf(chars));
        }
        final String[] res = {""};
        values.stream().filter(x-> !x.equals(" ")).forEach(x-> res[0] +=x);
        return res[0];
        }
    }


