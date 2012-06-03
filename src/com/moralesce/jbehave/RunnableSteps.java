package com.moralesce.jbehave;

import java.lang.annotation.Annotation;

import org.jbehave.scenario.JUnitScenario;
import org.jbehave.scenario.steps.Steps;

import com.moralesce.jbehave.annotations.ReportOn;

public final class RunnableSteps {

	public static Steps newSteps(Class<? extends JUnitScenario> testClass) {
		verifyClassAnnotated(testClass, ReportOn.class);
		return (Steps) newInstance(((ReportOn) testClass.getAnnotation(ReportOn.class)).value());
	}

	private static <T> T newInstance(Class<T> testClass) {
		try {
			return testClass.newInstance();
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Could not instantiate test class: " + testClass.getSimpleName() + " : " + e.getMessage());
		}
	}

	private static void verifyClassAnnotated(Class<? extends JUnitScenario> testClass, Class<? extends Annotation> annotationType) {
		Annotation annotation = testClass.getAnnotation(annotationType);
		if (annotation == null) {
			throw new IllegalArgumentException("Test class " + testClass.getSimpleName() + " must be annotated with @" + annotationType.getSimpleName());
		}
	}

}