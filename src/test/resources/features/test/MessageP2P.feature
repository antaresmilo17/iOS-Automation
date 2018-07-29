@messageP2P
Feature: MessageP2P feature

  Background:
    Given I am logged in as "IOSUser1"
    And I have an existing recipient "iOS1"

  @smoke @message
    Scenario Outline: Verify I am able to send a P2P message
    When I send a <message type> P2P message to "iOS1"
    Then I should see the latest message sent/delivered status
    Examples:
    |  message type  |
    |  random        |
    |  link          |
    |  emoji         |


  Scenario Outline: Verify I am able to send messages with attachments
    When I send a <type> attachment to "iOS1"
    Then I should see the latest attachment sent/delivered status

    Examples:
      | type          |
      | image camera  |
      | video camera  |
      | voice         |
      | location      |

  @smoke
  Scenario: Verify I am able to send a priority message
    When I send a "random" priority message to "iOS1"
    Then I should see the latest message sent/delivered status

  Scenario: Verify the priority message appears correctly on the roster for the receiver
    When I receive a "random" priority message from "iOS1"
    Then I should see the priority message conversation style on the roster

  Scenario: Verify I am able to send an attachment as a priority message
    When I send a "random" as a priority attachment message to "iOS1"
    Then I should see the latest message sent/delivered status

  @cleanup @smoke
  Scenario: Clear conversation with an existing Group
    When I clear user conversations
    Then I see no user conversations