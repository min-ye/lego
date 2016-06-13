package com.lia.lego.bee;

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
import com.lia.common.mysql.Configure;
import com.lia.common.mysql.CreateHandler;
import com.lia.common.mysql.DeleteHandler;
import com.lia.lego.model.Theme;
import com.lia.lego.model.brickset.Set;

public class DBController {
   private String _url = "jdbc:mysql://127.0.0.1:3306/lego?characterEncoding=utf8&useSSL=true";
   private String _user = "root";
   private String _password = "lia";
   
   public void convertSetFromJsonToMySQL(){
      try{
         Configure c = new Configure(_url, _user, _password);
         DeleteHandler deleteHandler = new DeleteHandler();
         deleteHandler.empty(c, "BrickSet");
         JsonController jsonController = new JsonController();
         List<CommonObject> setList = jsonController.getSetList();
         CreateHandler createHandler = new CreateHandler();
         createHandler.create(c, setList);
      }
      catch (Exception ex){
         System.out.println(ex.getMessage());
      }
   }
   
   public void convertInventoryFromJsonToMySQL(){
      try{
         Configure c = new Configure(_url, _user, _password);
         DeleteHandler deleteHandler = new DeleteHandler();
         deleteHandler.empty(c, "BrickSetInventory");
         JsonController jsonController = new JsonController();
         CreateHandler createHandler = new CreateHandler();
         List<CommonObject> setList = jsonController.getSetList();
         int i = 0;
         for (CommonObject object : setList){
            String output = String.format("\r %d",  i);
            System.out.print(output);
            //String setId = set.getFieldValue("SetID");
            Set set = new Set(object);
            List<CommonObject> inventoryList = jsonController.getInventoryBySet(set);
            createHandler.create(c, inventoryList);
            i++;
         }
      }
      catch (Exception ex){
         System.out.println(ex.getMessage());
      }
   }
   
   public void generateTheme(){
      try {
         List<String> themeNameList = getThemeNameList();
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         
         try {
            session = factory.openSession();
            session.beginTransaction();
            String script = "delete from Theme"; 
            Query query = session.createQuery(script);
            query.executeUpdate();
            
            script = "delete from SubTheme";
            query = session.createQuery(script);
            query.executeUpdate();
            
            for (String themeName : themeNameList) {
               UUID key = UUID.randomUUID();
               Theme theme = new Theme(themeName, key);
               session.save(theme);
               List<String> subThemeNameList = getSubThemeNameList(themeName);
               for (String subThemeName : subThemeNameList) {
                  UUID subThemeKey = UUID.randomUUID();
                  //SubTheme subTheme = new SubTheme()
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
   
   public void generateSubTheme(){
      try {
         List<String> themeNameList = getThemeNameList();
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         
         try {
            session = factory.openSession();
            session.beginTransaction();
            String script = "delete from Theme"; 
            Query query =session.createQuery(script);
            query.executeUpdate();
            
            for (String themeName : themeNameList) {
               UUID key = UUID.randomUUID();
               Theme theme = new Theme(themeName, key);
               session.save(theme);
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
   
   public void list() {
      List<String> themeList = new ArrayList<String>();
      try {
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         Transaction transaction = null;
         
         try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            List setList = session.createQuery("select distinct s.Theme FROM com.lia.lego.model.brickset.Set s").list();
            for (Iterator iterator = setList.iterator(); iterator.hasNext();){
               String theme = (String) iterator.next();
               if (!themeList.contains(theme)) {
                  themeList.add(theme);
               }
            }
            
            transaction.commit();
            
            
         }
         catch (Exception ex) {
            if (transaction!=null) transaction.rollback(); 
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
   
   private List<String> getThemeNameList(){
      List<String> themeList = new ArrayList<String>();
      try {
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         Transaction transaction = null;
         
         try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            List setList = session.createQuery("select distinct s.Theme FROM com.lia.lego.model.brickset.Set s").list();
            for (Iterator iterator = setList.iterator(); iterator.hasNext();){
               String theme = (String) iterator.next();
               if (!themeList.contains(theme)) {
                  themeList.add(theme);
               }
            }
            
            transaction.commit();
            
            
         }
         catch (Exception ex) {
            if (transaction!=null) transaction.rollback(); 
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
      return themeList;
   }
   
   private List<String> getSubThemeNameList(String themeName){
      List<String> subThemeList = new ArrayList<String>();
      try {
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         Transaction transaction = null;
         
         try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            List setList = session.createQuery("select distinct s.SubTheme FROM com.lia.lego.model.brickset.Set s where s.Theme = '" + themeName + "'").list();
            for (Iterator iterator = setList.iterator(); iterator.hasNext();){
               String theme = (String) iterator.next();
               if (!subThemeList.contains(theme)) {
                  subThemeList.add(theme);
               }
            }
            
            transaction.commit();
            
            
         }
         catch (Exception ex) {
            if (transaction!=null) transaction.rollback(); 
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
}
