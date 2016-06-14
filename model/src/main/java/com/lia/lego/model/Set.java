package com.lia.lego.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.lia.common.CommonObject;
import com.lia.common.mysql.FieldModel;

public class Set extends CommonObject {
   private String _number = "";
   private Integer _variant = 0;
   private UUID _subThemeKey = null;
   private Short _year = 0;
   private String _name = "";
   private Short _minifigs = 0;
   private Integer _piece = 0;
   private Float _priceUK = 0f;
   private Float _priceUS = 0f;
   private Float _priceCA = 0f;
   private Float _priceEU = 0f;
   private String _imageURL = "";
   private UUID _key = null;

   
   public Set() { }

   public Set(String number,
              Integer variant,
              UUID subThemeKey,
              Short year,
              String name,
              Short minifigs,
              Integer piece,
              Float priceUK,
              Float priceUS,
              Float priceCA,
              Float priceEU,
              String imageURL,
              UUID key){
      this._number = number;
      this._variant = variant;
      this._subThemeKey = subThemeKey;
      this._year = year;
      this._name = name;
      this._minifigs = minifigs;
      this._piece = piece;
      this._priceUK = priceUK;
      this._priceUS = priceUS;
      this._priceCA = priceCA;
      this._priceEU = priceEU;
      this._imageURL = imageURL;
      this._key = key;
   }
   
   public Set(CommonObject commonObject){
      Object[] object = commonObject.fetchObject();
      this._number = convertToString(object[0]);
      this._variant = convertToInteger(object[1]);
      this._subThemeKey = convertToUUID(object[2]);
      this._year = convertToShort(object[3]);
      this._name = convertToString(object[4]);
      this._minifigs = convertToShort(object[5]);
      this._piece = convertToInteger(object[6]);
      this._priceUK = convertToFloat(object[7]);
      this._priceUS = convertToFloat(object[8]);
      this._priceCA = convertToFloat(object[9]);
      this._priceEU = convertToFloat(object[10]);
      this._imageURL = convertToString(object[11]);
      this._key = convertToUUID(object[12]);
   }
   
   public String getNumber() {
      return this._number;
   }

   public void setNumber(String number) {
      this._number = number;
   }

   public Integer getVariant() {
      return this._variant;
   }

   public void setVariant(Integer variant) {
      this._variant = variant;
   }

   public UUID getSubThemeKey() {
      return this._subThemeKey;
   }

   public void setSubThemeKey(UUID subThemeKey) {
      this._subThemeKey = subThemeKey;
   }

   public Short getYear() {
      return this._year;
   }

   public void setYear(Short year) {
      this._year = year;
   }

   public String getName() {
      return this._name;
   }

   public void setName(String name) {
      this._name = name;
   }

   public Short getMinifigs() {
      return this._minifigs;
   }

   public void setMinifigs(Short minifigs) {
      this._minifigs = minifigs;
   }

   public Integer getPiece() {
      return this._piece;
   }

   public void setPiece(Integer piece) {
      this._piece = piece;
   }

   public Float getPriceUK() {
      return this._priceUK;
   }

   public void setPriceUK(Float priceUK) {
      this._priceUK = priceUK;
   }

   public Float getPriceUS() {
      return this._priceUS;
   }

   public void setPriceUS(Float priceUS) {
      this._priceUS = priceUS;
   }

   public Float getPriceCA() {
      return this._priceCA;
   }

   public void setPriceCA(Float priceCA) {
      this._priceCA = priceCA;
   }

   public Float getPriceEU() {
      return this._priceEU;
   }

   public void setPriceEU(Float priceEU) {
      this._priceEU = priceEU;
   }

   public String getImageURL() {
      return this._imageURL;
   }

   public void setImageURL(String imageURL) {
      this._imageURL = imageURL;
   }

   public UUID getKey() {
      return this._key;
   }

   public void setKey(UUID key) {
      this._key = key;
   }


   
   @Override
   public String fetchObjectName(){
      return "Set";
   }
   
   @Override
   public String getPropertyValue(String fieldName) throws Exception{
      switch (fieldName){
      case "Number":
         return this._number;
      case "Variant":
         return this._variant.toString();
      case "SubThemeKey":
         return this._subThemeKey.toString();
      case "Year":
         return this._year.toString();
      case "Name":
         return this._name;
      case "Minifigs":
         return this._minifigs.toString();
      case "Piece":
         return this._piece.toString();
      case "PriceUK":
         return this._priceUK.toString();
      case "PriceUS":
         return this._priceUS.toString();
      case "PriceCA":
         return this._priceCA.toString();
      case "PriceEU":
         return this._priceEU.toString();
      case "ImageURL":
         return this._imageURL;
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
      case "Number":
         this._number = fieldValue;
         break;
      case "Variant":
         this._variant = convertToInteger(fieldValue);
         break;
      case "SubThemeKey":
         this._subThemeKey = convertToUUID(fieldValue);
         break;
      case "Year":
         this._year = convertToShort(fieldValue);
         break;
      case "Name":
         this._name = fieldValue;
         break;
      case "Minifigs":
         this._minifigs = convertToShort(fieldValue);
         break;
      case "Piece":
         this._piece = convertToInteger(fieldValue);
         break;
      case "PriceUK":
         this._priceUK = convertToFloat(fieldValue);
         break;
      case "PriceUS":
         this._priceUS = convertToFloat(fieldValue);
         break;
      case "PriceCA":
         this._priceCA = convertToFloat(fieldValue);
         break;
      case "PriceEU":
         this._priceEU = convertToFloat(fieldValue);
         break;
      case "ImageURL":
         this._imageURL = fieldValue;
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
      modelMap.put("Number", new FieldModel("String", this._number, false));
      modelMap.put("Variant", new FieldModel("Integer", this._variant.toString(), false));
      modelMap.put("SubThemeKey", new FieldModel("UUID", this._subThemeKey.toString(), false));
      modelMap.put("Year", new FieldModel("Short", this._year.toString(), false));
      modelMap.put("Name", new FieldModel("String", this._name, false));
      modelMap.put("Minifigs", new FieldModel("Short", this._minifigs.toString(), false));
      modelMap.put("Piece", new FieldModel("Integer", this._piece.toString(), false));
      modelMap.put("PriceUK", new FieldModel("Float", this._priceUK.toString(), false));
      modelMap.put("PriceUS", new FieldModel("Float", this._priceUS.toString(), false));
      modelMap.put("PriceCA", new FieldModel("Float", this._priceCA.toString(), false));
      modelMap.put("PriceEU", new FieldModel("Float", this._priceEU.toString(), false));
      modelMap.put("ImageURL", new FieldModel("String", this._imageURL, false));
      modelMap.put("Key", new FieldModel("UUID", this._key.toString(), true));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportPropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("Number", getPropertyValueString(this._number));
      modelMap.put("Variant", getPropertyValueString(this._variant));
      modelMap.put("SubThemeKey", getPropertyValueString(this._subThemeKey));
      modelMap.put("Year", getPropertyValueString(this._year));
      modelMap.put("Name", getPropertyValueString(this._name));
      modelMap.put("Minifigs", getPropertyValueString(this._minifigs));
      modelMap.put("Piece", getPropertyValueString(this._piece));
      modelMap.put("PriceUK", getPropertyValueString(this._priceUK));
      modelMap.put("PriceUS", getPropertyValueString(this._priceUS));
      modelMap.put("PriceCA", getPropertyValueString(this._priceCA));
      modelMap.put("PriceEU", getPropertyValueString(this._priceEU));
      modelMap.put("ImageURL", getPropertyValueString(this._imageURL));
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
      modelMap.put("Number", getPropertyValueString(this._number));
      modelMap.put("Variant", getPropertyValueString(this._variant));
      modelMap.put("SubThemeKey", getPropertyValueString(this._subThemeKey));
      modelMap.put("Year", getPropertyValueString(this._year));
      modelMap.put("Name", getPropertyValueString(this._name));
      modelMap.put("Minifigs", getPropertyValueString(this._minifigs));
      modelMap.put("Piece", getPropertyValueString(this._piece));
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
      fieldNameList.add("Number");
      fieldNameList.add("Variant");
      fieldNameList.add("SubThemeKey");
      fieldNameList.add("Year");
      fieldNameList.add("Name");
      fieldNameList.add("Minifigs");
      fieldNameList.add("Piece");
      fieldNameList.add("PriceUK");
      fieldNameList.add("PriceUS");
      fieldNameList.add("PriceCA");
      fieldNameList.add("PriceEU");
      fieldNameList.add("ImageURL");
      fieldNameList.add("Key");

      return fieldNameList;
   }
   
   @Override
   public Object[] fetchObject() {
      Object[] obj = new Object[13];
      obj[0] = this._number;
      obj[1] = this._variant;
      obj[2] = this._subThemeKey;
      obj[3] = this._year;
      obj[4] = this._name;
      obj[5] = this._minifigs;
      obj[6] = this._piece;
      obj[7] = this._priceUK;
      obj[8] = this._priceUS;
      obj[9] = this._priceCA;
      obj[10] = this._priceEU;
      obj[11] = this._imageURL;
      obj[12] = this._key;

      return obj;
   }

   @Override
   public String fetchDescription() {
      return String.format("%s(%s)", this._name);
   }
}
