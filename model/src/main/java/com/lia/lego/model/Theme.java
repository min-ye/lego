package com.lia.lego.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.lia.common.CommonObject;
import com.lia.common.mysql.FieldModel;

public class Theme extends CommonObject {
   private String _name = "";
   private UUID _key = null;
   
   public Theme() {
      
   }
   
   public Theme(String name,
                UUID key){
      this._name = name;
      this._key = key;
   }
   
   public String getName() {
      return _name;
   }

   public void setName(String name) {
      this._name = name;
   }

   public UUID getKey() {
      return this._key;
   }

   public void setKey(UUID key) {
      this._key = key;
   }
   
   @Override
   public String fetchObjectName(){
      return "Theme";
   }
   
   @Override
   public String getPropertyValue(String fieldName) throws Exception{
      switch (fieldName){
      case "Name":
         return this._name.toString();
      case "Key":
         return this._key.toString();

      default:
         throw new Exception(String.format("Unknown Field Name:[%s]", fieldName));
      }
   }
   
   @Override
   public void setValue(String fieldName, String fieldValue) throws Exception
   {
      switch (fieldName) {
      case "Name":
         this._name = fieldValue;
         break;
      case "Key":
         this._key = convertToUUID(fieldValue);
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
      modelMap.put("Name", new FieldModel("String", this._name, false));
      modelMap.put("Key", new FieldModel("UUID", this._key.toString(), true));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportPropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("Name", getPropertyValueString(this._name));
      modelMap.put("Key", getPropertyValueString(this._key));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportKeyPropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("Key", getPropertyValueString(this._key));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportValuePropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("Name", getPropertyValueString(this._name));

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
      obj[0] = this._key;
      obj[1] = this._name;
      
      return obj;
   }

   @Override
   public String fetchDescription() {
      return String.format("%s", this._name);
   }
}
