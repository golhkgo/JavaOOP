package beancontainer;


import beanfactory.BeanDefinition;
import beanloader.BeanDefinitionLoader;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * User : Carry
 * Date : 20. 12. 2014.
 * 빈을 들고있는 컨테이너.
 * BeanDefinitionContainer를 이용하여 빈정보를 들고있고, 해당 빈정보에 따라 빈을 생성하여 들고있습니다.
 * 팩토리들의 요청에 따라 들고있는 빈을 반환합니다.
 */
public class BeanContainer {
    private Map<String, Object> beanMap = new HashMap();
    private BeanDefinitionContainer beanDefinitionContainer;

    public <T extends BeanDefinitionLoader> BeanContainer(T loader) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        beanDefinitionContainer = new BeanDefinitionContainer(loader);
        loadBeans();
    }

    private void loadBeans() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for(BeanDefinition beanDefinition : beanDefinitionContainer.getBeanDefinitions()){
            beanMap.put(beanDefinition.getBeanName(), createBean(beanDefinition));
        }
    }

    public Object getBean(String beanName) {
        return beanMap.get(beanName);
    }

    public Object getBeanFromClassName(String className) {
        for(BeanDefinition beanDefinition : beanDefinitionContainer.getBeanDefinitions()){
            if(beanDefinition.getClassFullName().equals(className)){
                return getBean(beanDefinition.getBeanName());
            }
        }
        return null;
    }

    public void addBean(BeanDefinition beanDefinition) throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        beanDefinitionContainer.addBeanDefinition(beanDefinition);
        beanMap.put(beanDefinition.getBeanName(), createBean(beanDefinition));
    }

    private Object createBean(BeanDefinition beanDefinition) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        Class clazz = Class.forName(beanDefinition.getClassFullName());
        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);

        return constructor.newInstance();
    }

}