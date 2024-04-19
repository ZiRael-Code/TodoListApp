package com.TodoLists.Application.Data.Model;

import java.util.HashMap;
import java.util.List;
public class Curiosity1 {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name","praise");
        hashMap.put("name2","israel");
        if (hashMap.size() > 1){
            for (int i = 0; i < hashMap.size(); i++) {
                List<String> keys = hashMap.keySet().stream().toList();
                String key = keys.get(i);
            }
        }
    }
    }
