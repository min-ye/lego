package com.lia.lego.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.lia.common.CommonObject;
import com.lia.common.mysql.FieldModel;

public class Theme extends CommonObject {
   private String name = "";
   private UUID key = null;
   
   public Theme() {
      
   }
   
   public Theme(String name,
                UUID key){
      this.name = name;
      this.key = key;
   }
   
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public UUID getKey() {
      return this.key;
   }

   public void setKey(UUID key) {
      this.key = key;
   }
   
   @Override
   public String fetchObjectName(){
      return "Theme";
   }
   
   @Override
   public String getPropertyValue(String fieldName) throws Exception{
      switch (fieldName){
      case "Name":
         return this.name.toString();
      case "Key":
         return this.key.toString();

      default:
         throw new Exception(String.format("Unknown Field Name:[%s]", fieldName));
      }
   }
   
   @Override
   public void setValue(String fieldName, String fieldValue) throws Exception
   {
      switch (fieldName) {
      case "Name":
         this.name = fieldValue;
         break;
      case "Key":
         this.key = convertToUUID(fieldValue);
         break;

      default:
         throw new Exception(String.format("Unknown Field Name:[%s]", fieldName));
      }
   }
   
   @Override
   public void importModel(Map<String, Object> item) throws Exception{
      for (Entry<String, Object> entry : item.entrySet()) {
         setValue(entry.getKey(), entry.getValue().toString());
      }
   }
   
   public Map<String, FieldModel> exportModel(){
      Map<String, FieldModel> modelMap = new HashMap<String, FieldModel>();
      modelMap.put("Name", new FieldModel("String", this.name, false));
      modelMap.put("Key", new FieldModel("UUID", this.key.toString(), true));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportPropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("Name", getPropertyValueString(this.name));
      modelMap.put("Key", getPropertyValueString(this.key));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportKeyPropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("Key", getPropertyValueString(this.key));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportValuePropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("Name", getPropertyValueString(this.name));

      return modelMap;
   }
   
   @Override
   public ArrayList<String> fetchPropertyName(){
      ArrayList<String> fieldNameList = new ArrayList<String>();
      fieldNameList.add("Key");
      fieldNameList.add("Name");
      return fieldNameList;
   }
   
   @Override
   public Object[] fetchObject() {
      Object[] obj = new Object[2];
      obj[0] = this.key;
      obj[1] = this.name;
      
      return obj;
   }

   @Override
   public String fetchDescription() {
      return String.format("%s", this.name);
   }
}
