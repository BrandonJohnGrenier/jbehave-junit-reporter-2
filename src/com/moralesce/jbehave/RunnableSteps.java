/**
 *  Copyright 2012 Brandon Grenier
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.moralesce.jbehave;

import java.lang.annotation.Annotation;

import org.jbehave.scenario.JUnitScenario;
import org.jbehave.scenario.steps.Steps;

import com.moralesce.jbehave.annotations.ReportOn;

public final class RunnableSteps {

	public static Steps createSteps(Class<? extends JUnitScenario> testClass) {
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