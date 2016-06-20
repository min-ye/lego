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
import com.lia.common.HibernateHelper;
import com.lia.lego.model.Brick;
import com.lia.lego.model.Category;
import com.lia.lego.model.Color;
import com.lia.lego.model.Set;
import com.lia.lego.model.SubTheme;
import com.lia.lego.model.Theme;

public class BrickController implements Controller{

   public void delete(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Brick brick = (Brick) obj;
         session.delete(brick);
      }
      finally {
         HibernateHelper.closeSession();
      }
   }

   public void create(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Brick brick = (Brick) obj;
         session.save(brick);
      }
      finally {
         HibernateHelper.closeSession();
      }
   }

   public void update(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Brick brick = (Brick) obj;
         session.update(brick);
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
         String hql="from com.lia.lego.Brick as b where b.Key=:key";
         Query query=session.createQuery(hql);
         query.setString("key", key.toString());
            
         List<Brick> brickList = query.list();
         if (brickList.size() > 0){
            output = brickList.get(0);
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
         String hql="from com.lia.lego.Brick";
         Query query=session.createQuery(hql);

         List<Brick> brickList = query.list();
         for (Brick brick : brickList) {
            output.add(brick);
         }
      }
      finally {
         HibernateHelper.closeSession();
      }
      return output;
   }

   public void initialize() {
      Integer count = 0;
      List<String> logList = new ArrayList<String>();
      try {
         Session session = HibernateHelper.currentSession();
         com.lia.lego.brickset.business.InventoryController inventoryController = new com.lia.lego.brickset.business.InventoryController();
         ColorController colorController = new ColorController();
         CategoryController categoryController = new CategoryController();
         
         session.beginTransaction();
         String script = "delete from com.lia.lego.model.Brick"; 
         Query query = session.createQuery(script);
         query.executeUpdate();
         
         List<String[]> brickList = inventoryController.getBrickList();
         for (String[] brickObject : brickList) {
            String elementID = brickObject[0];
            String designID = brickObject[1];
            String name = brickObject[2];
            String imageURL = brickObject[3];
            String colorName = brickObject[4];
            String categoryName = brickObject[5];
            Color color = (Color)colorController.retrieveAccordingName(categoryName);
            Category category = (Category)categoryController.retrieveAccordingName(categoryName);
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
      finally {
         HibernateHelper.closeSession();
      }
   }
}
