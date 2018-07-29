Feature: Forums Feature
  Background:
    Given I am logged in as "IOSUser2"

  Scenario Outline: Verify My Forums and Explore Forums are displayed
    When I navigate to the "forums" page
    Then I should see the <section> section

    Examples:
      | section         |
      | my forums       |
      | explore forums  |

  Scenario: Verify the created forum shows in the roster
    When I create a new forum with name "random"
    Then I should see the forum in the "inbox" page

  Scenario: Verify the created forum shows up in the my forums page
    When I create a new forum with name "random"
    Then I should see the forum in the "forums" page

  Scenario: Verify I am able to join a forum from the forums page
    And I navigate to the "forums" page
    And I join a forum "Celebrations" from the forums page
    And I navigate to the "inbox" page
    When I should see the forum in the "roster" page
    Then I leave forum "Celebrations"

  Scenario: Verify leaving forum gets removed from the roster
    And I navigate to the "forums" page
    And I join a forum "Celebrations" from the forums page
    And I navigate to the "inbox" page
    When I leave forum "Celebrations"
    Then I should not see the forum in the "roster" page

  Scenario: Verify leaving forum gets removed from the my forums tab
    And I navigate to the "forums" page
    And I join a forum "Celebrations" from the forums page
    And I navigate to the "inbox" page
    When I leave forum "Celebrations"
    Then I should not see the forum in the "forums" page

  Scenario Outline: Verify joined forums can be muted for a given amount of time
    And I have an existing forum "Auto QA Forum"
    And I open the "Auto QA Forum" details option
    And I select the "Mute" option from the forum settings
    When I mute the conversation for <duration>
    Then I should see the conversation is muted

    Examples:
      |duration  |
      |8 Hours   |
      |1 Day     |
      |1 Week    |
      |1 Year    |

Scenario Outline: Verify joined forums can be unmuted
    And I have an existing forum "Auto QA Forum"
    And I open the "Auto QA Forum" details option
    And I select the "Mute" option from the forum settings
    And I mute the conversation for <duration>
    When I select the "Unmute" option from the forum settings
    Then I should see the conversation is not muted

    Examples:
    |duration  |
    |8 Hours   |
    |1 Day     |
    |1 Week    |
    |1 Year    |

  Scenario: Verify I am able to add member to a joined forum
    And I create a new forum with name "random"
    And I navigate to the "inbox" page
    And I have an existing forum "random"
    And I open the "random" details option
    When I select the "Add Members" option from the forum settings
    Then I should see the member added to the forum