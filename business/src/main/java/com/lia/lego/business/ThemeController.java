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
import com.lia.lego.model.Theme;

public class ThemeController implements Controller{

   public void delete(CommonObject obj) {
      Theme theme = (Theme) obj;
      HibernateHelper.currentSession().delete(theme);
   }

   public void create(CommonObject obj) {
      Theme theme = (Theme) obj;
      HibernateHelper.currentSession().save(theme);
   }

   public void update(CommonObject obj) {
      Theme theme = (Theme) obj;
      HibernateHelper.currentSession().update(theme);
   }

   public CommonObject retrieveAccordingKey(UUID key) {
      CommonObject output = null;
      String hql = "from com.lia.lego.Theme as t where t.Key=:key";
      Query query = HibernateHelper.currentSession().createQuery(hql);
      query.setString("", key.toString());
            
      List<Theme> themeList = query.list();
      if (themeList.size() > 0){
         output = themeList.get(0);
      }
      return output;
   }

   public List<CommonObject> retrieve() {
      List<CommonObject> output = new ArrayList<CommonObject>();
      String hql = "from com.lia.lego.Theme";
      Query query = HibernateHelper.currentSession().createQuery(hql);

      List<Theme> themeList = query.list();
      for (Theme theme : themeList) {
         output.add(theme);
      }
      return output;
   }
   
   public void initialize(){
         com.lia.lego.brickset.business.SetController setController = new com.lia.lego.brickset.business.SetController();
         List<String> themeNameList = setController.getThemeNameList();
         Session session = HibernateHelper.currentSession();
         
         session.beginTransaction();
         String script = "delete from Theme"; 
         Query query = session.createQuery(script);
         query.executeUpdate();
            
         for (String themeName : themeNameList) {
            UUID key = UUID.randomUUID();
            Theme theme = new Theme(themeName, key);
            create(theme);
         }
         session.getTransaction().commit();
      
   }
}
