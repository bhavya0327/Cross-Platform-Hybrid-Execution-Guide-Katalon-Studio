# Cross-Platform-Hybrid-Execution-Guide-Katalon-Studio
This repository provides implementation examples for running Web and Mobile Native Application tests within a single execution flow. This is particularly useful for end-to-end scenarios like Web → Native App → Web (e.g., triggering a process on a website and verifying it in a mobile app).


🚀 Scenario 1: Web + Mobile on a Single Mobile Device
Use this approach if you are executing both the web browser and the native app on the same Android/iOS device.

Prerequisites
ChromeDriver Compatibility: Ensure your ChromeDriver version matches the Chrome version on your mobile device.

Guide: Refer to the Configuration Document

Implementation Example:

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import io.appium.java_client.android.AndroidDriver

// Phase 1: Web Browser on Mobile
WebUI.openBrowser("")
WebUI.navigateToUrl("https://katalon-demo-cura.herokuapp.com/")
WebUI.click(findTestObject("Object Repository/Page_CURA Healthcare Service/a_Make Appointment"))

// Phase 2: Switch to Native App
Mobile.startApplication("/path/to/your/app.apk", true)
Mobile.tap(findTestObject("Object Repository/android.widget.TextView - Animation"), 2)

// Phase 3: Resume Existing Web Session
AndroidDriver driver = MobileDriverFactory.getDriver()
driver.activateApp("com.android.chrome")
WebUI.delay(4)
WebUI.setText(findTestObject("Object Repository/Page_CURA Healthcare Service/input_Username"), "John Doe")

--------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------

🖥️ Scenario 2: Desktop Web + Mobile App Integration
Use this approach to start a test on a Local Desktop Browser (Chrome/Firefox) and then interact with a Mobile Device mid-test.

Configuration
Run Mode: Execute as "Chrome" (or your preferred desktop browser).

Appium Server: Ensure your local Appium server is running at http://127.0.0.1:4723.

Implementation Example:

import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities
import com.kms.katalon.core.appium.driver.AppiumDriverManager
import com.kms.katalon.core.webui.driver.DriverFactory
// ... other imports

// --- PHASE 1: DESKTOP WEB ---
WebUI.openBrowser('')
WebDriver desktopDriver = DriverFactory.getWebDriver()
WebUI.navigateToUrl("https://katalon-demo-cura.herokuapp.com/")

// --- PHASE 2: LAUNCH MOBILE VIA APPIUM ---
DesiredCapabilities capabilities = new DesiredCapabilities()
capabilities.setCapability("platformName", "Android")
capabilities.setCapability("appium:deviceName", "emulator-5554")
capabilities.setCapability("appium:automationName", "UiAutomator2")
capabilities.setCapability("appium:app", "/path/to/app.apk")

AndroidDriver mobileDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities)
AppiumDriverManager.setDriver(mobileDriver)

Mobile.tap(findTestObject("Object Repository/android.widget.TextView - Animation"), 2)
Mobile.closeApplication()

// --- PHASE 3: RETURN TO DESKTOP WEB ---
DriverFactory.changeWebDriver(desktopDriver)
WebUI.setText(findTestObject("Object Repository/Page_CURA Healthcare Service/input_Username"), "John Doe")


Please also refer to this [video] for a better understanding of the implementation: https://drive.google.com/file/d/1R9QZMYKIV1KXE4LeAkQlGuPnBEeLpcBe/view
