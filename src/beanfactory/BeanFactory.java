package beanfactory;

/**
 * User : Carry
 * Date : 21. 12. 2014.
 *
 * 빈팩토리 인터페이스
 * 기본적으로 빈팩토리가 가져야할 함수들을 정의합니다.
 */
public interface BeanFactory {
    public <T> T getInstance(Class<T> type);

    public Object getInstance(String beanName);

    public void addBean(BeanDefinition beanDefinition);
}