package com.holat.holat.ui.dynamic.models

class PartRuleModel(
    var pRId: String = "",
    var ruleType: Int = 1,
    var ruleCondition: ArrayList<RuleConditionModel> = arrayListOf(),
    var partId: String = "",
    var status: String = ""
)