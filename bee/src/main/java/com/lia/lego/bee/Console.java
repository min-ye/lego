package com.lia.lego.bee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;

import com.lia.common.exception.CancelInputException;
import com.lia.common.CommonObject;
import com.lia.common.Profile;
//import com.lia.common.FileHelper;
//import com.lia.common.WebHelper;

public class Console {
   private static boolean ide = true;
   private static java.io.Console c = System.console();
   private static BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
   private static String _quitIdent = " ";
   public static void main(String[] args) throws Exception {
      RawController raw = new RawController();
      JsonController json = new JsonController();
      DBController db = new DBController();
      
      try{
         int choice = -1;
         Map<Integer, String> option = new HashMap<Integer, String>();
         option.put(1, "Set output folder");
         option.put(2, "Get set raw data from brickset.com");
         option.put(3, "Convert set data from csv format to json format");
         option.put(5, "Convert set data from json format to database");
         option.put(6, "Get brick raw data from brickset.com");
         option.put(7, "Convert brick data from csv format to json format");
         option.put(8, "Convert inventory data from json format to database");
         option.put(9, "Check set");

         do {
            try {
               choice = readStringChoose(option);
            }
            catch (CancelInputException ex) {
               choice = 0;
            }
            switch(choice){
            case 1:
               db.initialize();
               break;
            case 2:
               raw.downloadSetRawToCSV();
               break;
            case 3:
               json.convertSetFromRawToJson();
               break;
            case 5:
               db.convertSetFromJsonToMySQL();
               break;
            case 6:
               raw.downloadInventoryRawToCSV();
               break;
            case 7:
               json.convertInventoryFromRawToJson();
               break;
            case 8:
               db.convertInventoryFromJsonToMySQL();
               break;
            }
         } while (choice != 0);
      }
      catch (CancelInputException ex){
         System.exit(0);
      }
      catch (Exception ex){
         writeLine(ex.getMessage());
      }
   }
   
   private static String readLine(String prompt) throws IOException, CancelInputException{
      String input = "";
      if (ide){
         System.out.println(prompt);
         input = b.readLine();
         /*if (input.length() == 0){
            System.out.print("Are you want to exit? (Y/N):");
            input = b.readLine();
            if (input.toString().equals("Y")) {
               throw new CancelInputException();
            }
            else {
               input = "";
            }
         }*/
      }
      else {
         input = c.readLine(prompt);
         /*if (input.length() == 0){
            System.out.print("Are you want to exit? (Y/N):");
            input = b.readLine();
            if (input.toString().toUpperCase().equals("Y")) {
               throw new CancelInputException();
            }
            else {
               input = "";
            }
         }*/
      }
      return input;
   }
   
   private static void writeLine(String message) {
      if (ide){
         System.out.println(message);
      }
      else {
         c.printf(message);
      }
   }
   
   private static void writeLine(List<String> message) {
      if (ide){
         System.out.println("--------------------------------------------------");
      }
      else {
         c.printf("------------------------------------------------");
      }
      for (String m : message) {
         if (ide){
            System.out.println(m);
         }
         else {
            c.printf(m);
         }
      }
   }
   
   private static int readStringChoose(Map<Integer, String> option) throws Exception{
      writeLine("--------------------------------------------------");
      boolean choosed = false;
      String result = "";
      Integer index = -1;
      while (!choosed){
         for (Map.Entry<Integer, String> entry : option.entrySet()) {
            writeLine(String.format("%d: %s;", entry.getKey(), entry.getValue()));
         }
         writeLine(String.format("%s: quit;", _quitIdent));
         writeLine("--------------------------------------------------");
         result = readLine("Please choose:");
         
         if (result.equals(" ")) {
            throw new CancelInputException();
         }
         try{
               index = Integer.parseInt(result);
               if (option.containsKey(index)) {
                  choosed = true;
               }
            
         }
         catch (Exception ex) {
            writeLine(ex.getMessage());
            choosed = false;
         }
      }
      return index;
   }
   
   private static int readObjectChoose(Map<Integer, CommonObject> option) throws Exception{
      writeLine("--------------------------------------------------");
      boolean choosed = false;
      String result = "";
      Integer index = -1;
      while (!choosed){
         for (Entry<Integer, CommonObject> entry : option.entrySet()) {
            writeLine(String.format("%d: %s;", entry.getKey(), entry.getValue().fetchDescription()));
         }
         writeLine(String.format("%s: quit;", _quitIdent));
         writeLine("--------------------------------------------------");
         result = readLine("Please choose:");
         if (result.equals(" ")) {
            throw new CancelInputException();
         }
         try{
               index = Integer.parseInt(result);
               if (option.containsKey(index)) {
                  choosed = true;
               }
            
         }
         catch (Exception ex) {
            writeLine(ex.getMessage());
            choosed = false;
         }
      }
      return index;
   }
   
   private static String getConfigFile() throws Exception {
      InputStream url = Console.class.getResourceAsStream("/config.json");
      return IOUtils.toString(url);
   }
}
