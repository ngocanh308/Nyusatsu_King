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


import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl('http://dev.nyusatsu-king.com:8080/admin/ad_login')

WebUI.maximizeWindow()

//WebUI.navigateToUrl('https://username:password@zuno/j4G2Qz7V')
WebDriver driver = DriverFactory.getWebDriver()
Thread.sleep(5000)

String AlertText = driver.switchTo().alert().getText()
println("A_____________________"+AlertText)
driver.switchTo().alert().sendKeys('zuno')
WebUI.dismissAlert()
//WebUI.authenticate('https://dev.nyusatsu-king.com:8080/admin/ad_login', 'zuno', 'j4G2Qz7V', 12)

//WebUI.setText(findTestObject('Object Repository/Type_Account/Login_Admin/Page_-/txt_userName'), 'testadmin')
//
//WebUI.setEncryptedText(findTestObject('Object Repository/Type_Account/Login_Admin/Page_-/txt_passWord'), '5Ed5CIkj9UQfaMZXAkDVaQ==')
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Login_Admin/Page_-/btn_submit_Login'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Login_Admin/Admin/lbl_menuAcount(CM)'))
//
//int maxRow = 0
//
//maxRow = findTestData('Account_Staging').getRowNumbers()
//
//for (def rowNum = 1; rowNum <= maxRow; rowNum++) {
//	
//	WebUI.setText(findTestObject('Object Repository/Type_Account/Login_Admin/Admin/txt_inputEmail'), findTestData('Account_Staging').getValue(1, rowNum))
//	
//	WebUI.click(findTestObject('Object Repository/Type_Account/Login_Admin/Admin/rdo_All_Account'))
//	
//	WebUI.click(findTestObject('Object Repository/Type_Account/Login_Admin/Admin/btn_SearchAccount'))
//	
//	WebUI.click(findTestObject('Object Repository/Type_Account/Login_Admin/Admin/lbl_idAccount_Inlist'))
//	
//	WebUI.switchToWindowTitle('NyusatsuKingAdminSystem')
//	
//	WebUI.click(findTestObject('Object Repository/Type_Account/Login_Admin/Admin/btn_LoginWeb'))
//	
//	actualURL = WebUI.getUrl()
//	
//	if(rowNum in [1,4,7])
//		{
//			WebUI.verifyMatch(actualURL, GlobalVariable.url + 'usermenu/guide_budget/', false)
//			
//		}
//		else
//		{
//			WebUI.verifyMatch(actualURL, GlobalVariable.url + 'usermenu/budget_search/', false)
//		}
//	
//}
