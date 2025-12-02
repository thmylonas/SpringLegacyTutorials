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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class DependencyInjectionBySettersTutorial extends AbstractSpringTutorials {

    private static final String EXAMPLE_1_CONFIG = "config/spring_dependency_injection_config/di_by_setters/di_by_setters_example1_config.xml";
    private static final String EXAMPLE_2a_CONFIG = "config/spring_dependency_injection_config/di_by_setters/di_by_setters_example2a_config.xml";
    private static final String EXAMPLE_2b_CONFIG = "config/spring_dependency_injection_config/di_by_setters/di_by_setters_example2b_config.xml";
    private static final String EXAMPLE_3_CONFIG = "config/spring_dependency_injection_config/di_by_setters/di_by_setters_example3_config.xml";

    public void diBySettersExample1() {

        try (var context = new ClassPathXmlApplicationContext(EXAMPLE_1_CONFIG)) {

            GroupOfPeople bySettersGroupOfPeople = (GroupOfPeople) context.getBean("bySettersGroupOfPeople");
            log.info("{}", bySettersGroupOfPeople);

            // Russian singers beans (based on "Person*")

            Person1 bySettersAniLorak = (Person1) context.getBean("bySettersAniLorak");
            log.info("{}", bySettersAniLorak);

            Person2 bySettersJasmine = (Person2) context.getBean("bySettersJasmine");
            log.info("{}", bySettersJasmine);

            Person3 bySettersLiubanya = (Person3) context.getBean("bySettersLiubanya");
            log.info("{}", bySettersLiubanya);

            Person4 bySettersMarina = (Person4) context.getBean("bySettersMarina");
            log.info("{}", bySettersMarina);

            // Rhythmic gymnastics girls beans (based on "Person1")

            Person1 bySettersSasha = (Person1) context.getBean("bySettersSasha");
            log.info("{}", bySettersSasha);

            Person1 bySettersYana = (Person1) context.getBean("bySettersYana");
            log.info("{}", bySettersYana);

            Person1 bySettersZenya = (Person1) context.getBean("bySettersZenya");
            log.info("{}", bySettersZenya);

            Person1 bySettersAlina = (Person1) context.getBean("bySettersAlina");
            log.info("{}", bySettersAlina);

            Person1 bySettersMelita = (Person1) context.getBean("bySettersMelita");
            log.info("{}", bySettersMelita);
        } catch (BeansException e) {
            log.error("The bean could not be obtained!");
        }
    }

    public void diBySettersExample2() {

        try (var context1 = new ClassPathXmlApplicationContext(EXAMPLE_2a_CONFIG);
             var context2 = new ClassPathXmlApplicationContext(EXAMPLE_2b_CONFIG)) {

            // Case A
            Employee bySettersThomas1 = (Employee) context1.getBean("bySettersThomas1");
            log.info("{}", bySettersThomas1);

            // Case B
            Employee bySettersThomas2 = (Employee) context2.getBean("bySettersThomas2");
            log.info("{}", bySettersThomas2);
        } catch (BeansException e) {
            log.error("The bean could not be obtained!");
        }
    }

    public void diBySettersExample3() {

        try (var context = new ClassPathXmlApplicationContext(EXAMPLE_3_CONFIG)) {

            Library bySettersLibrary = (Library) context.getBean("bySettersLibrary");
            log.info("{}", bySettersLibrary);

            // Teachers beans (based on "Teacher")

            Teacher bySettersSpilca = (Teacher) context.getBean("bySettersSpilca");
            log.info("{}", bySettersSpilca);

            Teacher bySettersPaumard = (Teacher) context.getBean("bySettersPaumard");
            log.info("{}", bySettersPaumard);

            Teacher bySettersSubramaniam = (Teacher) context.getBean("bySettersSubramaniam");
            log.info("{}", bySettersSubramaniam);

            Teacher bySettersMihalcea = (Teacher) context.getBean("bySettersMihalcea");
            log.info("{}", bySettersMihalcea);

            // Books beans (based on "Book")

            Book bySettersSpringSecurityInAction2Edition = (Book) context.getBean("bySettersSpringSecurityInAction2Edition");
            log.info("{}", bySettersSpringSecurityInAction2Edition);

            Book bySettersJava8InAction = (Book) context.getBean("bySettersJava8InAction");
            log.info("{}", bySettersJava8InAction);

            Book bySettersFunctionalProgrammingInJava2Edition = (Book) context.getBean("bySettersFunctionalProgrammingInJava2Edition");
            log.info("{}", bySettersFunctionalProgrammingInJava2Edition);

            Book bySettersHighPerformanceJavaPersistence = (Book) context.getBean("bySettersHighPerformanceJavaPersistence");
            log.info("{}", bySettersHighPerformanceJavaPersistence);

            Book bySettersZombiesOnPlane = (Book) context.getBean("bySettersZombiesOnPlane");
            log.info("{}", bySettersZombiesOnPlane);

            Book bySettersZombiesOnTrain = (Book) context.getBean("bySettersZombiesOnTrain");
            log.info("{}", bySettersZombiesOnTrain);
        } catch (BeansException e) {
            log.error("The bean could not be obtained!");
        }
    }
}
