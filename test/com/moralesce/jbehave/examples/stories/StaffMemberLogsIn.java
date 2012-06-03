package com.moralesce.jbehave.examples.stories;

import org.jbehave.scenario.JUnitScenario;
import org.jbehave.scenario.reporters.ScenarioReporter;
import org.junit.runner.RunWith;

import com.moralesce.jbehave.JUnitScenarioRunner;
import com.moralesce.jbehave.SimplePropertyBasedConfiguration;
import com.moralesce.jbehave.annotations.ReportOn;
import com.moralesce.jbehave.examples.steps.LoginSteps;

@RunWith(JUnitScenarioRunner.class)
@ReportOn(LoginSteps.class)
public class StaffMemberLogsIn extends JUnitScenario {

	public StaffMemberLogsIn(ScenarioReporter reporter) {
		super(new SimplePropertyBasedConfiguration(reporter), new LoginSteps());
	}

}
