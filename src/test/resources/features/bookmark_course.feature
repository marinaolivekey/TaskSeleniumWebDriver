Feature: Bookmarking a course

  Background:
    Given the user is logged in
    And the user is on the main page

  Scenario Outline: User adds a course to bookmarks and verifies it appears
    When the user navigates to course "<CourseTitle>"
    And the user bookmarks the course "<CourseTitle>"
    Then the course "<CourseTitle>" should be present in the bookmarks

    Examples:
      | CourseTitle                   |
      | Getting Ready for Assessment  |
      | What Does It Take to Be an Expert? |