package beancontainer;


import beanfactory.BeanDefinition;
import beanloader.BeanDefinitionLoader;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * User : Carry
 * Date : 20. 12. 2014.
 * 빈정보를 들고있는 객체
 * 빈컨테이너가 사용하기 쉽도록 Collection객체인 List형태로 빈 정보를 들고있습니다.
 */
public class BeanDefinitionContainer {
    private BeanDefinitionLoader beanDefinitionLoader;
    private List<BeanDefinition> beanDefinitionList;

    public <T extends BeanDefinitionLoader> BeanDefinitionContainer(T loader) throws IOException {
        beanDefinitionLoader = loader;
        beanDefinitionList = Collections.emptyList();
        loadBeans();
    }

    private void loadBeans() throws IOException {
        beanDefinitionList = beanDefinitionLoader.loadBeans();
    }

    public List<BeanDefinition> getBeanDefinitions() {
        return beanDefinitionList;
    }

    public void addBeanDefinition(BeanDefinition beanDefinition) {
        beanDefinitionList.add(beanDefinition);
    }
}