package com.moralesce.jbehave.reporter;

import org.apache.commons.lang.StringUtils;
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

	public void pending(String key) {
		Description description = findStepDescription(key);
		if (description != null) {
			this.notifier.fireTestIgnored(description);
		}
	}

	public void successful(String key) {
		Description description = findStepDescription(key);
		if (description != null) {
			this.notifier.fireTestStarted(description);
			this.notifier.fireTestFinished(description);
		}
	}

	public void failed(String key, Throwable t) {
		Description localDescription = findStepDescription(key);
		if (localDescription != null) {
			this.notifier.fireTestStarted(localDescription);
			this.notifier.fireTestFailure(new Failure(localDescription, t));
		}
	}

	private Description findStepDescription(String key) {
		for (Description description : scenarioDescription.getChildren()) {
			if (getStepName(description).equals(key)) {
				return description;
			}
		}
		return null;
	}

	private String getStepName(Description paramDescription) {
		String str = stripClassNameFromDisplayName(paramDescription);
		int i = str.indexOf(":", 0);
		return str.substring(i + 1, str.length()).trim();
	}

	private String stripClassNameFromDisplayName(Description paramDescription) {
		return StringUtils.remove(paramDescription.getDisplayName(), "(" + paramDescription.getClassName() + ")");
	}

}
