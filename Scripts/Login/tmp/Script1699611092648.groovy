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

//WebUI.verifyElementChecked(findTestObject('Type_Account/Page_/btn_uncheckAllItem_Bugdet'), 10)
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_checkAllItem_Budget'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/lbl_leftitem1_Budget'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/lbl_leftitem2_Budget'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/lbl_leftitem3_Budget'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_SelectedItem_Budget'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_close(X)_popup_Budget'))
//
//WebUI.setText(findTestObject('Object Repository/Type_Account/Page_/txt_InputKW_Budget'), 'hello')
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_submitSearch(c)'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/tab_menu_Yotei'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_popup_kindKW_Yotei'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_close(X)_kind_Yotei'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_popup_BlockID(c)'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_uncheckAllItem_BlockID(c)'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_checkALLitem_BlockID(c)'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_submitSearch_BlockID(c)'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_popup_Area_Yotei'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_uncheckALLitem_Area(c)'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_checkALLitem_area(c)'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_close(X)Popup_Area_Yotei'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_submitSearch(c)'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_downloadCSV(c)'))
//
//WebUI.setText(findTestObject('Object Repository/Type_Account/Page_/txt_inputOR'), 'oo')
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/tab_menu_Both'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/menu_both(Nyu_Raku)'))
//
//WebUI.setText(findTestObject('Object Repository/Type_Account/Page_/txt_inputOR'), 'he')
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_popup_BlockID(c)'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_uncheckAllItem_BlockID(c)'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_checkALLitem_BlockID(c)'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_submitSearch_BlockID(c)'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_area_Both'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_uncheckALLitem_Area(c)'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_checkALLitem_area(c)'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_submitSelected_Area(c)'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_Categories_Both'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_uncheckALLitem_Categorie_Both'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_checkALLitem_Categories_Both'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_submitSelected_Categories'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_submitSearch(c)'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_downloadCSV(c)'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/tab_menu_Mail'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_popup_listMail'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/span__1_2'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_selectedMailList'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_close(X)_popupMail'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/tab_menu_Anken'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_anken_yotei'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_download_Anken(c)'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_anken_Both'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_download_Anken(c)'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_anken_Nyusatsu'))
//
//WebUI.click(findTestObject('Object Repository/Type_Account/Page_/btn_anken_Rakusatsu'))
//
