package com.lia.lego.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.lia.common.CommonObject;
import com.lia.common.HibernateHelper;
import com.lia.lego.model.Color;

public class ColorController implements Controller{

   public void delete(CommonObject obj) {
      Color color = (Color) obj;
      HibernateHelper.currentSession().delete(color);
   }

   public void create(CommonObject obj) {
      Color color = (Color) obj;
      HibernateHelper.currentSession().save(color);
   }

   public void update(CommonObject obj) {
      Color color = (Color) obj;
      HibernateHelper.currentSession().update(color);
   }

   public CommonObject retrieveAccordingKey(UUID key) {
      CommonObject output = null;
      String hql = "from com.lia.lego.model.Color as c where c.Key=:key";
      Query query = HibernateHelper.currentSession().createQuery(hql);
      query.setString("key", key.toString());
            
      List<Color> colorList = query.list();
      if (colorList.size() > 0){
         output = colorList.get(0);
      }
      return output;
   }

   public List<CommonObject> retrieve() {
      List<CommonObject> output = new ArrayList<CommonObject>();
      String hql = "from com.lia.lego.model.Color";
      Query query = HibernateHelper.currentSession().createQuery(hql);

      List<Color> colorList = query.list();
      for (Color color : colorList) {
         output.add(color);
      }
      return output;
   }

   public void initialize(){

      com.lia.lego.brickset.business.InventoryController inventoryController = new com.lia.lego.brickset.business.InventoryController();
      List<String> colorNameList = inventoryController.getColorNameList();

      Session session = HibernateHelper.currentSession();

      session.beginTransaction();

      if (colorNameList.size() > 0) {
         String script = "delete from Color";
         Query query = session.createQuery(script);
         query.executeUpdate();

         for (String colorName : colorNameList) {
            UUID key = UUID.randomUUID();
            Color color = new Color(colorName, key);
            session.save(color);
         }
      }
      session.getTransaction().commit();
   }
   
   public CommonObject retrieveAccordingName(String name) throws Exception {
      CommonObject output = null;

      Session session = HibernateHelper.currentSession();
      String hql = "from com.lia.lego.model.Color as c where c.Name=:name";
      Query query = session.createQuery(hql);
      query.setString("name", name);

      List<CommonObject> colorList = query.list();
      if (colorList.size() > 1) {
         throw new Exception("Duplicated Color Name:" + name);
      }
      if (colorList.size() > 0) {
         output = colorList.get(0);
      }

      return output;
   }
}
