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

// 1.Customaize volume panel
// 2. Custom
// 3. Standard button
// 4. Move

Mobile.startExistingApplication('com.samsung.android.soundassistant')

// Clear toàn bộ dữ liệu của app
def clearProcess = Runtime.getRuntime().exec("adb shell pm clear com.samsung.android.soundassistant")
clearProcess.waitFor()
println("Đã clear data của app com.samsung.android.soundassistant!")

// 2. Ấn nút Recent (Apps overview)
//def recentProcess = Runtime.getRuntime().exec("adb shell input keyevent 187")
//recentProcess.waitFor()
//Thread.sleep(2000) // Đợi cho màn hình Recent App hiện ra

// 3.  Nhấn "Close all"
//Mobile.tap(findTestObject('Object Repository/android.widget.Button - Close all'), 0)
// Không cần recentProcess.waitFor()
// Đợi cho hành động "Close all" hoàn tất
//Thread.sleep(2000)

Mobile.startExistingApplication('com.samsung.android.soundassistant')

// 4. Các bước tiếp theo của bạn...
Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Button - Continue'), 2000)
Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Button - Allow'), 2000)

// Tap vào "Customize volume panel"
Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Customize volume panel'), 0)

// 1. Kiểm tra trạng thái nút (On/Off)
boolean isOn = Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.TextView - On'), 2, FailureHandling.OPTIONAL)
boolean isOff = Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.TextView - Off'), 2, FailureHandling.OPTIONAL)

if (isOn) {
	// Đang ON -> chuyển sang bước tiếp theo
	println("Button đang ON, thực hiện vào Custom")

	// Thực hiện tap vào Custom
	Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Custom'), 0)
	Thread.sleep(1000) // đợi giao diện load

	// Verify đã vào màn hình Custom, bằng cách xác thực object đặc trưng của màn này
	boolean isInCustom = Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.TextView - Standard panel'), 3, FailureHandling.OPTIONAL)
	if (isInCustom) {
		println("Đã vào màn hình Custom thành công!")
	} else {
		Mobile.takeScreenshot('D:/KATALON/katalon_proj/katalon_proj/Screenshot/Custom_Verify_Error.png')
		assert false : "Không xác thực được vào màn hình Custom!"
	}
} else if (isOff) {
	// Nếu Off thì bật ON lên trước, rồi tiếp tục như trên
	Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Switch'), 0)
	Thread.sleep(1000)
	
	boolean nowIsOn = Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.TextView - On'), 2, FailureHandling.OPTIONAL)
	if (nowIsOn) {
		println("Đã bật ON, thực hiện vào Custom")

		Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Custom'), 0)
		Thread.sleep(1000)

		boolean isInCustom = Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.TextView - Standard panel'), 3, FailureHandling.OPTIONAL)
		if (isInCustom) {
			println("Đã vào màn hình Custom thành công!")
		} else {
			Mobile.takeScreenshot('D:/KATALON/katalon_proj/katalon_proj/Screenshot/Custom_Verify_Error.png')
			assert false : "Không xác thực được vào màn hình Custom!"
		}
	} else {
		Mobile.takeScreenshot('D:/KATALON/katalon_proj/katalon_proj/Screenshot/VolumeSwitch_On_Error.png')
		assert false : "Không bật được button ON!"
	}
} else {
	Mobile.takeScreenshot('D:/KATALON/katalon_proj/katalon_proj/Screenshot/VolumeSwitch_Unknown.png')
	assert false : "Không xác định được trạng thái của button!"
}
Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Standard panel'), 0)

Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Move'), 0)

int xStart = 797
int yStart = 810 + 300   // Gần cuối SeekBar
int xEnd = 797
int yEnd = 810 + 30      // Gần đầu SeekBar

Mobile.swipe(xStart, yStart, xEnd, yEnd)

