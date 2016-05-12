package com.lia.lego.bee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

import com.lia.common.Cookie;
import com.lia.common.FileHelper;
import com.lia.common.Profile;
import com.lia.common.WebHelper;
import com.lia.lego.model.brickset.Inventory;
import com.lia.lego.model.brickset.Set;

public class RawController {
   private String _setUrl = "";
   private String _inventoryUrl = "";
   private String _setFolder = "";
   private String _inventoryFolder = "";

   public RawController() throws Exception {
      String json = getConfigFile();
      _setUrl = Profile.INSTANCE.getConfigValue(json, "source_url");
      _inventoryUrl = Profile.INSTANCE.getConfigValue(json, "source_inventory_url");
      String outputFolder = Profile.INSTANCE.getConfigValue(json, "target_raw_folder");
      _setFolder = outputFolder + "csv/";
      _inventoryFolder = outputFolder + "csv/inventory/";
   }

   public void downloadSetRawToCSV() {
      try {
         for (int year = 2013; year <= 2013; year++) {
            int index = 1;
            while (index != 0) {
               String url = String.format(_setUrl, year, index);
               ArrayList<Cookie> multi_cookie = new ArrayList<Cookie>();
               multi_cookie.add(new Cookie("setsListFormat", "CSV", "/", "brickset.com"));
               String content = WebHelper.INSTANCE.getContent(url, multi_cookie);
               String beginTag = "<textarea name=\"ctl00$mainContent$list1$ctl01\">";
               String endTag = "</textarea>";
               content = FileHelper.INSTANCE.getContentAccordingTag(content, beginTag, endTag);
               if (content.length() > 125) {
                  String fileName = _setFolder + String.valueOf(year) + "-" + String.valueOf(index) + ".csv";
                  FileHelper.INSTANCE.saveContent(content, fileName);
                  System.out.println(fileName);
                  index++;
               } else {
                  index = 0;
               }
            }
         }
      } catch (Exception ex) {
         System.out.println(ex.getMessage());
      } finally {

      }
   }

   public void downloadInventoryRawToCSV() throws Exception {
      List<Set> setList = getSetList();
      int index = 1;
      for (Set set : setList) {
         //if (set.getYear().equals("2009")) {
            downloadInventoryBySet(set);
            System.out.println(String.valueOf(index));
            index++;
         //}
      }
      System.out.println(setList.size());
   }

   private String getConfigFile() throws Exception {
      InputStream url = RawController.class.getResourceAsStream("/lego.json");
      return IOUtils.toString(url);
   }
   
   public List<Set> getSetList() throws Exception{
      File root = new File(_setFolder);
      File[] multiFile = root.listFiles();
      List<Set> setList = new ArrayList<Set>();
      for (File file : multiFile) {
         if (file.isFile() && file.toString().contains(".csv")) {
            System.out.println("processing " + file.toString());
            for (Set set : getSetListFromFile(file.toString())) {
               setList.add(set);
            }
         }
      }
      return setList;
   }

   public List<Inventory> getInventoryListBySetFromFile(Set set) throws Exception {
      String fileName = String.format("%s%s.csv", _inventoryFolder, set.getSetID());
      List<Inventory> inventoryList = new ArrayList<Inventory>();
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferReader = new BufferedReader(fileReader);
      String line = "";
      boolean foundTitle = false;
      while ((line = bufferReader.readLine()) != null) {
         String[] arrayItem = line.split(",");
         if (arrayItem.length > 1) {
            if (arrayItem[0].toLowerCase().equals("setnumber")) {
               if (foundTitle) {
                  System.out.println(line);
               } else {
                  foundTitle = true;
               }
            } else {
               Inventory inventory = getInventoryFromRow(line);
               inventoryList.add(inventory);
            }
         }
      }
      bufferReader.close();
      fileReader.close();

      return inventoryList;
   }

   private void downloadInventoryBySet(Set set) throws Exception {
      String fileName = _inventoryFolder + set.getSetID() + ".csv";
      File file = new File(fileName);
      if (!file.exists()) {
         String url = String.format(_inventoryUrl, set.getNumber().trim() + "-" + set.getVariant().trim());
         String content = WebHelper.INSTANCE.getContent(url);

         FileHelper.INSTANCE.saveContent(content, fileName);
         System.out.println(fileName + "-" + set.getYear());
      }
   }
   
   private List<Set> getSetListFromFile(String fileName) throws Exception {
      List<Set> setList = new ArrayList<Set>();
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferReader = new BufferedReader(fileReader);
      String line = "";
      boolean foundTitle = false;
      while ((line = bufferReader.readLine()) != null) {
         String[] arrayItem = line.split(",");
         if (arrayItem[0].toLowerCase().equals("setid")) {
            if (foundTitle) {
               System.out.println(line);
            } else {
               foundTitle = true;
            }
         } else {
            Set set = getSetFromRow(line);
            setList.add(set);
         }
      }
      bufferReader.close();
      fileReader.close();

      return setList;
   }
   
   private Inventory getInventoryFromRow(String row) throws Exception {
      row = row.replace("&quot;", "\"");
      row += ",";
      List<String> arrayItem = getItem(row);
      if (arrayItem.size() != 9) {
         System.out.println(row);
         throw new Exception("Invalid row;");
      } else {
         Inventory inventory = new Inventory(arrayItem.get(0), arrayItem.get(1), arrayItem.get(2), arrayItem.get(3),
               arrayItem.get(4), arrayItem.get(5), arrayItem.get(6), arrayItem.get(7), arrayItem.get(8));
         return inventory;
      }
   }
   
   private Set getSetFromRow(String row) throws Exception {
      row = row.replace("&quot;", "\"");
      row += ",";
      List<String> arrayItem = getItem(row);
      if (arrayItem.size() != 14) {
         System.out.println(row);
         throw new Exception("Invalid row;");
      } else {
         Set set = new Set(arrayItem.get(0), arrayItem.get(1), arrayItem.get(2), arrayItem.get(3), arrayItem.get(4),
               arrayItem.get(5), arrayItem.get(6), arrayItem.get(7), arrayItem.get(8), arrayItem.get(9),
               arrayItem.get(10), arrayItem.get(11), arrayItem.get(12), arrayItem.get(13));
         return set;
      }
   }
   
   private List<String> getItem(String input) {
      Pattern pCells = Pattern.compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,");
      Matcher mCells = pCells.matcher(input);
      ArrayList<String> output = new ArrayList<String>();
      while (mCells.find()) {
         String item = mCells.group();
         item = item.replaceAll("(?sm)\"?([^\"]*(\"{2})*[^\"]*)\"?.*,", "$1");
         item = item.replaceAll("(?sm)(\"(\"))", "$2");
         output.add(item);
      }
      return output;
   }
   
   public void download(){
      
   }
}