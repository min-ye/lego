package com.lia.lego.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.lia.common.CommonObject;
import com.lia.common.mysql.FieldModel;

public class InventoryMap extends CommonObject implements Serializable{
   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   private String _setNumber = "";
   private String _elementID = "";
   private Integer _quantity = 0;
   private UUID _setKey = null;
   private UUID _brickKey = null;

   
   public InventoryMap() { }

   public InventoryMap(String setNumber,
                       String elementID,
                       Integer quantity, 
                       UUID setKey,
                       UUID brickKey){
      this._setNumber = setNumber;
      this._elementID = elementID;
      this._quantity = quantity;
      this._setKey = setKey;
      this._brickKey = brickKey;
   }
   
   public InventoryMap(CommonObject commonObject){
      Object[] object = commonObject.fetchObject();
      this._setNumber = convertToString(object[0]);
      this._elementID = convertToString(object[1]);
      this._quantity = convertToInteger(object[2]);
      this._setKey = convertToUUID(object[3]);
      this._brickKey = convertToUUID(object[4]);
   }
   
   public String getSetNumber() {
      return this._setNumber;
   }

   public void setSetNumber(String setNumber) {
      this._setNumber = setNumber;
   }
   
   public String getElementID() {
      return this._elementID;
   }

   public void setElementID(String elementID) {
      this._elementID = elementID;
   }
   
   public UUID getSetKey() {
      return this._setKey;
   }

   public void setSetKey(UUID setKey) {
      this._setKey = setKey;
   }

   public UUID getBrickKey() {
      return this._brickKey;
   }

   public void setBrickKey(UUID brickKey) {
      this._brickKey = brickKey;
   }

   public Integer getQuantity() {
      return this._quantity;
   }

   public void setQuantity(Integer quantity) {
      this._quantity = quantity;
   }
   
   @Override
   public String fetchObjectName(){
      return "InventoryMap";
   }
   
   @Override
   public String getPropertyValue(String fieldName) throws Exception{
      switch (fieldName){
      case "SetNumber":
         return this._setNumber.toString();
      case "ElementID":
         return this._elementID.toString();
      case "Quantity":
         return this._quantity.toString();
      case "SetKey":
         return this._setKey.toString();
      case "BrickKey":
         return this._brickKey.toString();

      default:
         throw new Exception(String.format("Unknown Field Name:[%s]", fieldName));
      }
   }
   
   @Override
   public void setValue(String fieldName, String fieldValue) throws Exception
   {
      switch (fieldName) {
      case "SetNumber":
         this._setNumber = convertToString(fieldValue);
         break;
      case "ElementID":
         this._elementID = convertToString(fieldValue);
         break;
      case "Quantity":
         this._quantity = convertToInteger(fieldValue);
         break;
      case "SetKey":
         this._setKey = convertToUUID(fieldValue);
         break;
      case "BrickKey":
         this._brickKey = convertToUUID(fieldValue);
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
      modelMap.put("SetNumber", new FieldModel("String", this._setNumber.toString(), true));
      modelMap.put("ElementID", new FieldModel("String", this._elementID.toString(), true));
      modelMap.put("Quantity", new FieldModel("Integer", this._quantity.toString(), false));
      modelMap.put("SetKey", new FieldModel("UUID", this._setKey.toString(), false));
      modelMap.put("BrickKey", new FieldModel("UUID", this._brickKey.toString(), false));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportPropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("SetNumber", getPropertyValueString(this._setNumber));
      modelMap.put("ElementID", getPropertyValueString(this._elementID));
      modelMap.put("Quantity", getPropertyValueString(this._quantity));
      modelMap.put("SetKey", getPropertyValueString(this._setKey));
      modelMap.put("BrickKey", getPropertyValueString(this._brickKey));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportKeyPropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("SetNumber", getPropertyValueString(this._setNumber));
      modelMap.put("ElementID", getPropertyValueString(this._elementID));

      return modelMap;
   }
   
   @Override
   public Map<String, String> exportValuePropertyMap(){
      Map<String, String> modelMap = new HashMap<String, String>();
      modelMap.put("Quantity", getPropertyValueString(this._quantity));
      modelMap.put("SetKey", getPropertyValueString(this._setKey));
      modelMap.put("BrickKey", getPropertyValueString(this._brickKey));

      return modelMap;
   }
   
   @Override
   public ArrayList<String> fetchPropertyName(){
      ArrayList<String> fieldNameList = new ArrayList<String>();
      fieldNameList.add("SetNumber");
      fieldNameList.add("ElementID");
      fieldNameList.add("Quantity");
      fieldNameList.add("SetKey");
      fieldNameList.add("BrickKey");

      return fieldNameList;
   }
   
   @Override
   public Object[] fetchObject() {
      Object[] obj = new Object[5];
      obj[0] = this._setNumber;
      obj[1] = this._elementID;
      obj[2] = this._quantity;
      obj[3] = this._setKey;
      obj[4] = this._brickKey;

      return obj;
   }

   @Override
   public String fetchDescription() {
      return String.format("%s(%s)", this._setKey.toString(), this._brickKey.toString());
   }
   
   @Override
   public int hashCode()
   {
       final int prime = 31;
       int result = 1;
       result = prime * result + ((_setNumber == null) ? 0 : _setNumber.hashCode());
       result = prime * result + ((_elementID == null) ? 0 : _elementID.hashCode());
       return result;
   }

   @Override
   public boolean equals(Object obj)
   {
       if (this == obj)
           return true;
       if (obj == null)
           return false;
       if (getClass() != obj.getClass())
           return false;
       InventoryMap other = (InventoryMap) obj;
       if (_setNumber == null)
       {
           if (other._setNumber != null)
               return false;
       }
       else if (!_setNumber.equals(other._setNumber))
           return false;
       if (_elementID == null)
       {
           if (other._elementID != null)
               return false;
       }
       else if (!_elementID.equals(other._elementID))
           return false;
       return true;
   }
}
