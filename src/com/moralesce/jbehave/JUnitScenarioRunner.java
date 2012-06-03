package com.moralesce.jbehave;

import org.jbehave.scenario.JUnitScenario;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

import com.moralesce.jbehave.reporter.JUnitScenarioReporter;

public class JUnitScenarioRunner extends Runner {

	private final Description description;
	private final Class<? extends JUnitScenario> testClass;
	private static int storyCounter = 1;

	public JUnitScenarioRunner(Class<? extends JUnitScenario> paramClass) {
		this.testClass = paramClass;
		this.description = DescriptionGenerator.newDescription(paramClass);
		storyCounter += 1;
	}

	public Description getDescription() {
		return this.description;
	}

	public void run(RunNotifier runNotifier) {
		try {
			JUnitScenario scenario = RunnableScenario.newScenario(this.testClass, JUnitScenarioReporter.newReporter(runNotifier, this.description));
			scenario.runScenario();
		}
		catch (Throwable t) {
			runNotifier.fireTestStarted(this.description);
			runNotifier.fireTestFailure(new Failure(this.description, t));
		}
	}

	public static int getStoryCounter() {
		return storyCounter;
	}

}
