package com.moralesce.jbehave.reporter;

import java.util.List;
import java.util.Map;

import org.jbehave.scenario.definition.Blurb;
import org.jbehave.scenario.definition.ExamplesTable;
import org.jbehave.scenario.definition.StoryDefinition;
import org.jbehave.scenario.reporters.ScenarioReporter;

abstract class AbstractScenarioReporter implements ScenarioReporter {

	public void beforeScenario(String paramString) {

	}

	public void afterScenario() {

	}

	public void beforeStory(Blurb paramBlurb) {

	}

	public void afterStory() {

	}

	public void examplesTable(ExamplesTable paramExamplesTable) {

	}

	public void examplesTableRow(Map<String, String> paramMap) {

	}

	public void givenScenarios(List<String> paramList) {

	}

	public void afterExamples() {

	}

	public void afterStory(boolean paramBoolean) {

	}

	public void beforeExamples(List<String> paramList, ExamplesTable paramExamplesTable) {

	}

	public void beforeStory(StoryDefinition paramStoryDefinition, boolean paramBoolean) {

	}

	public void dryRun() {

	}

	public void example(Map<String, String> paramMap) {

	}

	public void ignorable(String paramString) {

	}

	public void notPerformed(String key) {

	}

}