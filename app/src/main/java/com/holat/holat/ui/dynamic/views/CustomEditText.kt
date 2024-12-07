package com.holat.holat.ui.dynamic.views

import android.app.Activity
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.widget.LinearLayoutCompat
import com.holat.holat.data.models.fields.Field
import com.holat.holat.ui.dynamic.models.CustomView
import com.holat.holat.ui.dynamic.models.GlobalView
import com.holat.holat.ui.dynamic.models.Pattern
import com.holat.holat.ui.dynamic.views.initialize.drawLabelOrTitle
import com.holat.holat.utils.listener.GlobalViewListener
import com.holat.login.utils.Constants
import com.holat.login.utils.changeLanguage
import com.yariksoffice.lingver.Lingver
import org.json.JSONObject

class CustomEditText {
    private fun customBasicEditText(
        globalView: GlobalView,
        activity: Activity,
        globalViewListener: GlobalViewListener,
        inputType: Int,
        maxHeight: Int
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
//        editText.isEnabled = globalView.tablesModel.readOnlyFlag == "0"
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
                globalViewListener.globalView(globalView)
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
        field: Field,
        globalView: GlobalView,
        activity: Activity,
        inputType: Int,
        globalViewListener: GlobalViewListener,
        maxHeight: Int
    ): CustomView<EditText>? {
        globalView.editTextView = CustomView()
        globalView.editTextView?.title = activity.drawLabelOrTitle(true, changeLanguage(field.display_name_ar,field.display_name_en))
        globalView.editTextView?.typeView = customBasicEditText(
            globalView = globalView,
            activity = activity,
            inputType = inputType,
            globalViewListener = globalViewListener,
            maxHeight = maxHeight
        )
        return globalView.editTextView
    }

    suspend fun drawEditTextOnView(
        activity: Activity,
        field: Field,
        globalView: GlobalView,
        inputType: Int = InputType.TYPE_CLASS_TEXT,
        maxHeight: Int = 0,
        globalViewListener: (GlobalView) -> Unit,
        drawView: suspend (GlobalView) -> Unit
    ): GlobalView {
        val customView = drawEditText(
            field = field,
            globalView = globalView,
            activity = activity,
            inputType = inputType,
            maxHeight = maxHeight,
            globalViewListener = object : GlobalViewListener {
                override fun globalView(globalView: GlobalView) {
                    globalViewListener(globalView)
                }
            })
        globalView.field = field
        globalView.editTextView = customView
        globalView.editTextView?.title = customView?.title
        drawView(globalView)
        return globalView
    }
}