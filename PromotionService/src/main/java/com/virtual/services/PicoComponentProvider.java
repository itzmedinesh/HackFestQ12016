package com.virtual.services;

import com.sun.jersey.core.spi.component.ioc.IoCInstantiatedComponentProvider;
import org.picocontainer.PicoContainer;

public class PicoComponentProvider implements IoCInstantiatedComponentProvider {
    private final Class<?> resourceClass;
    private final PicoContainer container;

    /**
     * Constructor PicoComponentProvider
     * @param resourceClass
     * @param container
     */

    public PicoComponentProvider(Class<?> resourceClass, PicoContainer container) {
        this.resourceClass = resourceClass;
        this.container = container;
    }

    /**
     * Method getInstance
     * @return Object
     */
    public Object getInstance() {
        Object component = container.getComponent(resourceClass);
        if(component == null) {
            throw new RuntimeException("Failed to get instance of class " + resourceClass.getName() + " from dependency injector");
        }
        return component;
    }

    public Object getInjectableInstance(Object o) {
        return o;
    }
}
