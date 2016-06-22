package com.lia.lego.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.lia.common.CommonHelper;
import com.lia.common.CommonObject;
import com.lia.common.FileHelper;
import com.lia.common.HibernateHelper;
import com.lia.lego.model.Set;
import com.lia.lego.model.SubTheme;
import com.lia.lego.model.Theme;

public class SetController implements Controller{

   public void delete(CommonObject obj) {
      Set set = (Set) obj;
      HibernateHelper.currentSession().delete(set);
   }

   public void create(CommonObject obj) {
      Set set = (Set) obj;
      HibernateHelper.currentSession().save(set);
   }

   public void update(CommonObject obj) {
      Set set = (Set) obj;
      HibernateHelper.currentSession().update(set);
   }

   public CommonObject retrieveAccordingKey(UUID key) {
      CommonObject output = null;
      String hql = "from com.lia.lego.Set as s where s.Key=:key";
      Query query = HibernateHelper.currentSession().createQuery(hql);
      query.setString("key", key.toString());
            
      List<Set> setList = query.list();
      if (setList.size() > 0){
         output = setList.get(0);
      }
      return output;
   }

   public List<CommonObject> retrieve() {
      List<CommonObject> output = new ArrayList<CommonObject>();
      String hql = "from com.lia.lego.Set";
      Query query = HibernateHelper.currentSession().createQuery(hql);

      List<Set> setList = query.list();
      for (Set set : setList) {
         output.add(set);
      }
      return output;
   }

   public void initialize() throws Exception{
      Integer count = 0;
      List<String> logList = new ArrayList<String>();

      com.lia.lego.brickset.business.SetController setController = new com.lia.lego.brickset.business.SetController();
      ThemeController themeController = new ThemeController();
      SubThemeController subThemeController = new SubThemeController();

      Session session = HibernateHelper.currentSession();

      session.beginTransaction();
      String script = "delete from com.lia.lego.model.Set";
      Query query = session.createQuery(script);
      query.executeUpdate();
      List<CommonObject> themeList = themeController.retrieve();
      for (CommonObject themeObject : themeList) {
         Theme theme = (Theme) themeObject;
         String themeName = theme.getName();
         UUID themeKey = theme.getKey();
         logList.add(String.format("%s,[%s]", themeName, themeKey.toString()));
         List<CommonObject> subThemeList = subThemeController.getSubThemeAccordingThemeKey(theme.getKey());

         for (CommonObject subThemeObject : subThemeList) {
            SubTheme subTheme = (SubTheme) subThemeObject;
            String subThemeName = subTheme.getName();
            UUID subThemeKey = subTheme.getKey();
            logList.add(String.format("   %s,[%s]", subThemeName, subThemeKey.toString()));
            List<CommonObject> setList = setController.getSetAccordingTheme(themeName, subThemeName);
            count += setList.size();
            for (CommonObject setObject : setList) {
               com.lia.lego.brickset.model.Set brickSet = (com.lia.lego.brickset.model.Set) setObject;
               UUID key = UUID.randomUUID();

               try {
                  String number = brickSet.getNumber();
                  Integer variant = Integer.parseInt(brickSet.getVariant());
                  Short year = Short.parseShort(brickSet.getYear());
                  String name = brickSet.getName();
                  Short defaultMinifigs = 0;
                  Short minifigs = CommonHelper.convertToShort(brickSet.getMinifigs(), defaultMinifigs);
                  Integer pieces = CommonHelper.convertToInteger(brickSet.getPieces(), 0);
                  Float priceUK = CommonHelper.convertToFloat(brickSet.getPriceUK(), 0f);
                  Float priceUS = CommonHelper.convertToFloat(brickSet.getPriceUS(), 0f);
                  Float priceCA = CommonHelper.convertToFloat(brickSet.getPriceCA(), 0f);
                  Float priceEU = CommonHelper.convertToFloat(brickSet.getPriceEU(), 0f);
                  String imageURL = brickSet.getImageURL();
                  logList.add(String.format("      %s,[%s]", number, name));
                  Set set = new Set(brickSet.getNumber(), variant, subThemeKey, year, name, minifigs, pieces, priceUK,
                        priceUS, priceCA, priceEU, imageURL, key);
                  session.save(set);
               } catch (Exception ex) {
                  System.out.println(ex.toString());
               }
            }
         }
      }
      session.getTransaction().commit();

      String log = "";
      for (String content: logList) {
         log += content + "\r\n";
      }
      FileHelper.INSTANCE.saveContent(log, "/Users/yemin/workspace/lego/initial.log");
   }
}
