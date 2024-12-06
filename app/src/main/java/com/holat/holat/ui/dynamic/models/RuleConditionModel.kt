package com.holat.holat.ui.dynamic.models

class RuleConditionModel(
    var question_id: String = "",
    var equation: String = "",
    var params: String = "",
    var relation: String = "",
    var value: String = "",
    var values: ArrayList<String>? = null
)