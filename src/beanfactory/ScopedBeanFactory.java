package beanfactory;

import beanloader.BeanDefinitionLoader;
import scope.Scope;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * User : Carry
 * Date : 21. 12. 2014.
 *
 * Scope에 따라 빈을 찾아오기 위한 빈팩토리
 * 스코프에 따른 빈팩토리들을 맵으로 들고있고,
 * 생성 시 BeanDefinitionLoader를 받아 포멧에 맞춰 프로퍼티를 불러옵니다.
 */
public class ScopedBeanFactory implements  BeanFactory{
    Map<Scope, BeanFactory> beanFactoryMap;
    BeanDefinitionLoader beanDefinitionLoader;

    public ScopedBeanFactory(BeanDefinitionLoader loader) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        beanFactoryMap = new HashMap();
        beanDefinitionLoader = loader;
        SingletonBeanFactory factory = new SingletonBeanFactory(loader);
        beanFactoryMap.put(Scope.SINGLETONE_TYPE, factory);
    }

    /**
     * class을 받아 가지고있는 빈팩토리 맵을 검색하여 찾으려는 빈이 있으면 해당 팩토리에게 빈 생성을 요청
     * @param type
     * @return BeanObject
     */
    @Override
    public <T> T getInstance(Class<T> type) {
        for (Entry<Scope, BeanFactory> beanFactory : beanFactoryMap.entrySet()) {
            T instance = beanFactory.getValue().getInstance(type);
            if (instance != null) {
                return instance;
            }
        }
        return null;
    }

    /**
     * Bean 이름으로 가지고있는 빈팩토리 맵을 검색하여 찾으려는 빈이 있으면 해당 팩토리에게 빈 생성을 요청
     * @param beanName
     * @return BeanObject
     */
    @Override
    public Object getInstance(String beanName) {
        for (Entry<Scope, BeanFactory> beanFactory : beanFactoryMap.entrySet()) {
            Object instance = beanFactory.getValue().getInstance(beanName);
            if (instance != null) {
                return instance;
            }
        }
        return null;
    }

    /**
     * BeanDefinition을 받아 스코프를 조사하고, 스코프가 있을 경우 스코프에 해당하는 빈팩토리에 추가하고 스코프가 없을 경우 기본적으로 싱글톤 빈팩토리에 빈을 추가
     * @param beanDefinition
     */
    @Override
    public void addBean(BeanDefinition beanDefinition) {
        Scope scope = beanDefinition.getScope();
        try{
            if(scope != null){
                getBeanFactory(scope).addBean(beanDefinition);
            }else{
                getBeanFactory(Scope.SINGLETONE_TYPE).addBean(beanDefinition);
            }
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
    }

    public BeanFactory getBeanFactory(Scope scope) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        if(beanFactoryMap.containsKey(scope)){
            return beanFactoryMap.get(scope);
        }else{
            BeanFactoryCreator creator = new BeanFactoryCreator();
            beanFactoryMap.put(scope, creator.createBeanFactory(scope, beanDefinitionLoader));
            return beanFactoryMap.get(scope);
        }
    }
}