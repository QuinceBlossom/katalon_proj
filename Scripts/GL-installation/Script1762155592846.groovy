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

// 1. Mở ứng dụng Goodlock
Mobile.startExistingApplication('com.samsung.android.goodlock')
Thread.sleep(2000)

// 2. Ấn nút Recent (Apps overview)
def process = Runtime.getRuntime().exec("adb shell input keyevent 187")
process.waitFor()
Thread.sleep(2000) // Đợi cho màn hình Recent App hiện ra

// 3. [SỬA LẠI] Nhấn "Close all" bằng tọa độ ADB
// !!! THAY THẾ 540 2150 BẰNG TỌA ĐỘ BẠN TÌM ĐƯỢC !!!
Mobile.tap(findTestObject('Object Repository/android.widget.Button - Close all'), 0)
process.waitFor()

// Đợi cho hành động "Close all" hoàn tất
Thread.sleep(2000)


// ... Các bước script tiếp theo của bạn sẽ ở đây ...

Mobile.startExistingApplication('com.samsung.android.goodlock')
Thread.sleep(5000)
 

Mobile.tap(findTestObject('Object Repository/android.widget.TextView - Plugins (1)'), 2000)

Mobile.tap(findTestObject('Object Repository/android.widget.TextView - Life up'), 2000)

Mobile.swipe(341, 2000, 341, 300)
Thread.sleep(2000)

Mobile.tap(findTestObject('Object Repository/MultiStar_download_outside'), 2000)