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
import com.lia.lego.model.Inventory;

public class InventoryController implements Controller{

   public void delete(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Inventory inventory = (Inventory) obj;
         session.delete(inventory);
      }
      finally {
         HibernateHelper.closeSession();
      }
   }

   public void create(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Inventory inventory = (Inventory) obj;
         session.save(inventory);
      }
      finally {
         HibernateHelper.closeSession();
      }
   }

   public void update(CommonObject obj) {
      Session session = null;
      try {
         session = HibernateHelper.currentSession();
         Inventory inventory = (Inventory) obj;
         session.update(inventory);
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
         String hql="from com.lia.lego.Inventory as i where i.Key=:key";
         Query query=session.createQuery(hql);
         query.setString("key", key.toString());
            
         List<Inventory> inventoryList = query.list();
         if (inventoryList.size() > 0){
            output = inventoryList.get(0);
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
         String hql="from com.lia.lego.Inventory";
         Query query=session.createQuery(hql);

         List<Inventory> inventoryList = query.list();
         for (Inventory inventory : inventoryList) {
            output.add(inventory);
         }
      }
      finally {
         HibernateHelper.closeSession();
      }
      return output;
   }
}
