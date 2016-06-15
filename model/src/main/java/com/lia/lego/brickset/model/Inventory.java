package com.lia.lego.brickset.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.lia.common.CommonObject;
import com.lia.common.mysql.FieldModel;

public class Inventory extends CommonObject implements Serializable {
   private String _setNumber = "";
   private String _partID = "";
   private String _quantity = "";
   private String _colour = "";
   private String _category = "";
   private String _designID = "";
   private String _partName = "";
   private String _imageURL = "";
   private String _setCount = "";

   
   public Inventory() { }

   public Inventory(String setNumber,
                    String partID,
                    String quantity,
                    String colour,
                    String category,
                    String designID,
                    String partName,
                    String imageURL,
                    String setCount){
      this._setNumber = setNumber;
      this._partID = partID;
      this._quantity = quantity;
      this._colour = colour;
      this._category = category;
      this._designID = designID;
      this._partName = partName;
      this._imageURL = imageURL;
      this._setCount = setCount;
   }
   
   public Inventory(CommonObject commonObject){
      Object[] object = commonObject.fetchObject();
      this._setNumber = convertToString(object[0]);
      this._partID = convertToString(object[1]);
      this._quantity = convertToString(object[2]);
      this._colour = convertToString(object[3]);
      this._category = convertToString(object[4]);
      this._designID = convertToString(object[5]);
      this._partName = convertToString(object[6]);
      this._imageURL = convertToString(object[7]);
      this._setCount = convertToString(object[8]);
   }
   
   public String getSetNumber() {
      return this._setNumber;
   }

   public void setSetNumber(String setNumber) {
      this._setNumber = setNumber;
   }

   public String getPartID() {
      return this._partID;
   }

   public void setPartID(String partID) {
      this._partID = partID;
   }

   public String getQuantity() {
      return this._quantity;
   }

   public void setQuantity(String quantity) {
      this._quantity = quantity;
   }

   public String getColour() {
      return this._colour;
   }

   public void setColour(String colour) {
      this._colour = colour;
   }

   public String getCategory() {
      return this._category;
   }

   public void setCategory(String category) {
      this._category = category;
   }

   public String getDesignID() {
      return this._designID;
   }

   public void setDesignID(String designID) {
      this._designID = designID;
   }

   public String getPartName() {
      return this._partName;
   }

   public void setPartName(String partName) {
      this._partName = partName;
   }

   public String getImageURL() {
      return this._imageURL;
   }

   public void setImageURL(String imageURL) {
      this._imageURL = imageURL;
   }

   public String getSetCount() {
      return this._setCount;
   }

   public void setSetCount(String setCount) {
      this._setCount = setCount;
   }


   
   @Override
   public String fetchObjectName(){
      return "Inventory";
   }
   
   @Override
   public String getPropertyValue(String fieldName) throws Exception{
      switch (fieldName){
      case "SetNumber":
         return this._setNumber;
      case "PartID":
         return this._partID;
      case "Quantity":
         return this._quantity;
      case "Colour":
         return this._colour;
      case "Category":
         return this._category;
      case "DesignID":
         return this._designID;
      case "PartName":
         return this._partName;
      case "ImageURL":
         return this._imageURL;
      case "SetCount":
         return this._setCount;

      default:
         throw new Exception(String.format("Unknown Field Name:[%s]", fieldName));
      }
   }
   
   @Override
   public void setValue(String fieldName, String fieldValue) throws Exception
   {
      switch (fieldName) {
      case "SetNumber":
         this._setNumber = fieldValue;
         break;
      case "PartID":
         this._partID = fieldValue;
         break;
      case "Quantity":
         this._quantity = fieldValue;
         break;
      case "Colour":
         this._colour = fieldValue;
         break;
      case "Category":
         this._category = fieldValue;
         break;
      case "DesignID":
         this._designID = fieldValue;
         break;
      case "PartName":
         this._partName = fieldValue;
         break;
      case "ImageURL":
         this._imageURL = fieldValue;
         break;
      case "SetCount":
         this._setCount = fieldValue;
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
      modelMap.put("SetNumber", new FieldModel("String", this._setNumber, true));
      modelMap.put("PartID", new FieldModel("String", this._partID, true));
      modelMap.put("Quantity", new FieldModel("String", this._quantity, false));
      modelMap.put("Colour", new FieldModel("String", this._colour, false));
      modelMap.put("Category", new FieldModel("String", this._category, false));
      modelMap.put("DesignID", new FieldModel("String", this._designID, false));
      modelMap.put("PartName", new FieldModel("String", this._partName, false));
      modelMap.put("ImageURL", new FieldModel("String", this._imageURL, false));
      modelMap.put("SetCount", new FieldModel("String", this._setCount, false));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportPropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("SetNumber", getPropertyValueString(this._setNumber));
      modelMap.put("PartID", getPropertyValueString(this._partID));
      modelMap.put("Quantity", getPropertyValueString(this._quantity));
      modelMap.put("Colour", getPropertyValueString(this._colour));
      modelMap.put("Category", getPropertyValueString(this._category));
      modelMap.put("DesignID", getPropertyValueString(this._designID));
      modelMap.put("PartName", getPropertyValueString(this._partName));
      modelMap.put("ImageURL", getPropertyValueString(this._imageURL));
      modelMap.put("SetCount", getPropertyValueString(this._setCount));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportKeyPropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("SetNumber", getPropertyValueString(this._setNumber));
      modelMap.put("PartID", getPropertyValueString(this._partID));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportValuePropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("Quantity", getPropertyValueString(this._quantity));
      modelMap.put("Colour", getPropertyValueString(this._colour));
      modelMap.put("Category", getPropertyValueString(this._category));
      modelMap.put("DesignID", getPropertyValueString(this._designID));
      modelMap.put("PartName", getPropertyValueString(this._partName));
      modelMap.put("ImageURL", getPropertyValueString(this._imageURL));
      modelMap.put("SetCount", getPropertyValueString(this._setCount));

      return modelMap;
   }
   
   @Override
   public ArrayList<String> fetchPropertyName(){
      ArrayList<String> fieldNameList = new ArrayList<String>();
      fieldNameList.add("SetNumber");
      fieldNameList.add("PartID");
      fieldNameList.add("Quantity");
      fieldNameList.add("Colour");
      fieldNameList.add("Category");
      fieldNameList.add("DesignID");
      fieldNameList.add("PartName");
      fieldNameList.add("ImageURL");
      fieldNameList.add("SetCount");

      return fieldNameList;
   }
   
   @Override
   public Object[] fetchObject() {
      Object[] obj = new Object[9];
      obj[0] = this._setNumber;
      obj[1] = this._partID;
      obj[2] = this._quantity;
      obj[3] = this._colour;
      obj[4] = this._category;
      obj[5] = this._designID;
      obj[6] = this._partName;
      obj[7] = this._imageURL;
      obj[8] = this._setCount;

      return obj;
   }

   @Override
   public String fetchDescription() {
      return String.format("%s(%s)", this._setNumber, this._partID);
   }
}
