## About

The JBehave JUnit reporter is an addon to [JBehave](http://jbehave.org) 2 that provides fine-grained visualisation and reporting around the progress of your stories. Each JBehave step is tracked and reported on as an individual test case. 

If you're running a story as a JUnit test through your favorite IDE, you'll be able to get output like this:

<img src="http://www.moralesce.com/wp-content/uploads/2012/06/jbehave-scenario-reporter.png"></img>

You can gain this capability by simply dropping the jbehave-junit-reporter JAR file in your classpath, and then telling the reporter what you'd like to report on using the @ReportOn annotation. Visit the [downloads](https://github.com/BrandonJohnGrenier/jbehave-junit-reporter/downloads) page to grab a copy of the library.

<br/>

## What You Need

#### JUnit 4.5 or Later
The JBehave Junit reporter depends on JUnit 4.5 or later, and is not compatible with earlier versions of JUnit.

#### JBehave 2
The JBehave JUnit reporter depends the 2.x series of JBehave (2.0.0 to 2.5.9), and is not compatible with JBehave 3.0.0 or above.

<br/>

## How it Works

The reporter will visually mark failing steps, and also provides a count of the number of steps and stories that are failing.

<img src="http://www.moralesce.com/wp-content/uploads/2012/06/jbehave-failing-steps.png"></img>

<br/>

The reporter will visually mark pending steps, and also provides a count of the number of steps that are pending. These are reported as 'ignored' in JUnit.

<img src="http://www.moralesce.com/wp-content/uploads/2012/06/jbehave-pending-steps.png"></img>

<br/>

## A Walkthrough
To show you how to use the JBehave JUnit reporter, we'll go through a complete example

<br/>

First, we'll create a plain text file named **staff_member_logs_in** with the following content:

	Story: As a staff member, I want to login so that I can get my work done.


	Scenario: A staff member provides a valid username and password

	Given a staff member is on the Login page
	When the staff member enters a username Administrator and password Password
	And the staff member clicks on the Login button
	Then the staff member should be redirected to the Home page

	Scenario: A staff member provides an invalid username

	Given a staff member is on the Login page
	When the staff member enters a username Rubbish and password Password
	And the staff member clicks on the Login button
	Then the staff member should be redirected to the Login page
	And the staff member is presented with a login message: Invalid username or password, try again
	
<br/>

Next, we'll create a Java class named **LoginSteps.java** which will map our text descriptions to Java methods. Note the use of the @Given, @When and @Then annotations, and how they map to the steps above. For readability purposes we've just mapped a few text descriptions.

Also take note of the Assert.assertTrue(true) calls within each method. This is where your standard JUnit assert-y calls would typically go in actual tests.

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

		@Then("the staff member should be redirected to the Home page")
		public void shouldBeRedirectedToTheHomePage() {
			Assert.assertTrue(true);
		}

	}

<br/>

To wrap things up, create a class named **StaffMemberLogsIn.java**, and place it in the same directory as our text file.


    import org.jbehave.scenario.JUnitScenario;
	import org.jbehave.scenario.reporters.ScenarioReporter;
	import org.junit.runner.RunWith;

	import com.moralesce.jbehave.JUnitScenarioRunner;
	import com.moralesce.jbehave.DefaultStoryConfiguration;
	import com.moralesce.jbehave.annotations.ReportOn;
	import com.moralesce.jbehave.examples.steps.LoginSteps;

	@RunWith(JUnitScenarioRunner.class)
	@ReportOn(LoginSteps.class)
	public class StaffMemberLogsIn extends JUnitScenario {

		public StaffMemberLogsIn(ScenarioReporter reporter) {
			super(new SimplePropertyBasedConfiguration(reporter), new LoginSteps());
		}

	}
	
That's it! You should be able to report on your first JBehave test. A few points to note here:

1. We're configuring JUnit to @RunWith our JUnitScenarioRunner.
2. As we've touched on before, we want to @ReportOn the steps we defined in our LoginSteps class.
3. Our class extends JUnitScenario - every JBehave test must extend JUnitScenario.
4. You should be able to run this test as a JUnit test, and get output similar to the screenshots presented above.

<br/>

We are using a PropertyBasedConfiguration instance named SimplePropertyBasedConfiguration instance. Custom JBehave configuration is a bit beyond our scope, however it is important to note that if you do want to use a different PropertyBasedConfiguration implementation you can. You'll just need to setup the @UseConfiguration annotation, shown in the example below: 



	import org.jbehave.scenario.JUnitScenario;
	import org.jbehave.scenario.reporters.ScenarioReporter;
	import org.junit.runner.RunWith;
	
	import com.moralesce.jbehave.JUnitScenarioRunner;
	import com.moralesce.jbehave.annotations.ReportOn;
	import com.moralesce.jbehave.annotations.UseConfiguration;
	import com.moralesce.jbehave.examples.steps.LoginSteps;

	@RunWith(JUnitScenarioRunner.class)
	@ReportOn(LoginSteps.class)
	@UseConfiguration(MyCustomPropertyConfiguration.class)
	public class StaffMemberLogsIn extends JUnitScenario {

		public StaffMemberLogsIn(ScenarioReporter reporter) {
			super(new MyCustomPropertyConfiguration(reporter), new LoginSteps());
		}

	}
	
<br/>

## Liscence

The JBehave JUnit reporter is lisced under Apache 2.0.

<br/>