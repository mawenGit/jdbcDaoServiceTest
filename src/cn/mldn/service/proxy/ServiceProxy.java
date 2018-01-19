package cn.mldn.service.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cn.mldn.dbc.DatabaseConnection;

public class ServiceProxy  implements InvocationHandler {
 private Object target;
 public  Object bind(Object target) {
	 this.target=target;
	 return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
 }
 private static boolean checkMethod(String methodName) {
	 return methodName.startsWith("add")||methodName.startsWith("delete")||methodName.startsWith("edit");
			 
}
	@SuppressWarnings("finally")
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	System.out.println(method.getName());
		if(checkMethod(method.getName())) {
			DatabaseConnection.getConnection().setAutoCommit(false);
		}
		Object back =null;
		try {
			back=method.invoke(this.target, args);
			if(checkMethod(method.getName())) {
				DatabaseConnection.getConnection().commit();
		}
	}catch(Exception e) {
		if(checkMethod(method.getName())) {
				DatabaseConnection.getConnection().rollback();
			throw e;
		}
	}finally {
			if(checkMethod(method.getName())) {
				DatabaseConnection.close();
		}return null;
	}

}
}
