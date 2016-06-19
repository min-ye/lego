package com.lia.lego.brickset.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.lia.common.CommonObject;
import com.lia.common.mysql.FieldModel;

public class Set extends CommonObject {
   private String _setID = "";
   private String _number = "";
   private String _variant = "";
   private String _theme = "";
   private String _subTheme = "";
   private String _year = "";
   private String _name = "";
   private String _minifigs = "";
   private String _pieces = "";
   private String _priceUK = "";
   private String _priceUS = "";
   private String _priceCA = "";
   private String _priceEU = "";
   private String _imageURL = "";

   
   public Set() { }

   public Set(String setID,
              String number,
              String variant,
              String theme,
              String subTheme,
              String year,
              String name,
              String minifigs,
              String pieces,
              String priceUK,
              String priceUS,
              String priceCA,
              String priceEU,
              String imageURL){
      this._setID = setID;
      this._number = number;
      this._variant = variant;
      this._theme = theme;
      this._subTheme = subTheme;
      this._year = year;
      this._name = name;
      this._minifigs = minifigs;
      this._pieces = pieces;
      this._priceUK = priceUK;
      this._priceUS = priceUS;
      this._priceCA = priceCA;
      this._priceEU = priceEU;
      this._imageURL = imageURL;
   }
   
   public Set(CommonObject commonObject){
      Object[] object = commonObject.fetchObject();
      this._setID = convertToString(object[0]);
      this._number = convertToString(object[1]);
      this._variant = convertToString(object[2]);
      this._theme = convertToString(object[3]);
      this._subTheme = convertToString(object[4]);
      this._year = convertToString(object[5]);
      this._name = convertToString(object[6]);
      this._minifigs = convertToString(object[7]);
      this._pieces = convertToString(object[8]);
      this._priceUK = convertToString(object[9]);
      this._priceUS = convertToString(object[10]);
      this._priceCA = convertToString(object[11]);
      this._priceEU = convertToString(object[12]);
      this._imageURL = convertToString(object[13]);
   }
   
   public String getSetID() {
      return this._setID;
   }

   public void setSetID(String setID) {
      this._setID = setID;
   }

   public String getNumber() {
      return this._number;
   }

   public void setNumber(String number) {
      this._number = number;
   }

   public String getVariant() {
      return this._variant;
   }

   public void setVariant(String variant) {
      this._variant = variant;
   }

   public String getTheme() {
      return this._theme;
   }

   public void setTheme(String theme) {
      this._theme = theme;
   }

   public String getSubTheme() {
      return this._subTheme;
   }

   public void setSubTheme(String subTheme) {
      this._subTheme = subTheme;
   }

   public String getYear() {
      return this._year;
   }

   public void setYear(String year) {
      this._year = year;
   }

   public String getName() {
      return this._name;
   }

   public void setName(String name) {
      this._name = name;
   }

   public String getMinifigs() {
      return this._minifigs;
   }

   public void setMinifigs(String minifigs) {
      this._minifigs = minifigs;
   }

   public String getPieces() {
      return this._pieces;
   }

   public void setPieces(String pieces) {
      this._pieces = pieces;
   }

   public String getPriceUK() {
      return this._priceUK;
   }

   public void setPriceUK(String priceUK) {
      this._priceUK = priceUK;
   }

   public String getPriceUS() {
      return this._priceUS;
   }

   public void setPriceUS(String priceUS) {
      this._priceUS = priceUS;
   }

   public String getPriceCA() {
      return this._priceCA;
   }

   public void setPriceCA(String priceCA) {
      this._priceCA = priceCA;
   }

   public String getPriceEU() {
      return this._priceEU;
   }

   public void setPriceEU(String priceEU) {
      this._priceEU = priceEU;
   }

   public String getImageURL() {
      return this._imageURL;
   }

   public void setImageURL(String imageURL) {
      this._imageURL = imageURL;
   }


   
   @Override
   public String fetchObjectName(){
      return "BrickSet";
   }
   
   @Override
   public String getPropertyValue(String fieldName) throws Exception{
      switch (fieldName){
      case "SetID":
         return this._setID;
      case "Number":
         return this._number;
      case "Variant":
         return this._variant;
      case "Theme":
         return this._theme;
      case "SubTheme":
         return this._subTheme;
      case "Year":
         return this._year;
      case "Name":
         return this._name;
      case "Minifigs":
         return this._minifigs;
      case "Pieces":
         return this._pieces;
      case "PriceUK":
         return this._priceUK;
      case "PriceUS":
         return this._priceUS;
      case "PriceCA":
         return this._priceCA;
      case "PriceEU":
         return this._priceEU;
      case "ImageURL":
         return this._imageURL;

      default:
         throw new Exception(String.format("Unknown Field Name:[%s]", fieldName));
      }
   }
   
   @Override
   public void setValue(String fieldName, String fieldValue) throws Exception
   {
      switch (fieldName) {
      case "SetID":
         this._setID = fieldValue;
         break;
      case "Number":
         this._number = fieldValue;
         break;
      case "Variant":
         this._variant = fieldValue;
         break;
      case "Theme":
         this._theme = fieldValue;
         break;
      case "SubTheme":
         this._subTheme = fieldValue;
         break;
      case "Year":
         this._year = fieldValue;
         break;
      case "Name":
         this._name = fieldValue;
         break;
      case "Minifigs":
         this._minifigs = fieldValue;
         break;
      case "Pieces":
         this._pieces = fieldValue;
         break;
      case "PriceUK":
         this._priceUK = fieldValue;
         break;
      case "PriceUS":
         this._priceUS = fieldValue;
         break;
      case "PriceCA":
         this._priceCA = fieldValue;
         break;
      case "PriceEU":
         this._priceEU = fieldValue;
         break;
      case "ImageURL":
         this._imageURL = fieldValue;
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
      modelMap.put("SetID", new FieldModel("String", this._setID, true));
      modelMap.put("Number", new FieldModel("String", this._number, false));
      modelMap.put("Variant", new FieldModel("String", this._variant, false));
      modelMap.put("Theme", new FieldModel("String", this._theme, false));
      modelMap.put("SubTheme", new FieldModel("String", this._subTheme, false));
      modelMap.put("Year", new FieldModel("String", this._year, false));
      modelMap.put("Name", new FieldModel("String", this._name, false));
      modelMap.put("Minifigs", new FieldModel("String", this._minifigs, false));
      modelMap.put("Pieces", new FieldModel("String", this._pieces, false));
      modelMap.put("PriceUK", new FieldModel("String", this._priceUK, false));
      modelMap.put("PriceUS", new FieldModel("String", this._priceUS, false));
      modelMap.put("PriceCA", new FieldModel("String", this._priceCA, false));
      modelMap.put("PriceEU", new FieldModel("String", this._priceEU, false));
      modelMap.put("ImageURL", new FieldModel("String", this._imageURL, false));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportPropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("SetID", getPropertyValueString(this._setID));
      modelMap.put("Number", getPropertyValueString(this._number));
      modelMap.put("Variant", getPropertyValueString(this._variant));
      modelMap.put("Theme", getPropertyValueString(this._theme));
      modelMap.put("SubTheme", getPropertyValueString(this._subTheme));
      modelMap.put("Year", getPropertyValueString(this._year));
      modelMap.put("Name", getPropertyValueString(this._name));
      modelMap.put("Minifigs", getPropertyValueString(this._minifigs));
      modelMap.put("Pieces", getPropertyValueString(this._pieces));
      modelMap.put("PriceUK", getPropertyValueString(this._priceUK));
      modelMap.put("PriceUS", getPropertyValueString(this._priceUS));
      modelMap.put("PriceCA", getPropertyValueString(this._priceCA));
      modelMap.put("PriceEU", getPropertyValueString(this._priceEU));
      modelMap.put("ImageURL", getPropertyValueString(this._imageURL));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportKeyPropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("SetID", getPropertyValueString(this._setID));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportValuePropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("Number", getPropertyValueString(this._number));
      modelMap.put("Variant", getPropertyValueString(this._variant));
      modelMap.put("Theme", getPropertyValueString(this._theme));
      modelMap.put("SubTheme", getPropertyValueString(this._subTheme));
      modelMap.put("Year", getPropertyValueString(this._year));
      modelMap.put("Name", getPropertyValueString(this._name));
      modelMap.put("Minifigs", getPropertyValueString(this._minifigs));
      modelMap.put("Pieces", getPropertyValueString(this._pieces));
      modelMap.put("PriceUK", getPropertyValueString(this._priceUK));
      modelMap.put("PriceUS", getPropertyValueString(this._priceUS));
      modelMap.put("PriceCA", getPropertyValueString(this._priceCA));
      modelMap.put("PriceEU", getPropertyValueString(this._priceEU));
      modelMap.put("ImageURL", getPropertyValueString(this._imageURL));

      return modelMap;
   }
   
   @Override
   public ArrayList<String> fetchPropertyName(){
      ArrayList<String> fieldNameList = new ArrayList<String>();
      fieldNameList.add("SetID");
      fieldNameList.add("Number");
      fieldNameList.add("Variant");
      fieldNameList.add("Theme");
      fieldNameList.add("SubTheme");
      fieldNameList.add("Year");
      fieldNameList.add("Name");
      fieldNameList.add("Minifigs");
      fieldNameList.add("Pieces");
      fieldNameList.add("PriceUK");
      fieldNameList.add("PriceUS");
      fieldNameList.add("PriceCA");
      fieldNameList.add("PriceEU");
      fieldNameList.add("ImageURL");

      return fieldNameList;
   }
   
   @Override
   public Object[] fetchObject() {
      Object[] obj = new Object[14];
      obj[0] = this._setID;
      obj[1] = this._number;
      obj[2] = this._variant;
      obj[3] = this._theme;
      obj[4] = this._subTheme;
      obj[5] = this._year;
      obj[6] = this._name;
      obj[7] = this._minifigs;
      obj[8] = this._pieces;
      obj[9] = this._priceUK;
      obj[10] = this._priceUS;
      obj[11] = this._priceCA;
      obj[12] = this._priceEU;
      obj[13] = this._imageURL;

      return obj;
   }

   @Override
   public String fetchDescription() {
      return String.format("%s(%s)", this._name);
   }
}
