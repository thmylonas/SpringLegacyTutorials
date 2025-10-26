package com.thomasmylonas.spring_legacy._services;

import com.thomasmylonas.spring_legacy._base.AbstractService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * This class is Deprecated, because all of its methods are Deprecated
 */
@Deprecated
public class ApplicationContextProvider extends AbstractService {

    /**
     * This method is Deprecated, because it adds code-complexity without reason
     *
     * @param config The Configuration XML file
     * @return The "ApplicationContext"
     */
    @Deprecated
    public static ApplicationContext createApplicationContext1(String config) {

        try {
            return new ClassPathXmlApplicationContext(config); // NoSuchBeanDefinitionException, BeanCreationException, UnsatisfiedDependencyException, BeansException
        } catch (BeansException e) {
            throw new RuntimeException("The context creation failed!");
        }
    }

    /**
     * The documentation is the same as in the "ApplicationContextProvider::createApplicationContext1".
     * Additionally, this method swallows the "BeansExceptions"
     *
     * @param config The Configuration XML file
     * @return The "ApplicationContext"
     */
    @Deprecated
    public static ApplicationContext createApplicationContext2(String config) {

        try {
            return new AnnotationConfigApplicationContext(config); // NoSuchBeanDefinitionException, BeanCreationException, UnsatisfiedDependencyException, BeansException
        } catch (BeansException e) {
            LOGGER.error("'{}::new' throws '{}': '{}'", AnnotationConfigApplicationContext.class.getSimpleName(), e.getClass().getName(), e.getMessage());
        }
        return null;
    }

    /**
     * This method is Deprecated, because of the following reason:
     * <p>
     * Spring understands the method "ApplicationContext::getBean", and with the "Click+Pointer":
     * - The "Pointer" becomes "Hand", and
     * - It points to the bean definition in the "Config_File/Class"
     * <p>
     * This method "ApplicationContextProvider::getBean" wraps the method "ApplicationContext::getBean".
     * <p>
     * In this case Spring does not understand the method "ApplicationContextProvider::getBean",
     * and it DOES NOT point to the bean definition in the "Config_File/Class" (with a "Hand")
     *
     * @param context  The "ApplicationContext" used
     * @param beanName The bean name
     * @return The real bean
     */
    @Deprecated
    public static Object getBean1(ApplicationContext context, String beanName) {

        try {
            return context.getBean(beanName); // NoSuchBeanDefinitionException, BeanCreationException, UnsatisfiedDependencyException, BeansException
        } catch (BeansException e) {
            throw new RuntimeException("The bean could not be obtained!");
        }
    }

    /**
     * The documentation is the same as in the "ApplicationContextProvider::getBean1".
     * Additionally, this method swallows the "BeansExceptions"
     *
     * @param context  The "ApplicationContext" used
     * @param beanName The bean name
     * @return The real bean
     */
    @Deprecated
    public static Object getBean2(ApplicationContext context, String beanName) {

        if (context == null) {
            LOGGER.error("Error while creating the ApplicationContext!");
            return null;
        }
        try {
            return context.getBean(beanName); // NoSuchBeanDefinitionException, BeanCreationException, UnsatisfiedDependencyException, BeansException
        } catch (BeansException e) {
            LOGGER.error("'{}::getBean' throws '{}': '{}'", AnnotationConfigApplicationContext.class.getSimpleName(), e.getClass().getName(), e.getMessage());
        }
        return null;
    }

    /**
     * This method is not correct, so it is Deprecated, because it is not logical to create the "ApplicationContext",
     * get the bean, close the "ApplicationContext", and then return the bean.
     * In this case, the bean would exist in a no-existing "ApplicationContext"
     *
     * @param configClazz The Configuration class
     * @param beanName    The bean name
     * @return The real bean
     */
    @Deprecated
    public static Object getBean(Class<?> configClazz, String beanName) {

        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(configClazz)) { // Exception
            return context.getBean(beanName); // BeansException
        } catch (BeansException e) {
            LOGGER.error("'{}::getBean' throws '{}': '{}'", AnnotationConfigApplicationContext.class.getSimpleName(), e.getClass().getName(), e.getMessage());
        } catch (Exception e) {
            LOGGER.error("'{}::new' throws '{}': '{}'", AnnotationConfigApplicationContext.class.getSimpleName(), e.getClass().getName(), e.getMessage());
        }
        return null;
    }

    /**
     * This method is not correct, so it is Deprecated, because it is not logical to create the "ApplicationContext",
     * get the bean, close the "ApplicationContext", and then return the bean.
     * In this case, the bean would exist in a no-existing "ApplicationContext"
     *
     * @param config   The Configuration XML file
     * @param beanName The bean name
     * @return The real bean
     */
    @Deprecated
    public static Object getBean(String config, String beanName) {

        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config)) { // Exception
            return context.getBean(beanName); // BeansException
        } catch (BeansException e) {
            LOGGER.error("'{}::getBean' throws '{}': '{}'", ClassPathXmlApplicationContext.class.getSimpleName(), e.getClass().getName(), e.getMessage());
        } catch (Exception e) {
            LOGGER.error("'{}::new' throws '{}': '{}'", ClassPathXmlApplicationContext.class.getSimpleName(), e.getClass().getName(), e.getMessage());
        }
        return null;
    }
}
