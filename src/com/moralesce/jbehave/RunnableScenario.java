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
