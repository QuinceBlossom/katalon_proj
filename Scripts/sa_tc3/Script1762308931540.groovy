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

// 1.Sound Assistant > Customize volume panel > Turn on

Mobile.startExistingApplication('com.samsung.android.soundassistant')

// 2. Ấn nút Recent (Apps overview)
def process = Runtime.getRuntime().exec("adb shell input keyevent 187")
process.waitFor()
Thread.sleep(2000) // Đợi cho màn hình Recent App hiện ra

// 3.  Nhấn "Close all"
Mobile.tap(findTestObject('Object Repository/android.widget.Button - Close all'), 0)
process.waitFor()

// Đợi cho hành động "Close all" hoàn tất
Thread.sleep(2000)

Mobile.startExistingApplication('com.samsung.android.soundassistant')
Thread.sleep(2000)

// 1. Tap vào "Customize volume panel"
Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Customize volume panel'), 0)

// 2. Lấy trạng thái hiện tại (Off/On)
String currentStatus = Mobile.getText(findTestObject('Object Repository/Extensions/android.widget.TextView - Off'), 0)

// 3. Nếu đang "Off" thì bật switch lên
if (currentStatus == "Off") {
    Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Switch'), 0)
    Thread.sleep(1000) // đợi trạng thái đổi
}

// 4. Lấy lại trạng thái sau khi chuyển switch
String newStatus = Mobile.getText(findTestObject('Object Repository/Extensions/android.widget.TextView - On'), 0)

// 5. Verify: nếu không phải On thì chụp màn hình và fail
if (newStatus == "On") {
    println("Switch đã bật thành công!")
} else {
    Mobile.takeScreenshot('D:/KATALON/katalon_proj/katalon_proj/Screenshot/VolumeSwitch_Error.png')
    assert false : "Switch không bật thành công!"
}


// 6. Đóng app
Mobile.closeApplication()


