package com.TodoLists.Application.Data.Model;

import java.util.ArrayList;
import java.util.List;
public class Curiosity {

    public static void main(String[] args) throws Exception {
////        String fileName =  "C:\\Users\\Israel\\MyFile\\TodoLists\\src\\main\\java\\com\\TodoLists\\Application\\Data\\Model\\test.java";
//        String fileName = "C:\\Users\\Israel\\MyFile\\TodoLists\\src\\main\\java\\com\\TodoLists\\Application\\Data\\Model\\TaskType.java";
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

            String testing = "Discription";

        System.out.println(arrangingHint(testing));
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


