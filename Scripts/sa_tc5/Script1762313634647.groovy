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

//


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

// Lấy object nút Right và Left
def rightObj = findTestObject('Object Repository/Extensions/android.widget.TextView - Layout  Right (1)')
def leftObj = findTestObject('Object Repository/Extensions/android.widget.TextView - Layout Left (1)')

// Kiểm tra nút nào đang hiển thị
boolean isRightPresent = Mobile.waitForElementPresent(rightObj, 2, FailureHandling.OPTIONAL)
boolean isLeftPresent = Mobile.waitForElementPresent(leftObj, 2, FailureHandling.OPTIONAL)

if (isRightPresent) {
    println("Layout Right hiển thị, tiến hành nhấn Right.")
    Mobile.tap(rightObj, 0)
    Thread.sleep(1000) // Đợi giao diện đổi trạng thái

    // Sau khi nhấn, kiểm tra lại nút Left đã hiện chưa, nếu có thì nhấn tiếp
    boolean nowIsLeftPresent = Mobile.waitForElementPresent(leftObj, 2, FailureHandling.OPTIONAL)
    if (nowIsLeftPresent) {
        println("Layout Left đã xuất hiện, tiến hành nhấn Left luôn.")
        Mobile.tap(leftObj, 0)
    } else {
        println("Không thấy nút Layout Left sau khi đã tap Right.")
    }
} else if (isLeftPresent) {
    println("Layout Left hiển thị, tiến hành nhấn Left.")
    Mobile.tap(leftObj, 0)
    Thread.sleep(1000) // Đợi giao diện đổi trạng thái
	

    // Sau khi nhấn, kiểm tra lại nút Right đã hiện chưa, nếu có thì nhấn tiếp
    boolean nowIsRightPresent = Mobile.waitForElementPresent(rightObj, 2, FailureHandling.OPTIONAL)
    if (nowIsRightPresent) {
        println("Layout Right đã xuất hiện, tiến hành nhấn Right luôn.")
        Mobile.tap(rightObj, 0)
    } else {
        println("Không thấy nút Layout Right sau khi đã tap Left.")
    }
} else {
    Mobile.takeScreenshot('D:/KATALON/katalon_proj/katalon_proj/Screenshot/Layout_Button_Not_Found.png')
    assert false : "Không tìm thấy cả nút Right lẫn Left để thao tác!"
}


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

Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Floating button'), 0)

Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Floating button (1)'), 0)

Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Floating button Sync position'), 0)

Mobile.closeApplication()
