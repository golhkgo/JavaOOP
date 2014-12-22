package beanloader;


import beanfactory.BeanDefinition;
import config.FileConfiguration;
import util.ResourceUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * User : Carry
 * Date : 20. 12. 2014.
 * BeanDefinitionLoader들을 가지고있는 enum객체
 * DefinitionLoader를 추가하려면 여기에서 enum을 추가하고 loadBeans 메서드를 해당 로더에 맞게 구현하면됩니다.
 */
public enum BeanDefinitionLoaders implements BeanDefinitionLoader {
    PROPERTY_FILE_DEFINITION_LOADER{
        @Override
        protected synchronized List<BeanDefinition> loadBeans(InputStream inputStream) throws IOException {
            String[] lines = ResourceUtil.readFully(inputStream);
            List<BeanDefinition> result = new ArrayList();
            if(lines != null){
                for(String line : lines){
                    String[] parsed = line.split("=");
                    result.add(new BeanDefinition(parsed[0], parsed[1]));
                }
            }
            return result;
        }
        @Override
        public List<BeanDefinition> loadBeans() throws IOException {
            InputStream inputStream = ResourceUtil.resourceAsInputStream(FileConfiguration.FILE_PATH);
            return loadBeans(inputStream);
        }
    };

    protected abstract List<BeanDefinition> loadBeans(InputStream inputStream) throws IOException;
}