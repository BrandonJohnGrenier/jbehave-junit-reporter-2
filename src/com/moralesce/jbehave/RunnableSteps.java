package com.moralesce.jbehave;

import java.lang.annotation.Annotation;

import org.jbehave.scenario.JUnitScenario;
import org.jbehave.scenario.steps.Steps;

public final class RunnableSteps {
	public static Steps newSteps(Class<? extends JUnitScenario> paramClass) {
		verifyClassAnnotated(paramClass, ReportOn.class);
		return (Steps) newInstance(((ReportOn) paramClass.getAnnotation(ReportOn.class)).value());
	}

	private static <T> T newInstance(Class<T> paramClass) {
		try {
			return paramClass.newInstance();
		}
		catch (Exception localException) {
			throw new IllegalArgumentException("Could not instantiate test class: " + paramClass.getSimpleName() + " : " + localException.getMessage());
		}
	}

	private static void verifyClassAnnotated(Class<? extends JUnitScenario> paramClass, Class<? extends Annotation> paramClass1) {
		Annotation localAnnotation = paramClass.getAnnotation(paramClass1);
		if (localAnnotation == null)
			throw new IllegalArgumentException("Test class " + paramClass.getSimpleName() + " must be annotated with @" + paramClass1.getSimpleName());
	}
}