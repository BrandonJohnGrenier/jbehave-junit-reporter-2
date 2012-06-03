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
import org.jbehave.scenario.definition.ScenarioDefinition;
import org.jbehave.scenario.definition.StoryDefinition;
import org.jbehave.scenario.steps.Steps;
import org.junit.runner.Description;

public final class DescriptionGenerator {

	public static Description newDescription(Class<? extends JUnitScenario> testClass) {
		return createStoryDescription(testClass);
	}

	private static Description createStoryDescription(Class<? extends JUnitScenario> testClass) {
		StoryDefinition story = RunnableStory.createStoryDefinition(testClass);
		Description storyDescription = Description.createSuiteDescription(story.getBlurb().asString().replace("Story: ", ""), new Annotation[0]);

		int i = 1;
		for (ScenarioDefinition scenario : story.getScenarios()) {
			storyDescription.addChild(createScenarioDescription(scenario, i, RunnableSteps.createSteps(testClass)));
			i++;
		}

		return storyDescription;
	}

	private static Description createScenarioDescription(ScenarioDefinition scenario, Integer scenarioNumber, Steps steps) {
		Description scenarioDescrption = Description.createSuiteDescription("Scenario " + scenarioNumber + ": " + scenario.getTitle(), new Annotation[0]);
		String str1 = "Story " + JUnitScenarioRunner.getStoryCounter() + ", Scenario " + scenarioNumber + ", ";

		int i = 1;
		for (String step : scenario.getSteps()) {
			scenarioDescrption.addChild(createStepDescription(str1, step, steps, i));
			i++;
		}

		return scenarioDescrption;
	}

	private static Description createStepDescription(String paramString1, String paramString2, Steps steps, int stepNumber) {
		paramString1 = paramString1 + " Step " + stepNumber + ": ";
		return Description.createTestDescription(steps.getClass(), paramString1 + paramString2);
	}

}
