package com.holat.holat.ui.dynamic.rolemovement

//class CheckRoleMovement {
//
//    fun checkRuleMovement(
//        ruleMovement: ArrayList<QuestionRuleModel>,
//        globalAllQuestions: ArrayList<GlobalView>,
//        lookUpViewModel: LookUpViewModel,
//        valuesDependedOnColumnName: ArrayList<SaveData>,
//    ): ArrayList<GlobalView> {
//        ruleMovement.forEach { item ->
//            if (item.ruleCondition.isNotEmpty()) {
//                globalAllQuestions.forEach { globalQuestions ->
//                    if (globalQuestions.tablesModel.questId.isNotEmpty() && globalQuestions.tablesModel.questId != "") {
//                        if (globalQuestions.tablesModel.questId == item.questionId) {
//                            var questionId: String
//                            var relation = ""
//                            //Must all items inside an array true then do the condition
//                            val andArray = arrayListOf<Boolean>()
//                            outerLoop@ for (ruleConditionModel in item.ruleCondition.indices) {
//                                if (ruleConditionModel == 0)
//                                    relation = item.ruleCondition[ruleConditionModel].relation
//                                try {
//                                    questionId = item.ruleCondition[ruleConditionModel].question_id
//                                    if (Utils.isNumberOrString(item.ruleCondition[ruleConditionModel].question_id)) {
//                                        for (globalAllQuestion in globalAllQuestions.indices) {
//                                            if (globalAllQuestions[globalAllQuestion].tablesModel.questId == questionId) {
//                                                item.ruleCondition[ruleConditionModel].value =
//                                                    globalAllQuestions[globalAllQuestion].customAttributes?.value
//                                                        ?: ""
//
//                                                if (relation == "" || relation == Relation.OR.type) {
//                                                    val paramsMethod = params(
//                                                        globalQuestions,
//                                                        ruleConditionModel = item.ruleCondition[ruleConditionModel],
//                                                        lookUpViewModel = lookUpViewModel
//                                                    )
//                                                    if (paramsMethod)
//                                                        break@outerLoop
//
//                                                } else if (relation == Relation.AND.type) {
//                                                    val element = params(
//                                                        globalQuestions,
//                                                        ruleConditionModel = item.ruleCondition[ruleConditionModel],
//                                                        lookUpViewModel = lookUpViewModel
//                                                    )
//                                                    //if (element)
//                                                    andArray.add(element)
//
//                                                    var checkValueInAndArray = false
//
//                                                    run AndArray@{
//                                                        andArray.forEach {
//                                                            if (!it) {
//                                                                checkValueInAndArray = true
//                                                                return@AndArray
//                                                            }
//                                                        }
//                                                    }
//                                                    globalQuestions.questionsRule.value =
//                                                        !checkValueInAndArray
//                                                    globalQuestions.questionsRule.isThreeRule =if(checkValueInAndArray)  1 else  2
//
//                                                }
//                                            }
//                                        }
//                                    } else {
//                                        run valueLoop@{
//                                            valuesDependedOnColumnName.forEach {
//                                                if (it.columnName.lowercase() == questionId.lowercase()) {
//                                                    item.ruleCondition[ruleConditionModel].value =
//                                                        it.value
//                                                    return@valueLoop
//                                                }
//
//                                            }
//                                        }
//                                        if (relation == "" || relation == Relation.OR.type) {
//                                            val paramsMethod = params(
//                                                globalQuestions,
//                                                ruleConditionModel = item.ruleCondition[ruleConditionModel],
//                                                lookUpViewModel = lookUpViewModel
//                                            )
//                                            if (paramsMethod)
//                                                break@outerLoop
//                                        } else if (relation == Relation.AND.type) {
//                                            val getAndParams = params(
//                                                globalQuestions,
//                                                ruleConditionModel = item.ruleCondition[ruleConditionModel],
//                                                lookUpViewModel = lookUpViewModel
//                                            )
//                                            // if (getAndParams)
//                                            andArray.add(getAndParams)
//                                            var checkValueInAndArray = false
//                                            run AndArray@{
//                                                andArray.forEach {
//                                                    if (!it) {
//                                                        checkValueInAndArray = true
//                                                        return@AndArray
//                                                    }
//
//                                                }
//                                            }
//                                            globalQuestions.questionsRule.value =
//                                                !checkValueInAndArray
//                                            globalQuestions.questionsRule.isThreeRule =  if(checkValueInAndArray)  1 else  2
//
//                                        }
//
//                                    }
//                                } catch (e: java.lang.NumberFormatException) {
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return applyRoleMovementInLayout(globalAllQuestions)
//    }
//
//    private fun params(
//        globalQuestions: GlobalView,
//        ruleConditionModel: RuleConditionModel,
//        lookUpViewModel: LookUpViewModel,
//    ): Boolean {
//
//        when (ruleConditionModel.params) {
//            Params.VALUE.type -> {
//                val putEquation = putEquation(
//                    globalQuestions,
//                    ruleConditionModel
//                )
//                if (putEquation)
//                    return true
//            }
//
//            Params.VISIBLE.type -> {
//
//            }
//
//            Params.CHECKED.type -> {
//
//            }
//
//            Params.COL1.type -> {
//                val lookupResult =
//                    lookUpViewModel.getCol1ForLookUp(
//                        ruleConditionModel.value,
//                        SessionClassMain.currentLanguage
//                    )
//                ruleConditionModel.value = lookupResult
//                val putEquation = putEquation(
//                    globalQuestions,
//                    ruleConditionModel
//                )
//                if (putEquation)
//                    return true
//            }
//
//            Params.COL_TYPE.type -> {
//                val lookupResult =
//                    lookUpViewModel.getColTypeForLookUp(
//                        ruleConditionModel.value,
//                        SessionClassMain.currentLanguage
//                    )
//                ruleConditionModel.value = lookupResult
//                val putEquation = putEquation(
//                    globalQuestions,
//                    ruleConditionModel
//                )
//                if (putEquation)
//                    return true
//            }
//
//            Params.CODE.type -> {
//                val lookupResult =
//                    lookUpViewModel.getCodeForLookUp(
//                        ruleConditionModel.value,
//                        SessionClassMain.currentLanguage
//                    )
//                ruleConditionModel.value = lookupResult
//                val putEquation = putEquation(
//                    globalQuestions,
//                    ruleConditionModel
//                )
//                if (putEquation)
//                    return true
//            }
//        }
//        return false
//    }
//
//    private fun putEquation(
//        globalQuestions: GlobalView,
//        ruleConditionModel: RuleConditionModel,
//    ): Boolean {
//        globalQuestions.questionsRule.value = false
//        globalQuestions.questionsRule.isThreeRule = 1
//        when (ruleConditionModel.equation) {
//            Equation.EQUAL.type, Equation.IN.type -> {
//                if (ruleConditionModel.values != null)
//                    for (ruleCondition in ruleConditionModel.values!!.indices) {
//                        if (ruleConditionModel.values!![ruleCondition] == ruleConditionModel.value) {
//                            globalQuestions.questionsRule.value = true
//                            globalQuestions.questionsRule.isThreeRule = 2
//                            return true
//                        }
//                    }
//            }
//
//            Equation.NOT_EQUAL.type, Equation.NOT_IN.type -> {
//                if (ruleConditionModel.values != null)
//                    for (ruleCondition in ruleConditionModel.values!!.indices) {
//                        if (ruleConditionModel.values!![ruleCondition] != ruleConditionModel.value) {
//                            globalQuestions.questionsRule.value = true
//                            globalQuestions.questionsRule.isThreeRule = 2
//                            return true
//                        }
//                    }
//            }
//
//            Equation.GREATER_THAN.type -> {
//                if (ruleConditionModel.values != null && ruleConditionModel.value != "" && ruleConditionModel.value != ".")
//                    for (ruleCondition in ruleConditionModel.values!!.indices) {
//                        if (ruleConditionModel.value.toDouble() > ruleConditionModel.values!![ruleCondition].toDouble()) {
//                            globalQuestions.questionsRule.value = true
//                            globalQuestions.questionsRule.isThreeRule = 2
//                            return true
//                        }
//                    }
//            }
//
//            Equation.LESS_THAN.type -> {
//                if (ruleConditionModel.values != null && ruleConditionModel.value != "" && ruleConditionModel.value != ".")
//                    for (ruleCondition in ruleConditionModel.values!!.indices) {
//                        if (ruleConditionModel.value.toDouble() < ruleConditionModel.values!![ruleCondition].toDouble()) {
//                            globalQuestions.questionsRule.value = true
//                            globalQuestions.questionsRule.isThreeRule = 2
//                            return true
//                        }
//                    }
//            }
//
//            Equation.IS_NULL.type, Equation.EMPTY.type -> {
//            }
//
//            Equation.IS_NOT_NULL.type, Equation.NOT_EMPTY.type -> {
//                if (ruleConditionModel.value != "" && ruleConditionModel.value != ".")
//                    globalQuestions.questionsRule.value = true
//                globalQuestions.questionsRule.isThreeRule = 2
//                return true
//            }
//
//            Equation.START_WITH.type -> {
//            }
//        }
//        return false
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    private fun applyRoleMovementInLayout(globalAllQuestions: ArrayList<GlobalView>): ArrayList<GlobalView> {
//        globalAllQuestions.forEach { questId ->
//            globalAllQuestions.forEach { questRefId ->
//                if (questId.tablesModel.questId == questRefId.tablesModel.questRefId) {
//                    //If there rule in this situation we did not need gone the question depended on the ref id
//                    when (questRefId.questionsRule.isThreeRule) {
//                        0 -> {
//                            questRefId.questionsRule.value = questId.questionsRule.value
//                        }
//                        1 -> {
//                         //   questRefId.questionsRule.value = questId.questionsRule.value
//                        }
//                        2 -> {
//                          //  questRefId.questionsRule.value = questId.questionsRule.value
//                        }
//                    }
//                }
//            }
//        }
//        globalAllQuestions.forEach { question ->
//            if (question.questionsRule.ruleType == 1) {
//                if (question.questionsRule.value) {
//                    question.label?.typeView?.visibility = View.VISIBLE
//                    question.editTextView?.typeView?.visibility = View.VISIBLE
//                    question.title?.visibility = View.VISIBLE
//                    //If table
//                    question.recyclerView?.typeView?.visibility = View.VISIBLE
//                    //If radio Group
//                    question.radioGroupView?.typeView?.visibility = View.VISIBLE
//                    //If Button
//                    question.buttonView?.typeView?.visibility = View.VISIBLE
//
//                } else {
//                    question.label?.typeView?.visibility = View.GONE
//                    question.editTextView?.typeView?.visibility = View.GONE
//                    question.title?.visibility = View.GONE
//                    question.editTextView?.typeView?.text?.clear()
//                    question.editTextView?.customAttributes?.value = ""
//                    question.recyclerView?.typeView?.visibility = View.GONE
//                    question.buttonView?.typeView?.visibility =
//                        View.GONE                       //question.title?.visibility = View.GONE
//                    //If table
//                    question.recyclerView?.customAttributes?.adapterValues?.forEach {
//                        it.value1 = ""
//                        it.value2 = ""
//                        it.value3 = ""
//                        it.lookupListTxt = ""
//                    }
//                    //  question.recyclerView?.typeView?.adapter?.notifyDataSetChanged()
//                    //If Radio Group
//                    question.radioGroupView?.typeView?.visibility = View.GONE
//                    if (question.radioGroupView?.typeView?.checkedRadioButtonId != -1)
//                        question.radioGroupView?.typeView?.clearCheck()
//                    question.radioGroupView?.customAttributes?.value = ""
//                }
//            } else if (question.questionsRule.ruleType == 2) {
//                if (question.questionsRule.value) {
//                    question.editTextView?.typeView?.isEnabled = true
//                    question.title?.visibility = View.VISIBLE
//                } else {
//                    question.editTextView?.typeView?.isEnabled = false
//                    question.title?.visibility = View.VISIBLE
//                }
//            }
//        }
//        return globalAllQuestions
//    }
//
//    fun checkEmptyValues(
//        globalAllQuestions: ArrayList<GlobalView>,
//        requiredValuesForTables: ArrayList<TablesModel>,
//    ): Boolean {
//        // For check all values
//        globalAllQuestions.forEach {
//            // For check all values except tables
//            if (it.questionsRule.value && it.recyclerView == null && it.tablesModel.questUiType != ViewType.TABLE.type && it.tablesModel.questUiType != ViewType.LABEL.type && it.tablesModel.questUiType != ViewType.MAP.type)
//                if (it.customAttributes?.value == "") return false
//
//            // For check all values inside tables
//            if (it.questionsRule.value && it.recyclerView != null)
//                if (it.customAttributes?.adapterValues != null) {
//                    var column1 = ""
//                    var column2 = ""
//                    var column3 = ""
//                    it.customAttributes?.adapterValues?.forEachIndexed { index, multiCheckModel ->
//                        if (index == 0) {
//                            multiCheckModel.columnNameOriginal?.forEachIndexed { indexColumnName, columnName ->
//                                if (indexColumnName == 0)
//                                    column1 = columnName
//                                if (indexColumnName == 1)
//                                    column2 = columnName
//                                if (indexColumnName == 2)
//                                    column3 = columnName
//                            }
//                        }
//                        val putValueWithRequiredStat = ArrayList<HashModel>()
//                        //For check values in table required or not required
//                        requiredValuesForTables.forEach { item ->
//                            val model = HashModel()
//                            if (column1.uppercase() == item.questDbColumnName.uppercase() && item.requiredFlag == "1") {
//                                model.key = item.requiredFlag
//                                model.value = multiCheckModel.value1
//                                model.visible = multiCheckModel.visible1
//                                putValueWithRequiredStat.add(model)
//                            } else if (column2.uppercase() == item.questDbColumnName.uppercase() && item.requiredFlag == "1") {
//                                model.key = item.requiredFlag
//                                model.value = multiCheckModel.value2
//                                model.visible = multiCheckModel.visible2
//                                putValueWithRequiredStat.add(model)
//                            } else if (column3.uppercase() == item.questDbColumnName.uppercase() && item.requiredFlag == "1") {
//                                model.key = item.requiredFlag
//                                model.value = multiCheckModel.value3
//                                model.visible = multiCheckModel.visible3
//                                putValueWithRequiredStat.add(model)
//                            }
//                        }
//                        run breakLoopAndNotSave@{
//                            putValueWithRequiredStat.forEach { hashModel ->
//                                if (hashModel.key == "1" && hashModel.value == "" && hashModel.visible) return false
//                            }
//                        }
//                    }
//                }
//        }
//        return true
//    }
//
//    fun checkEmptyValuesWithText(
//        globalAllQuestions: ArrayList<GlobalView>,
//        requiredValuesForTables: ArrayList<TablesModel>,
//    ): HashMap<Boolean,String> {
//        val hashMap = HashMap<Boolean,String>()
//        // For check all values
//        globalAllQuestions.forEach {
//            // For check all values except tables
//            if (it.tablesModel.requiredFlag == "1" && it.questionsRule.value && it.recyclerView == null && it.tablesModel.questUiType != ViewType.TABLE.type && it.tablesModel.questUiType != ViewType.LABEL.type && it.tablesModel.questUiType != ViewType.MAP.type)
//                if (it.customAttributes?.value == ""){
//                    hashMap[false] = it.tablesModel.questName
//                    return hashMap
//                }
//
//            // For check all values inside tables
//            if (it.questionsRule.value && it.recyclerView != null)
//                if (it.customAttributes?.adapterValues != null) {
//                    var column1 = ""
//                    var column2 = ""
//                    var column3 = ""
//                    it.customAttributes?.adapterValues?.forEachIndexed { index, multiCheckModel ->
//                        if (index == 0) {
//                            multiCheckModel.columnNameOriginal?.forEachIndexed { indexColumnName, columnName ->
//                                if (indexColumnName == 0)
//                                    column1 = columnName
//                                if (indexColumnName == 1)
//                                    column2 = columnName
//                                if (indexColumnName == 2)
//                                    column3 = columnName
//                            }
//                        }
//                        val putValueWithRequiredStat = ArrayList<HashModel>()
//                        //For check values in table required or not required
//                        requiredValuesForTables.forEach { item ->
//                            val model = HashModel()
//                            if (column1.uppercase() == item.questDbColumnName.uppercase() && item.requiredFlag == "1") {
//                                model.key = item.requiredFlag
//                                model.value = multiCheckModel.value1
//                                model.visible = multiCheckModel.visible1
//                                putValueWithRequiredStat.add(model)
//                            } else if (column2.uppercase() == item.questDbColumnName.uppercase() && item.requiredFlag == "1") {
//                                model.key = item.requiredFlag
//                                model.value = multiCheckModel.value2
//                                model.visible = multiCheckModel.visible2
//                                putValueWithRequiredStat.add(model)
//                            } else if (column3.uppercase() == item.questDbColumnName.uppercase() && item.requiredFlag == "1") {
//                                model.key = item.requiredFlag
//                                model.value = multiCheckModel.value3
//                                model.visible = multiCheckModel.visible3
//                                putValueWithRequiredStat.add(model)
//                            }
//                        }
//                        run breakLoopAndNotSave@{
//                            putValueWithRequiredStat.forEach { hashModel ->
//                                if (hashModel.key == "1" && hashModel.value == "" && hashModel.visible){
//                                    hashMap[false] = it.tablesModel.questName
//                                    return hashMap
//                                }
//                            }
//                        }
//                    }
//                }
//        }
//        hashMap[true] =""
//        return hashMap
//    }
//
//    fun checkMinMaxValues(globalAllQuestions: ArrayList<GlobalView>): HashMap<Boolean, String> {
//        // For check all values
//        val hashMap = HashMap<Boolean, String>()
//        var minValue: Int
//        var maxValue: Int
//        var minLength: Int
//        var maxLength: Int
//        globalAllQuestions.forEach {
//            // For check min and max values except tables
//            if (it.tablesModel.minValue.isNotEmpty()) {
//                minValue = it.tablesModel.minValue.toInt()
//                if (!Utility.stringMatch(it.customAttributes?.value, ""))
//                    if (minValue > it.customAttributes?.value!!.toInt()) {
//                        hashMap[false] = it.tablesModel.questCode
//                        return hashMap
//                    }
//            }
//            if (it.tablesModel.maxValue.isNotEmpty()) {
//                maxValue = it.tablesModel.maxValue.toInt()
//                if (!Utility.stringMatch(it.customAttributes?.value, ""))
//                    if (maxValue < it.customAttributes?.value!!.toInt()) {
//                        hashMap[false] = it.tablesModel.questCode
//                        return hashMap
//                    }
//            }
////            if (it.tablesModel.minLength.isNotEmpty()) {
////                minLength = it.tablesModel.minLength
////                if (!Utility.stringMatch(it.customAttributes?.value, ""))
////                    if (minLength.length > it.customAttributes?.value!!.length) {
////                        hashMap[false] = it.tablesModel.questCode
////                        return hashMap
////                    }
////            }
////            if (it.tablesModel.maxLength.isNotEmpty()) {
////                maxLength = it.tablesModel.maxLength
////                if (!Utility.stringMatch(it.customAttributes?.value, ""))
////                    if (maxLength.length < it.customAttributes?.value!!.length) {
////                        hashMap[false] = it.tablesModel.questCode
////                        return hashMap
////                    }
////            }
//            if (it.tablesModel.minLength.isNotEmpty()) {
//                minLength = it.tablesModel.minLength.toInt()
//                if (!Utility.stringMatch(it.customAttributes?.value, ""))
//                    if (minLength > it.customAttributes?.value!!.length) {
//                        hashMap[false] = it.tablesModel.questCode
//                        return hashMap
//                    }
//            }
//            if (it.tablesModel.maxLength.isNotEmpty()) {
//                maxLength = it.tablesModel.maxLength.toInt()
//                if (!Utility.stringMatch(it.customAttributes?.value, ""))
//                    if (maxLength < it.customAttributes?.value!!.length) {
//                        hashMap[false] = it.tablesModel.questCode
//                        return hashMap
//                    }
//            }
//
//
//        }
//        hashMap[true] = ""
//        return hashMap
//    }
//
//}