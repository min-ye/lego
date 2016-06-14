package com.lia.lego.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.lia.common.CommonObject;
import com.lia.lego.model.Set;

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

}
