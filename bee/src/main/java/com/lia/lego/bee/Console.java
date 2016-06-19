package com.lia.lego.bee;

import java.util.HashMap;
import java.util.Map;

import com.lia.common.exception.CancelInputException;
import com.lia.common.IOHelper;

public class Console {
   
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
               choice = IOHelper.readStringChoose(option);
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
         IOHelper.writeLine(ex.getMessage());
      }
   }
}
