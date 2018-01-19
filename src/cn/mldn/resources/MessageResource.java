package cn.mldn.resources;

import java.util.ResourceBundle;

public class MessageResource {
  private ResourceBundle rb =null;
  
  public MessageResource(String baseName) {
	  if(baseName.contains(".")) {
		   this.rb=ResourceBundle.getBundle(baseName);
		  
	  }else {
		   this.rb=ResourceBundle.getBundle("cn.mldn.resources" +baseName);
	  }
  }
 public   String getMessage(String key) {
	  try {
		 return this.rb.getString(key);
	  }catch(Exception e) {
		  e.printStackTrace();
	  }return null;
  }
}
