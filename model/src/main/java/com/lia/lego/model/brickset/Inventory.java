package com.lia.lego.model.brickset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.lia.common.mysql.FieldModel;
import com.lia.common.mysql.CommonObject;

public class Inventory extends CommonObject{
   private String _setNumber = "";
   private String _partID = "";
   private String _quantity = "";
   private String _colour = "";
   private String _category = "";
   private String _designID = "";
   private String _partName = "";
   private String _imageUrl = "";
   private String _setCount = "";
   
   public Inventory(String setNumber, String partID, String quantity,
                    String colour, String category, String designID,
                    String partName, String imageUrl, String setCount) {
      this._setNumber = setNumber;
      this._partID = partID;
      this._quantity = quantity;
      this._colour = colour;
      this._category = category;
      this._designID = designID;
      this._partName = partName;
      this._imageUrl = imageUrl;
      this._setCount = setCount;
   }
   
   public Inventory(CommonObject commonObject){
      Object[] object = commonObject.fetchObject();
      this._setNumber = object[0].toString();
      this._partID = object[1].toString();
      this._quantity = object[2].toString();
      this._colour = object[3].toString();
      this._category = object[4].toString();
      this._designID = object[5].toString();
      this._partName = object[6].toString();
      this._imageUrl = object[7].toString();
      this._setCount = object[8].toString();
   }
   
   public String getSetNumber() {
      return _setNumber;
   }
   public void setSetNumber(String setNumber) {
      this._setNumber = setNumber;
   }
   public String getPartID() {
      return _partID;
   }
   public void setPartID(String partID) {
      this._partID = partID;
   }
   public String getQuantity() {
      return _quantity;
   }
   public void setQuantity(String quantity) {
      this._quantity = quantity;
   }
   public String getColour() {
      return _colour;
   }
   public void setColour(String colour) {
      this._colour = colour;
   }
   public String getCategory() {
      return _category;
   }
   public void setCategory(String category) {
      this._category = category;
   }
   public String getDesignID() {
      return _designID;
   }
   public void setDesignID(String designID) {
      this._designID = designID;
   }
   public String getPartName() {
      return _partName;
   }
   public void setPartName(String partName) {
      this._partName = partName;
   }
   public String getImageUrl() {
      return _imageUrl;
   }
   public void setImageUrl(String imageUrl) {
      this._imageUrl = imageUrl;
   }
   public String getSetCount() {
      return _setCount;
   }
   public void setSetCount(String setCount) {
      this._setCount = setCount;
   }
   
   @Override
   public String fetchObjectName(){
      return "BrickSetInventory";
   }
   
   @Override
   public String getFieldValue(String fieldName) throws Exception{
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
         return this._imageUrl;
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
            this._imageUrl = fieldValue;
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
      modelMap.put("SetNumber", new FieldModel("string", this._setNumber, true));
      modelMap.put("PartID", new FieldModel("string", this._partID, false));
      modelMap.put("Quantity", new FieldModel("string", this._quantity, false));
      modelMap.put("Colour", new FieldModel("string", this._colour, false));
      modelMap.put("Category", new FieldModel("string", this._category, false));
      modelMap.put("DesignID", new FieldModel("string", this._designID, false));
      modelMap.put("PartName", new FieldModel("string", this._partName, false));
      modelMap.put("ImageURL", new FieldModel("string", this._imageUrl, false));
      modelMap.put("SetCount", new FieldModel("string", this._setCount, false));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportFieldMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("SetNumber", getFieldValueString(this._setNumber));
      modelMap.put("PartID", getFieldValueString(this._partID));
      modelMap.put("Quantity", getFieldValueString(this._quantity));
      modelMap.put("Colour", getFieldValueString(this._colour));
      modelMap.put("Category", getFieldValueString(this._category));
      modelMap.put("DesignID", getFieldValueString(this._designID));
      modelMap.put("PartName", getFieldValueString(this._partName));
      modelMap.put("ImageURL", getFieldValueString(this._imageUrl));
      modelMap.put("SetCount", getFieldValueString(this._setCount));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportKeyFieldMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("SetNumber", getFieldValueString(this._setNumber));
      modelMap.put("PartID", getFieldValueString(this._partID));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportValueFieldMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      
      modelMap.put("Quantity", getFieldValueString(this._quantity));
      modelMap.put("Colour", getFieldValueString(this._colour));
      modelMap.put("Category", getFieldValueString(this._category));
      modelMap.put("DesignID", getFieldValueString(this._designID));
      modelMap.put("PartName", getFieldValueString(this._partName));
      modelMap.put("ImageURL", getFieldValueString(this._imageUrl));
      modelMap.put("SetCount", getFieldValueString(this._setCount));

      return modelMap;
   }

   @Override
   public ArrayList<String> fetchFieldName() {
      ArrayList<String> fieldNameArray = new ArrayList<String>();
      
      fieldNameArray.add("SetNumber");
      fieldNameArray.add("PartID");
      fieldNameArray.add("Quantity");
      fieldNameArray.add("Colour");
      fieldNameArray.add("Category");
      fieldNameArray.add("DesignID");
      fieldNameArray.add("PartName");
      fieldNameArray.add("ImageURL");
      fieldNameArray.add("SetCount");
      return fieldNameArray;
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
      obj[6] = this._partID;
      obj[7] = this._imageUrl;
      obj[8] = this._setCount;
      return obj;
   }
}
