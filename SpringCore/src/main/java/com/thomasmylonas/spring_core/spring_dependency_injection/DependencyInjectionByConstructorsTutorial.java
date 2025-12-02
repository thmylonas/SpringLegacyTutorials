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
public class DependencyInjectionByConstructorsTutorial extends AbstractSpringTutorials {

    private static final String EXAMPLE_1_CONFIG = "config/spring_dependency_injection_config/di_by_constructors/di_by_constructors_example1_config.xml";
    private static final String EXAMPLE_2a_CONFIG = "config/spring_dependency_injection_config/di_by_constructors/di_by_constructors_example2a_config.xml";
    private static final String EXAMPLE_2b_CONFIG = "config/spring_dependency_injection_config/di_by_constructors/di_by_constructors_example2b_config.xml";
    private static final String EXAMPLE_3_CONFIG = "config/spring_dependency_injection_config/di_by_constructors/di_by_constructors_example3_config.xml";

    public void diByConstructorsExample1() {

        try (var context = new ClassPathXmlApplicationContext(EXAMPLE_1_CONFIG)) {

            GroupOfPeople byConstructorsGroupOfPeople = (GroupOfPeople) context.getBean("byConstructorsGroupOfPeople");
            log.info("{}", byConstructorsGroupOfPeople);

            // Russian singers beans (based on "Person*")

            Person1 byConstructorsAniLorak = (Person1) context.getBean("byConstructorsAniLorak");
            log.info("{}", byConstructorsAniLorak);

            Person2 byConstructorsJasmine = (Person2) context.getBean("byConstructorsJasmine");
            log.info("{}", byConstructorsJasmine);

            Person3 byConstructorsLiubanya = (Person3) context.getBean("byConstructorsLiubanya");
            log.info("{}", byConstructorsLiubanya);

            Person4 byConstructorsMarina = (Person4) context.getBean("byConstructorsMarina");
            log.info("{}", byConstructorsMarina);

            // Rhythmic gymnastics girls beans (based on "Person1")

            Person1 byConstructorsSasha = (Person1) context.getBean("byConstructorsSasha");
            log.info("{}", byConstructorsSasha);

            Person1 byConstructorsYana = (Person1) context.getBean("byConstructorsYana");
            log.info("{}", byConstructorsYana);

            Person1 byConstructorsZenya = (Person1) context.getBean("byConstructorsZenya");
            log.info("{}", byConstructorsZenya);

            Person1 byConstructorsAlina = (Person1) context.getBean("byConstructorsAlina");
            log.info("{}", byConstructorsAlina);

            Person1 byConstructorsMelita = (Person1) context.getBean("byConstructorsMelita");
            log.info("{}", byConstructorsMelita);
        } catch (BeansException e) {
            log.error("The bean could not be obtained!");
        }
    }

    public void diByConstructorsExample2() {

        try (var context1 = new ClassPathXmlApplicationContext(EXAMPLE_2a_CONFIG);
             var context2 = new ClassPathXmlApplicationContext(EXAMPLE_2b_CONFIG)) {

            // Case A
            Employee byConstructorsThomas1 = (Employee) context1.getBean("byConstructorsThomas1");
            log.info("{}", byConstructorsThomas1);

            // Case B
            Employee byConstructorsThomas2 = (Employee) context2.getBean("byConstructorsThomas2");
            log.info("{}", byConstructorsThomas2);
        } catch (BeansException e) {
            log.error("The bean could not be obtained!");
        }
    }

    public void diByConstructorsExample3() {

        try (var context = new ClassPathXmlApplicationContext(EXAMPLE_3_CONFIG)) {

            Library byConstructorsLibrary = (Library) context.getBean("byConstructorsLibrary");
            log.info("{}", byConstructorsLibrary);

            // Teachers beans (based on "Teacher")

            Teacher byConstructorsSpilca = (Teacher) context.getBean("byConstructorsSpilca");
            log.info("{}", byConstructorsSpilca);

            Teacher byConstructorsPaumard = (Teacher) context.getBean("byConstructorsPaumard");
            log.info("{}", byConstructorsPaumard);

            Teacher byConstructorsSubramaniam = (Teacher) context.getBean("byConstructorsSubramaniam");
            log.info("{}", byConstructorsSubramaniam);

            Teacher byConstructorsMihalcea = (Teacher) context.getBean("byConstructorsMihalcea");
            log.info("{}", byConstructorsMihalcea);

            // Books beans (based on "Book")

            Book byConstructorsSpringSecurityInAction2Edition = (Book) context.getBean("byConstructorsSpringSecurityInAction2Edition");
            log.info("{}", byConstructorsSpringSecurityInAction2Edition);

            Book byConstructorsJava8InAction = (Book) context.getBean("byConstructorsJava8InAction");
            log.info("{}", byConstructorsJava8InAction);

            Book byConstructorsFunctionalProgrammingInJava2Edition = (Book) context.getBean("byConstructorsFunctionalProgrammingInJava2Edition");
            log.info("{}", byConstructorsFunctionalProgrammingInJava2Edition);

            Book byConstructorsHighPerformanceJavaPersistence = (Book) context.getBean("byConstructorsHighPerformanceJavaPersistence");
            log.info("{}", byConstructorsHighPerformanceJavaPersistence);

            Book byConstructorsZombiesOnPlane = (Book) context.getBean("byConstructorsZombiesOnPlane");
            log.info("{}", byConstructorsZombiesOnPlane);

            Book byConstructorsZombiesOnTrain = (Book) context.getBean("byConstructorsZombiesOnTrain");
            log.info("{}", byConstructorsZombiesOnTrain);
        } catch (BeansException e) {
            log.error("The bean could not be obtained!");
        }
    }
}
