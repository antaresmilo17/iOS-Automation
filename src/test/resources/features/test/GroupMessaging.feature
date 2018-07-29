Feature: Group message feature

  Background:
    Given I am logged in as "IOSUser2"
    And I have an existing recipient "Auto Test Existing"

  @smoke
  Scenario Outline: Verify I am able to send a group message
    When I send a <type> group message to "Auto Test Existing"
    Then I should see the latest message sent/delivered status
    Examples:
      | type    |
      | random  |
      | link    |
      | emoji   |

  Scenario Outline: Verify I am able to send messages with attachments
    When I send a <type> attachment to group "Auto Test Existing"
    Then I should see the latest attachment sent/delivered status

    Examples:
      | type          |
      | image camera  |
      | video camera  |
      | voice         |
      | location      |

  Scenario Outline: Verify I am able to resend my own message from an existing group
    When I send a random group message
    When I select "<option>" from the message options
    Then I should see the latest message sent/delivered status

    Examples:
      | option              |
      | resend              |
      | resend as priority  |
#
#  @ignore @needsXpathFixing
#  Scenario Outline: Verify I am able to send a quick reply to an existing group
#    And I select the "<quick reply>" option
#    When I send the "<quick reply>" option
#    Then I should see the latest message sent/delivered status
#
#    Examples:
#      |quick reply|
#      |first|
##      |second|
##      |third|

  @cleanup @smoke
  Scenario: Clear conversation with an existing Group
    When I clear user conversations
    Then I see no user conversations