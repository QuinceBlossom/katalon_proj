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

Mobile.startExistingApplication('com.samsung.android.soundassistant')

Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Layout  Right (1)'), 0)

Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Layout Left (1)'), 0)

Mobile.closeApplication()

Mobile.startExistingApplication('com.samsung.android.soundassistant')

Mobile.getText(findTestObject('Object Repository/Extensions/android.widget.TextView - Show volume level (1)'), 0)

Mobile.closeApplication()

Mobile.startExistingApplication('com.samsung.android.soundassistant')

Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Expanded panel'), 0)

Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Bluetooth metronome'), 0)

Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Show toolbar functions'), 0)

Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - App volume'), 0)

Mobile.closeApplication()

