package com.moralesce.jbehave.examples.steps;

import junit.framework.Assert;

import org.jbehave.scenario.annotations.Given;
import org.jbehave.scenario.steps.Steps;

public class LoginSteps extends Steps {

	@Given("a staff member is on the Login page")
	public void automateSuccessfulLoginForRole() {
		Assert.assertTrue(true);
	}

}