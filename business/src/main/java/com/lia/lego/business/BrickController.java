package com.lia.lego.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.lia.common.CommonHelper;
import com.lia.common.CommonObject;
import com.lia.common.FileHelper;
import com.lia.lego.model.Brick;
import com.lia.lego.model.Category;
import com.lia.lego.model.Color;
import com.lia.lego.model.Set;
import com.lia.lego.model.SubTheme;
import com.lia.lego.model.Theme;

public class BrickController implements Controller{

   public void delete(CommonObject obj) {
      try {
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         try {
            session = factory.openSession();
            Brick brick = (Brick) obj;
            session.delete(brick);
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
            Brick brick = (Brick) obj;
            session.save(brick);
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
            Brick brick = (Brick) obj;
            session.update(brick);
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
            String hql="from com.lia.lego.model.Brick as b where b.Key=:key";
            Query query=session.createQuery(hql);
            query.setString("key", key.toString());
            
            List<Brick> brickList = query.list();
            if (brickList.size() > 0){
               output = brickList.get(0);
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
            String hql="from com.lia.lego.model.Brick";
            Query query=session.createQuery(hql);
            
            List<Brick> brickList = query.list();
            for (Brick brick : brickList) {
               output.add(brick);
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
      Brick brick = (Brick) obj;
      session.delete(brick);
   }

   public void create(Session session, CommonObject obj) {
      Brick brick = (Brick) obj;
      session.save(brick);
      
   }

   public void update(Session session, CommonObject obj) {

      Brick brick = (Brick) obj;
      session.update(brick);
      
   }

   public CommonObject retrieveAccordingKey(Session session, UUID key) {
      CommonObject output = null;
      String hql="from com.lia.lego.model.Brick as b where b.Key=:key";
      Query query=session.createQuery(hql);
      query.setString("key", key.toString());
      
      List<Brick> brickList = query.list();
      if (brickList.size() > 0){
         output = brickList.get(0);
      }
      return output;
   }

   public List<CommonObject> retrieve(Session session) {
      List<CommonObject> output = new ArrayList<CommonObject>();
      String hql="from com.lia.lego.model.Brick";
      Query query=session.createQuery(hql);

      List<Brick> brickList = query.list();
      for (Brick brick : brickList) {
         output.add(brick);
      }
      return output;
   }

   public void initialize() {
      Integer count = 0;
      List<String> logList = new ArrayList<String>();
      try {
         
         com.lia.lego.brickset.business.InventoryController inventoryController = new com.lia.lego.brickset.business.InventoryController();
         ColorController colorController = new ColorController();
         CategoryController categoryController = new CategoryController();
         
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         
         try {
            session = factory.openSession();
            session.beginTransaction();
            String script = "delete from com.lia.lego.model.Brick"; 
            Query query = session.createQuery(script);
            query.executeUpdate();
            List<String[]> brickList = inventoryController.getBrickList(session);
            for (String[] brickObject : brickList) {
               String elementID = brickObject[0];
               String designID = brickObject[1];
               String name = brickObject[2];
               String imageURL = brickObject[3];
               String colorName = brickObject[4];
               String categoryName = brickObject[5];
               Color color = (Color)colorController.retrieveAccordingName(session, categoryName);
               Category category = (Category)categoryController.retrieveAccordingName(session, categoryName);
               if (color == null) {
                  throw new Exception("Unknown Color Name:" + colorName);
               }
               if (category == null) {
                  throw new Exception("Unknown Category Name:" + categoryName);
               }
               UUID colorKey = color.getKey();
               UUID categoryKey = category.getKey();
               UUID key = UUID.randomUUID();
               Brick brick = new Brick(elementID, designID, colorKey, categoryKey, name, imageURL, key );
               session.save(brick);
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
      String log = "";
      for (String content: logList) {
         log += content + "\r\n";
      }
      //FileHelper.INSTANCE.saveContent(log, "/Users/yemin/workspace/lego/initial.log");
   }
}
