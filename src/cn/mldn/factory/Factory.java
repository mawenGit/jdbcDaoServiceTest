package cn.mldn.factory;

import cn.mldn.resources.MessageResource;
import cn.mldn.service.proxy.ServiceProxy;

public class Factory {
private static final  MessageResource DAO_RESOURCE =new MessageResource("dao");
private static final  MessageResource SERVICE_RESOURCE =new MessageResource("service");
private Factory() {}
public static <T> T getIDAOInstance(String classname) {
	try {
		return (T) Class.forName(DAO_RESOURCE.getMessage(classname)).newInstance();
	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}return null;
}
public static <T> T getServiceInstance(String classname) {
	try {
		return (T)  new ServiceProxy().bind(Class.forName(SERVICE_RESOURCE.getMessage(classname)).newInstance());
	}catch(Exception e) {
		e.printStackTrace();
	}return null;
}
public static <T> T getInstance(String classname) {
	String str=classname.substring(classname.indexOf(".")+1);
	switch(str ) {
	case("dao"):{
		return getIDAOInstance(classname);
	}
	case("service"):{
		return getServiceInstance(classname)	;
	}
	default :{
		return null;
	}
	}
}
}
