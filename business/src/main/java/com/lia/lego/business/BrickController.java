package com.lia.lego.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.lia.common.CommonObject;
import com.lia.common.HibernateHelper;
import com.lia.lego.model.Brick;
import com.lia.lego.model.Category;
import com.lia.lego.model.Color;

public class BrickController implements Controller{

   public void delete(CommonObject obj) {
      Brick brick = (Brick) obj;
      HibernateHelper.currentSession().delete(brick);
   }

   public void create(CommonObject obj) {
      Brick brick = (Brick) obj;
      HibernateHelper.currentSession().save(brick);
   }

   public void update(CommonObject obj) {
      Brick brick = (Brick) obj;
      HibernateHelper.currentSession().update(brick);
   }

   public CommonObject retrieveAccordingKey(UUID key) {
      CommonObject output = null;
      String hql = "from com.lia.lego.Brick as b where b.Key=:key";
      Query query = HibernateHelper.currentSession().createQuery(hql);
      query.setString("key", key.toString());
            
      List<Brick> brickList = query.list();
      if (brickList.size() > 0){
         output = brickList.get(0);
      }
      return output;
   }

   public List<CommonObject> retrieve() {
      List<CommonObject> output = new ArrayList<CommonObject>();
      String hql = "from com.lia.lego.Brick";
      Query query = HibernateHelper.currentSession().createQuery(hql);

      List<Brick> brickList = query.list();
      for (Brick brick : brickList) {
         output.add(brick);
      }
      return output;
   }

   public void initialize() throws Exception {
      Integer count = 0;
      List<String> logList = new ArrayList<String>();

      com.lia.lego.brickset.business.InventoryController inventoryController = new com.lia.lego.brickset.business.InventoryController();
      ColorController colorController = new ColorController();
      List<CommonObject> colorList = colorController.retrieve();
      CategoryController categoryController = new CategoryController();
      List<CommonObject> categoryList = categoryController.retrieve();
      List<String[]> brickList = inventoryController.getBrickList();
      Session session = HibernateHelper.currentSession();
      session.beginTransaction();
      String script = "delete from com.lia.lego.model.Brick";
      Query query = session.createQuery(script);
      query.executeUpdate();
      for (String[] brickObject : brickList) {
         String elementID = brickObject[0];
         String designID = brickObject[1];
         String name = brickObject[2];
         String imageURL = brickObject[3];
         String colorName = brickObject[4];
         String categoryName = brickObject[5];
         Color color = null;
         for(int i = 0; i < colorList.size(); i++)  
         {  
             if (colorList.get(i).getPropertyValue("Name").equals(colorName)) {
                color = (Color)colorList.get(i);
             }
         }
         Category category = null;
         for(int i = 0; i < categoryList.size(); i++)  
         {  
             if (categoryList.get(i).getPropertyValue("Name").equals(categoryName)) {
                category = (Category)categoryList.get(i);
             }
         }  
         if (color == null) {
            throw new Exception("Unknown Color Name:" + colorName);
         }
         if (category == null) {
            throw new Exception("Unknown Category Name:" + categoryName);
         }
         UUID colorKey = color.getKey();
         UUID categoryKey = category.getKey();
         UUID key = UUID.randomUUID();
         Brick brick = new Brick(elementID, designID, colorKey, categoryKey, name, imageURL, key);
         session.save(brick);
      }
      session.getTransaction().commit();
   }
}
