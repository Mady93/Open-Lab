package spring.springcore1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class A {
	@Autowired
	@Qualifier("b1")
	private B b2;
	
	public B getB() {
		return b2; 
		}
	public void setB(B b) { 
		this.b2 = b; 
		}
}
