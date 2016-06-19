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
import com.lia.lego.business.Controller;
import com.lia.lego.model.Inventory;

public class InventoryController implements Controller{

   public void delete(CommonObject obj) {
      try {
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         try {
            session = factory.openSession();
            Inventory inventory = (Inventory) obj;
            session.delete(inventory);
         }
         catch (Exception ex) {
            throw ex;
         }
         finally {
            if (session != null) {
               if (session.isOpen()) {
                  session.close();
               }
            }
         }
      }
      catch (Exception ex){
         System.out.println(ex.getMessage());
      }
      
   }

   public void create(CommonObject obj) {
      try {
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         try {
            session = factory.openSession();
            Inventory inventory = (Inventory) obj;
            session.save(inventory);
         }
         catch (Exception ex) {
            throw ex;
         }
         finally {
            if (session != null) {
               if (session.isOpen()) {
                  session.close();
               }
            }
         }
      }
      catch (Exception ex){
         System.out.println(ex.getMessage());
      }
      
   }

   public void update(CommonObject obj) {
      try {
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         try {
            session = factory.openSession();
            Inventory inventory = (Inventory) obj;
            session.update(inventory);
         }
         catch (Exception ex) {
            throw ex;
         }
         finally {
            if (session != null) {
               if (session.isOpen()) {
                  session.close();
               }
            }
         }
      }
      catch (Exception ex){
         System.out.println(ex.getMessage());
      }
      
   }

   public CommonObject retrieveAccordingKey(UUID key) {
      CommonObject output = null;
      try {
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         try {
            session = factory.openSession();
            String hql="from com.lia.lego.brickset.Inventory as i where i.Key=:key";
            Query query=session.createQuery(hql);
            query.setString("key", key.toString());
            
            List<Inventory> inventoryList = query.list();
            if (inventoryList.size() > 0){
               output = inventoryList.get(0);
            }
         }
         catch (Exception ex) {
            throw ex;
         }
         finally {
            if (session != null) {
               if (session.isOpen()) {
                  session.close();
               }
            }
         }
      }
      catch (Exception ex){
         System.out.println(ex.getMessage());
      }
      return output;
   }

   public List<CommonObject> retrieve() {
      List<CommonObject> output = new ArrayList<CommonObject>();
      try {
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         try {
            session = factory.openSession();
            String hql="from com.lia.lego.brickset.Inventory";
            Query query=session.createQuery(hql);
            
            List<Inventory> inventoryList = query.list();
            for (Inventory inventory : inventoryList) {
               output.add(inventory);
            }
         }
         catch (Exception ex) {
            throw ex;
         }
         finally {
            if (session != null) {
               if (session.isOpen()) {
                  session.close();
               }
            }
         }
      }
      catch (Exception ex){
         System.out.println(ex.getMessage());
      }
      return output;
   }

   public void delete(Session session, CommonObject obj) {
      Inventory inventory = (Inventory) obj;
      session.delete(inventory);
   }

   public void create(Session session, CommonObject obj) {
      Inventory inventory = (Inventory) obj;
      session.save(inventory);
      
   }

   public void update(Session session, CommonObject obj) {

      Inventory inventory = (Inventory) obj;
      session.update(inventory);
      
   }

   public CommonObject retrieveAccordingKey(Session session, UUID key) {
      CommonObject output = null;
      String hql="from com.lia.lego.brickset.Inventory as i where i.Key=:key";
      Query query=session.createQuery(hql);
      query.setString("key", key.toString());
      
      List<Inventory> inventoryList = query.list();
      if (inventoryList.size() > 0){
         output = inventoryList.get(0);
      }
      return output;
   }

   public List<CommonObject> retrieve(Session session) {
      List<CommonObject> output = new ArrayList<CommonObject>();
      String hql="from com.lia.lego.brickset.Inventory";
      Query query=session.createQuery(hql);

      List<Inventory> inventoryList = query.list();
      for (Inventory inventory : inventoryList) {
         output.add(inventory);
      }
      return output;
   }
   
   public List<String> getColorNameListInSession(Session session) {
      List<String> output = new ArrayList<String>();
      List colorList = session.createQuery("select distinct i.Colour FROM com.lia.lego.brickset.model.Inventory i").list();
      for (Iterator iterator = colorList.iterator(); iterator.hasNext();){
         String color = (String) iterator.next();
         if (!colorList.contains(color)) {
            output.add(color);
         }
      }
      return output;
   }
   
   public List<String> getCategoryNameListInSession(Session session) {
      List<String> output = new ArrayList<String>();
      List categoryList = session.createQuery("select distinct i.Category FROM com.lia.lego.brickset.model.Inventory i").list();
      for (Iterator iterator = categoryList.iterator(); iterator.hasNext();){
         String category = (String) iterator.next();
         if (!categoryList.contains(category)) {
            output.add(category);
         }
      }
      return output;
   }
   
   public List<String[]> getBrickList(Session session) {
      List<String[]> output = new ArrayList<String[]>();
      List brickList = session.createQuery("select distinct i.PartID, i.DesignID, i.PartName, i.ImageURL, i.Colour, i.Category FROM com.lia.lego.brickset.model.Inventory i").list();
      for (Iterator iterator = brickList.iterator(); iterator.hasNext();){
         Object[] brick = (Object[]) iterator.next();
         String[] brickString = new String[6];
         for (Integer index = 0; index <6; index ++) {
            brickString[index] = brick[index].toString();
         }
         
         output.add(brickString);
      }
      return output;
   }

}
