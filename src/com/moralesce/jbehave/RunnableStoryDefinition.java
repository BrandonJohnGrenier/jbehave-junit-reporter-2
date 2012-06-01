package com.moralesce.jbehave;

import java.lang.annotation.Annotation;

import org.jbehave.scenario.Configuration;
import org.jbehave.scenario.JUnitScenario;
import org.jbehave.scenario.definition.StoryDefinition;

public final class RunnableStoryDefinition {
	public static StoryDefinition newStoryDefinition(Class<? extends JUnitScenario> paramClass) {
		verifyClassAnnotatedWithConfiguration(paramClass);
		return ((Configuration) newInstance(getAnnotatedConfigurationFor(paramClass))).forDefiningScenarios().loadScenarioDefinitionsFor(paramClass);
	}

	private static <T> T newInstance(Class<T> paramClass) {
		try {
			return paramClass.newInstance();
		}
		catch (Exception localException) {
			throw new IllegalArgumentException("Could not instantiate test class: " + paramClass.getSimpleName() + " : " + localException.getMessage());
		}
	}

	private static void verifyClassAnnotatedWithConfiguration(Class<? extends JUnitScenario> paramClass) {
		Annotation localAnnotation = paramClass.getAnnotation(UseConfiguration.class);
		if (localAnnotation == null)
			localAnnotation = paramClass.getSuperclass().getAnnotation(UseConfiguration.class);
		if (localAnnotation == null)
			throw new IllegalArgumentException("Test class " + paramClass.getSimpleName() + " must be annotated with @" + UseConfiguration.class.getSimpleName());
	}

	private static Class<? extends Configuration> getAnnotatedConfigurationFor(Class<? extends JUnitScenario> paramClass) {
		UseConfiguration localUseConfiguration = (UseConfiguration) paramClass.getAnnotation(UseConfiguration.class);
		if (localUseConfiguration == null)
			localUseConfiguration = (UseConfiguration) paramClass.getSuperclass().getAnnotation(UseConfiguration.class);
		return localUseConfiguration.value();
	}
}