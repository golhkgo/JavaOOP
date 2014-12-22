package beanfactory;


import beanloader.BeanDefinitionLoader;
import scope.Scope;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * User : Carry
 * Date : 21. 12. 2014.
 * ScopeBeanFactory의 요청에 따라 빈팩토리를 만들기 위한 클래스
 * Scope를 추가하려면 해당 스코프에 맞는 팩토리를 추가하고, 여기에서 스코프에 따라 팩토리를 반환하도록 하면됩니다.
 */
public class BeanFactoryCreator {
    public BeanFactory createBeanFactory(Scope scope, BeanDefinitionLoader loader) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        switch(scope){
            case PROTO_TYPE:
                return new PrototypeBeanFactory(loader);
            case SINGLETONE_TYPE:
                return new SingletonBeanFactory(loader);
            default:
                return null;
        }
    }
}