package beanfactory;

import beancontainer.BeanContainer;
import beanloader.BeanDefinitionLoader;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * User : Carry
 * Date : 21. 12. 2014.
 * Proto type 빈을 관리하기 위한 빈팩토리
 * 프로토타입 빈들이 담겨 있는 빈컨테이너를 들고있습니다.
 * 생성 시 BeanDefinitionLoader를 받아 포멧에 맞춰 프로퍼티를 불러옵니다.
 */
public class PrototypeBeanFactory implements BeanFactory{
    private BeanContainer beanContainer;

    public <T extends BeanDefinitionLoader> PrototypeBeanFactory(T loader) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        beanContainer = new BeanContainer(loader);
    }

    /**
     * Bean의 class를 받아 새로운 인스턴스를 생성한 후 반환
     * @param type
     * @return Prototype BeanObject
     */
    @Override
    public <T> T getInstance(Class<T> type) {
        T t = (T) beanContainer.getBeanFromClassName(type.getName());
        try{
            Constructor constructor = t.getClass().getDeclaredConstructor();
            constructor.setAccessible(true);
            return (T) constructor.newInstance();
        }catch(NoSuchMethodException e){
            e.printStackTrace();
        }catch(InvocationTargetException e){
            e.printStackTrace();
        }catch(InstantiationException e){
            e.printStackTrace();
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }finally{
            return null;
        }
    }

    /**
     * Bean의 class를 받아 새로운 인스턴스를 생성한 후 반환
     * @param beanName
     * @return Prototype BeanObject
     */
    @Override
    public Object getInstance(String beanName) {
        Object returnObject = beanContainer.getBean(beanName);
        try{
            Constructor constructor = returnObject.getClass().getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        }catch(NoSuchMethodException e){
            e.printStackTrace();
        }catch(InvocationTargetException e){
            e.printStackTrace();
        }catch(InstantiationException e){
            e.printStackTrace();
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }finally{
            return null;
        }
    }

    /**
     * BeanDefinition을 받아 프로토타입 빈컨테이너에 빈을 추가
     * @param beanDefinition
     */
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