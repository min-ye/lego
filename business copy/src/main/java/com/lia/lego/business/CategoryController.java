package com.lia.lego.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.lia.common.CommonObject;
import com.lia.lego.model.Category;
import com.lia.lego.model.Color;
import com.lia.lego.model.Theme;

public class CategoryController implements Controller{

   public void delete(CommonObject obj) {
      try {
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         try {
            session = factory.openSession();
            Category category = (Category) obj;
            session.delete(category);
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
            Category category = (Category) obj;
            session.save(category);
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
            Category category = (Category) obj;
            session.update(category);
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
            String hql="from com.lia.lego.model.Category as c where c.Key=:key";
            Query query=session.createQuery(hql);
            query.setString("key", key.toString());
            
            List<Category> categoryList = query.list();
            if (categoryList.size() > 0){
               output = categoryList.get(0);
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
            String hql="from com.lia.lego.model.Category";
            Query query=session.createQuery(hql);
            
            List<Category> categoryList = query.list();
            for (Category category : categoryList) {
               output.add(category);
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
      Category category = (Category) obj;
      session.delete(category);
   }

   public void create(Session session, CommonObject obj) {
      Category category = (Category) obj;
      session.save(category);
      
   }

   public void update(Session session, CommonObject obj) {

      Category category = (Category) obj;
      session.update(category);
      
   }

   public CommonObject retrieveAccordingKey(Session session, UUID key) {
      CommonObject output = null;
      String hql="from com.lia.lego.model.Category as c where c.Key=:key";
      Query query=session.createQuery(hql);
      query.setString("key", key.toString());
      
      List<Category> categoryList = query.list();
      if (categoryList.size() > 0){
         output = categoryList.get(0);
      }
      return output;
   }

   public List<CommonObject> retrieve(Session session) {
      List<CommonObject> output = new ArrayList<CommonObject>();
      String hql="from com.lia.lego.model.Category";
      Query query=session.createQuery(hql);

      List<Category> categoryList = query.list();
      for (Category category : categoryList) {
         output.add(category);
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
            List<String> categoryNameList = inventoryController.getCategoryNameListInSession(session);
            if (categoryNameList.size() > 0) {
               String script = "delete from Category"; 
               Query query = session.createQuery(script);
               query.executeUpdate();
            
               for (String categoryName : categoryNameList) {
                  UUID key = UUID.randomUUID();
                  Category category = new Category(categoryName, key);
                  create(session, category);
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
      String hql="from com.lia.lego.model.Category as c where c.Name=:name";
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
