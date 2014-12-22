package beanfactory;



import scope.Scope;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * User : Carry
 * Date : 21. 12. 2014.
 *
 * 빈이 가지는 정보를 저장합니다.
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class BeanDefinition {
	@XmlAttribute(name = "name")
	private String beanName;
	@XmlAttribute(name = "class")
	private String classFullName;
	private Scope scope;

	public BeanDefinition(String beanName, String classFullName) {
		this.classFullName = classFullName;
		this.beanName = beanName;
	}

	public String getBeanName() {
		return beanName;
	}

	public String getClassFullName() {
		return classFullName;
	}

	public Scope getScope() {
		return scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}

	@Override
	public String toString() {
		return "BeanDefinition [beanName=" + beanName + ", classFullName=" + classFullName + "]";
	}
}
