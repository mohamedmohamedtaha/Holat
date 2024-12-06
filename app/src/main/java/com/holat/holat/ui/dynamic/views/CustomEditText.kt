package com.holat.holat.ui.dynamic.views

import android.app.Activity
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.widget.LinearLayoutCompat
import com.holat.holat.ui.dynamic.models.CustomView
import com.holat.holat.ui.dynamic.models.GlobalView
import com.holat.holat.ui.dynamic.models.Pattern
import com.holat.holat.ui.dynamic.models.SaveData
import com.holat.holat.ui.dynamic.models.TablesModel
import com.holat.holat.ui.dynamic.views.initialize.drawLabelOrTitle
import com.holat.holat.utils.listener.OtherListener
import org.json.JSONObject

class CustomEditText {
    private fun customBasicEditText(
        globalView: GlobalView,
        activity: Activity,
        checkRuleMovementListener: OtherListener,
        inputType: Int,
        maxHeight: Int,
    ): EditText {
        val layoutParams = LinearLayoutCompat.LayoutParams(
            LinearLayoutCompat.LayoutParams.MATCH_PARENT,
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT
        )

        layoutParams.setMargins(5, 18, 5, layoutParams.bottomMargin)
        val editText = EditText(activity)
        editText.setPadding(10, 15, 10, 15)
//        editText.setTextColor(ContextCompat.getColor(activity,R.color.black))
        editText.setText(globalView.tablesModel.value)
        editText.isEnabled = globalView.tablesModel.readOnlyFlag == "0"
        if (maxHeight != 0) {
            editText.filters = arrayOf(InputFilter.LengthFilter(maxHeight))

        }
        if (globalView.tablesModel.refValueIfNull.isNotEmpty()) {
            val inputTypeValue = getPatternFromJson(globalView.tablesModel.refValueIfNull).value
            if (inputTypeValue.isNotEmpty() && inputTypeValue == "string") {
                val filter = InputFilter { source, start, end, dest, dstart, dend ->
                    val filetered = StringBuilder()
                    for (i in start until end) {
                        if (source[i].isLetter()) {
                            filetered.append(source[i])
                            //    return@InputFilter "" //Reject the input if it's not a letter
                        }
                    }
                    if (filetered.isEmpty()) null else filetered.toString()
                    //   null // Accept the input
                }
                editText.filters = arrayOf(filter)

            } else if (inputTypeValue.isNotEmpty() && inputTypeValue == "number") {
                val filter = InputFilter { source, start, end, dest, dstart, dend ->
                    val filetered = StringBuilder()
                    for (i in start until end) {
                        if (source[i].isDigit()) {
                            filetered.append(source[i])
                        }
                    }
                    if (filetered.isEmpty()) null else filetered.toString()
                }
                editText.filters = arrayOf(filter)

            }
        }
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                globalView.tablesModel.value = p0.toString().trim()
                checkRuleMovementListener.other(globalView.tablesModel)
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        }
        editText.addTextChangedListener(textWatcher)
        globalView.tablesModel.textWatcher = textWatcher
        editText.inputType = inputType
        editText.layoutParams = layoutParams
        return editText
    }

    private fun getPatternFromJson(refValueIfNull: String): Pattern {
        val data = Pattern()
        try {
            if (refValueIfNull != "") {
                val jsonObj = JSONObject(refValueIfNull)
                val jsonArray = jsonObj.names()
                if (jsonArray != null) {
                    for (k in 0 until jsonArray.length()) {
                        val key = jsonArray.getString(k)
                        val value = jsonObj.getString(key) ?: ""
                        if (key == "input_type") {
                            data.inputType = key
                            data.value = value
                            return data
                        }
                    }
                }
            }
        } catch (e: Exception) {
            // If happened any error handle there
        }

        return data
    }

    private fun drawEditText(
        allAnswersFirstTable: ArrayList<SaveData>,
        globalView: GlobalView,
        activity: Activity,
        inputType: Int,
        checkRuleMovementListener: OtherListener,
        maxHeight: Int,
    ): CustomView<EditText>? {
        allAnswersFirstTable.forEach {
            if (it.columnName.lowercase() == globalView.tablesModel.questDbColumnName.lowercase())
                globalView.tablesModel.value = it.value
        }
        globalView.editTextView = CustomView()
        globalView.editTextView?.title = activity.drawLabelOrTitle(true, globalView)
        globalView.editTextView?.typeView = customBasicEditText(
            globalView = globalView,
            activity = activity,
            inputType = inputType,
            checkRuleMovementListener = checkRuleMovementListener,
            maxHeight = maxHeight
        )
        return globalView.editTextView
    }
//    fun <T>drawSelectEditText(
//        mainData: MainReasons,
//        activity: Activity,
//        data: ArrayList<T>,
//        checkRuleMovementListener: SelectListener<CustomAttributes>
//    ): CustomView<EditText> {
//        val customView = CustomView<EditText>()
//        //if (mainData.isNotEmpty()) {
//        //val result = lookUpViewModel.getLookUpMethod(mainData.questLookUpId.toInt(), SessionClassMain.currentLanguage)
//        if (data != null) {
//            customView.customAttributes = CustomAttributes()
//            val customTextView = CustomTextView()
//
//            customView.title = customTextView.drawLabel(true, mainData, activity = activity)
//
//            customView.typeView = customEditTextSelect(
//                activity,
//                customView.customAttributes!!,
//                object : SelectListener<CustomAttributes> {
//
//                    override fun onClick(t: CustomAttributes) {
//                        checkRuleMovementListener.onClick(t)
//                    }
//                })
//            //   }
//        }
//        return customView
//    }
    suspend fun drawEditTextOnView(
        activity: Activity,
        globalView: GlobalView,
        allAnswersFirstTable: ArrayList<SaveData>,
        inputType: Int = InputType.TYPE_CLASS_TEXT,
        maxHeight: Int = 0,
        checkRuleMovementListener: (TablesModel) -> Unit,
        drawView: suspend (GlobalView) -> Unit,
    ): GlobalView {
        val customView = drawEditText(
            globalView = globalView,
            allAnswersFirstTable = allAnswersFirstTable,
            activity = activity,
            inputType = inputType,
            maxHeight = maxHeight,
            checkRuleMovementListener = object : OtherListener {
                override fun other(tablesModel: TablesModel) {
                    checkRuleMovementListener(tablesModel)

                }
            })
        globalView.editTextView = customView
        globalView.editTextView?.title = customView?.title
        drawView(globalView)
        return globalView
    }
}