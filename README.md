## About

The JBehave JUnit reporter is an addon to [JBehave](http://jbehave.org) 2 that provides fine-grained visualisation and reporting around the progress of your stories. Each JBehave step is tracked and reported on as an individual test case. 

If you're running a story as a unit test through your favorite IDE, you'll be able to get output like this:

<img src="http://www.moralesce.com/wp-content/uploads/2012/06/jbehave-scenario-reporter.png"></img>

You can gain this capability by simply dropping the jbehave-junit-reporter JAR file in your classpath, and then telling the reporter what you'd like to report on using the @ReportOn annotation. Visit the [downloads](https://github.com/BrandonJohnGrenier/jbehave-junit-reporter/downloads) page to grab a copy of the library.

<br/>

## What You Need

#### JUnit 4.5 or Later
The JBehave Junit reporter depends on JUnit 4.5 or later, and is not compatible with earlier versions of JUnit.

#### JBehave 2
The JBehave JUnit reporter depends the 2.x series of JBehave (2.0.0 to 2.5.9), and is not compatible with JBehave 3.0.0 or above.

<br/>

## Features

The reporter will visually mark failing steps, and also provides a count of the number of steps and stories that are failing.

<img src="http://www.moralesce.com/wp-content/uploads/2012/06/jbehave-failing-steps.png"></img>

<br/>

The reporter will visually mark pending steps, and also provides a count of the number of steps that are pending. These are reported as 'ignored' in JUnit.

<img src="http://www.moralesce.com/wp-content/uploads/2012/06/jbehave-pending-steps.png"></img>