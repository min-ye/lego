package com.lia.lego.business;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;

import com.lia.common.CommonObject;

public interface Controller {
   public void delete(CommonObject obj);
   public void create(CommonObject obj);
   public void update(CommonObject obj);
   public CommonObject retrieveAccordingKey(UUID key);
   public List<CommonObject> retrieve();
   
   public void deleteInSession(Session session, CommonObject obj);
   public void createInSession(Session session, CommonObject obj);
   public void updateInSession(Session session, CommonObject obj);
}
