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
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.interactions.Actions as Actions
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.url)

WebUI.maximizeWindow()

WebDriver driver = DriverFactory.getWebDriver()

// 1. Login
WebUI.click(findTestObject('Object Repository/Login/Page_/btn_Login_Topmenu'))

WebUI.setText(findTestObject('Object Repository/Login/Page_/txt_userName'), 'nyusatsu-king-test9@zuno.vc')

WebUI.setEncryptedText(findTestObject('Object Repository/Login/Page_/txt_passWord'), 'IcnaS4Pet11kvpFnM+eeWQ==')

WebUI.click(findTestObject('Object Repository/Login/Page_/btn_submit_Login'))

// MENU BUDGET
//2. Verify open page bugget Search
WebUI.click(findTestObject('Object Repository/Type_Account/Page_/tab_menu_budget'))

Thread.sleep(2000)

actualURL = WebUI.getUrl()

WebUI.verifyMatch(actualURL, GlobalVariable.url + 'usermenu/budget_search/', false)

//3. Verify open popup successfully
WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_popupBugdet'))

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Type_Account/Page_/lbl_title_popup_Budget')), '発注機関選択', 
    false)

//4. Verify checkbox 
WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_uncheckAllItem_Bugdet'))

def checkboxes = findTestObject('Object Repository/Type_Account/Page_/list_checkbox_budget')

for (def checkbox : checkboxes) {
    WebUI.verifyElementNotChecked(checkbox, 10)
}

//5. Verify all checkboxs has been checked when click button checkALL
WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_checkAllItem_Budget'))

for (def checkbox : checkboxes) {
    WebUI.verifyElementChecked(checkbox, 10)
}

//6. Verify scroll focus correct item when click left item 1
WebUI.click(findTestObject('Object Repository/Type_Account/Page_/lbl_leftitem1_Budget'))

