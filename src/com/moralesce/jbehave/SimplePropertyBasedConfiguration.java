package com.moralesce.jbehave;

import org.jbehave.scenario.PropertyBasedConfiguration;
import org.jbehave.scenario.errors.ErrorStrategy;
import org.jbehave.scenario.parser.ClasspathScenarioDefiner;
import org.jbehave.scenario.parser.PatternScenarioParser;
import org.jbehave.scenario.parser.UnderscoredCamelCaseResolver;
import org.jbehave.scenario.reporters.ScenarioReporter;

public class SimplePropertyBasedConfiguration extends PropertyBasedConfiguration {

	private ScenarioReporter reporter;

	public SimplePropertyBasedConfiguration() {

	}

	public SimplePropertyBasedConfiguration(ScenarioReporter reporter) {
		this.reporter = reporter;
	}

	public ClasspathScenarioDefiner forDefiningScenarios() {
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		return new ClasspathScenarioDefiner(new UnderscoredCamelCaseResolver(), new PatternScenarioParser(this), contextClassLoader);
	}

	public ScenarioReporter forReportingScenarios() {
		return reporter;
	}

	public ErrorStrategy forHandlingErrors() {
		return ErrorStrategy.RETHROW;
	}

}
