package com.lia.lego.bee;

//import com.lia.common.FileHelper;
//import com.lia.common.WebHelper;

public class Console {
   public static void main(String[] args) {
      try {
         RawController raw = new RawController();
         JsonController json = new JsonController();
         DBController db = new DBController();
         db.generateTheme();
         //raw.downloadSetRawToCSV();
         //raw.downloadInventoryRawToCSV();
         //json.convertSetFromRawToJson();
         //json.convertInventoryFromRawToJson();
         //db.convertSetFromJsonToMySQL();
         db.convertInventoryFromJsonToMySQL();
         
         /*for (int i = 1; i <= 35; i++)
         {
            String userId = "1140301" + String.format("%03d", i);
            String output = WebHelper.INSTANCE.getContent(userId, "123");
            String file = "C:\\Users\\mye\\workspace\\hy\\" + userId + ".fil";
            FileHelper.INSTANCE.saveContent(output, file);
         }*/
         
         String choice = "";
         while (!choice.equals("0")) {
            java.io.Console console = System.console();
            console.printf("Please input your choice:\n");
            console.printf("1. Set output folder;");
            console.printf("2. Get set raw data from brickset.com\n");
            console.printf("3. Convert set data from csv format to json format\n");
            console.printf("5. Convert set data from json format to database\n");
            console.printf("6. Get brick raw data from brickset.com\n");
            console.printf("7. Convert brick data from csv format to json format\n");
            console.printf("8. Convert inventory data from json format to database\n");
            console.printf("9. Check set \n");
            console.printf("0. Quit\n");
            choice = console.readLine("Choice: \n");
            switch (choice){
            case "1":
               db.generateTheme();
               break;
            case "2":
               raw.downloadSetRawToCSV();
               break;
            case "3":
               json.convertSetFromRawToJson();
               break;
            case "5":
               db.convertSetFromJsonToMySQL();
               break;
            case "6":
               raw.downloadInventoryRawToCSV();
               break;
            case "7":
               json.convertInventoryFromRawToJson();
               break;
            case "8":
               db.convertInventoryFromJsonToMySQL();
               break;
            case "0":
               
            }
         }
         //BrickSet brickSet = new BrickSet();
         //brickSet.getSetRaw();
         //brickSet.convertSetToJson();
         //brickSet.getBrickRaw();
         //brickSet.convertBrickToJson();*/
      } catch (Exception ex) {
         System.out.println(ex.getMessage());
      }
   }
}
