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


// 1. 1.Sound Assistant > Customize volume panel > Turn off

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

// Kiểm tra trạng thái hiện tại
boolean isOn = Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.TextView - On'), 2, FailureHandling.OPTIONAL)
boolean isOff = Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.TextView - Off'), 2, FailureHandling.OPTIONAL)

// Xử lý theo trạng thái
if (isOn) {
    // Nếu đang On, thì switch sang Off
    Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Switch'), 0)
    Thread.sleep(1000)
    
    // Kiểm tra lại, phải hiển thị object "Off"
    boolean nowIsOff = Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.TextView - Off'), 2, FailureHandling.OPTIONAL)
    if (nowIsOff) {
        println("Switch đã tắt thành công!")
    } else {
        Mobile.takeScreenshot('D:/KATALON/katalon_proj/katalon_proj/Screenshot/VolumeSwitch_Off_Error.png')
        assert false : "Switch không tắt thành công!"
    }
} else if (isOff) {
    // Nếu đã Off, báo điều kiện chưa thỏa mãn + chụp ảnh + dừng script
    println("Điều kiện chưa được thỏa mãn - Switch should be off!")
    Mobile.takeScreenshot('D:/KATALON/katalon_proj/katalon_proj/Screenshot/VolumeSwitch_Already_Off.png')
    assert false : "Switch đã ở trạng thái Off, không cần thực hiện thao tác."
} else {
    // Nếu cả 2 không xuất hiện, báo lỗi và lưu ảnh để kiểm tra
    Mobile.takeScreenshot('D:/KATALON/katalon_proj/katalon_proj/Screenshot/VolumeSwitch_Unknown.png')
    assert false : "Không xác định được trạng thái Switch!"
}

// 4. Đóng app
Mobile.closeApplication()
