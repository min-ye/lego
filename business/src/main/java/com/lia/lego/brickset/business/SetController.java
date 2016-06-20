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
import com.lia.common.HibernateHelper;
import com.lia.lego.business.Controller;
import com.lia.lego.brickset.model.Set;

public class SetController implements Controller{

   public void delete(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Set set = (Set) obj;
         session.delete(set);
      }
      finally {
         HibernateHelper.closeSession();
      }
   }

   public void create(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Set set = (Set) obj;
         session.save(set);
      }
      finally {
         HibernateHelper.closeSession();
      }
   }

   public void update(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Set set = (Set) obj;
         session.update(set);
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
         String hql="from com.lia.lego.brickset.Set as s where s.Key=:key";
         Query query=session.createQuery(hql);
         query.setString("key", key.toString());
            
         List<Set> setList = query.list();
         if (setList.size() > 0){
            output = setList.get(0);
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
         String hql="from com.lia.lego.brickset.Set";
         Query query=session.createQuery(hql);

         List<Set> setList = query.list();
         for (Set set : setList) {
            output.add(set);
         }
      }
      finally {
         HibernateHelper.closeSession();
      }
      return output;
   }

   public List<String> getThemeNameList() {
      List<String> themeList = new ArrayList<String>();
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         List setList = session.createQuery("select distinct s.Theme FROM com.lia.lego.brickset.model.Set s").list();
         for (Iterator iterator = setList.iterator(); iterator.hasNext();){
            String theme = (String) iterator.next();
            if (!themeList.contains(theme)) {
               themeList.add(theme);
            }
         }
      }
      finally {
         HibernateHelper.closeSession();
      }
      return themeList;
   }
   
   public List<String> getSubThemeNameList(String themeName){
      List<String> subThemeList = new ArrayList<String>();
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         List setList = session.createQuery("select distinct s.SubTheme FROM com.lia.lego.brickset.model.Set s where s.Theme = '" + themeName + "'").list();
         for (Iterator iterator = setList.iterator(); iterator.hasNext();){
            String theme = (String) iterator.next();
            if (!subThemeList.contains(theme)) {
               subThemeList.add(theme);
            }
         }
      }
      finally {
         HibernateHelper.closeSession();
      }
      return subThemeList;
   }

   public List<CommonObject> getSetAccordingTheme(String themeName, String subThemeName) {
      List<CommonObject> output = new ArrayList<CommonObject>();
      try {
         Session session = HibernateHelper.currentSession();
         List setList = session.createQuery("FROM com.lia.lego.brickset.model.Set s where s.Theme = '" + themeName + "' and s.SubTheme = '" + subThemeName + "'").list();
         for (Iterator iterator = setList.iterator(); iterator.hasNext();){
            Set set = (Set) iterator.next();
            output.add(set);
         }
            
      }
      finally {
         HibernateHelper.closeSession();
      }
      return output;
   }
}
