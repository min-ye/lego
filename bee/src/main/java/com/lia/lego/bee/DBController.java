package com.lia.lego.bee;

import java.util.List;

import com.lia.common.CommonObject;
import com.lia.common.mysql.Configure;
import com.lia.common.mysql.CreateHandler;
import com.lia.common.mysql.DeleteHandler;
import com.lia.lego.brickset.model.Set;
import com.lia.lego.business.SubThemeController;
import com.lia.lego.business.ThemeController;

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
   
   public void initialize() throws Exception {
      //ThemeController themeController = new ThemeController();
      //themeController.initialize();
      //SubThemeController subThemeController = new SubThemeController();
      //subThemeController.initialize();
      //com.lia.lego.business.SetController setController = new com.lia.lego.business.SetController();
      //setController.initialize();
      //com.lia.lego.business.ColorController colorController = new com.lia.lego.business.ColorController();
      //colorController.initialize();
      //com.lia.lego.business.CategoryController categoryController = new com.lia.lego.business.CategoryController();
      //categoryController.initialize();
      com.lia.lego.business.BrickController brickController = new com.lia.lego.business.BrickController();
      brickController.initialize();
   }
}
