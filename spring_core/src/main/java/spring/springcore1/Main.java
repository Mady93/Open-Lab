package spring.springcore1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

	public static void main(String[] args) {

								// Parrot
		/*var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		/*var p = context.getBean(Parrot.class);
		var hello = context.getBean(String.class);
		var ten = context.getBean(Integer.class);
		System.out.println(p.getName());
		System.out.println(hello);
		System.out.println(ten);*/
		
		//ESERCITAZIONE 3
		
		/* org.springframework.beans.factory.NoUniqueBeanDefinitionException
		 * Non sa qual'è l'istanza di parrot
		 * 
		 * var p = context.getBean(Parrot.class);
		 * 
		 * SOLUZIONE:
		 
		var parrot1 = context.getBean("parrot1",Parrot.class);
		var parrot2 = context.getBean("parrot2",Parrot.class);
		var parrot3 = context.getBean("parrot3",Parrot.class);
		System.out.println(parrot1.getName());
		System.out.println(parrot2.getName());
		System.out.println(parrot3.getName());
		 */
		/*var parrot = context.getBean(Parrot.class);
		System.out.println(parrot.getName());
		*/
		
		
		//                           A - B
		
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		A a = context.getBean(A.class);
		System.out.println(a);
		System.out.println(a.getB().getName());

	}
	

}
