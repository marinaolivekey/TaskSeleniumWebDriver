## Test Automation

### Description
The project implements automated tests for a web application using Selenium WebDriver, TestNG, and design patterns.

### Implemented Patterns
- **Singleton**: WebDriver management through `DriverSingleton`.
- **Factory Method**: WebDriver creation in `WebDriverFactory`.
- **Decorator**: Highlighting login fields through `HighlightLoginPageDecorator`.
- **Facade**: Simplifying navigation through `NavigationFacade`.

### S.O.L.I.D. Fixes
Below are the fixes for S.O.L.I.D. principle violations implemented in the project:

| Class             | Violated Principle                                                                                      | Problem                                                                                                                                      | Solution                                                                                                                                                                                                                          |
|-------------------|--------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `DriverSingleton` | SRP (Single Responsibility Principle)                                                                  | The class was responsible for both creating the driver and managing its lifecycle.                                                          | The driver creation logic was moved to `WebDriverFactory`, which is responsible only for configuring and creating `WebDriver`. `DriverSingleton` now manages only the lifecycle (initialization, access, shutdown).              |
| `LoginPage`       | SRP (Single Responsibility Principle) and OCP (Open-Closed Principle)                                  | The `LoginPage` class handled actions on the login page (entering username, clicking a button). Adding field highlighting would require modifying the code, violating OCP and SRP. | A `HighlightLoginPageDecorator` was created to wrap `LoginPage` and add field highlighting without changing the original code. The `LoginPageActions` interface defines the contract, allowing functionality to be extended. |
| `HomePage`        | SRP (Single Responsibility Principle)                                                                  | The `HomePage` class handled actions (clicking avatar, retrieving email) and state checks (`isProfileMenuDisplayed`, `isDropdownBodyDisplayed`). | State checks were moved to `HomePageValidator`, which is responsible only for validation. `HomePage` now handles only actions and waits. The `PageValidator` interface ensures flexibility.                                    |
