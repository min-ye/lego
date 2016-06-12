package com.lia.lego.bee;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
         Configuration config = new Configuration().configure();
         SessionFactory factory = config.buildSessionFactory();
         Session session = null;
         
         try {
            session = factory.openSession();
            session.beginTransaction();
            UUID key = UUID.randomUUID();
            Theme theme = new Theme("Theme", key.toString());
            session.save(theme);
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
}
