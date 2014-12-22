package beanloader;


import beanfactory.BeanDefinition;

import java.io.IOException;
import java.util.List;

public interface BeanDefinitionLoader {
	public List<BeanDefinition> loadBeans() throws IOException;
}
