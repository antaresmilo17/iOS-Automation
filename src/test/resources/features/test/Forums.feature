Feature: Forums Feature
  Background:
    Given I am logged in as "IOSUser2"
    And I have an existing forum "Auto QA Forum"

  Scenario Outline: Verify user is able to send a valid forum message
    When I send a <type> forum message to "Auto QA Forum"
    Then I should see the latest message sent/delivered status
    Examples:
      | type            |
      | random          |
      | link            |
      | emoji           |

  Scenario Outline: Verify I am able to send messages with attachments to a forum
    When I send a <type> attachment to forum "Auto QA Forum"
    Then I should see the latest attachment sent/delivered status

    Examples:
      | type          |
      | image camera  |
      | video camera  |
      | voice         |
      | location      |

  Scenario: Verify I am able to send a priority message to a forum
    When I send a random priority message to "Auto QA Forum"
    Then I should see the latest message sent/delivered status

  @cleanup
  Scenario: Clear conversation with an existing Group
    When I clear user conversations
    Then I see no user conversations