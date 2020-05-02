package me.umeshpatidar.file;

import sun.nio.ch.IOStatus;

import java.io.*;

public class WriteDocument {


    public static boolean write(String data, File file){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(data);
            return true;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    public static String read(File file){
        StringBuffer sb = new StringBuffer();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String input ;
            while((input = reader.readLine()) != null){
                sb.append(input);
                sb.append("\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return sb.toString();
    }
}
