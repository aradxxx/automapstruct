package ru.aradxxx.automapstruct;

import org.mapstruct.ap.spi.DefaultAccessorNamingStrategy;

import javax.lang.model.element.ExecutableElement;

public class AutoValueAccessorNamingStrategy extends DefaultAccessorNamingStrategy {
    private static final String BUILD_METHOD_NAME_PART = "build";
    private static final String BUILDER_METHOD_NAME_PART = "builder";
    private static final int GETTER_METHOD_PARAM_COUNT = 0;
    private static final int SETTER_METHOD_PARAM_COUNT = 1;

    @Override
    public boolean isGetterMethod(ExecutableElement method) {
        return method.getParameters().size() == GETTER_METHOD_PARAM_COUNT && !isBuilderMethod(method);
    }

    @Override
    public String getPropertyName(ExecutableElement method) {
        return method.getSimpleName().toString();
    }

    @Override
    protected boolean isFluentSetter(ExecutableElement method) {
        return method.getParameters().size() == SETTER_METHOD_PARAM_COUNT && !isBuilderMethod(method);
    }

    private boolean isBuilderMethod(ExecutableElement method) {
        String name = getPropertyName(method);
        return name.endsWith(BUILD_METHOD_NAME_PART) || name.endsWith(BUILDER_METHOD_NAME_PART);
    }
}
