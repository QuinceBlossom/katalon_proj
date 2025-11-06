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

// 1. Báo cho script biết app nào cần gỡ
String packageName = 'com.samsung.android.soundassistant'

// 2. Tạo lệnh để KIỂM TRA app
// Lệnh này sẽ liệt kê các package và lọc ra (grep) tên app
String checkCommand = "adb shell pm list packages " + packageName
Process checkProcess = Runtime.getRuntime().exec(checkCommand)
checkProcess.waitFor() // Đợi lệnh kiểm tra chạy xong

// 3. Đọc kết quả trả về
String checkResult = checkProcess.inputStream.text

// 4. Kiểm tra kết quả
if (checkResult.contains(packageName)) {
	// 5. Nếu có, thực hiện GỠ CÀI ĐẶT
	println('Tìm thấy ' + packageName + ', đang gỡ cài đặt...')
	
	String uninstallCommand = "adb shell pm uninstall " + packageName
	Process uninstallProcess = Runtime.getRuntime().exec(uninstallCommand)
	uninstallProcess.waitFor() // Đợi lệnh gỡ cài đặt chạy xong
	
	// In kết quả (thường sẽ là "Success")
	println(uninstallProcess.inputStream.text)
	
} else {
	// 6. Nếu không có, chỉ in ra thông báo
	println('App ' + packageName + ' không có trên thiết bị.')
}
