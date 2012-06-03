package com.moralesce.jbehave;

import java.lang.annotation.Annotation;
import java.util.Iterator;

import org.jbehave.scenario.JUnitScenario;
import org.jbehave.scenario.definition.ScenarioDefinition;
import org.jbehave.scenario.definition.StoryDefinition;
import org.jbehave.scenario.steps.Steps;
import org.junit.runner.Description;

public final class DescriptionGenerator {

	public static Description newDescription(Class<? extends JUnitScenario> testClass) {
		return createStoryDescription(testClass);
	}

	private static Description createStoryDescription(Class<? extends JUnitScenario> testClass) {
		StoryDefinition localStoryDefinition = RunnableStory.newStoryDefinition(testClass);
		Description description = Description.createSuiteDescription(localStoryDefinition.getBlurb().asString(), new Annotation[0]);
		
		int i = 1;
		Iterator localIterator = localStoryDefinition.getScenarios().iterator();
		while (localIterator.hasNext()) {
			ScenarioDefinition localScenarioDefinition = (ScenarioDefinition) localIterator.next();
			description.addChild(createScenarioDescription(localScenarioDefinition, i, RunnableSteps.newSteps(testClass)));
			i++;
		}
		
		return description;
	}

	private static Description createScenarioDescription(ScenarioDefinition scenario, Integer scenarioNumber, Steps steps) {
		Description description = Description.createSuiteDescription("Scenario " + scenarioNumber + ": " + scenario.getTitle(), new Annotation[0]);
		String str1 = "Story " + JUnitScenarioRunner.getStoryCounter() + ", Scenario " + scenarioNumber + ", ";
		
		int i = 1;
		for (String step : scenario.getSteps()) {
			description.addChild(createStepDescription(str1, step, steps, i));
			i++;
		}

		return description;
	}

	private static Description createStepDescription(String paramString1, String paramString2, Steps steps, int stepNumber) {
		paramString1 = paramString1 + " Step " + stepNumber + ": ";
		return Description.createTestDescription(steps.getClass(), paramString1 + paramString2);
	}

}
