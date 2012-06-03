package com.moralesce.jbehave.examples.stories;

import org.jbehave.scenario.reporters.ScenarioReporter;

import com.moralesce.jbehave.annotations.ReportOn;
import com.moralesce.jbehave.examples.Story;
import com.moralesce.jbehave.examples.steps.LoginSteps;

@ReportOn(LoginSteps.class)
public class StaffMemberLogsIn extends Story {

    public StaffMemberLogsIn(ScenarioReporter reporter) {
        super(reporter, new LoginSteps());
    }

}
