package com.lia.lego.brickset.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.lia.common.CommonObject;
import com.lia.common.HibernateHelper;
import com.lia.lego.business.Controller;
import com.lia.lego.model.Inventory;

public class InventoryController implements Controller{

   public void delete(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Inventory inventory = (Inventory) obj;
         session.delete(inventory);
      }
      finally {
         HibernateHelper.closeSession();
      }
   }

   public void create(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Inventory inventory = (Inventory) obj;
         session.save(inventory);
      }
      finally {
         HibernateHelper.closeSession();
      }
   }

   public void update(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Inventory inventory = (Inventory) obj;
         session.update(inventory);
      }
      finally {
         HibernateHelper.closeSession();
      }
   }

   public CommonObject retrieveAccordingKey(UUID key) {
      CommonObject output = null;
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         String hql="from com.lia.lego.brickset.Inventory as i where i.Key=:key";
         Query query=session.createQuery(hql);
         query.setString("key", key.toString());
            
         List<Inventory> inventoryList = query.list();
         if (inventoryList.size() > 0){
            output = inventoryList.get(0);
         }
      }
      finally {
         HibernateHelper.closeSession();
      }
      return output;
   }

   public List<CommonObject> retrieve() {
      List<CommonObject> output = new ArrayList<CommonObject>();
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         String hql="from com.lia.lego.brickset.Inventory";
         Query query=session.createQuery(hql);

         List<Inventory> inventoryList = query.list();
         for (Inventory inventory : inventoryList) {
            output.add(inventory);
         }
      }
      finally {
         HibernateHelper.closeSession();
      }
      return output;
   }
   
   public List<String> getColorNameList() {
      List<String> output = new ArrayList<String>();
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         List colorList = session.createQuery("select distinct i.Colour FROM com.lia.lego.brickset.model.Inventory i").list();
         for (Iterator iterator = colorList.iterator(); iterator.hasNext();){
            String color = (String) iterator.next();
            if (!colorList.contains(color)) {
               output.add(color);
            }
         }
      }
      finally {
         HibernateHelper.closeSession();
      }
      return output;
   }
   
   public List<String> getCategoryNameList() {
      List<String> output = new ArrayList<String>();
      Session session = null;
      try {
         List categoryList = session.createQuery("select distinct i.Category FROM com.lia.lego.brickset.model.Inventory i").list();
         for (Iterator iterator = categoryList.iterator(); iterator.hasNext();){
            String category = (String) iterator.next();
            if (!categoryList.contains(category)) {
               output.add(category);
            }
         }
      }
      finally {
         HibernateHelper.closeSession();
      }
      return output;
   }
   
   public List<String[]> getBrickList() {
      List<String[]> output = new ArrayList<String[]>();
      Session session = null;
      try {
         List brickList = session.createQuery("select distinct i.PartID, i.DesignID, i.PartName, i.ImageURL, i.Colour, i.Category FROM com.lia.lego.brickset.model.Inventory i").list();
         for (Iterator iterator = brickList.iterator(); iterator.hasNext();){
            Object[] brick = (Object[]) iterator.next();
            String[] brickString = new String[6];
            for (Integer index = 0; index <6; index ++) {
               brickString[index] = brick[index].toString();
            }
         
            output.add(brickString);
         }
      }
      finally {
         HibernateHelper.closeSession();
      }
      return output;
   }

}
