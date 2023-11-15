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

import bsh.Variable
import internal.GlobalVariable as GlobalVariable
import jdk.nashorn.internal.objects.Global as Global
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.interactions.Actions as Actions
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.support.ui.Select as Select
import org.openqa.selenium.By as By
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration



import java.awt.Toolkit as Toolkit
import java.awt.datatransfer.Clipboard as Clipboard
import java.awt.datatransfer.DataFlavor as DataFlavor

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.url)

WebUI.maximizeWindow()

WebDriver driver = DriverFactory.getWebDriver()

// Check current Enviroment for Run Test Script
currentProfile = RunConfiguration.getExecutionProfile()

int maxRow = 0

if (currentProfile == 'Production') {
    maxRow = findTestData('Account_Production').getRowNumbers()
} else if (currentProfile == 'Staging') {
    WebUI.authenticate('https://nyusatsu-o-neo-dev-8085.zuno.vc/', 'zuno', 'g1', 12)

    maxRow = findTestData('Account_Staging').getRowNumbers()
}

// 1. Check list account of each Enviroment for input username/password
for (def rowNum = 1; rowNum <= maxRow; rowNum++) {
    WebUI.click(findTestObject('Object Repository/Login/Page_/btn_Login_Topmenu'))

    if (currentProfile == 'Production') {
        WebUI.setText(findTestObject('Object Repository/Login/Page_/txt_userName'), findTestData('Account_Production').getValue(
                1, rowNum))

        WebUI.setText(findTestObject('Object Repository/Login/Page_/txt_passWord'), findTestData('Account_Production').getValue(
                2, rowNum))
    } else if (currentProfile == 'Staging') {
        WebUI.setText(findTestObject('Object Repository/Login/Page_/txt_userName'), findTestData('Account_Staging').getValue(
                1, rowNum))

        WebUI.setText(findTestObject('Object Repository/Login/Page_/txt_passWord'), findTestData('Account_Staging').getValue(
                2, rowNum))
    }
    
    WebUI.click(findTestObject('Object Repository/Login/Page_/btn_submit_Login'))

    // MENU Yotei
    //2. Verify open page Yotei Search
    WebUI.click(findTestObject('Object Repository/Type_Account/Web/tab_menu_Yotei'))

    Thread.sleep(GlobalVariable.short_time)

    actualURL = WebUI.getUrl()

    if (rowNum in [1, 3, 5]) {
        // Verify account 1,3,5 navigate to page guide_predict and can not search
        WebUI.verifyMatch(actualURL, GlobalVariable.url + 'usermenu/guide_predict/', false) 
		
		
    } else {
		// Verify account navigate corect Yotei search when click tab menu Yotei search
        WebUI.verifyMatch(actualURL, GlobalVariable.url + 'usermenu/yotei_search/', false)
				
		//Verify can be input text into textbox or検索
        WebUI.setText(findTestObject('Object Repository/Type_Account/Web/txt_inputOR'), 'o')
		
		WebUI.clearText(findTestObject('Object Repository/Type_Account/Web/txt_inputOR'))
		
		// Verify that max length textbox = 20
		WebUI.setText(findTestObject('Object Repository/Type_Account/Web/txt_inputOR'), '1111111111111111111111111111111111')
			
		variableHoldingValue = WebUI.getAttribute(findTestObject('Object Repository/Type_Account/Web/txt_inputOR'), 'value')
		
		WebUI.verifyEqual(variableHoldingValue, '11111111111111111111')
		
		WebUI.clearText(findTestObject('Object Repository/Type_Account/Web/txt_inputOR'))
		
		// Verify that Init exist 4 textbox of or検索
		List<WebElement> listNumTextbox_Init = driver.findElements(By.cssSelector('div#subjectORTable input'))		
		
		WebUI.verifyEqual(listNumTextbox_Init.size(),4 )
				
		//Verify that max textbox or検索 = 20
		for (int i =1 ; i<=8 ; i++) {
			
			WebUI.click(findTestObject('Type_Account/Web/btn_AddTextboxOR'))
		}
		List<WebElement> listNumTextbox_MaxOR = driver.findElements(By.cssSelector('div#subjectORTable input'))
		
		WebUI.verifyEqual(listNumTextbox_MaxOR.size(),20 )
		
		// Verify that remove textbox or検索  after click button Remove -
		WebUI.click(findTestObject('Type_Account/Web/btn_RemoveTextboxOR'))
		
		List<WebElement> listNumTextbox_Remove = driver.findElements(By.cssSelector('div#subjectORTable input'))
		
		WebUI.verifyEqual(listNumTextbox_Remove.size(),4 )
		
		//Verify Init exist 1 textbox and検索
		List<WebElement> listNumTextbox_InitAnd = driver.findElements(By.cssSelector('div#subjectANDTable input'))
				
		WebUI.verifyEqual(listNumTextbox_InitAnd.size(),1 )
		
		//Verify that max textbox and検索 = 6 and button add (+) will be hide 
		for (int i =1 ; i<=5 ; i++) {
			
			WebUI.click(findTestObject('Type_Account/Web/btn_AddTextboxAnd'))
		}
		List<WebElement> listNumTextbox_MaxAnd = driver.findElements(By.cssSelector('div#subjectANDTable input'))
		
		WebUI.verifyEqual(listNumTextbox_MaxAnd.size(),6 )
		
		WebUI.verifyElementNotVisible(findTestObject('Type_Account/Web/btn_AddTextboxAnd'))
		
		//Verify remove textbox and検索  after click button Remove -
		WebUI.click(findTestObject('Type_Account/Web/btn_RemoveTextboxAnd'))
		
		List<WebElement> listNumTextbox_RemoveAnd = driver.findElements(By.cssSelector('div#subjectANDTable input'))
		
		WebUI.verifyEqual(listNumTextbox_RemoveAnd.size(),1 )
		
		// Verify that open popup Kind Array OR キーワード選択  successfully when click button 
        WebUI.click(findTestObject('Object Repository/Type_Account/Web/btn_popup_kindKW_Yotei'))
		
		WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Type_Account/Web/lbl_title_popup_Kind')), '業種(種別・工種)検索 キーワード選択', false)
		
		// Verify left menu popup Kind hightlight text when click
		List<WebElement> listNumPage = driver.findElements(By.cssSelector('.workkind-item'))
		
		for ( int x = 1; x <= listNumPage.size(); x++)
			{
				WebElement itemLeftKind = driver.findElement(By.cssSelector('ul.block__large-list li:nth-child('+x+') a'))
				
				itemLeftKind.click()
				
//				css_color = WebUI.getCSSValue(driver.findElement(By.cssSelector('ul.block__large-list li:nth-child('+x+')')), 'color')
//				
//				WebUI.verifyEqual(css_color, 'rgba(229, 153, 24)')
			}
			
		// Verify close popup Kind when click button X 
		WebUI.click(findTestObject('Object Repository/Type_Account/Web/btn_close(X)_kind_Yotei'))
		
		WebUI.verifyElementNotVisible(findTestObject('Type_Account/Web/lbl_title_popup_Kind'))
		
		// Verify open popup Block ID when click button 発注機関を選択
		WebUI.click(findTestObject('Object Repository/Type_Account/Web/btn_popup_BlockID(c)'))
		
		WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Type_Account/Web/lbl_title_popup_BlockID')), '発注機関選択', false)
		
		// Verify click button uncheckAll item Block ID
		WebUI.click(findTestObject('Object Repository/Type_Account/Web/btn_uncheckAllItem_BlockID(c)'))
		
		def checkboxes_check = findTestObject('Object Repository/Type_Account/Web/list_checkbox_blockID')
		
		for (def checkbox : checkboxes_check) {
			WebUI.verifyElementNotChecked(checkbox, 10)
		}
		
		// Verify click button checkALL item block
		WebUI.click(findTestObject('Object Repository/Type_Account/Web/btn_checkALLitem_BlockID(c)'))
		def checkboxes_uncheck = findTestObject('Object Repository/Type_Account/Web/list_checkbox_blockID')
		
		for (def checkbox : checkboxes_uncheck) {
			WebUI.verifyElementChecked(checkbox, 10)
		}
    }
    
    // Logout
    WebUI.scrollToPosition(0, 0)

    WebUI.mouseOver(findTestObject('Object Repository/Login/Page_/lbl_iconUser'))

    Thread.sleep(2000)

    WebUI.click(findTestObject('Login/Page_/lbl_Logout'))
}

