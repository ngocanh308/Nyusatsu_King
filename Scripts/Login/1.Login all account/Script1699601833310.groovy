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

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.url)

WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/Login/Page_/btn_Login_Topmenu'))

//1. TCs_01: Verify displayed error message when input Valid username - Invalid Password
WebUI.setText(findTestObject('Object Repository/Login/Page_/txt_userName'), 'nyusatsu-king-test9@zuno.vc')

WebUI.setEncryptedText(findTestObject('Object Repository/Login/Page_/txt_passWord'), 'zHYHJuhncVZghD44yZLsjg==')

WebUI.click(findTestObject('Object Repository/Login/Page_/btn_submit_Login'))

WebUI.verifyElementText(findTestObject('Login/Page_/lbl_textErrorLogin'), '※登録したメールアドレス・パスワードをご確認ください。', FailureHandling.STOP_ON_FAILURE)

//2. TCs_02: Verify displayed error message when input Invalid username - Valid Password
WebUI.setText(findTestObject('Object Repository/Login/Page_/txt_userName'), 'nyusatsu-king-test99@zuno.vc')

WebUI.setEncryptedText(findTestObject('Object Repository/Login/Page_/txt_passWord'), 'IcnaS4Pet11kvpFnM+eeWQ==')

WebUI.click(findTestObject('Object Repository/Login/Page_/btn_submit_Login'))

WebUI.verifyElementText(findTestObject('Login/Page_/lbl_textErrorLogin'), '※登録したメールアドレス・パスワードをご確認ください。', FailureHandling.STOP_ON_FAILURE)

//3. TCs_03: Verify displayed error message when input Valid username - empty Password
WebUI.setText(findTestObject('Object Repository/Login/Page_/txt_userName'), 'nyusatsu-king-test9@zuno.vc')

WebUI.setEncryptedText(findTestObject('Object Repository/Login/Page_/txt_passWord'), '')

WebUI.click(findTestObject('Object Repository/Login/Page_/btn_submit_Login'))

WebUI.verifyElementText(findTestObject('Login/Page_/lbl_textErrorLogin'), '※登録したメールアドレス・パスワードをご確認ください。', FailureHandling.STOP_ON_FAILURE)

//4. TCs_04: Verify displayed error message when input empty username - Valid Password
WebUI.setText(findTestObject('Object Repository/Login/Page_/txt_userName'), '')

WebUI.setEncryptedText(findTestObject('Object Repository/Login/Page_/txt_passWord'), 'IcnaS4Pet11kvpFnM+eeWQ==')

WebUI.click(findTestObject('Object Repository/Login/Page_/btn_submit_Login'))

WebUI.verifyElementText(findTestObject('Login/Page_/lbl_textErrorLogin'), '※登録したメールアドレス・パスワードをご確認ください。', FailureHandling.STOP_ON_FAILURE)

//5. TCs_05: Verify displayed error message when input Valid username - Valid Password but not the same 1 account
WebUI.setText(findTestObject('Object Repository/Login/Page_/txt_userName'), 'nyusatsu-king-test8@zuno.vc')

WebUI.setEncryptedText(findTestObject('Object Repository/Login/Page_/txt_passWord'), 'IcnaS4Pet11kvpFnM+eeWQ==')

WebUI.click(findTestObject('Object Repository/Login/Page_/btn_submit_Login'))

WebUI.verifyElementText(findTestObject('Login/Page_/lbl_textErrorLogin'), '※登録したメールアドレス・パスワードをご確認ください。', FailureHandling.STOP_ON_FAILURE)

//6. TCs_06: Verify login successfully when input Valid userName - Valid password
WebUI.setText(findTestObject('Object Repository/Login/Page_/txt_userName'), 'nyusatsu-king-test9@zuno.vc')

WebUI.setEncryptedText(findTestObject('Object Repository/Login/Page_/txt_passWord'), 'IcnaS4Pet11kvpFnM+eeWQ==')

WebUI.click(findTestObject('Object Repository/Login/Page_/btn_submit_Login'))

Thread.sleep(2000)

actualURL = WebUI.getUrl()

WebUI.verifyMatch(actualURL, GlobalVariable.url + "usermenu/both_item_search/", false)

WebUI.closeBrowser()

