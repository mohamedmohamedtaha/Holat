package com.holat.holat.ui.dynamic.models

import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.holat.holat.data.models.fields.Field

class GlobalView {
    var editTextView: CustomView<EditText>? = null
    var checkBox: CustomView<CheckBox>? = null
    var recyclerView: CustomView<RecyclerView>? = null
    var spinnerView: CustomView<Spinner>? = null
    var radioGroupView: CustomView<RadioGroup>? = null
    var buttonView: CustomView<Button>? = null
    var label: CustomView<TextView>? = null

    //    var title: TextView? = null
//    var customAttributes: CustomAttributes? = null
    var tablesModel = TablesModel()

    //var questionsRule: QuestionsRule?= null
    var questionsRule = QuestionsRule()
    var field: Field? = null
}