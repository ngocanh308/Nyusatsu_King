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

int maxRow = 0

maxRow = findTestData('Account_Production').getRowNumbers()

for (def rowNum = 1; rowNum <= maxRow; rowNum++) {
    WebUI.click(findTestObject('Object Repository/Login/Page_/btn_Login_Topmenu'))

    WebUI.setText(findTestObject('Object Repository/Login/Page_/txt_userName'), findTestData('Account_Production').getValue(
            1, rowNum))

    WebUI.setText(findTestObject('Object Repository/Login/Page_/txt_passWord'), findTestData('Account_Production').getValue(
            2, rowNum))

    WebUI.click(findTestObject('Object Repository/Login/Page_/btn_submit_Login'))

    Thread.sleep(2000)

    actualURL = WebUI.getUrl()

    WebUI.verifyMatch(actualURL, GlobalVariable.url + 'usermenu/both_item_search/', false)

    WebUI.mouseOver(findTestObject('Object Repository/Login/Page_/lbl_iconUser'))

    Thread.sleep(2000)

    WebUI.click(findTestObject('Login/Page_/lbl_Logout'))
	
	Thread.sleep(2000)

    actualURL = WebUI.getUrl()

    WebUI.verifyMatch(actualURL, GlobalVariable.url, false)
}

WebUI.closeBrowser()
