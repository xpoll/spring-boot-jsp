package site.blmdz.test.method;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;

public class Test {

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		B b = new B();
		b.setAge(12);
		b.setName("sdf");
		System.out.println(new PropertyUtilsBean().getProperty(b, "age"));
		System.out.println(BeanUtilsBean.getInstance().getPropertyUtils().getProperty(b, "name"));
	}
	
}

