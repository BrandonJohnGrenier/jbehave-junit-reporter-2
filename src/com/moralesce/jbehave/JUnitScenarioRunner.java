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

	public JUnitScenarioRunner(Class<? extends JUnitScenario> testClass) {
		this.testClass = testClass;
		this.description = DescriptionGenerator.newDescription(testClass);
		storyCounter += 1;
	}

	public Description getDescription() {
		return this.description;
	}

	public void run(RunNotifier notifier) {
		try {
			JUnitScenario scenario = RunnableScenario.createScenario(this.testClass, JUnitScenarioReporter.newReporter(notifier, this.description));
			scenario.runScenario();
		}
		catch (Throwable t) {
			notifier.fireTestStarted(this.description);
			notifier.fireTestFailure(new Failure(this.description, t));
		}
	}

	public static int getStoryCounter() {
		return storyCounter;
	}

}
