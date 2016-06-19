package com.lia.lego.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.lia.common.CommonObject;
import com.lia.lego.model.Theme;

public class ThemeController implements Controller{

   public void delete(CommonObject obj) {
      try {
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         try {
            session = factory.openSession();
            Theme theme = (Theme) obj;
            session.delete(theme);
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
            Theme theme = (Theme) obj;
            session.save(theme);
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
            Theme theme = (Theme) obj;
            session.update(theme);
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
            String hql="from com.lia.lego.model.Theme as t where t.Key=:key";
            Query query=session.createQuery(hql);
            query.setString("key", key.toString());
            
            List<Theme> themeList = query.list();
            if (themeList.size() > 0){
               output = themeList.get(0);
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
            String hql="from com.lia.lego.model.Theme";
            Query query=session.createQuery(hql);
            
            List<Theme> themeList = query.list();
            for (Theme theme : themeList) {
               output.add(theme);
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
      Theme theme = (Theme) obj;
      session.delete(theme);
   }

   public void create(Session session, CommonObject obj) {
      Theme theme = (Theme) obj;
      session.save(theme);
      
   }

   public void update(Session session, CommonObject obj) {

      Theme theme = (Theme) obj;
      session.update(theme);
      
   }

   public CommonObject retrieveAccordingKey(Session session, UUID key) {
      CommonObject output = null;
      String hql="from com.lia.lego.model.Theme as t where t.Key=:key";
      Query query=session.createQuery(hql);
      query.setString("key", key.toString());
      
      List<Theme> themeList = query.list();
      if (themeList.size() > 0){
         output = themeList.get(0);
      }
      return output;
   }

   public List<CommonObject> retrieve(Session session) {
      List<CommonObject> output = new ArrayList<CommonObject>();
      String hql="from com.lia.lego.model.Theme";
      Query query=session.createQuery(hql);

      List<Theme> themeList = query.list();
      for (Theme theme : themeList) {
         output.add(theme);
      }
      return output;
   }
   
   public void initialize(){
      try {
         com.lia.lego.brickset.business.SetController setController = new com.lia.lego.brickset.business.SetController();
         List<String> themeNameList = setController.getThemeNameList();
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         
         try {
            session = factory.openSession();
            session.beginTransaction();
            String script = "delete from Theme"; 
            Query query = session.createQuery(script);
            query.executeUpdate();
            
            for (String themeName : themeNameList) {
               UUID key = UUID.randomUUID();
               Theme theme = new Theme(themeName, key);
               create(session, theme);
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
}
