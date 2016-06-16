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
import com.lia.lego.model.Set;
import com.lia.lego.model.SubTheme;
import com.lia.lego.model.Theme;

public class SetController implements Controller{

   public void delete(CommonObject obj) {
      try {
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         try {
            session = factory.openSession();
            Set set = (Set) obj;
            session.delete(set);
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
            Set set = (Set) obj;
            session.save(set);
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
            Set set = (Set) obj;
            session.update(set);
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
            String hql="from com.lia.lego.Set as s where s.Key=:key";//使用命名参数，推荐使用，易读。
            Query query=session.createQuery(hql);
            query.setString("key", key.toString());
            
            List<Set> setList = query.list();
            if (setList.size() > 0){
               output = setList.get(0);
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
            String hql="from com.lia.lego.Set";
            Query query=session.createQuery(hql);
            
            List<Set> setList = query.list();
            for (Set set : setList) {
               output.add(set);
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

   public void deleteInSession(Session session, CommonObject obj) {
      Set set = (Set) obj;
      session.delete(set);
   }

   public void createInSession(Session session, CommonObject obj) {
      Set set = (Set) obj;
      session.save(set);
      
   }

   public void updateInSession(Session session, CommonObject obj) {
      Set set = (Set) obj;
      session.update(set);
      
   }

   public void initialize(){
      try {
         com.lia.lego.brickset.business.SetController setController = new com.lia.lego.brickset.business.SetController();
         ThemeController themeController = new ThemeController();
         SubThemeController subThemeController = new SubThemeController();
         List<CommonObject> themeList = themeController.retrieve();
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         
         try {
            session = factory.openSession();
            session.beginTransaction();
            String script = "delete from com.lia.lego.model.Set"; 
            Query query = session.createQuery(script);
            query.executeUpdate();

            for (CommonObject themeObject : themeList) {
               Theme theme = (Theme) themeObject;
               String themeName = theme.getName();
               List<CommonObject> subThemeList = subThemeController.getSubThemeAccordingThemeKey(theme.getKey());
               
               for (CommonObject subThemeObject : subThemeList) {
                  SubTheme subTheme = (SubTheme) subThemeObject;
                  String subThemeName = subTheme.getName();
                  UUID subThemeKey = subTheme.getKey();
                  List<CommonObject> setList = setController.getSetAccordingTheme(themeName, subThemeName);
                  
                  for (CommonObject setObject : setList) {
                     com.lia.lego.brickset.model.Set brickSet = (com.lia.lego.brickset.model.Set) setObject;
                     UUID key = UUID.randomUUID();
                     try {
                     Integer variant = Integer.parseInt(brickSet.getVariant());
                     Short year = Short.parseShort(brickSet.getYear());
                     String name = brickSet.getName();
                     Short defaultMinifigs = 0;
                     Short minifigs = CommonHelper.ConvertToShort(brickSet.getMinifigs(), defaultMinifigs);
                     Integer pieces = CommonHelper.ConvertToInteger(brickSet.getPieces(), 0);
                     Float priceUK = CommonHelper.ConvertToFloat(brickSet.getPriceUK(), 0f);
                     Float priceUS = CommonHelper.ConvertToFloat(brickSet.getPriceUS(), 0f);
                     Float priceCA = CommonHelper.ConvertToFloat(brickSet.getPriceCA(), 0f);
                     Float priceEU = CommonHelper.ConvertToFloat(brickSet.getPriceEU(), 0f);
                     String imageURL = brickSet.getImageURL();
                     Set set = new Set(brickSet.getNumber(), 
                           variant, 
                           subThemeKey, 
                           year, 
                           name, 
                           minifigs,
                           pieces,
                           priceUK,
                           priceUS,
                           priceCA,
                           priceEU,
                           imageURL,
                           key);
                     session.save(set);
                     }
                     catch (Exception ex) {
                        System.out.println(ex.toString());
                     }
                  }
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
}
