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

//Tap vào text Plugins
Mobile.tap(findTestObject('Object Repository/android.widget.TextView - Plugins (1)'), 2000)

Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Extensions'), 2000)

Mobile.scrollToText('Sound Assistant')

Mobile.getText(findTestObject('Object Repository/Extensions/android.widget.TextView - Sound Assistant'), 0)

Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Sound Assistant'), 2000)

//vuốt xuống để katalon nhận object
Mobile.swipe(341, 2000, 341, 300)

//vuốt lên để bắt nút download
Mobile.swipe(540, 430, 540, 1900)

// tap vào download khi đang fold
Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Button - download_fold'), 2000)
// tap vào download khi đang unfold
Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Button - Download_unfold'), 0)

//nếu ko thể tap vào nut download bên trong SA thì tap vào download ngay trên mục SA
Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Button - download_inline_SA'), 0)
// đợi khi nào nút download trên mục SA down xog thì tap vào SA, 
// download_inline_SA nút này sẽ biến mất nghĩa là down xog, thì tap trực tiếp vào SA
Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.TextView - Sound Assistant'), 2000)

// 2. [BƯỚC QUAN TRỌNG BỊ THIẾU]
// Đợi cho nút "PLAY" khi đang unfold xuất hiện (chờ tối đa 120 giây)
Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.Button - play_fold'), 120)

// Đợi cho nút "PLAY" khi đang fold xuất hiện (chờ tối đa 120 giây)
Mobile.waitForElementPresent(findTestObject('Object Repository/Extensions/android.widget.Button - play_unfold'), 120)

// 3. Sau khi nút "PLAY" đã xuất hiện ở fold, BÂY GIỜ MỚI NHẤN VÀO NÓ
Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Button - play_fold'), 2000)

// 3.1 Sau khi nút "PLAY" đã xuất hiện khi đang unfold, BÂY GIỜ MỚI NHẤN VÀO NÓ
Mobile.tap(findTestObject('Object Repository/Extensions/android.widget.Button - play_unfold'), 2000)





