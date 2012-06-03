package com.moralesce.jbehave;

import org.jbehave.scenario.JUnitScenario;
import org.jbehave.scenario.reporters.ScenarioReporter;

public final class RunnableScenario {
	
	public static JUnitScenario newScenario(Class<? extends JUnitScenario> testClass, ScenarioReporter reporter) {
		try {
			return (JUnitScenario) testClass.getConstructor(new Class[] { ScenarioReporter.class }).newInstance(new Object[] { reporter });
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Could not instantiate test class: " + testClass.getSimpleName() + " : " + e.getMessage());
		}
	}
	
}
