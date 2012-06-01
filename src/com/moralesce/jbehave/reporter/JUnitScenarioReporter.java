package com.moralesce.jbehave.reporter;

import java.util.Iterator;

import org.jbehave.scenario.reporters.ScenarioReporter;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

public class JUnitScenarioReporter extends AbstractScenarioReporter implements ScenarioReporter {
	private final RunNotifier notifier;
	private final Description storyDescription;
	private Description scenarioDescription = null;

	public static JUnitScenarioReporter newReporter(RunNotifier paramRunNotifier, Description paramDescription) {
		return new JUnitScenarioReporter(paramRunNotifier, paramDescription);
	}

	JUnitScenarioReporter(RunNotifier paramRunNotifier, Description paramDescription) {
		this.notifier = paramRunNotifier;
		this.storyDescription = paramDescription;
	}

	public void beforeScenario(String paramString) {
		Description localDescription = scenarioDescription(paramString);
		if (localDescription != null) {
			this.scenarioDescription = localDescription;
			this.notifier.fireTestStarted(localDescription);
		}
	}

	public void notPerformed(String paramString) {
		Description localDescription = stepDescription(paramString);
		if (localDescription != null) {
			this.notifier.fireTestStarted(localDescription);
			this.notifier.fireTestIgnored(localDescription);
		}
	}

	public void pending(String paramString) {
		Description localDescription = stepDescription(paramString);
		if (localDescription != null) {
			this.notifier.fireTestStarted(localDescription);
			this.notifier.fireTestIgnored(localDescription);
		}
	}

	public void successful(String paramString) {
		Description localDescription = stepDescription(paramString);
		if (localDescription != null) {
			this.notifier.fireTestStarted(localDescription);
			this.notifier.fireTestFinished(localDescription);
		}
	}

	public void failed(String paramString, Throwable paramThrowable) {
		Description localDescription = stepDescription(paramString);
		if (localDescription != null) {
			this.notifier.fireTestStarted(localDescription);
			this.notifier.fireTestFailure(new Failure(localDescription, paramThrowable));
		}
	}

	private Description stepDescription(String paramString) {
		Iterator localIterator = this.scenarioDescription.getChildren().iterator();
		while (localIterator.hasNext()) {
			Description localDescription = (Description) localIterator.next();
			if (getStepName(localDescription).equals(paramString))
				return localDescription;
		}
		return null;
	}

	private Description scenarioDescription(String paramString) {
		Iterator localIterator = this.storyDescription.getChildren().iterator();
		while (localIterator.hasNext()) {
			Description localDescription = (Description) localIterator.next();
			if (localDescription.getDisplayName().contains(paramString))
				return localDescription;
		}
		return null;
	}

	private String getStepName(Description paramDescription) {
		String str = stripClassNameFromDisplayName(paramDescription);
		int i = str.indexOf(":", 0);
		return str.substring(i + 1, str.length()).trim();
	}

	private String stripClassNameFromDisplayName(Description paramDescription) {
		return "";
		//return StringUtils.remove(paramDescription.getDisplayName(), "(" + paramDescription.getClassName() + ")");
	}

}
