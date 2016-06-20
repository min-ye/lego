package com.lia.lego.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.lia.common.CommonObject;
import com.lia.common.HibernateHelper;
import com.lia.lego.model.SubTheme;
import com.lia.lego.model.Theme;

public class SubThemeController implements Controller{

   public void delete(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         SubTheme subTheme = (SubTheme) obj;
         session.delete(subTheme);
      }
      finally {
         HibernateHelper.closeSession();
      }
   }

   public void create(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         SubTheme subTheme = (SubTheme) obj;
         session.save(subTheme);
      }
      finally {
         HibernateHelper.closeSession();
      }
   }

   public void update(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         SubTheme subTheme = (SubTheme) obj;
         session.update(subTheme);
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
         String hql="from com.lia.lego.SubTheme as s where s.Key=:key";
         Query query=session.createQuery(hql);
         query.setString("key", key.toString());
            
         List<SubTheme> subThemeList = query.list();
         if (subThemeList.size() > 0){
            output = subThemeList.get(0);
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
         String hql="from com.lia.lego.SubTheme";
         Query query=session.createQuery(hql);

         List<SubTheme> subThemeList = query.list();
         for (SubTheme subTheme : subThemeList) {
            output.add(subTheme);
         }
      }
      finally {
         HibernateHelper.closeSession();
      }
      return output;
   }
   
   public void initialize(){
      try {
         com.lia.lego.brickset.business.SetController setController = new com.lia.lego.brickset.business.SetController();
         ThemeController themeController = new ThemeController();
         List<CommonObject> themeList = themeController.retrieve();
         Session session = HibernateHelper.currentSession();
         
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
               create(subTheme);
            }
         }
         session.getTransaction().commit();
      }
      finally {
         HibernateHelper.closeSession();
      }
   }
   
   public List<CommonObject> getSubThemeAccordingThemeKey(UUID themeKey){
      List<CommonObject> subThemeList = new ArrayList<CommonObject>();
      try {
         Session session = HibernateHelper.currentSession();
         
         String hql="from SubTheme where ThemeKey = '" + themeKey.toString() + "'";
         Query query=session.createQuery(hql);
            
         subThemeList = query.list();
      }
      finally {
         HibernateHelper.closeSession();
      }
      return subThemeList;
   }
}
