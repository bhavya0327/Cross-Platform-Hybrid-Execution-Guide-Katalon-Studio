import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities
import com.kms.katalon.core.appium.driver.AppiumDriverManager
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import io.appium.java_client.android.AndroidDriver
 
// --- PHASE 1: WEB ---
WebUI.openBrowser('')
WebDriver desktopDriver = DriverFactory.getWebDriver()
WebUI.navigateToUrl("https://katalon-demo-cura.herokuapp.com/")
WebUI.click(findTestObject("Object Repository/Page_CURA Healthcare Service/a_Make Appointment"))
 
// --- PHASE 2: START APPIUM and launch app ---
DesiredCapabilities capabilities = new DesiredCapabilities()
capabilities.setCapability("platformName", "Android")
capabilities.setCapability("appium:deviceName", "emulator-5554") //This will be your device name which you can get by running the command: adb devices inside a terminal.

capabilities.setCapability("appium:automationName", "UiAutomator2")
capabilities.setCapability("appium:app", "/Users/bhavyansh-katalon/Downloads/ApiDemos-WhiteScreen.apk") //Path to your application file.
 
AndroidDriver mobileDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities)
AppiumDriverManager.setDriver(mobileDriver)
 
Mobile.tap(findTestObject("Object Repository/android.widget.TextView - Animation"), 2)
Mobile.closeApplication()
 
// --- PHASE 4: RETURN TO WEB ---
DriverFactory.changeWebDriver(desktopDriver)
WebUI.setText(findTestObject("Object Repository/Page_CURA Healthcare Service/input_Username"), "John Doe")