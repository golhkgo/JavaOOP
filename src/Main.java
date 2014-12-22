import beanfactory.ScopedBeanFactory;
import beanloader.BeanDefinitionLoaders;
import beans.Sample1;
import beans.Sample2;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class Main {
	public static void main(String[] args) {
		ScopedBeanFactory scopedBeanFactory = null;
		try{
			scopedBeanFactory = new ScopedBeanFactory(BeanDefinitionLoaders.PROPERTY_FILE_DEFINITION_LOADER);
		}catch(NoSuchMethodException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(InstantiationException e){
			e.printStackTrace();
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}catch(InvocationTargetException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}

		Sample1 instance = scopedBeanFactory.getInstance(Sample1.class);
		System.out.println(instance != null);

		Object instance2 = scopedBeanFactory.getInstance("sample2");
		System.out.println(instance2 != null);
		System.out.println(Sample2.class.isAssignableFrom(instance2.getClass()));
		System.out.println(instance2 instanceof Sample2);
	}
}
