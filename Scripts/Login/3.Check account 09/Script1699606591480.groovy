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
import com.kms.katalon.core.configuration.RunConfiguration


WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.url)

WebUI.maximizeWindow()

WebDriver driver = DriverFactory.getWebDriver()


// Check current Enviroment for Run Test Script
currentProfile = RunConfiguration.getExecutionProfile()

int maxRow = 0

if(currentProfile == 'Production')
{
	maxRow = findTestData('Account_Production').getRowNumbers()
}
else if(currentProfile == 'Staging')
{
	WebUI.authenticate('https://nyusatsu-o-neo-dev-8085.zuno.vc/', 'zuno', 'g1', 12)
	
	maxRow = findTestData('Account_Staging').getRowNumbers()
}

// 1. Check list account of each Enviroment for input username/password
for (def rowNum = 1; rowNum <= maxRow; rowNum++) {
	
	WebUI.click(findTestObject('Object Repository/Login/Page_/btn_Login_Topmenu'))

	if(currentProfile == 'Production')
	{
		WebUI.setText(findTestObject('Object Repository/Login/Page_/txt_userName'), findTestData('Account_Production').getValue(
				1, rowNum))
	
		WebUI.setText(findTestObject('Object Repository/Login/Page_/txt_passWord'), findTestData('Account_Production').getValue(
				2, rowNum))
	}
	else if(currentProfile == 'Staging')
	{
		WebUI.setText(findTestObject('Object Repository/Login/Page_/txt_userName'), findTestData('Account_Staging').getValue(
			1, rowNum))

	WebUI.setText(findTestObject('Object Repository/Login/Page_/txt_passWord'), findTestData('Account_Staging').getValue(
			2, rowNum))
	}
	
	WebUI.click(findTestObject('Object Repository/Login/Page_/btn_submit_Login'))
	
	
	// MENU BUDGET
	//2. Verify open page bugget Search
	WebUI.click(findTestObject('Object Repository/Type_Account/Page_/tab_menu_budget'))
	
	Thread.sleep(GlobalVariable.short_time)
	
	actualURL = WebUI.getUrl()
	
	if(rowNum in [1,4,7])
	{
		WebUI.verifyMatch(actualURL, GlobalVariable.url + 'usermenu/guide_budget/', false)
		
	}
	else 
    {
		
		WebUI.verifyMatch(actualURL, GlobalVariable.url + 'usermenu/budget_search/', false)
		
		SelectedItemsStart = WebUI.getNumberOfTotalOption(findTestObject('Object Repository/Type_Account/Page_/dpl_StartYear_Budget'))
		
		SelectedItemsEnd = WebUI.getNumberOfTotalOption(findTestObject('Object Repository/Type_Account/Page_/dpl_EndYear_Budget'))
		
		// Verify acocunt 2,3,8,10 use trial budget -> Droplist exist 3 items
		if(rowNum in [2,3,8,10])
		{
			WebUI.verifyEqual(SelectedItemsStart, 3)
			
			WebUI.verifyEqual(SelectedItemsEnd, 3)
		}
		else // Verify acocunt 5,6,9 use paid  budget -> Droplist exist 11 items
		{
			WebUI.verifyEqual(SelectedItemsStart, 11)
			
			WebUI.verifyEqual(SelectedItemsEnd, 11)
		}
		
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
		WebUI.setText(findTestObject('Object Repository/Type_Account/Page_/txt_InputKW_Budget'), 'kind')
		
		WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_submitSearch(c)'))
		
		WebUI.verifyElementPresent(findTestObject('Object Repository/Type_Account/Page_/list_result_budget'), 3, FailureHandling.STOP_ON_FAILURE)
		
		//// 12. Verify that add favorite successfully
		WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_favorite_budget1'))
		
		WebUI.mouseOver(findTestObject('Object Repository/Type_Account/Page_/btn_favorite_top'))
		
		actualText_check = WebUI.getText(findTestObject('Object Repository/Type_Account/Page_/btn_favorite_budgetText2'))
		
		WebUI.verifyMatch(actualText_check, "お気に入りに追加済", false)
		
		WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_favorite_budget2'))
		
		actualText_uncheck = WebUI.getText(findTestObject('Object Repository/Type_Account/Page_/btn_favorite_budgetText1'))
		
		WebUI.verifyMatch(actualText_uncheck, "お気に入りに追加", false)
		
		//WebUI.verifyMatch(actualText, "お気に入りに追加", false)
		//
		//
		//// Verify uncheck when click again button favorite
		//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_favorite_budget1'))
		//
		//WebUI.verifyElementText(findTestObject('Object Repository/Type_Account/Page_/btn_favorite_budgetText1'), 'お気に入りに追加')
    }
	WebUI.scrollToPosition(100, 20)
	
	WebUI.mouseOver(findTestObject('Object Repository/Login/Page_/lbl_iconUser'))
	
	Thread.sleep(2000)
	
	WebUI.click(findTestObject('Login/Page_/lbl_Logout'))
}

WebUI.closeBrowser()
