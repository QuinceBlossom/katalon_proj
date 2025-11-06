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


// 1.Goodlock > Plugins > extensions > Sound Assistant > installing > run app

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

Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Sound Assistant'), 2000)

Mobile.swipe(341, 2000, 341, 300)

Mobile.swipe(540, 430, 540, 1900)

Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Button - download'), 2000)

// 2. [BƯỚC QUAN TRỌNG BỊ THIẾU]
// Đợi cho nút "PLAY" xuất hiện (chờ tối đa 120 giây)
Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.Button - play'), 120)

// 3. Sau khi nút "PLAY" đã xuất hiện, BÂY GIỜ MỚI NHẤN VÀO NÓ
Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Button - play'), 2000)

// 4. Các bước tiếp theo của bạn...
Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Button - Continue'), 2000)

Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Button - Allow'), 2000)



