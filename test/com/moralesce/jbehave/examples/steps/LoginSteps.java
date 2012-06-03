package com.moralesce.jbehave.examples.steps;

import junit.framework.Assert;

import org.jbehave.scenario.annotations.Given;
import org.jbehave.scenario.annotations.Then;
import org.jbehave.scenario.annotations.When;
import org.jbehave.scenario.steps.Steps;

public class LoginSteps extends Steps {

	@Given("a staff member is on the Login page")
	public void automateSuccessfulLoginForRole() {
		Assert.assertTrue(true);
	}

	@When("the staff member enters a username $username and password $password")
	public void enterCredentials(String username, String password) {
		Assert.assertTrue(true);
	}

	@When("the staff member clicks on the Login button")
	public void clickOnTheLoginButton() {
		Assert.assertTrue(true);
	}

	@Then("the staff member should be redirected to the Login page")
	public void shouldBeRedirectedToTheLoginPage() {
		Assert.assertTrue(true);
	}

	@Then("the staff member is presented with a login message: Invalid username or password, try again")
	public void presentedWithLoginMessage() {
		Assert.assertTrue(true);
	}


	@Then("the staff member should be redirected to the Home page")
	public void shouldBeRedirectedToTheHomePage() {
		Assert.assertTrue(true);
	}

	
}