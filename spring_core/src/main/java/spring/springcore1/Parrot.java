package spring.springcore1;

import org.springframework.stereotype.Component;
//@Component - Stereotype annotations

@Component
public class Parrot {
private String name;

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}
}
