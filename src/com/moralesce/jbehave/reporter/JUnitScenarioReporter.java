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
package com.moralesce.jbehave.reporter;

import org.jbehave.scenario.reporters.ScenarioReporter;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

public class JUnitScenarioReporter extends AbstractScenarioReporter implements ScenarioReporter {

	private final RunNotifier notifier;
	private final Description storyDescription;
	private Description scenarioDescription = null;

	public static JUnitScenarioReporter newReporter(RunNotifier notifier, Description storyDescription) {
		return new JUnitScenarioReporter(notifier, storyDescription);
	}

	JUnitScenarioReporter(RunNotifier notifier, Description storyDescription) {
		this.notifier = notifier;
		this.storyDescription = storyDescription;
	}

	public void beforeScenario(String key) {
		for (Description description : storyDescription.getChildren()) {
			if (description.getDisplayName().contains(key)) {
				this.scenarioDescription = description;
			}
		}
	}

	public void afterScenario() {
		this.scenarioDescription = null;
	}

	public void pending(String key) {
		Description description = resolveStepDescription(key);
		if (description != null) {
			this.notifier.fireTestIgnored(description);
		}
	}

	public void successful(String key) {
		Description description = resolveStepDescription(key);
		if (description != null) {
			this.notifier.fireTestStarted(description);
			this.notifier.fireTestFinished(description);
		}
	}

	public void failed(String key, Throwable t) {
		Description localDescription = resolveStepDescription(key);
		if (localDescription != null) {
			this.notifier.fireTestStarted(localDescription);
			this.notifier.fireTestFailure(new Failure(localDescription, t));
		}
	}

	private Description resolveStepDescription(String key) {
		for (Description description : scenarioDescription.getChildren()) {
			if (getStepName(description).equals(key)) {
				return description;
			}
		}
		return null;
	}

	private String getStepName(Description stepDescription) {
		String stepName = stripClassNameFromDisplayName(stepDescription);
		int i = stepName.indexOf(" - ", 0);
		return stepName.substring(i + 3, stepName.length()).trim();
	}

	private String stripClassNameFromDisplayName(Description stepDescription) {
		return stepDescription.getDisplayName().split("\\(")[0];
	}

}
