package com.holat.holat.ui.dynamic.models

class QuestionRuleModel(
    var qRId: String = "",
    var questionId: String = "",
    var ruleType: Int = 1,
    var ruleCondition: ArrayList<RuleConditionModel> = arrayListOf(),
    var ruleMsg: String = "",
    var ruleMsgEn: String = "",
    var partId: String = "",
    var status: String = ""
)