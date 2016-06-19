package com.lia.lego.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.lia.common.CommonObject;
import com.lia.lego.model.Color;

public class ColorController implements Controller{

   public void delete(CommonObject obj) {
      try {
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         try {
            session = factory.openSession();
            Color color = (Color) obj;
            session.delete(color);
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
            Color color = (Color) obj;
            session.save(color);
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
            Color color = (Color) obj;
            session.update(color);
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
            String hql="from com.lia.lego.model.Color as c where c.Key=:key";
            Query query=session.createQuery(hql);
            query.setString("key", key.toString());
            
            List<Color> colorList = query.list();
            if (colorList.size() > 0){
               output = colorList.get(0);
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
            String hql="from com.lia.lego.model.Color";
            Query query=session.createQuery(hql);
            
            List<Color> colorList = query.list();
            for (Color color : colorList) {
               output.add(color);
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
      Color color = (Color) obj;
      session.delete(color);
   }

   public void create(Session session, CommonObject obj) {
      Color color = (Color) obj;
      session.save(color);
      
   }

   public void update(Session session, CommonObject obj) {

      Color color = (Color) obj;
      session.update(color);
      
   }

   public CommonObject retrieveAccordingKey(Session session, UUID key) {
      CommonObject output = null;
      String hql="from com.lia.lego.model.Color as c where c.Key=:key";
      Query query=session.createQuery(hql);
      query.setString("key", key.toString());
      
      List<Color> colorList = query.list();
      if (colorList.size() > 0){
         output = colorList.get(0);
      }
      return output;
   }

   public List<CommonObject> retrieve(Session session) {
      List<CommonObject> output = new ArrayList<CommonObject>();
      String hql="from com.lia.lego.model.Color";
      Query query=session.createQuery(hql);

      List<Color> colorList = query.list();
      for (Color color : colorList) {
         output.add(color);
      }
      return output;
   }

   public void initialize(){
      try {
         com.lia.lego.brickset.business.InventoryController inventoryController = new com.lia.lego.brickset.business.InventoryController();
         
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         
         try {
            session = factory.openSession();
            session.beginTransaction();
            List<String> colorNameList = inventoryController.getColorNameListInSession(session);
            if (colorNameList.size() > 0) {
               String script = "delete from Color"; 
               Query query = session.createQuery(script);
               query.executeUpdate();
            
               for (String colorName : colorNameList) {
                  UUID key = UUID.randomUUID();
                  Color color = new Color(colorName, key);
                  create(session, color);
               }
            }
            session.getTransaction().commit();
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
   
   public CommonObject retrieveAccordingName(Session session, String name) throws Exception {
      CommonObject output = null;
      String hql="from com.lia.lego.model.Color as c where c.Name=:name";
      Query query=session.createQuery(hql);
      query.setString("name", name);
      
      List<CommonObject> colorList = query.list();
      if (colorList.size() > 1) {
         throw new Exception("Duplicated Color Name:" + name);
      }
      if (colorList.size() > 0){
         output = colorList.get(0);
      }
      return output;
   }
}
