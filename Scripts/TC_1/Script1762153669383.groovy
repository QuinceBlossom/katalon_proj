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


// Bước 1: Xác định trạng thái hiện tại
boolean isDim = Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.TextView - Floating button'), 1, FailureHandling.OPTIONAL)
boolean isHighlight = Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.TextView - Floating button (1)'), 1, FailureHandling.OPTIONAL)
boolean isSync = Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.TextView - Floating button Sync position'), 1, FailureHandling.OPTIONAL)

// Đưa về trạng thái dim (nếu chưa ở trạng thái này)
if (!isDim) {
    // Nếu đang Highlight hoặc Sync thì phải tap đến khi về Dim
    int maxTries = 3 // tránh lặp vô tận
    while (!isDim && maxTries > 0) {
        // Nhấn vào trạng thái hiện tại
        if (isHighlight) {
            Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Floating button (1)'), 0)
        } else if (isSync) {
            Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Floating button Sync position'), 0)
        }
        Thread.sleep(700)
        // Kiểm tra lại
        isDim = Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.TextView - Floating button'), 1, FailureHandling.OPTIONAL)
        isHighlight = Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.TextView - Floating button (1)'), 1, FailureHandling.OPTIONAL)
        isSync = Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.TextView - Floating button Sync position'), 1, FailureHandling.OPTIONAL)
        maxTries--
    }
    assert isDim : "Không đưa được Floating button về trạng thái dim!"
    println("Floating button đã về trạng thái dim (off).")
} else {
    println("Floating button đã ở trạng thái dim (off).")
}

// Bước 2: Tap để bật (highlight)
Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Floating button'), 0)
Thread.sleep(700)
isHighlight = Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.TextView - Floating button (1)'), 1, FailureHandling.OPTIONAL)
assert isHighlight : "Floating button không chuyển sang highlight!"

println("Floating button đã highlight!");

// Bước 3: Tap để chuyển sang Sync position
Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Floating button (1)'), 0)
Thread.sleep(700)
isSync = Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.TextView - Floating button Sync position'), 1, FailureHandling.OPTIONAL)
assert isSync : "Floating button không chuyển sang trạng thái Sync position!"

println("Floating button đã ở trạng thái Sync position.")



