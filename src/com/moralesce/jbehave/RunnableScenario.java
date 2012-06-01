package com.moralesce.jbehave;

import org.jbehave.scenario.JUnitScenario;
import org.jbehave.scenario.reporters.ScenarioReporter;

public final class RunnableScenario {
	
	public static JUnitScenario newScenario(Class<? extends JUnitScenario> paramClass, ScenarioReporter paramScenarioReporter) {
		try {
			return (JUnitScenario) paramClass.getConstructor(new Class[] { ScenarioReporter.class }).newInstance(new Object[] { paramScenarioReporter });
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Could not instantiate test class: " + paramClass.getSimpleName() + " : " + e.getMessage());
		}
	}
	
}
