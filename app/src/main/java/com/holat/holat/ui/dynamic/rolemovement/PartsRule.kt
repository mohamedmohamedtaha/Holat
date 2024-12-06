package com.holat.holat.ui.dynamic.rolemovement

//class PartsRule {
//    fun checkPartsRule(
//        partsRule: ArrayList<PartRuleModel>,
//        lookUpViewModel: LookUpViewModel,
//        dataMembers: ArrayList<DataMembers>
//    ): ArrayList<DataMembers> {
//        partsRule.forEach { item ->
//            if (item.ruleCondition.isNotEmpty()) {
//                dataMembers.forEach { data ->
//                    data.members.forEach { members ->
//                        var questionId: String
//                        var relation = ""
//                        //Must all items inside an array true then do the condition
//                        val andArray = arrayListOf<Boolean>()
//                        outerLoop@ for (ruleConditionModel in item.ruleCondition.indices) {
//                            if (ruleConditionModel == 0)
//                                relation = item.ruleCondition[ruleConditionModel].relation
//                            try {
//                                questionId = item.ruleCondition[ruleConditionModel].question_id
//                                if (Utils.isNumberOrString(item.ruleCondition[ruleConditionModel].question_id)) {
////                                    for (section in currentSections.indices) {
////                                        if (currentSections[section].tablesModel.questId == questionId) {
////                                            item.ruleCondition[ruleConditionModel].value =
////                                                currentSections[section].customAttributes?.value
////                                                    ?: ""
////
////                                            if (relation == "" || relation == Relation.OR.type) {
////                                                val paramsMethod = params(
////                                                    currentSection,
////                                                    ruleConditionModel = item.ruleCondition[ruleConditionModel],
////                                                    lookUpViewModel = lookUpViewModel
////                                                )
////                                                if (paramsMethod)
////                                                    break@outerLoop
////
////                                            } else if (relation == Relation.AND.type) {
////                                                val element = params(
////                                                    currentSection,
////                                                    ruleConditionModel = item.ruleCondition[ruleConditionModel],
////                                                    lookUpViewModel = lookUpViewModel
////                                                )
////                                                //if (element)
////                                                andArray.add(element)
////
////                                                var checkValueInAndArray = false
////
////                                                run AndArray@{
////                                                    andArray.forEach {
////                                                        if (!it) {
////                                                            checkValueInAndArray = true
////                                                            return@AndArray
////                                                        }
////                                                    }
////                                                }
////                                                currentSection.questionsRule.value =
////                                                    !checkValueInAndArray
////                                            }
////                                        }
////                                    }
//                                } else {
//                                    run memberLoop@{
//                                        members.keys.forEach { key ->
//                                            if (key.lowercase() == questionId.lowercase()) {
//                                                item.ruleCondition[ruleConditionModel].value =
//                                                    members.getValue(key = key).toString()
//                                                return@memberLoop
//                                            }
//                                        }
//                                    }
//                                    if (relation == "" || relation == Relation.OR.type) {
//                                        val paramsMethod = params(
//                                            dataMember = data,
//                                            ruleConditionModel = item.ruleCondition[ruleConditionModel],
//                                            lookUpViewModel = lookUpViewModel
//                                        )
//                                        if (paramsMethod)
//                                            break@outerLoop
//                                    } else if (relation == Relation.AND.type) {
//                                        val getAndParams = params(
//                                            dataMember = data,
//                                            ruleConditionModel = item.ruleCondition[ruleConditionModel],
//                                            lookUpViewModel = lookUpViewModel
//                                        )
//                                        andArray.add(getAndParams)
//                                        var checkValueInAndArray = false
//                                        run AndArray@{
//                                            andArray.forEach {
//                                                if (!it) {
//                                                    checkValueInAndArray = true
//                                                    return@AndArray
//                                                }
//                                            }
//                                        }
//                                        data.questionsRule.value = !checkValueInAndArray
//                                    }
//                                }
//                            } catch (e: java.lang.NumberFormatException) {
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return dataMembers
//    }
//
//    private fun params(
//        dataMember: DataMembers,
//        ruleConditionModel: RuleConditionModel,
//        lookUpViewModel: LookUpViewModel
//    ): Boolean {
//        when (ruleConditionModel.params) {
//            Params.VALUE.type -> {
//                val putEquation = putEquation(
//                    dataMember,
//                    ruleConditionModel
//                )
//                if (putEquation)
//                    return true
//            }
//            Params.VISIBLE.type -> {
//
//            }
//            Params.CHECKED.type -> {
//
//            }
//            Params.COL1.type -> {
//                val lookupResult =
//                    lookUpViewModel.getCol1ForLookUp(
//                        ruleConditionModel.value,
//                        SessionClassMain.currentLanguage
//                    )
//                ruleConditionModel.value = lookupResult
//                val putEquation = putEquation(
//                    dataMember,
//                    ruleConditionModel
//                )
//                if (putEquation)
//                    return true
//            }
//            Params.COL_TYPE.type -> {
//                val lookupResult =
//                    lookUpViewModel.getColTypeForLookUp(
//                        ruleConditionModel.value,
//                        SessionClassMain.currentLanguage
//                    )
//                ruleConditionModel.value = lookupResult
//                val putEquation = putEquation(
//                    dataMember,
//                    ruleConditionModel
//                )
//                if (putEquation)
//                    return true
//            }
//            Params.CODE.type -> {
//                val lookupResult =
//                    lookUpViewModel.getCodeForLookUp(
//                        ruleConditionModel.value,
//                        SessionClassMain.currentLanguage
//                    )
//                ruleConditionModel.value = lookupResult
//                val putEquation = putEquation(
//                    dataMember,
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
//        dataMember: DataMembers,
//        ruleConditionModel: RuleConditionModel
//    ): Boolean {
//        dataMember.questionsRule.value = false
//        when (ruleConditionModel.equation) {
//            Equation.EQUAL.type, Equation.IN.type -> {
//                if (ruleConditionModel.values != null)
//                    for (ruleCondition in ruleConditionModel.values!!.indices) {
//                        if (ruleConditionModel.values!![ruleCondition] == ruleConditionModel.value) {
//                            dataMember.questionsRule.value = true
//                            return true
//                        }
//                    }
//            }
//            Equation.NOT_EQUAL.type, Equation.NOT_IN.type -> {
//                if (ruleConditionModel.values != null)
//                    for (ruleCondition in ruleConditionModel.values!!.indices) {
//                        if (ruleConditionModel.values!![ruleCondition] != ruleConditionModel.value) {
//                            dataMember.questionsRule.value = true
//                            return true
//                        }
//                    }
//            }
//            Equation.GREATER_THAN.type -> {
//                if (ruleConditionModel.values != null && ruleConditionModel.value != "" && ruleConditionModel.value != ".")
//                    for (ruleCondition in ruleConditionModel.values!!.indices) {
//                        if (ruleConditionModel.value.toDouble() > ruleConditionModel.values!![ruleCondition].toDouble()) {
//                            dataMember.questionsRule.value = true
//                            return true
//                        }
//                    }
//            }
//            Equation.LESS_THAN.type -> {
//                if (ruleConditionModel.values != null && ruleConditionModel.value != "" && ruleConditionModel.value != ".")
//                    for (ruleCondition in ruleConditionModel.values!!.indices) {
//                        if (ruleConditionModel.value.toDouble() < ruleConditionModel.values!![ruleCondition].toDouble()) {
//                            dataMember.questionsRule.value = true
//                            return true
//                        }
//                    }
//            }
//            Equation.IS_NULL.type, Equation.EMPTY.type -> {
//            }
//            Equation.IS_NOT_NULL.type, Equation.NOT_EMPTY.type -> {
//                if (ruleConditionModel.value != "" && ruleConditionModel.value != ".")
//                    dataMember.questionsRule.value = true
//                return true
//            }
//            Equation.START_WITH.type -> {
//            }
//        }
//        return false
//    }
//
////    fun checkSectionsRule(
////        partsRule: ArrayList<PartRuleModel>,
////        dataMembers: ArrayList<DataMembers>
////    ): ArrayList<DataMembers> {
////        partsRule.forEach { item ->
////            if (item.ruleCondition.isNotEmpty()) {
////                dataMembers.forEach { data ->
////                    data.members.forEach { members ->
////                        var questionId: String
////                        var relation = ""
////                        //Must all items inside an array true then do the condition
////                        val andArray = arrayListOf<Boolean>()
////                        outerLoop@ for (ruleConditionModel in item.ruleCondition.indices) {
////                            if (ruleConditionModel == 0)
////                                relation = item.ruleCondition[ruleConditionModel].relation
////                            try {
////                                questionId = item.ruleCondition[ruleConditionModel].question_id
////                                if (Utils.isNumberOrString(item.ruleCondition[ruleConditionModel].question_id)) {
////                                } else {
////                                    run memberLoop@{
////                                        members.keys.forEach { key ->
////                                            if (key.lowercase() == questionId.lowercase()) {
////                                                item.ruleCondition[ruleConditionModel].value =
////                                                    members.getValue(key = key).toString()
////                                                return@memberLoop
////                                            }
////                                        }
////                                    }
////                                    if (relation == "" || relation == Relation.OR.type) {
////                                        val paramsMethod = params(
////                                            dataMember = data,
////                                            ruleConditionModel = item.ruleCondition[ruleConditionModel],
////                                            lookUpViewModel = lookUpViewModel
////                                        )
////                                        if (paramsMethod)
////                                            break@outerLoop
////                                    } else if (relation == Relation.AND.type) {
////                                        val getAndParams = params(
////                                            dataMember = data,
////                                            ruleConditionModel = item.ruleCondition[ruleConditionModel],
////                                            lookUpViewModel = lookUpViewModel
////                                        )
////                                        andArray.add(getAndParams)
////                                        var checkValueInAndArray = false
////                                        run AndArray@{
////                                            andArray.forEach {
////                                                if (!it) {
////                                                    checkValueInAndArray = true
////                                                    return@AndArray
////                                                }
////                                            }
////                                        }
////                                        data.questionsRule.value = !checkValueInAndArray
////                                    }
////                                }
////                            } catch (e: java.lang.NumberFormatException) {
////                            }
////                        }
////                    }
////                }
////            }
////        }
////        return dataMembers
////    }
//
////    fun checkSectionsRule(
////        allSections: ArrayList<Section>,
////        dataMembers: ArrayList<DataMembers>): ArrayList<DataMembers> {
////        allSections.forEach { item ->
////            if (item.partNo.isNotEmpty()) {
////                dataMembers.forEach { data ->
////                    data.members.forEach { members ->
////                        //Must all items inside an array true then do the condition
////                        val andArray = arrayListOf<Boolean>()
////                        outerLoop@ for (ruleConditionModel in item.partNo) {
////                            try {
////                                    run memberLoop@{
////                                        members.keys.forEach { key ->
////                                            if (key.lowercase() == "age".lowercase()) {
////                                                item.value = members.getValue(key = key).toString()
////                                                return@memberLoop
////                                            }
////                                        }
////                                    }
////                                        val paramsMethod = paramsSections(
////                                            dataMember = data,
////                                            allSections = item)
////                                        if (paramsMethod)
////                                            break@outerLoop
////
////                            } catch (e: java.lang.NumberFormatException) {
////                            }
////                        }
////                    }
////                }
////            }
////       }
////        return dataMembers
////    }
//
//    private fun paramsSections(
//        dataMember: DataMembers,
//        allSections: Section,
//    ): Boolean {
//                val putEquation = putEquationSection(
//                    dataMember,
//                    allSections
//                )
//        return putEquation
//    }
//
//    private fun putEquationSection(
//        dataMember: DataMembers,
//        allSections: Section
//    ): Boolean {
//        dataMember.questionsRule.value = false
////        if (allSections.value.toDouble() > ruleConditionModel.values!![ruleCondition].toDouble() &&
////            allSections.value.toDouble() < ruleConditionModel.values!![ruleCondition].toDouble()) {
////            dataMember.questionsRule.value = true
////            return true
////        }
//        return false
//    }
//}