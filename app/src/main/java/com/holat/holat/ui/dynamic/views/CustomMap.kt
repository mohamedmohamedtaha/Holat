package com.holat.holat.ui.dynamic.views

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.widget.LinearLayoutCompat
import com.holat.holat.ui.dynamic.models.CustomView
import com.holat.holat.ui.dynamic.models.GlobalView
import com.holat.holat.ui.dynamic.models.SaveData
import com.holat.holat.ui.dynamic.models.TablesModel
import com.holat.holat.ui.dynamic.views.initialize.drawLabelOrTitle

class CustomMap {
    private fun customEditText(
        activity: Activity,
        tablesModel: TablesModel,
    ): EditText {
        val viewParams = LinearLayoutCompat.LayoutParams(
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT
        )
        viewParams.setMargins(5, 5, 0, viewParams.bottomMargin)

        val editText = EditText(activity)
        editText.setText(tablesModel.value)
        editText.isEnabled = false
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tablesModel.value = p0.toString().trim()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        editText.layoutParams = viewParams
        //layoutHorizontal.addView(editText)
        return editText
    }
//    private fun customEditText(
//        activity: Activity,
//        customAttributes: CustomAttributes,
//        openMap: OtherListener,
//    ): LinearLayoutCompat {
//        val layout = LinearLayoutCompat(activity)
//        val layoutParams = LinearLayoutCompat.LayoutParams(
//            LinearLayoutCompat.LayoutParams.MATCH_PARENT,
//            LinearLayoutCompat.LayoutParams.WRAP_CONTENT
//        )
//        layout.layoutParams = layoutParams
//        layout.orientation = LinearLayoutCompat.HORIZONTAL
//
//        val viewParams = LinearLayoutCompat.LayoutParams(
//            100,
//            LinearLayoutCompat.LayoutParams.WRAP_CONTENT
//        )
//        viewParams.setMargins(18, 18, 18, layoutParams.bottomMargin)
//
//        val editText1 = EditText(activity)
//        editText1.setText(customAttributes.mapValues?.latitude.toString())
//        editText1.isEnabled = false
//        editText1.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                latLng.latitude = p0.toString().trim()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//            }
//
//        })
//
//        val editText2 = EditText(activity)
//        editText2.setText(customAttributes.mapValues?.longitude.toString())
//        editText2.isEnabled = false
//        editText2.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                customAttributes.mapValues?.longitude = p0.toString().trim()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//            }
//
//        })
//
//        val button = MaterialButton(activity)
//        button.text = activity.getString(R.string.map)
//        button.setOnClickListener {
//            openMap.other(customAttributes)
//        }
//
//        editText1.layoutParams = viewParams
//        editText2.layoutParams = viewParams
//        button.layoutParams = viewParams
//        layoutParams.weight = 1.0f
//        editText1.tag = "latitude"
//        editText2.tag = "longitude"
//        layout.addView(editText1)
//        layout.addView(editText2)
//        layout.addView(button)
//        return layout
//    }

    fun drawEditText(
        allAnswersFirstTable: ArrayList<SaveData>,
        globalView: GlobalView,
        activity: Activity,
    ): GlobalView {
        allAnswersFirstTable.forEach {
            if (it.columnName.lowercase() == globalView.tablesModel.questDbColumnName.lowercase())
                globalView.tablesModel.value = it.value
        }
        globalView.editTextView = CustomView()
        globalView.editTextView?.title = activity.drawLabelOrTitle(true, globalView)
        globalView.editTextView?.typeView = customEditText(
            activity = activity,
            tablesModel = globalView.tablesModel
        )
        return globalView
    }
}