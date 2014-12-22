package beanfactory;


import beancontainer.BeanContainer;
import beanloader.BeanDefinitionLoader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 *  Singleton type을 처리하기위한 빈팩토리
 *  싱글톤 스코프의 빈을 담고있는 빈컨테이너를 들고있습니다.
 *  생성 시 BeanDefinitionLoader를 받아 포멧에 맞춰 프로퍼티를 불러옵니다.
 */
public class SingletonBeanFactory implements BeanFactory {
	private BeanContainer beanContainer;

	public <T extends BeanDefinitionLoader> SingletonBeanFactory(T loader) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		beanContainer = new BeanContainer(loader);
	}

	/**
	 * class를 받아 빈컨테이너에서 해당 빈을 찾아 반환
	 * @param type
	 * @return Singleton BeanObject
	 */
	public <T> T getInstance(Class<T> type){
		return (T) beanContainer.getBeanFromClassName(type.getName());
	}

	/**
	 * Bean name을 받아 빈컨테이너에서 해당 빈을 찾아 반환
	 * @param beanName
	 * @return Singleton BeanObject
	 */
	public Object getInstance(String beanName){
		return beanContainer.getBean(beanName);
	}

	@Override
	public void addBean(BeanDefinition beanDefinition) {
		try{
			beanContainer.addBean(beanDefinition);
		}catch(InvocationTargetException e){
			e.printStackTrace();
		}catch(NoSuchMethodException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(InstantiationException e){
			e.printStackTrace();
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}
	}
}
