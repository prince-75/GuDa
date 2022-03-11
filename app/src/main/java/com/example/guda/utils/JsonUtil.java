package com.example.guda.utils;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonUtil {

    private static JSONArray ConvertToJsonArray(String path){
        JSONArray jsonArray = null;
        BufferedReader reader = null;
        InputStream inputStream=null;
        StringBuilder jsonStrs = new StringBuilder();
        try{
            inputStream = new FileInputStream(path);
            if(inputStream==null){
//                logger.info(path+" is not exist or the json file is wrong");
                return jsonArray;
            }
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempStr = null;
            while ((tempStr = reader.readLine()) != null) {
                jsonStrs.append(tempStr);
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonArray;
    }



}
