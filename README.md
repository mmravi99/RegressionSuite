### **Selenium Java Automation Framework User Guide**

* * *

**Index**
---------

1. Overview
2. Modules in the Project
3. Initial Setup
    * 1.1 Prerequisites
    * 1.2 Importing the Project
4. Project Structure
5. Explanation of Key Components
    * 3.1 Automation Framework
    * 3.2 Core Product Tests
    * 3.3 Derived Product 1 & 2 Tests
6. Configuring and Running Tests
    * 4.1 Running Tests for Individual Modules
    * 4.2 Running All Tests Across Modules
7. Creating Feature Files
8. Managing JSON Test Data
9. Reporting with ExtentManager
10. Conclusion

* * *

**Overview**
------------

This guide provides comprehensive instructions on setting up and using the **Selenium Java Automation Framework** for a multi-module Maven project. The project is structured into distinct modules, making it both scalable and modular. Each module is dedicated to specific components, products, or tests.

**Modules in the Project:**
---------------------------

1. **automation-framework**: Contains reusable components such as `BrowserFactory`, `ExtentManager`, `JSONUtils`, and other utility classes.
2. **core-product-tests**: Contains test cases, feature files, and resources specific to the core product.
3. **derived-product1-tests**: Contains test cases and resources specific to derived product 1.
4. **derived-product2-tests**: Contains test cases and resources specific to derived product 2.

* * *

**1. Initial Setup**
--------------------

### **1.1 Prerequisites**

Ensure that the following tools are installed:

* **Java SDK (JDK)**
* **Maven**
* **Selenium WebDriver**
* **TestNG**
* **Cucumber**

### **1.2 Importing the Project**

1. Clone the repository.
    
2. Open the project in your IDE (Eclipse or IntelliJ).
    
3. Run the following Maven command to install dependencies and build the project:
    
    ```bash
    mvn clean install
    ```
    

* * *

**2. Project Structure**
------------------------

Below is a detailed breakdown of the multi-module Maven project structure:

### **Root Directory:**

```scss
Root Directory
│
├── automation-framework (Module 1)
│   ├── src
│   │   ├── main
│   │   │   └── java
│   │   │       └── utilities
│   │   └── test
│   └── pom.xml
│
├── core-product-tests (Module 2)
│   ├── src
│   │   ├── main
│   │   │   └── java
│   │   │       └── pages
│   │   └── test
│   │       ├── java
│   │       │   ├── steps
│   │       │   └── runners
│   │       └── resources
│   │           ├── features
│   │           └── testdata
│   └── pom.xml
│
├── derived-product1-tests (Module 3)
│   ├── src
│   │   ├── main
│   │   │   └── java
│   │   │       └── pages
│   │   └── test
│   │       ├── java
│   │       │   ├── steps
│   │       │   └── runners
│   │       └── resources
│   │           ├── features
│   │           └── testdata
│   └── pom.xml
│
├── derived-product2-tests (Module 4)
│   ├── src
│   │   ├── main
│   │   │   └── java
│   │   │       └── pages
│   │   └── test
│   │       ├── java
│   │       │   ├── steps
│   │       │   └── runners
│   │       └── resources
│   │           ├── features
│   │           └── testdata
│   └── pom.xml
│
└── pom.xml (Root)
```

* * *

**3. Explanation of Key Components**
------------------------------------

### **automation-framework:**

Contains utility classes for browser setup, report generation, JSON handling, and reusable methods.

* **BrowserFactory.java**: Sets up WebDriver instances for different browsers.
* **ExtentManager.java**: Manages the creation of ExtentReports.
* **JSONUtil.java**: Handles reading and parsing JSON test data.
* **ReusableLibrary.java**: Contains reusable methods such as window switching, page load waits, and CSV file writing.

### **core-product-tests:**

* Contains feature files, step definitions, page objects, and test data specific to the core product.
* **features**: Cucumber feature files written in Gherkin syntax.
* **pages**: Page Object Model (POM) classes for the core product.
* **steps**: Step definitions implementing Gherkin steps for the core product.
* **runners**: Cucumber TestNG runner for executing the tests.
* **resources/testdata**: JSON files containing test data for various test cases.

### **derived-product1-tests** and **derived-product2-tests**:

These modules follow the same structure as **core-product-tests**, but they contain tests, feature files, and step definitions specific to derived products 1 and 2, respectively.

* * *

**4. Configuring and Running Tests**
------------------------------------

### **4.1 Running Tests for Individual Modules**

You can run tests for individual modules using the Maven `-pl` (projects list) and `-am` (also make dependencies) options. The `-DsuiteXmlFile=testng.xml` parameter allows you to specify the TestNG suite to run.

#### **Command to Run Tests for core-product-tests:**

```bash
mvn -pl core-product-tests -am clean test -DsuiteXmlFile=testng.xml
```

This command:

* Runs tests only for the **core-product-tests** module.
* Includes all necessary dependencies from the **automation-framework** module.
* Specifies the TestNG suite to execute.

#### **Command to Run Tests for derived-product1-tests:**

```bash
mvn -pl derived-product1-tests -am clean test -DsuiteXmlFile=testng.xml
```

#### **Command to Run Tests for derived-product2-tests:**

```bash
mvn -pl derived-product2-tests -am clean test -DsuiteXmlFile=testng.xml
```

### **4.2 Running All Tests Across Modules**

To run all tests across all modules, use the following command from the root directory:

```bash
mvn clean test
```

* * *

**5. Creating Feature Files**
-----------------------------

Each product module contains its own feature files, step definitions, and test data. For example, in the **core-product-tests** module, feature files are located in the `src/test/resources/features` directory. The corresponding step definitions are written in the `StepDefinitions.java` file located in the `src/test/java/steps` directory.

* * *

**6. Managing JSON Test Data**
------------------------------

Each module maintains its own JSON test data files under `src/test/resources/testdata`. JSON test data can be accessed in the step definitions using the `JSONUtil` class.

### **Example JSON File Structure:**

```json
{
    "username": "testUser",
    "password": "testPass",
    "expectedResult": "Login successful"
}
```

* * *

**7. Reporting with ExtentManager**
-----------------------------------

The `ExtentManager` class manages the generation of ExtentReports, which are detailed HTML reports of the test execution. Reports are generated under the **Automation Reports** directory for each test run.

### **Example of Writing Logs to Reports:**

```java
ExtentManager.getTest().info("Browser closed successfully on " + currentPlatform);
```

* * *

**8. Conclusion**
-----------------

This **Selenium Java Automation Framework User Guide** provides a comprehensive overview of setting up and utilizing the framework effectively. By following the outlined steps and best practices, you can efficiently create, manage, and execute automated tests, ensuring high-quality software delivery.

### **Key Highlights:**

* **Robust Project Structure**: Organized into distinct modules and packages for pages, test components, and step definitions.
* **Cross-Browser Testing**: Ensures comprehensive coverage across various platforms.
* **Parallel Execution**: Enhances efficiency by running tests concurrently using TestNG and Cucumber.
* **Enhanced Reporting**: Utilizes Extent Reports for detailed and visually appealing test reports.
* **Build Automation**: Employs Maven for flexible and efficient build and dependency management.
* **Reusable Components**: Promotes code reuse and maintainability through well-designed utilities and page objects.

* * *

With this structure, the guide now has a comprehensive index and better flow, allowing users to easily navigate through sections and understand the automation framework setup and usage.
