package com.lia.lego.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.lia.common.CommonObject;
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
            String hql="from com.lia.lego.model.Inventory as i where i.Key=:key";
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
            String hql="from com.lia.lego.model.Inventory";
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
      String hql="from com.lia.lego.model.Inventory as i where i.Key=:key";
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
      String hql="from com.lia.lego.model.Inventory";
      Query query=session.createQuery(hql);

      List<Inventory> inventoryList = query.list();
      for (Inventory inventory : inventoryList) {
         output.add(inventory);
      }
      return output;
   }

}
