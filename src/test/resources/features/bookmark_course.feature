Feature: Bookmarking a course

  Scenario Outline: User adds a course to bookmarks and verifies it appears
    Given the user is on the course page for "<CourseTitle>"
    And the user is logged in
    When the user bookmarks the course "<CourseTitle>"
    Then the course "<CourseTitle>" should be present in the bookmarks

    Examples:
      | CourseTitle                   |
      | Getting Ready for Assessment  |
      | What Does It Take to Be an Expert? |