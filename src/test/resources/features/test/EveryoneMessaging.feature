Feature: Everyone Feature
  Background:
    Given I am logged in as "IOSUser2"
    And I have an existing recipient "Everyone"

  Scenario Outline: Verify I am able to send a P2P message
    When I send a <type> broadcast message to "Everyone"
    Then I should see the latest message sent/delivered status
    Examples:
      | type    |
      | random  |
      | link    |
      | emoji   |


  Scenario Outline: Verify I am able to send messages with attachments
    When I send a <type> attachment to "Everyone"
    Then I should see the latest attachment sent/delivered status

    Examples:
      | type          |
      | image camera  |
      | video camera  |
      | voice         |
      | location      |

  Scenario: Verify I am able to send a priority message
    When I send a "random" priority message to "Everyone"
    Then I should see the latest message sent/delivered status

  Scenario: Verify I am able to send an attachment as a priority message
    When I send a "random" as a priority attachment message to "Everyone"
    Then I should see the latest message sent/delivered status

  @cleanup
  Scenario: Clear conversation with an existing broadcast
    When I clear user conversations
    Then I see no user conversations