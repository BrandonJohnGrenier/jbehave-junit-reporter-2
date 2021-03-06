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

import org.jbehave.scenario.Configuration;
import org.jbehave.scenario.JUnitScenario;
import org.jbehave.scenario.definition.StoryDefinition;

import com.moralesce.jbehave.annotations.UseConfiguration;

public final class RunnableStory {

	public static StoryDefinition createStoryDefinition(Class<? extends JUnitScenario> testClass) {
		Class<? extends Configuration> configurationClass = getAnnotatedConfigurationFor(testClass);
		return ((Configuration) newInstance(configurationClass != null ? configurationClass : SimplePropertyBasedConfiguration.class)).forDefiningScenarios().loadScenarioDefinitionsFor(testClass);
	}

	private static <T> T newInstance(Class<T> testClass) {
		try {
			return testClass.newInstance();
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Could not instantiate test class: " + testClass.getSimpleName() + " : " + e.getMessage());
		}
	}

	private static Class<? extends Configuration> getAnnotatedConfigurationFor(Class<? extends JUnitScenario> testClass) {
		UseConfiguration useConfiguration = (UseConfiguration) testClass.getAnnotation(UseConfiguration.class);
		if (useConfiguration == null) {
			useConfiguration = (UseConfiguration) testClass.getSuperclass().getAnnotation(UseConfiguration.class);
		}
		return useConfiguration == null ? null : useConfiguration.value();
	}

}