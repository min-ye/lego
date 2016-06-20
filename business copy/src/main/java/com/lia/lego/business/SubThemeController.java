package com.lia.lego.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.lia.common.CommonObject;
import com.lia.lego.model.SubTheme;
import com.lia.lego.model.Theme;

public class SubThemeController implements Controller{

   public void delete(CommonObject obj) {
      try {
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         try {
            session = factory.openSession();
            SubTheme subTheme = (SubTheme) obj;
            session.delete(subTheme);
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
            SubTheme subTheme = (SubTheme) obj;
            session.save(subTheme);
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
            SubTheme subTheme = (SubTheme) obj;
            session.update(subTheme);
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
            String hql="from com.lia.lego.model.SubTheme as s where s.Key=:key";
            Query query=session.createQuery(hql);
            query.setString("key", key.toString());
            
            List<SubTheme> subThemeList = query.list();
            if (subThemeList.size() > 0){
               output = subThemeList.get(0);
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
            String hql="from com.lia.lego.model.SubTheme";
            Query query=session.createQuery(hql);
            
            List<SubTheme> subThemeList = query.list();
            for (SubTheme subTheme : subThemeList) {
               output.add(subTheme);
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
      SubTheme subTheme = (SubTheme) obj;
      session.delete(subTheme);
   }

   public void create(Session session, CommonObject obj) {
      SubTheme subTheme = (SubTheme) obj;
      session.save(subTheme);
      
   }

   public void update(Session session, CommonObject obj) {

      SubTheme subTheme = (SubTheme) obj;
      session.update(subTheme);
      
   }

   public CommonObject retrieveAccordingKey(Session session, UUID key) {
      CommonObject output = null;
      String hql="from com.lia.lego.model.SubTheme as s where s.Key=:key";
      Query query=session.createQuery(hql);
      query.setString("key", key.toString());
      
      List<SubTheme> subThemeList = query.list();
      if (subThemeList.size() > 0){
         output = subThemeList.get(0);
      }
      return output;
   }

   public List<CommonObject> retrieve(Session session) {
      List<CommonObject> output = new ArrayList<CommonObject>();
      String hql="from com.lia.lego.model.SubTheme";
      Query query=session.createQuery(hql);

      List<SubTheme> subThemeList = query.list();
      for (SubTheme subTheme : subThemeList) {
         output.add(subTheme);
      }
      return output;
   }
   
   public void initialize(){
      try {
         com.lia.lego.brickset.business.SetController setController = new com.lia.lego.brickset.business.SetController();
         ThemeController themeController = new ThemeController();
         List<CommonObject> themeList = themeController.retrieve();
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         
         try {
            session = factory.openSession();
            session.beginTransaction();
            String script = "delete from SubTheme"; 
            Query query = session.createQuery(script);
            query.executeUpdate();

            for (CommonObject themeObject : themeList) {
               Theme theme = (Theme) themeObject;
               List<String> subThemeNameList = setController.getSubThemeNameList(theme.getName());
               UUID themeKey = theme.getKey();
               for (String subThemeName : subThemeNameList) {
                  UUID key = UUID.randomUUID();
                  SubTheme subTheme = new SubTheme(subThemeName, themeKey, key);
                  create(session, subTheme);
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
   
   public List<CommonObject> getSubThemeAccordingThemeKey(UUID themeKey){
      List<CommonObject> subThemeList = new ArrayList<CommonObject>();
      try {
         
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         
         try {
            session = factory.openSession();
            String hql="from SubTheme where ThemeKey = '" + themeKey.toString() + "'";
            Query query=session.createQuery(hql);
            
            subThemeList = query.list();
            
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
      return subThemeList;
   }
   
   public List<CommonObject> getSubThemeAccordingThemeKeyInSession(Session session, UUID themeKey){
      List<CommonObject> subThemeList = new ArrayList<CommonObject>();
      String hql="from SubTheme where ThemeKey = '" + themeKey.toString() + "'";
      Query query=session.createQuery(hql);
            
      subThemeList = query.list();
      return subThemeList;
   }
}
