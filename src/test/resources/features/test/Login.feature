Feature: Login feature

  Background:
    Given I am on the TT get started screen

  @nonsim @smoke @test
  Scenario: Verify user is able to login on a non-sim device
    When I enter the valid credentials for "IOSUser1" on "non sim" device
    And I accept the notification alerts
    Then I should see the roster

  @nonsim
  Scenario Outline: Verify user is unable to login on a non-sim device
    When I do not enter the valid credentials for "<userType>" on "<deviceType>" device
    And I do not accept the notification alerts
    Then I should not see the roster

    Examples:
      | userType         | deviceType  |
      | invalid user     | non sim     |
      | invalid format   | non sim     |
      | invalid password | non sim     |

  @sim @smoke
  Scenario: Verify user is able to login on a sim device
    When I enter the valid credentials for "IOSUser1" on "sim" device
    And I accept the notification alerts
    Then I should see the roster

  @sim
  Scenario Outline: Verify user is unable to login on a sim device
    When I do not enter the valid credentials for "<userType>" on "<deviceType>" device
    And I do not accept the notification alerts
    Then I should not see the roster

    Examples:
      | userType         | deviceType  |
      | invalid user     | sim         |
      | invalid format   | sim         |
      | invalid password | sim         |

  Scenario Outline: Verify user is able to login with different accounts
    When I enter the valid credentials for a <accounts> account
    And I enter valid authentication
    Then I should see the roster

    Examples:
      | accounts      |
#      | SAML          |
#      | ADFS          |
#      | Singapore     |
#      | Singapore/USA |
      | USA and Singapore |

  Scenario Outline: Verify user is unable to login with different account types
    When I do not enter the valid credentials for a <accounts> account
    And I do not enter valid authentication
    Then I should not see the roster

    Examples:
      | accounts      |
      | SAML          |
#      | ADFS          |
#      | Singapore     |
#      | Singapore/USA |
      | USA and Singapore |


  Scenario: Verify user is able to click the forgot password link
    When I enter the email address for "IOSUser1"
    And I click the forgot password link
    Then I should see the forgot password page

  Scenario: Verify user is able to login with old password after pressing the forgot password link
    When I enter the email address for "IOSUser1"
    And I click the forgot password link
    And I enter the original password for user by closing the forgot password page
    Then I should see the roster

  Scenario: Verify user is not able to login with invalid password after pressing the forgot password link
    When I enter the email address for "IOSUser1"
    And I click the forgot password link
    And I do not enter the original password for user by closing the forgot password page
    Then I should not see the roster

  @smoke
  Scenario: Verify user is able to log out
    And I enter the valid credentials for "IOSUser1" on "non sim" device
    And I accept the notification alerts
    And I navigate to the settings page
    When I go through the settings "log out" process
    Then I am on the TT get started screen
