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
import internal.GlobalVariable
import jdk.nashorn.internal.objects.Global

import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.interactions.Actions as Actions
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.support.ui.Select as Select
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

Thread.sleep(GlobalVariable.short_time)

actualURL = WebUI.getUrl()

WebUI.verifyMatch(actualURL, GlobalVariable.url + 'usermenu/budget_search/', false)

// Verify account 9 get list start from 2014 - 2024
SelectedItems = WebUI.getNumberOfTotalOption(findTestObject('Object Repository/Type_Account/Page_/dpl_StartYear_Budget'))

WebUI.verifyEqual(SelectedItems, 11)

// Verify account 9 get list end from 2014 - 2024
SelectedItems = WebUI.getNumberOfTotalOption(findTestObject('Object Repository/Type_Account/Page_/dpl_EndYear_Budget'))

WebUI.verifyEqual(SelectedItems, 11)

//3. Verify open popup successfully
WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_popupBugdet'))

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Type_Account/Page_/lbl_title_popup_Budget')), '発注機関選択', 
    false)

//4. Verify all checkboxs has been unchecked when click button checkALL 
WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_uncheckAllItem_Bugdet'))

def checkboxes = findTestObject('Object Repository/Type_Account/Page_/list_checkbox_budget')

for (def checkbox : checkboxes) {
    WebUI.verifyElementNotChecked(checkbox, 10)
}

//5. Verify all checkboxs has been checked when click button checkALL
WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_checkAllItem_Budget'))

def checkboxes_1 = findTestObject('Object Repository/Type_Account/Page_/tmp')

for (def checkbox_1 : checkboxes_1) {
    WebUI.verifyElementChecked(checkbox_1,5, FailureHandling.OPTIONAL)
}


//6. Verify scroll focus correct item when click left item 1
WebUI.click(findTestObject('Object Repository/Type_Account/Page_/lbl_leftitem1_Budget'))

WebUI.verifyElementInViewport(findTestObject('Object Repository/Type_Account/Page_/lbl_focusLeft1_budget'), 3, FailureHandling.STOP_ON_FAILURE)

//7. Verify scroll focus correct item when click left item 2
WebUI.click(findTestObject('Object Repository/Type_Account/Page_/lbl_leftitem2_Budget'))

WebUI.verifyElementInViewport(findTestObject('Object Repository/Type_Account/Page_/lbl_focusLeft2_budget'), 3, FailureHandling.STOP_ON_FAILURE)

//8. Verify scroll focus correct item when click left item 3
WebUI.click(findTestObject('Object Repository/Type_Account/Page_/lbl_leftitem3_Budget'))

WebUI.verifyElementInViewport(findTestObject('Object Repository/Type_Account/Page_/lbl_focusLeft3_budget'), 3, FailureHandling.STOP_ON_FAILURE)

// 9. Verify that popup close when click button X on popup budget
WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_SelectedItem_Budget'))

WebUI.verifyElementNotVisible(findTestObject('Object Repository/Type_Account/Page_/lbl_title_popup_Budget'))

//10 Verify that Return list successfully 
WebUI.setText(findTestObject('Object Repository/Type_Account/Page_/txt_InputKW_Budget'), 'helloanhanh')

WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_submitSearch(c)'))

Thread.sleep(2000)

// 11. Verify that display alert when search 0 item -> clear text
//String AlertText = driver.switchTo().alert().getText()
//
//WebUI.verifyEqual(AlertText, '検索結果は０件です。検索条件を変えて検索してください。')
//if (WebUI.verifyAlertPresent(30)) {
//    println('Alert is present')
//}
WebUI.clearText(findTestObject('Object Repository/Type_Account/Page_/txt_InputKW_Budget'))

//// 12. Verify that return valid result
WebUI.setText(findTestObject('Object Repository/Type_Account/Page_/txt_InputKW_Budget'), 'hello')

WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_submitSearch(c)'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Type_Account/Page_/list_result_budget'), 3, FailureHandling.STOP_ON_FAILURE)

//// 12. Verify that add favorite successfully
WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_favorite_budget'))

WebUI.mouseOver(findTestObject('Object Repository/Type_Account/Page_/btn_favorite_top'))

actualText = WebUI.getText(findTestObject('Object Repository/Type_Account/Page_/btn_favorite_budgetText'))

println("a_________________"+actualText)

//WebUI.verifyMatch(actualText, "お気に入りに追加", false)
//
//
//// Verify uncheck when click again button favorite
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_favorite_budget'))
//
//WebUI.verifyElementText(findTestObject('Object Repository/Type_Account/Page_/btn_favorite_budgetText'), 'お気に入りに追加')


