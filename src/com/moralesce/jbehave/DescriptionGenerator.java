package com.moralesce.jbehave;

import java.lang.annotation.Annotation;
import java.util.Iterator;

import org.jbehave.scenario.JUnitScenario;
import org.jbehave.scenario.definition.ScenarioDefinition;
import org.jbehave.scenario.definition.StoryDefinition;
import org.jbehave.scenario.steps.Steps;
import org.junit.runner.Description;

public final class DescriptionGenerator {
	public static Description newDescription(Class<? extends JUnitScenario> paramClass) {
		return storyDescription(paramClass);
	}

	private static Description storyDescription(Class<? extends JUnitScenario> paramClass) {
		StoryDefinition localStoryDefinition = RunnableStoryDefinition.newStoryDefinition(paramClass);
		Description localDescription = Description.createSuiteDescription(localStoryDefinition.getBlurb().asString(), new Annotation[0]);
		int i = 1;
		Iterator localIterator = localStoryDefinition.getScenarios().iterator();
		while (localIterator.hasNext()) {
			ScenarioDefinition localScenarioDefinition = (ScenarioDefinition) localIterator.next();
			localDescription.addChild(scenarioDescription(localScenarioDefinition, i, RunnableSteps.newSteps(paramClass)));
			i++;
		}
		return localDescription;
	}

	private static Description scenarioDescription(ScenarioDefinition paramScenarioDefinition, int paramInt, Steps paramSteps) {
		Description localDescription = Description.createSuiteDescription("Scenario " + paramInt + ": " + paramScenarioDefinition.getTitle(), new Annotation[0]);
		String str1 = "Story " + JUnitScenarioRunner.getStoryCounter() + ", Scenario " + paramInt + ", ";
		int i = 1;
		Iterator localIterator = paramScenarioDefinition.getSteps().iterator();
		while (localIterator.hasNext()) {
			String str2 = (String) localIterator.next();
			localDescription.addChild(stepDescription(str1, str2, paramSteps, i));
			i++;
		}
		return localDescription;
	}

	private static Description stepDescription(String paramString1, String paramString2, Steps paramSteps, int paramInt) {
		paramString1 = paramString1 + " Step " + paramInt + ": ";
		return Description.createTestDescription(paramSteps.getClass(), paramString1 + paramString2);
	}
}
