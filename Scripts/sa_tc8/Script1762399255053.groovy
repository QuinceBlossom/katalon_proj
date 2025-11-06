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
// 4. Volume display
// 4.1 Volume display activation
// 4.2 Disable Volume display

Mobile.startExistingApplication('com.samsung.android.soundassistant')

// Clear toàn bộ dữ liệu của app
def clearProcess = Runtime.getRuntime().exec("adb shell pm clear com.samsung.android.soundassistant")
clearProcess.waitFor()
println("Đã clear data của app com.samsung.android.soundassistant!")

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

// Kiểm tra trạng thái ban đầu
String isSelected = Mobile.getAttribute(findTestObject('Object Repository/Extensions/android.widget.TextView - Show volume level'), "selected", 0)

// Nếu chưa highlight (tức là chưa bật)
if (isSelected != "true") {
	println("Button hiện tại CHƯA bật, tiến hành bật lên.")
	Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Show volume level'), 0)
	Thread.sleep(500)
	// Kiểm tra lại sau khi bật
	String isNowSelected = Mobile.getAttribute(findTestObject('Object Repository/Extensions/android.widget.TextView - Show volume level'), "selected", 0)
	if (isNowSelected == "true") {
		println("Button đã highlight thành công!")
	} else {
		Mobile.takeScreenshot('D:/KATALON/katalon_proj/katalon_proj/Screenshot/ShowVolumeLevel_Error.png')
		assert false : "Button không highlight được sau khi bật!"
	}
} else {
	println("Button đã highlight từ trước, tiến hành TẮT rồi BẬT lại để xác minh.")
	// Tắt đi
	Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Show volume level'), 0)
	Thread.sleep(500)
	String isNowUnSelected = Mobile.getAttribute(findTestObject('Object Repository/Extensions/android.widget.TextView - Show volume level'), "selected", 0)
	if (isNowUnSelected != "true") {
		println("Button đã tắt thành công!")
		// Bật lên lại
		Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Show volume level'), 0)
		Thread.sleep(500)
		String isRelight = Mobile.getAttribute(findTestObject('Object Repository/Extensions/android.widget.TextView - Show volume level'), "selected", 0)
		if (isRelight == "true") {
			println("Button bật lại và highlight OK!")
		} else {
			Mobile.takeScreenshot('D:/KATALON/katalon_proj/katalon_proj/Screenshot/ShowVolumeLevel_Error_AfterRelight.png')
			assert false : "Button không highlight được sau khi bật lại!"
		}
	} else {
		Mobile.takeScreenshot('D:/KATALON/katalon_proj/katalon_proj/Screenshot/ShowVolumeLevel_TurnOff_Error.png')
		assert false : "Button không tắt được khi nhấn!"
	}
}

Mobile.closeApplication()