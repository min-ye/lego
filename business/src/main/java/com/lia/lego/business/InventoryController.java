package com.lia.lego.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.query.Query;

import com.lia.common.CommonObject;
import com.lia.common.HibernateHelper;
import com.lia.lego.model.Inventory;

public class InventoryController implements Controller{

   public void delete(CommonObject obj) {
      Inventory inventory = (Inventory) obj;
      HibernateHelper.currentSession().delete(inventory);
   }

   public void create(CommonObject obj) {
      Inventory inventory = (Inventory) obj;
      HibernateHelper.currentSession().save(inventory);
   }

   public void update(CommonObject obj) {
      Inventory inventory = (Inventory) obj;
      HibernateHelper.currentSession().update(inventory);
   }

   public CommonObject retrieveAccordingKey(UUID key) {
      CommonObject output = null;
      String hql = "from com.lia.lego.Inventory as i where i.Key=:key";
      Query query = HibernateHelper.currentSession().createQuery(hql);
      query.setString("key", key.toString());
            
      List<Inventory> inventoryList = query.list();
      if (inventoryList.size() > 0){
         output = inventoryList.get(0);
      }
      return output;
   }

   public List<CommonObject> retrieve() {
      List<CommonObject> output = new ArrayList<CommonObject>();
      String hql = "from com.lia.lego.Inventory";
      Query query = HibernateHelper.currentSession().createQuery(hql);

      List<Inventory> inventoryList = query.list();
      for (Inventory inventory : inventoryList) {
         output.add(inventory);
      }
      return output;
   }
}
