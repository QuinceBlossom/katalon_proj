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

// 1. Goodlock > Plugins > Extensions > info Sound Assistant > More option 
// Go to store
// About
// Check uninstallation

Mobile.startExistingApplication('com.samsung.android.goodlock')
Thread.sleep(2000)

// 2. Ấn nút Recent (Apps overview)
def process = Runtime.getRuntime().exec("adb shell input keyevent 187")
process.waitFor()
Thread.sleep(2000) // Đợi cho màn hình Recent App hiện ra

// 3.  Nhấn "Close all"
Mobile.tap(findTestObject('Object Repository/android.widget.Button - Close all'), 0)
process.waitFor()

// Đợi cho hành động "Close all" hoàn tất
Thread.sleep(2000)

Mobile.startExistingApplication('com.samsung.android.goodlock')
Thread.sleep(5000)

//Tap vào text Plugins
Mobile.tap(findTestObject('Object Repository/android.widget.TextView - Plugins (1)'), 2000)

Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Extensions'), 2000)

Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Button - info app'), 0)//info app

Mobile.swipe(540, 430, 540, 1900)

Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Button - more_option'), 0)//more option

Mobile.tap(findTestObject('Object Repository/android.widget.TextView - Go to store'), 2000)// tap vào go to store

Mobile.pressBack()

Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Button - more_option'), 0)// more option

Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - About'), 0) // tap vào About

boolean isPresent = Mobile.waitForElementPresent(findTestObject('Object Repository/android.widget.TextView - SoundAssistant'), 5, FailureHandling.OPTIONAL)

if (isPresent) {
	println("Vào màn About SoundAssistant thành công")
	Mobile.pressBack()
} else {
	println("Vào màn About SoundAssistant lỗi")
	def time = new Date().format("yyyyMMdd_HHmmss")
	Mobile.takeScreenshot("D:/KATALON/katalon_proj/katalon_proj/Screenshot/SA_${time}.png")
	assert false // Cho fail test ngay
}

Mobile.tap(findTestObject('Object Repository/android.widget.Button (3)'), 0)// tap vào uninstall button

Mobile.getText(findTestObject('Object Repository/Extensions/android.widget.TextView - Uninstall this app'), 0)// Kiểm tra xem dòng chữ này có phải là 'Uninstall this app?' không?

Mobile.pressBack()

Thread.sleep(2000)

//Mobile.getText(findTestObject('Object Repository/Extensions/android.widget.TextView - Open source licenses'), 0) // Kiểm tra xem dòng chữ này có phải là 'Open source licenses' không?


