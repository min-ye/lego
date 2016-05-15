package com.lia.lego.bee;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.lia.common.CommonObject;
import com.lia.common.FileHelper;
import com.lia.common.JsonHelper;
import com.lia.common.Profile;
import com.lia.lego.model.brickset.Inventory;
import com.lia.lego.model.brickset.Set;

public class JsonController {
   private String _setFolder = "";
   private String _inventoryFolder = "";
   
   public JsonController() throws Exception {
      String json = getConfigFile();
      String outputFolder = Profile.INSTANCE.getConfigValue(json, "target_raw_folder");
      _setFolder = outputFolder + "json/";
      _inventoryFolder = outputFolder + "json/inventory/";
   }
   
   public void convertSetFromRawToJson() throws Exception {
      RawController controller = new RawController();
      List<CommonObject> setList = controller.getSetList();
      String outputFile = _setFolder + "set.json";
      /*JSONArray jsonArray = new JSONArray(setList);

      String output = jsonArray.toString(3);
      
      FileHelper.INSTANCE.saveContent(output, outputFile);*/
      JsonHelper.INSTANCE.writeJson(setList, outputFile);
   }
   
   public void convertInventoryFromRawToJson() throws Exception {
      RawController controller = new RawController();
      List<CommonObject> setList = controller.getSetList();
      for (CommonObject object : setList) {
         Set set = (Set)object;
         List<CommonObject> inventoryList = controller.getInventoryListBySetFromFile(set);

         if (inventoryList.size() > 0) {
            String outputFile = _inventoryFolder + set.getSetID() + ".json";
            JsonHelper.INSTANCE.writeJson(inventoryList,outputFile);
            /*JSONArray jsonArray = new JSONArray(inventoryList);

            String output = jsonArray.toString(3);
            
            FileHelper.INSTANCE.saveContent(output, outputFile);*/
         }
      }
   }
   
   private String getConfigFile() throws Exception {
      InputStream url = RawController.class.getResourceAsStream("/lego.json");
      return IOUtils.toString(url);
   }

   public List<CommonObject> getSetList() throws Exception {
      String setJsonFile = _setFolder + "set.json";
      String jsonSet = FileHelper.INSTANCE.getContent(setJsonFile);
      JSONArray jsonArray = new JSONArray(jsonSet);
      List<CommonObject> arraySet = new ArrayList<CommonObject>();
      for (int index = 0; index < jsonArray.length(); index++) {
         JSONObject obj = jsonArray.getJSONObject(index);
         Set set = new Set(obj.getString("setID"), obj.getString("number"), obj.getString("variant"),
               obj.getString("theme"), obj.getString("subTheme"), obj.getString("year"), obj.getString("name"),
               obj.getString("minifigs"), obj.getString("pieces"), obj.getString("priceUK"), obj.getString("priceUS"),
               obj.getString("priceCA"), obj.getString("priceEU"), obj.getString("imageURL"));
         arraySet.add(set);
      }
      return arraySet;
   }
   
   public List<CommonObject> getInventoryBySet(Set set) throws Exception {
      String fileName = _inventoryFolder + set.getSetID() + ".json";
      List<CommonObject> inventoryList = new ArrayList<CommonObject>();
      File file = new File(fileName);
      if (file.exists()){
         String inventoryJson = FileHelper.INSTANCE.getContent(fileName);
         JSONArray jsonArray = new JSONArray(inventoryJson);
         
         for (int index = 0; index < jsonArray.length(); index++) {
            JSONObject obj = jsonArray.getJSONObject(index);
            Inventory inventory = new Inventory(obj.getString("setNumber"), obj.getString("partID"), obj.getString("quantity"),
                  obj.getString("colour"), obj.getString("category"), obj.getString("designID"), obj.getString("partName"),
                  obj.getString("imageUrl"), obj.getString("setCount"));
            inventoryList.add(inventory);
         }
      }
      return inventoryList;
   }
}
