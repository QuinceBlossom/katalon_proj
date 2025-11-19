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

// 1. Vào Plugins, Extensions
Mobile.tap(findTestObject('Object Repository/android.widget.TextView - Plugins (1)'), 10)
Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Extensions'), 5)
Mobile.scrollToText('Sound Assistant')

// 1.1. Xác nhận có "Sound Assistant" trên danh sách, nếu không -> fail!
boolean isSA = Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.TextView - Sound Assistant'), 10, FailureHandling.OPTIONAL)
if (!isSA) {
	Mobile.takeScreenshot('Screenshot/SA_Not_Found.png')
	assert false : "Không tìm thấy Sound Assistant trong danh sách!"
}
Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Sound Assistant'), 5)

// 2. Vuốt lên/xuống để refresh màn hình mới (tăng khả năng Katalon catch object mới)
Mobile.swipe(500, 1700, 500, 300) // Vuốt lên mạnh
Mobile.swipe(500, 300, 500, 1700) // Vuốt xuống lại
// Thông báo sau khi refresh
if (Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.Button - download_fold'), 3, FailureHandling.OPTIONAL) ||
	Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.Button - Download_unfold'), 3, FailureHandling.OPTIONAL) ||
	Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.Button - download_inline_SA'), 3, FailureHandling.OPTIONAL)) {
	println("Refresh màn hình SA thành công! Tìm thấy đối tượng download, tiếp tục.")
} else {
	Mobile.takeScreenshot('Screenshot/SA_Refresh_Error.png')
	assert false : "Không tìm thấy nút download hoặc các đối tượng trong màn hình SA sau khi refresh!"
}

// --- 3. Xử lý cho FOLD ---
if (Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.Button - download_fold'), 5, FailureHandling.OPTIONAL)) {
	// Trường hợp fold, cứ thế tap vào nút download
	Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Button - download_fold'), 5)
	println("Đã nhấn nút download ở FOLD.")

	// Đợi play xuất hiện sau khi download xong
	boolean isPlay = Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.Button - play_fold'), 120, FailureHandling.OPTIONAL)
	if (!isPlay) {
		Mobile.takeScreenshot('Screenshot/SA_Play_Not_Found_Fold.png')
		assert false : "Không tìm thấy nút PLAY sau khi download ở FOLD!"
	}
	Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Button - play_fold'), 5)
	println("Đã nhấn nút PLAY ở FOLD.")
} else {
	println("Không phải fold, chuyển qua case unfold.")
	// --- 4. Xử lý cho UNFOLD ---
	boolean isDownloadUnfold = Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.Button - Download_unfold'), 5, FailureHandling.OPTIONAL)
	if (isDownloadUnfold) {
		Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Button - Download_unfold'), 5)
		// Đợi play xuất hiện sau khi download xong
		boolean isPlayUnfold = Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.Button - play_unfold'), 300, FailureHandling.OPTIONAL)
		if (!isPlayUnfold) {
			Mobile.takeScreenshot('Screenshot/SA_Play_Not_Found_Unfold.png')
			assert false : "Không tìm thấy nút PLAY ở Unfold!"
		}
		Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Button - play_unfold'), 5)
		println("Đã nhấn nút PLAY ở Unfold.")
	} else {
		// --- 5. Không thấy nút download ở màn hình bên cạnh (unfold) ---
		println("Không thấy download_unfold, xử lý download_inline ngay trên mục SA.")
		boolean isDownloadInlineSA = Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.Button - download_inline_SA'), 8, FailureHandling.OPTIONAL)
		if (!isDownloadInlineSA) {
			Mobile.takeScreenshot('Screenshot/SA_DownloadInline_Not_Found.png')
			assert false : "Không tìm thấy nút download_inline_SA ở Unfold!"
		}
		Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Button - download_inline_SA'), 2)
		println("Đã tap vào download_inline_SA, đợi download hoàn tất (nút biến mất)")
		int waitCount = 0
		while(Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.Button - download_inline_SA'), 3, FailureHandling.OPTIONAL) && waitCount < 60) {
			Thread.sleep(2000)
			waitCount++
		}
		println("Nút download_inline_SA đã biến mất (download xong), tap lại vào SA.")
		Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Sound Assistant'), 5)
		// Đợi nút PLAY ở unfold rồi mới tap play
		boolean isPlayUnfold2 = Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.Button - play_unfold'), 60, FailureHandling.OPTIONAL)
		if (!isPlayUnfold2) {
			Mobile.takeScreenshot('Screenshot/SA_PlayInline_Not_Found.png')
			assert false : "Không xuất hiện nút PLAY ở màn unfold sau khi download lại!"
		}
		Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Button - play_unfold'), 5)
		println("Đã nhấn nút PLAY ở Unfold sau khi down inline.")
	}
}
// 4. Các bước tiếp theo của bạn...
Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Button - Continue'), 2000)

Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Button - Allow'), 2000)

