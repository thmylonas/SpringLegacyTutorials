package com.thomasmylonas.spring_core.spring_dependency_injection;

import com.thomasmylonas.spring_core._base.AbstractSpringTutorials;
import com.thomasmylonas.spring_core.spring_dependency_injection._models.example1.GroupOfPeople;
import com.thomasmylonas.spring_core.spring_dependency_injection._models.example1.Person1;
import com.thomasmylonas.spring_core.spring_dependency_injection._models.example1.Person2;
import com.thomasmylonas.spring_core.spring_dependency_injection._models.example1.Person3;
import com.thomasmylonas.spring_core.spring_dependency_injection._models.example1.Person4;
import com.thomasmylonas.spring_core.spring_dependency_injection._models.example2.Employee;
import com.thomasmylonas.spring_core.spring_dependency_injection._models.example3.Book;
import com.thomasmylonas.spring_core.spring_dependency_injection._models.example3.Library;
import com.thomasmylonas.spring_core.spring_dependency_injection._models.example3.Teacher;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependencyInjectionByConstructorsTutorial extends AbstractSpringTutorials {

    private static final String EXAMPLE_1_CONFIG = "config/spring_dependency_injection_config/di_by_constructors/di_by_constructors_example1_config.xml";
    private static final String EXAMPLE_2a_CONFIG = "config/spring_dependency_injection_config/di_by_constructors/di_by_constructors_example2a_config.xml";
    private static final String EXAMPLE_2b_CONFIG = "config/spring_dependency_injection_config/di_by_constructors/di_by_constructors_example2b_config.xml";
    private static final String EXAMPLE_3_CONFIG = "config/spring_dependency_injection_config/di_by_constructors/di_by_constructors_example3_config.xml";

    public void diByConstructorsExample1() {

        try (var context = new ClassPathXmlApplicationContext(EXAMPLE_1_CONFIG)) {

            GroupOfPeople byConstructorsGroupOfPeople = (GroupOfPeople) context.getBean("byConstructorsGroupOfPeople");
            LOGGER.info("{}", byConstructorsGroupOfPeople);

            // Russian singers beans (based on "Person*")

            Person1 byConstructorsAniLorak = (Person1) context.getBean("byConstructorsAniLorak");
            LOGGER.info("{}", byConstructorsAniLorak);

            Person2 byConstructorsJasmine = (Person2) context.getBean("byConstructorsJasmine");
            LOGGER.info("{}", byConstructorsJasmine);

            Person3 byConstructorsLiubanya = (Person3) context.getBean("byConstructorsLiubanya");
            LOGGER.info("{}", byConstructorsLiubanya);

            Person4 byConstructorsMarina = (Person4) context.getBean("byConstructorsMarina");
            LOGGER.info("{}", byConstructorsMarina);

            // Rhythmic gymnastics girls beans (based on "Person1")

            Person1 byConstructorsSasha = (Person1) context.getBean("byConstructorsSasha");
            LOGGER.info("{}", byConstructorsSasha);

            Person1 byConstructorsYana = (Person1) context.getBean("byConstructorsYana");
            LOGGER.info("{}", byConstructorsYana);

            Person1 byConstructorsZenya = (Person1) context.getBean("byConstructorsZenya");
            LOGGER.info("{}", byConstructorsZenya);

            Person1 byConstructorsAlina = (Person1) context.getBean("byConstructorsAlina");
            LOGGER.info("{}", byConstructorsAlina);

            Person1 byConstructorsMelita = (Person1) context.getBean("byConstructorsMelita");
            LOGGER.info("{}", byConstructorsMelita);
        } catch (BeansException e) {
            LOGGER.error("The bean could not be obtained!");
        }
    }

    public void diByConstructorsExample2() {

        try (var context1 = new ClassPathXmlApplicationContext(EXAMPLE_2a_CONFIG);
             var context2 = new ClassPathXmlApplicationContext(EXAMPLE_2b_CONFIG)) {

            // Case A
            Employee byConstructorsThomas1 = (Employee) context1.getBean("byConstructorsThomas1");
            LOGGER.info("{}", byConstructorsThomas1);

            // Case B
            Employee byConstructorsThomas2 = (Employee) context2.getBean("byConstructorsThomas2");
            LOGGER.info("{}", byConstructorsThomas2);
        } catch (BeansException e) {
            LOGGER.error("The bean could not be obtained!");
        }
    }

    public void diByConstructorsExample3() {

        try (var context = new ClassPathXmlApplicationContext(EXAMPLE_3_CONFIG)) {

            Library byConstructorsLibrary = (Library) context.getBean("byConstructorsLibrary");
            LOGGER.info("{}", byConstructorsLibrary);

            // Teachers beans (based on "Teacher")

            Teacher byConstructorsSpilca = (Teacher) context.getBean("byConstructorsSpilca");
            LOGGER.info("{}", byConstructorsSpilca);

            Teacher byConstructorsPaumard = (Teacher) context.getBean("byConstructorsPaumard");
            LOGGER.info("{}", byConstructorsPaumard);

            Teacher byConstructorsSubramaniam = (Teacher) context.getBean("byConstructorsSubramaniam");
            LOGGER.info("{}", byConstructorsSubramaniam);

            Teacher byConstructorsMihalcea = (Teacher) context.getBean("byConstructorsMihalcea");
            LOGGER.info("{}", byConstructorsMihalcea);

            // Books beans (based on "Book")

            Book byConstructorsSpringSecurityInAction2Edition = (Book) context.getBean("byConstructorsSpringSecurityInAction2Edition");
            LOGGER.info("{}", byConstructorsSpringSecurityInAction2Edition);

            Book byConstructorsJava8InAction = (Book) context.getBean("byConstructorsJava8InAction");
            LOGGER.info("{}", byConstructorsJava8InAction);

            Book byConstructorsFunctionalProgrammingInJava2Edition = (Book) context.getBean("byConstructorsFunctionalProgrammingInJava2Edition");
            LOGGER.info("{}", byConstructorsFunctionalProgrammingInJava2Edition);

            Book byConstructorsHighPerformanceJavaPersistence = (Book) context.getBean("byConstructorsHighPerformanceJavaPersistence");
            LOGGER.info("{}", byConstructorsHighPerformanceJavaPersistence);

            Book byConstructorsZombiesOnPlane = (Book) context.getBean("byConstructorsZombiesOnPlane");
            LOGGER.info("{}", byConstructorsZombiesOnPlane);

            Book byConstructorsZombiesOnTrain = (Book) context.getBean("byConstructorsZombiesOnTrain");
            LOGGER.info("{}", byConstructorsZombiesOnTrain);
        } catch (BeansException e) {
            LOGGER.error("The bean could not be obtained!");
        }
    }
}
