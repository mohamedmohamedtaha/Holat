package com.holat.holat.ui.dynamic.models

import android.widget.*
import androidx.recyclerview.widget.RecyclerView

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
    var questionsRule =  QuestionsRule()

}