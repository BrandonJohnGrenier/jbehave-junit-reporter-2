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


Scenario: A staff member provides an invalid password

Given a staff member is on the Login page
When the staff member enters a username Administrator and password Rubbish
And the staff member clicks on the Login button
Then the staff member should be redirected to the Login page
And the staff member is presented with a login message: Invalid username or password, try again