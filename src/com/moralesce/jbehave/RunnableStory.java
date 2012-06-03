package com.moralesce.jbehave;

import java.lang.annotation.Annotation;

import org.jbehave.scenario.Configuration;
import org.jbehave.scenario.JUnitScenario;
import org.jbehave.scenario.definition.StoryDefinition;

import com.moralesce.jbehave.annotations.UseConfiguration;

public final class RunnableStory {

	public static StoryDefinition newStoryDefinition(Class<? extends JUnitScenario> paramClass) {
		verifyClassAnnotatedWithConfiguration(paramClass);
		return ((Configuration) newInstance(getAnnotatedConfigurationFor(paramClass))).forDefiningScenarios().loadScenarioDefinitionsFor(paramClass);
	}

	private static <T> T newInstance(Class<T> testClass) {
		try {
			return testClass.newInstance();
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Could not instantiate test class: " + testClass.getSimpleName() + " : " + e.getMessage());
		}
	}

	private static void verifyClassAnnotatedWithConfiguration(Class<? extends JUnitScenario> testClass) {
		Annotation annotation = testClass.getAnnotation(UseConfiguration.class);
		if (annotation == null) {
			annotation = testClass.getSuperclass().getAnnotation(UseConfiguration.class);
		}
		if (annotation == null) {
			throw new IllegalArgumentException("Test class " + testClass.getSimpleName() + " must be annotated with @" + UseConfiguration.class.getSimpleName());
		}
	}

	private static Class<? extends Configuration> getAnnotatedConfigurationFor(Class<? extends JUnitScenario> testClass) {
		UseConfiguration useConfiguration = (UseConfiguration) testClass.getAnnotation(UseConfiguration.class);
		if (useConfiguration == null) {
			useConfiguration = (UseConfiguration) testClass.getSuperclass().getAnnotation(UseConfiguration.class);
		}
		return useConfiguration.value();
	}

}