package com.holat.holat.ui.dynamic.fragments

//@AndroidEntryPoint
//class Section1Fragment : BaseFragment() {
//    private lateinit var binding: FragmentSectionBinding
//    private val errorRolesVieWModel by viewModels<ErrorRolesVieWModel>()
//    private val dynamicViewModel by viewModels<DynamicViewModel>()
//    private val lookUpViewModel by viewModels<LookUpViewModel>()
//    private val placesViewModel by viewModels<PlacesViewModel>()
//    private val questionsRuleViewModel by viewModels<QuestionsRuleViewModel>()
//    private val updateMemberAndSectionVieWModel by viewModels<UpdateMemberAndSectionVieWModel>()
//    private val customEditText = CustomEditText()
//    private val customButton = CustomButton()
//    private val partRuleViewModel: PartRuleViewModel by viewModels()
//
//    private val customTextView = CustomTextView()
//    private val customRadioButton = CustomRadioButton()
//
//    //  private val customCheckBox = CustomCheckBox()
////  private var customSpinner: CustomSpinner? = null
//    private var globalAllQuestions = ArrayList<GlobalView>()
//    private var allAnswersFirstTable = ArrayList<SaveData>()
//    private var valuesDependedOnColumnName = ArrayList<SaveData>()
//    private var valueChange = false
//    private var valueChangeToColumn = false
//    private var num = ""
//    private var numInt = 0
//    private var enableDisableRowInsideTable = ""
//    private val numberOfQueryMember = ArrayList<GsonDataItem>()
//    private val numberOfQueryMultiData = ArrayList<GsonDataItem>()
//    private var requiredValuesForTables = ArrayList<TablesModel>()
//    private val checkRoleMovement = CheckRoleMovement()
//    private var valueQ308 = ""
//    private var sectionAfterCurrentSection = ArrayList<StatusModel>()
//    override fun getBinding(): ViewBinding {
//        binding = FragmentSectionBinding.inflate(layoutInflater)
//        return binding
//    }
//
//    private fun getSectionsAfterCurrentSection() {
//        partRuleViewModel.getSectionsAfterCurrentSection(
//            partIds = currentSection.partId,
//            sampleId = SessionClass.currentFamily.id
//        )
//        partRuleViewModel.getSectionsAfterCurrentSectionLiveData.observe(viewLifecycleOwner) {
//            if (it.isNotEmpty()) {
//                sectionAfterCurrentSection = it
//            }
//        }
//    }
//
//    private fun checkRuleMovement() {
//        questionsRuleViewModel.getQuestionsRuleByPartId(partId = currentSection.partId)
//        questionsRuleViewModel.questionsRuleLiveData.observe(viewLifecycleOwner) { data ->
//            if (data != null)
//                globalAllQuestions = checkRoleMovement.checkRuleMovement(
//                    ruleMovement = data,
//                    globalAllQuestions = globalAllQuestions,
//                    lookUpViewModel = lookUpViewModel,
//                    valuesDependedOnColumnName = valuesDependedOnColumnName
//                )
//        }
//    }
//
//    override fun getData() {
//        toolbar.showSync(false)
//        toolbar.showAddMember(false)
//           if (currentLanguage == "1")
//               toolbar.changeTextTitle(Utility.getStringValue(currentSection.partDescAr + ""))
//           else
//               toolbar.changeTextTitle(Utility.getStringValue(currentSection.partDescEn + ""))
//
//           //This call only first time if data not there
//           if (questionsRuleViewModel.getValueByColumnNameLiveData.value == null)
//               if (currentSection.partLevel == "1")
//                   questionsRuleViewModel.getValueByColumnName(currentHeadFmId)
//               else if (currentSection.partLevel == "2")
//                   if (currentMember != null)
//                       questionsRuleViewModel.getValueByColumnName(currentMember.id)
//
//           questionsRuleViewModel.getValueByColumnNameLiveData.observe(viewLifecycleOwner) {
//               if (it != null) {
//                   valuesDependedOnColumnName = it
//                   run valueLoop@{
//                       valuesDependedOnColumnName.forEach { age ->
//                           if (age.columnName.lowercase() == "age") {
//                               enableDisableRowInsideTable = age.value
//                               return@valueLoop
//                           }
//                       }
//                   }
//               }
//           }
//           getSectionsAfterCurrentSection()
//           //This call only first time if data not there
//           if (dynamicViewModel.getData.value == null)
//               dynamicViewModel.getData(currentSection.partId, currentLanguage)
//           dynamicViewModel.getData.observe(viewLifecycleOwner) {
//               it?.let {
//                   getBindingDynamic(it)
//               }
//           }
//           binding.buttonInclude.btnNext.setOnClickListener {
//               val checkEmptyValues = checkRoleMovement.checkEmptyValuesWithText(globalAllQuestions = globalAllQuestions,
//                   requiredValuesForTables = requiredValuesForTables)
//               if (checkEmptyValues.keys.first()) {
////               if (checkRoleMovement.checkEmptyValues(
////                       globalAllQuestions = globalAllQuestions,
////                       requiredValuesForTables = requiredValuesForTables
////                   )
////               ) {
//                   globalAllQuestions.forEach {
//                       if (it.tablesModel.questName == "اخرى توضح" && Utils.isNumberOrString(it.customAttributes?.value.toString())) {
//                           binding.root.showSnackBar(getString(R.string.not_number_only_error))
//                           return@setOnClickListener
//                       }
//                   }
//                   val hashMap =
//                       checkRoleMovement.checkMinMaxValues(globalAllQuestions = globalAllQuestions)
//                   if (hashMap.keys.first()) {
//                       saveData()
//                       runErrorsRoles(currentSection.partNo.toInt())
//                   } else {
//                       binding.root.showSnackBar(getString(R.string.data_not_arrange) + hashMap.values.first())
//                   }
//               } else
//                   binding.root.showSnackBar(getString(R.string.enter_data_required) + checkEmptyValues.values.first())
//           }
//           binding.buttonInclude.btnPrevious.setOnClickListener {
//               saveData()
//               if (currentSection.partLevel == "1") {
//                   if (!updateMemberAndSectionVieWModel.updateSectionStatus(
//                           status = SectionStatusEnum.UnComplete.numericType
//                       )
//                   ) {
//                       binding.root.showSnackBar(getString(R.string.err_in_local_db))
//                   }
//                   // updateSectionStatus(SectionStatusEnum.UnComplete.numericType)
//               } else if (currentSection.partLevel == "2") {
//                   if (!updateMemberAndSectionVieWModel.updateMemberStatus(
//                           status = SectionStatusEnum.UnComplete.numericType
//                       )
//                   ) {
//                       binding.root.showSnackBar(getString(R.string.err_in_local_db))
//                   }
//                   //  updateMemberStatus(SectionStatusEnum.UnComplete.numericType)
//               }
//               findNavController().safeNavigateUp()
//           }
//    }
//
//    override fun onClick() {
//
//    }
//
//    private fun getBindingDynamic(tablesModel: ArrayList<TablesModel>) {
//        getDataForTablesFullDynamic()
//        createViews(tablesModel)
//    }
//
//    private fun getDataForTablesFullDynamic(
//    ) {
//        if (currentSection.partProperty != null) {
//            currentSection.partProperty?.forEach {
//                val gsonDataFirstTable = GsonDataItem()
//                gsonDataFirstTable.tableName = it.form_properties.table_name
//
//                gsonDataFirstTable.columnNameOriginal = it.form_properties.columns
//                gsonDataFirstTable.columnName += it.form_properties.columns
//                // Delete first and last character
//                val firstAndLastCharacter = gsonDataFirstTable.columnName.substring(
//                    1,
//                    gsonDataFirstTable.columnName.length - 1
//                )
//                gsonDataFirstTable.columnName = firstAndLastCharacter
//                //Check questionId empty or not that specify Is that table master or detail
//                if (it.form_properties.question_id != null && it.form_properties.question_id != "") {
//                    gsonDataFirstTable.questionId = it.form_properties.question_id
//                    if (currentSection.partLevel == "1") {
//                        gsonDataFirstTable.whereCondition = it.form_properties.condition.replace(
//                            P_F_M_ID,
//                            "''"
//                        ).replace(QUESTION_ID, "'${it.form_properties.question_id}'").replace(
//                            SAMPLE_ID,
//                            "'${SessionClass.currentFamily.id}'"
//                        )
//                    } else if (currentSection.partLevel == "2") {
//                        gsonDataFirstTable.whereCondition = it.form_properties.condition.replace(
//                            P_F_M_ID,
//                            "'${Utility.getStringValue(currentMember.id)}'"
//                        ).replace(QUESTION_ID, "'${it.form_properties.question_id}'").replace(
//                            SAMPLE_ID,
//                            "'${SessionClass.currentFamily.id}'"
//                        )
//                    }
//                } else
//                    if (currentSection.partLevel == "1") {
//                        gsonDataFirstTable.whereCondition = it.form_properties.condition.replace(
//                            P_F_M_ID,
//                            "''"
//                        ).replace(
//                            SAMPLE_ID,
//                            "'${SessionClass.currentFamily.id}'"
//                        )
//                    } else if (currentSection.partLevel == "2") {
//                        gsonDataFirstTable.whereCondition = it.form_properties.condition.replace(
//                            P_F_M_ID,
//                            "'${Utility.getStringValue(currentMember.id)}'"
//                        ).replace(
//                            SAMPLE_ID,
//                            "'${SessionClass.currentFamily.id}'"
//                        )
//                    }
//                if (it.form_properties.table_name.uppercase() == WHS_FAMILY_MEMBER_TAB || it.form_properties.table_name.uppercase() == WHS_FAMILY_TAB)
//                    numberOfQueryMember.add(gsonDataFirstTable)
//                else if (it.form_properties.table_name.uppercase() == WHS_FAMILY_MULTI_DATA_TAB) {
//                    numberOfQueryMultiData.add(gsonDataFirstTable)
//                }
//            }
//            numberOfQueryMember.forEach {
//                dynamicViewModel.getDataPart1(gsonDataItem = it)
//            }
//            dynamicViewModel.getDataPart1LiveData.observe(viewLifecycleOwner) {
//                if (it != null) {
//                    allAnswersFirstTable = it
//                }
//            }
//        } else {
//            binding.root.showSnackBar(getString(R.string.part_property_empty))
//        }
//    }
//
//    private fun createViews(tablesModel: ArrayList<TablesModel>) {
//        tablesModel.forEach { mainData ->
//            when (mainData.questUiType) {
//                ViewType.NUMBER.type ->
//                    if (mainData.questRefId == "") {
//                        drawEditTextNumber(mainData = mainData)
//                    } else {
//                        tablesModel.forEach { localItem ->
//                            if (mainData.questRefId == localItem.questId) {
//                                if (localItem.questUiType != ViewType.TABLE.type && localItem.questUiType != ViewType.MAP.type) {
//                                    drawEditTextNumber(mainData = mainData)
//                                }
//                            }
//                        }
//                    }
//
//                ViewType.TEXT.type ->
//                    if (mainData.questRefId == "") {
//                        drawEditText(mainData = mainData)
//                    } else {
//                        tablesModel.forEach { localItem ->
//                            if (mainData.questRefId == localItem.questId) {
//                                if (localItem.questUiType != ViewType.TABLE.type && localItem.questUiType != ViewType.MAP.type) {
//                                    drawEditText(mainData = mainData)
//                                }
//
//                            }
//                        }
//                    }
//
//                ViewType.SELECT.type ->
//                    if (mainData.questRefId == "") {
//                        drawSelectEditText(mainData)
//                    } else {
//                        tablesModel.forEach { localItem ->
//                            if (mainData.questRefId == localItem.questId) {
//                                if (localItem.questUiType != ViewType.TABLE.type && localItem.questUiType != ViewType.MAP.type) {
//                                    drawSelectEditText(mainData)
//                                }
//                            }
//                        }
//                    }
//
//                ViewType.MAP.type -> {
//                    if (mainData.questRefId == "") {
//                        val layoutHorizontal = LinearLayoutCompat(requireActivity())
//                        var firstTime = true
//                        drawButton(mainData)
//                        tablesModel.forEach { localItem ->
//                            if (mainData.questId == localItem.questRefId) {
//                                if (firstTime){
//                                    drawMap(localItem,firstTime,layoutHorizontal)
//                                    firstTime = false
//                                }
//                                else
//                                    drawMap(localItem,firstTime,layoutHorizontal)
//
//                            }
//                        }
//
//                    }
//                }
//
//                ViewType.ADMINS.type ->
//                    if (mainData.questRefId == "") {
//                        drawSelectEditTextByAdmins(mainData)
//                    } else {
//                        tablesModel.forEach { localItem ->
//                            if (mainData.questRefId == localItem.questId) {
//                                if (localItem.questUiType != ViewType.TABLE.type && localItem.questUiType != ViewType.MAP.type) {
//                                    drawSelectEditTextByAdmins(mainData)
//                                }
//                            }
//                        }
//                    }
//
//                ViewType.REGIONS.type ->
//                    if (mainData.questRefId == "") {
//                        drawSelectEditTextByRegions(mainData)
//                    } else {
//                        tablesModel.forEach { localItem ->
//                            if (mainData.questRefId == localItem.questId) {
//                                if (localItem.questUiType != ViewType.TABLE.type && localItem.questUiType != ViewType.MAP.type) {
//                                    drawSelectEditTextByRegions(mainData)
//                                }
//                            }
//                        }
//                    }
//
//                ViewType.PLACE.type ->
//                    if (mainData.questRefId == "") {
//                        drawSelectEditTextByRegions(mainData)
//                    } else {
//                        tablesModel.forEach { localItem ->
//                            if (mainData.questRefId == localItem.questId) {
//                                if (localItem.questUiType != ViewType.TABLE.type && localItem.questUiType != ViewType.MAP.type) {
//                                    drawSelectEditTextByRegions(mainData)
//                                }
//                            }
//                        }
//                    }
//
//                ViewType.HARA.type ->
//                    if (mainData.questRefId == "") {
//                        drawSelectEditTextByRegions(mainData)
//                    } else {
//                        tablesModel.forEach { localItem ->
//                            if (mainData.questRefId == localItem.questId) {
//                                if (localItem.questUiType != ViewType.TABLE.type && localItem.questUiType != ViewType.MAP.type) {
//                                    drawSelectEditTextByRegions(mainData)
//                                }
//                            }
//                        }
//                    }
//
//                ViewType.RADIO.type ->
//                    if (mainData.questRefId == "") {
//                        drawRadioButton(mainData)
//                    } else {
//                        tablesModel.forEach { localItem ->
//                            if (mainData.questRefId == localItem.questId) {
//                                if (localItem.questUiType != ViewType.TABLE.type && localItem.questUiType != ViewType.MAP.type)
//                                    drawRadioButton(mainData)
//                            }
//                        }
//                    }
//
//                ViewType.LABEL.type ->
//                    if (mainData.questRefId == "") {
//                        drawLabelLinkWithQuestion(mainData)
//                    } else {
//                        tablesModel.forEach { localItem ->
//                            if (mainData.questRefId == localItem.questId) {
//                                if (localItem.questUiType != ViewType.TABLE.type && localItem.questUiType != ViewType.MAP.type)
//                                    drawLabelLinkWithQuestion(mainData)
//                            }
//                        }
//                    }
//
//                ViewType.TABLE.type ->
////                    if (mainData.questCode == "201.8.2" && mainData.questId == "3934")
////                        drawButton(mainData)
////                else
//                    drawTable(mainData, tablesModel)
//
//                else -> {
//
//                }
//            }
//        }
//        checkRuleMovement()
//    }
//
//    private fun addDataToGlobalArrayTextView(
//        customView: CustomView<TextView>,
//        mainData: TablesModel,
//    ) {
//        val global = GlobalView()
//        global.label = customView
//        global.tablesModel = mainData
//        global.customAttributes = customView.customAttributes
//        global.title = customView.title
//        globalAllQuestions.add(global)
//    }
//
//    private fun addDataToGlobalArrayEditText(
//        customView: CustomView<EditText>,
//        mainData: TablesModel,
//    ) {
//        val global = GlobalView()
//        global.editTextView = customView
//        global.tablesModel = mainData
//        global.customAttributes = customView.customAttributes
//        global.title = customView.title
//        globalAllQuestions.add(global)
//    }
//    private fun addDataToGlobalArrayButton(
//        customView: CustomView<Button>,
//        mainData: TablesModel,
//    ) {
//        val global = GlobalView()
//        global.buttonView = customView
//        global.tablesModel = mainData
//        global.customAttributes = customView.customAttributes
//        global.title = customView.title
//        globalAllQuestions.add(global)
//    }
//    private fun addDataToGlobalArrayRecyclerView(
//        customView: CustomView<RecyclerView>,
//        mainData: TablesModel,
//    ) {
//        val global = GlobalView()
//        global.recyclerView = customView
//        global.tablesModel = mainData
//        global.customAttributes = customView.customAttributes
//        global.title = customView.title
//        globalAllQuestions.add(global)
//    }
//
//    private fun addDataToGlobalArrayRadioGroup(
//        customView: CustomView<RadioGroup>,
//        mainData: TablesModel,
//    ) {
//        val global = GlobalView()
//        global.radioGroupView = customView
//        global.tablesModel = mainData
//        global.customAttributes = customView.customAttributes
//        global.title = customView.title
//        globalAllQuestions.add(global)
//    }
//
//    private fun drawTable(mainData: TablesModel, tablesModel: ArrayList<TablesModel>) {
//        if (mainData.questUiType == ViewType.TABLE.type) {
//            val countTableModel = ArrayList<TablesModel>()
//            val headerTitleForTables = ArrayList<TablesModel>()
//            var tableType = 1
//            var lookupInsideTable = ""
//            var mLocalItem = TablesModel()
//            tablesModels = tablesModel
//
//            // Loop for put the rows that equal the condition in anther array
//            tablesModel.forEach { localItem ->
//                if (mainData.questId == localItem.questRefId) {
//                    countTableModel.add(localItem)
//                    requiredValuesForTables.add(localItem)
//
//                    if (localItem.questName != "" && localItem.questName != "-")
//                        headerTitleForTables.add(localItem)
//                }
//            }
//
//            // Loop for put the item that equal the condition in new object
//            for (index in 0 until countTableModel.size) {
//                val localItem = countTableModel[index]
//                if (mainData.questId == localItem.questRefId && localItem.questLookUpId.isNotEmpty() && localItem.questUiType == ViewType.DETAILS.type) {
//                    mLocalItem = localItem
//                    break
//                }
//            }
//            // Loop for knows what is the type of table
//            for (index in 0 until countTableModel.size) {
//                val localItem = countTableModel[index]
//                if (mainData.questId == localItem.questRefId && localItem.questUiType != ViewType.DETAILS.type && localItem.questUiType != ViewType.TABLE.type) {
//                    when (localItem.questUiType) {
//                        ViewType.CHECKBOX.type -> tableType = 1
//                        ViewType.RADIO.type -> {
//                            tableType = if (countTableModel.size > 2)
//                                2
//                            else
//                                3
//                        }
//
//                        ViewType.SELECT.type -> {
//                            tableType = 3
//                            lookupInsideTable = localItem.questLookUpId
//                        }
//
//                        ViewType.NUMBER.type -> {
//                            tableType = 4
//                            lookupInsideTable = localItem.questLookUpId
//                        }
//                    }
//                    getDataFromSecondTable(
//                        mLocalItem,
//                        mainData,
//                        typeTable = tableType,
//                        lookupInsideTable = lookupInsideTable,
//                        headerTitleForTables = headerTitleForTables,
//                        requiredFlagArray = countTableModel
//                    )
//                    break
//                }
//            }
//        }
//    }
//
//    private fun drawLabelLinkWithQuestion(mainData: TablesModel) {
//        // title true , label false
//        val customView = CustomView<TextView>()
//        customView.customAttributes = CustomAttributes()
//        customView.customAttributes?.questCode = mainData.questCode
//        if (currentMember != null)
//            customView.customAttributes?.questName =
//                mainData.questName.replace("الفرد", currentMember.memberFirstName)
//                    .replace("الاسم", currentMember.memberFirstName)
//        else
//            customView.customAttributes?.questName = mainData.questName
//        val label =
//            customTextView.customTextViewLabel(requireActivity(), customView.customAttributes!!)
//        customView.typeView = label
//        binding.linearLayoutCompat.addView(customView.typeView)
//        addDataToGlobalArrayTextView(customView, mainData = mainData)
//    }
//
//    private fun drawLabel(isTitleOrLabel: Boolean, mainData: TablesModel): TextView {
//        // title true , label false
//        val customView = CustomView<TextView>()
//        customView.customAttributes = CustomAttributes()
//        customView.customAttributes?.questCode = mainData.questCode
//        if (currentMember != null)
//            customView.customAttributes?.questName =
//                mainData.questName.replace("الفرد", currentMember.memberFirstName)
//                    .replace("الاسم", currentMember.memberFirstName)
//        else
//            customView.customAttributes?.questName = mainData.questName
//
//        val title = customTextView.customTextView(requireActivity(), customView.customAttributes!!)
//        val label =
//            customTextView.customTextViewLabel(requireActivity(), customView.customAttributes!!)
//        return if (isTitleOrLabel) {
//            binding.linearLayoutCompat.addView(title)
//            title
//        } else {
////            binding.linearLayoutCompat.addView(label)
////            val global = GlobalView()
////            global.tablesModel = mainData
////            global.customAttributes = customView.customAttributes
////            global.title = label
////            global.questionsRule.value = false
////            globalAllQuestions.add(global)
//            binding.linearLayoutCompat.addView(label)
//            label
//        }
//
//    }
//
//    private fun drawEditTextNumber(mainData: TablesModel) {
//        val customView = customEditText.drawEditText(
//            mainData = mainData,
//            allAnswersFirstTable = allAnswersFirstTable,
//            activity = requireActivity(),
//            inputType = InputType.TYPE_CLASS_NUMBER,
////            or InputType.TYPE_NUMBER_FLAG_DECIMAL,
//            checkRuleMovementListener = object : OtherListener {
//                override fun other(customAttributes: CustomAttributes) {
//                    // if (valueChangeToColumn)
//                    checkRuleMovement()
//                }
//            })
//        binding.linearLayoutCompat.addView(customView.title)
//        binding.linearLayoutCompat.addView(customView.typeView)
//        addDataToGlobalArrayEditText(customView, mainData = mainData)
//
//
//        num = ""
//        num = customView.typeView!!.text.toString()
//    }
//
//    private fun drawEditText(mainData: TablesModel) {
//
//        val customView = customEditText.drawEditText(
//            mainData = mainData,
//            allAnswersFirstTable = allAnswersFirstTable,
//            activity = requireActivity(),
//            checkRuleMovementListener = object : OtherListener {
//                override fun other(customAttributes: CustomAttributes) {
//                }
//
//            })
//        binding.linearLayoutCompat.addView(customView.title)
//        binding.linearLayoutCompat.addView(customView.typeView)
//        addDataToGlobalArrayEditText(customView, mainData = mainData)
//    }
//
//    private fun drawSelectEditText(mainData: TablesModel) {
//        var customView = CustomView<EditText>()
//        customView = customEditText.drawSelectEditText(
//            mainData = mainData,
//            allAnswersFirstTable = allAnswersFirstTable,
//            activity = requireActivity(),
//            mSqlHelper = mSqlHelper,
//            lookUpViewModel = lookUpViewModel,
//            checkRuleMovementListener = object : OtherListener {
//                override fun other(customAttributes: CustomAttributes) {
//                    setFragmentListenerForLookUpsFragment(
//                        customView = customView
//                    )
//                    val yesValues = ArrayList<String>()
//                    if (mainData.questId == "4060") {
//                        globalAllQuestions.forEach {
//                            if (it.customAttributes?.questCode == "302.1")
//                                if (it.customAttributes?.adapterValues != null) {
//                                    it.customAttributes?.adapterValues?.forEachIndexed { _, multiCheckModel ->
//                                        if (multiCheckModel.value3.trim() == "69700001")
//                                            yesValues.add(multiCheckModel.lookupListId)
//                                    }
//                                }
//                        }
//                        val action =
//                            Section1FragmentDirections.actionSection1FragmentToLookUpSearchFragment(
//                                lookUpId = mainData.questLookUpId.toInt(),
//                                lookUpRef = yesValues.toTypedArray(),
//                                questCode = "302.1"
//                            )
//                        findNavController().safeNavigate(action)
//                    } else {
//                        val action =
//                            Section1FragmentDirections.actionSection1FragmentToLookUpSearchFragment(
//                                lookUpId = mainData.questLookUpId.toInt(),
//                                lookUpRef = yesValues.toTypedArray(),
//                                questCode = ""
//                            )
//                        findNavController().safeNavigate(action)
//                    }
//                }
//
//            })
//        binding.linearLayoutCompat.addView(customView.title)
//        binding.linearLayoutCompat.addView(customView.typeView)
//        addDataToGlobalArrayEditText(customView, mainData = mainData)
//    }
//
//    private fun drawButton(mainData: TablesModel) {
//        var customView = CustomView<Button>()
//        customView = customButton.drawButton(
//            mainData = mainData,
//            activity = requireActivity(),
//            clickButton = object : OtherListener {
//                override fun other(customAttributes: CustomAttributes) {
//                    setResultListenerMapLocation(customView)
//                    val action = Section1FragmentDirections.actionSection1FragmentToMapsFragment()
//                    findNavController().safeNavigate(action)
//                }
//
//            })
//        binding.linearLayoutCompat.addView(customView.title)
//        binding.linearLayoutCompat.addView(customView.typeView)
//        addDataToGlobalArrayButton(customView, mainData = mainData)
//    }
//    private fun drawMap(mainData: TablesModel,firstTime:Boolean, layoutHorizontal:LinearLayoutCompat){
//        val layoutParams = LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT,LinearLayoutCompat.LayoutParams.WRAP_CONTENT)
//        layoutHorizontal.layoutParams = layoutParams
//        layoutHorizontal.orientation = LinearLayoutCompat.HORIZONTAL
//        val customView: CustomView<EditText> = customMap.drawEditText(
//            mainData = mainData,
//            allAnswersFirstTable = allAnswersFirstTable,
//            activity = requireActivity())
//        layoutHorizontal.addView(customView.title)
//        layoutHorizontal.addView(customView.typeView)
//        if (!firstTime)
//        binding.linearLayoutCompat.addView(layoutHorizontal)
////        binding.linearLayoutCompat.addView(customView.title)
////        binding.linearLayoutCompat.addView(customView.typeView)
//        addDataToGlobalArrayEditText(customView, mainData = mainData)
//    }
//
//    private fun setResultListenerMapLocation(customView: CustomView<Button>?) {
//        setFragmentResultListener(
//            OpenMapsFragment_RESULT_KEY
//        ) { requestKey, result ->
//            if (requestKey == OpenMapsFragment_RESULT_KEY) {
//                val bundle =
//                    result.getParcelable<com.google.android.gms.maps.model.LatLng>(
//                        OpenMapsFragment_DATA
//                    )
//                if (bundle != null) {
//                    var first = true
//                    globalAllQuestions.forEach {
//                        if (customView?.customAttributes?.questId == it.tablesModel.questRefId){
//                            if (first){
//                                it.editTextView?.typeView?.setText(bundle.latitude.toString())
//                                it.customAttributes?.value = bundle.latitude.toString()
//                                first = false
//                            }
//                            else{
//                                it.editTextView?.typeView?.setText(bundle.longitude.toString())
//                                it.customAttributes?.value = bundle.longitude.toString()
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    private fun drawSelectEditTextByAdmins(mainData: TablesModel) {
//        // val type :String= mainData.androidKey.substring(6)
//        var customView = CustomView<EditText>()
//        customView = customEditText.drawSelectEditTextAdmins(
//            mainData = mainData,
//            allAnswersFirstTable = allAnswersFirstTable,
//            activity = requireActivity(),
//            placesViewModel = placesViewModel,
//            checkRuleMovementListener = object : OtherListener {
//                override fun other(customAttributes: CustomAttributes) {
//                    setFragmentListenerForPlacesFragment(
//                        customView = customView
//                    )
//                    val action =
//                        Section1FragmentDirections.actionSection1FragmentToAllPlaceHaraSearchFragment(
//                            androidKey = "", ""
//                        )
//                    findNavController().safeNavigate(action)
//                }
//            }, type = ""
//        )
//        binding.linearLayoutCompat.addView(customView.title)
//        binding.linearLayoutCompat.addView(customView.typeView)
//        addDataToGlobalArrayEditText(customView, mainData = mainData)
//    }
//
//    private fun drawSelectEditTextByRegions(mainData: TablesModel) {
//        val type: String = mainData.androidKey.substring(6)
//        var customView = CustomView<EditText>()
//        customView = customEditText.drawSelectEditTextRegions(
//            mainData = mainData,
//            allAnswersFirstTable = allAnswersFirstTable,
//            activity = requireActivity(),
//            placesViewModel = placesViewModel,
//            checkRuleMovementListener = object : OtherListener {
//                override fun other(customAttributes: CustomAttributes) {
//                    var adminId = ""
//                    when (type) {
//                        "admin" -> {
//                            run breakLoop@{
//                                globalAllQuestions.forEach {
//                                    if (it.tablesModel.questUiType == ViewType.ADMINS.type)
//                                        if (it.customAttributes?.value != null) {
//                                            adminId = it.customAttributes?.value.toString()
//                                            return@breakLoop
//                                        }
//                                }
//                            }
//
//                        }
//
//                        "region" -> {
//                            run breakLoop@{
//                                globalAllQuestions.forEach {
//                                    if (it.tablesModel.questUiType == ViewType.REGIONS.type)
//                                        if (it.customAttributes?.value != null) {
//                                            adminId = it.customAttributes?.value.toString()
//                                            return@breakLoop
//                                        }
//                                }
//                            }
//
//                        }
//
//                        "place" -> {
//                            run breakLoop@{
//                                globalAllQuestions.forEach {
//                                    if (it.tablesModel.questUiType == ViewType.PLACE.type)
//                                        if (it.customAttributes?.value != null) {
//                                            adminId = it.customAttributes?.value.toString()
//                                            return@breakLoop
//                                        }
//                                }
//                            }
//                        }
//                    }
//                    setFragmentListenerForPlacesFragment(
//                        customView = customView
//                    )
//
//                    val action =
//                        Section1FragmentDirections.actionSection1FragmentToAllPlaceHaraSearchFragment(
//                            androidKey = adminId, type = type
//                        )
//                    findNavController().safeNavigate(action)
//                }
//            }, type = type
//        )
//        binding.linearLayoutCompat.addView(customView.title)
//        binding.linearLayoutCompat.addView(customView.typeView)
//        addDataToGlobalArrayEditText(customView, mainData = mainData)
//    }
//
//    private fun drawRadioButton(mainData: TablesModel) {
//        var customView = CustomView<RadioGroup>()
//        customView = customRadioButton.drawRadioButton(
//            mainData = mainData,
//            allAnswersFirstTable = allAnswersFirstTable,
//            activity = requireActivity(),
//            lookUpViewModel = lookUpViewModel,
//            checkRuleMovementListener = object : OtherListener {
//                override fun other(customAttributes: CustomAttributes) {
////                    if (customView.customAttributes?.questDbColumnName == "q_308") {
////                        enableDisableRowInsideTable =
////                            customView.customAttributes?.value.toString()
////                        run BreakLoop@{
////                            globalAllQuestions.forEach {
////                                if (it.customAttributes?.questCode == "202.5") {
////                                    val adapter = TableAdapterThree(
////                                        mSqlHelper,
////                                        it.recyclerView?.recyclerViewData!![0].lookupListIds,
////                                        enableDisableRowInsideTable,
////                                        it.recyclerView?.headerTitleForTables!!
////                                    )
////                                    it.recyclerView?.typeView?.adapter = adapter
////                                    adapter.submitList(it.recyclerView?.recyclerViewData)
////                                    customAttributes.adapterValues =
////                                        adapter.retrieveDataSelected()
////                                            .toCollection(arrayListOf())
////                                    return@BreakLoop
////                                }
////                            }
////                        }
////                    }
//                    if (customAttributes.questDbColumnName == "q_308")
//                        valueQ308 = customAttributes.value
//                    if (customAttributes.questDbColumnName == "q_309" || customAttributes.questDbColumnName == "q_311"
//                        || customAttributes.questDbColumnName == "q_312" || customAttributes.questDbColumnName == "q_313"
//                    ) {
//                        run BreakLoop@{
//                            globalAllQuestions.forEach {
//                                if (it.customAttributes?.questDbColumnName == "q_308_2") {
//                                    if (valueQ308 == "3400002" || valueQ308 == "") {
//                                        it.customAttributes?.value = ""
//                                        it.editTextView?.typeView?.text?.clear()
//                                        it.editTextView?.typeView?.visibility = View.GONE
//                                        it.title?.visibility = View.GONE
//                                        it.questionsRule.value = false
//                                    }
//                                    return@BreakLoop
//                                }
//                            }
//                        }
//                    } else if (customAttributes.questDbColumnName == "q_308") {
//                        run BreakLoop@{
//                            globalAllQuestions.forEach {
//                                if (it.customAttributes?.questDbColumnName == "q_308_2") {
//                                    if (valueQ308 == "3400002" || valueQ308 == "") {
//                                        it.customAttributes?.value = ""
//                                        it.editTextView?.typeView?.text?.clear()
//                                        it.editTextView?.typeView?.visibility = View.GONE
//                                        it.title?.visibility = View.GONE
//                                        it.questionsRule.value = false
//                                    }
//                                    return@BreakLoop
//                                }
//                            }
//                        }
//                    }
//                    checkRuleMovement()
//                }
//
//            })
//        binding.linearLayoutCompat.addView(customView.title)
//        binding.linearLayoutCompat.addView(customView.typeView)
//        addDataToGlobalArrayRadioGroup(customView, mainData)
//    }
//
//    private fun getDataFromSecondTable(
//        localItem: TablesModel,
//        mainData: TablesModel, typeTable: Int, lookupInsideTable: String,
//        headerTitleForTables: ArrayList<TablesModel>,
//        requiredFlagArray: ArrayList<TablesModel>,
//    ) {
//        numberOfQueryMultiData.forEach { filterByTables ->
//            if (filterByTables.questionId == mainData.questId) {
//                if (mainData.questCode == "201.8.2" && mainData.lanID == currentLanguage && mainData.questId == "3934") {
//                    SessionClassMain.filterByTables = filterByTables
//                    tablesModel = mainData
//                    SessionClassMain.typeTable = typeTable
//                    SessionClassMain.lookupInsideTable = lookupInsideTable
//                    SessionClassMain.localItem = localItem
//                    SessionClassMain.headerTitleForTables = headerTitleForTables
//                    initializeTable1(
//                        filterByTables,
//                        mainData,
//                        typeTable,
//                        lookupInsideTable,
//                        headerTitleForTables,
//                        requiredFlagArray
//                    )
//                } else {
//                    initializeTable(
//                        filterByTables,
//                        mainData,
//                        typeTable,
//                        lookupInsideTable,
//                        localItem,
//                        headerTitleForTables,
//                        requiredFlagArray
//                    )
//                }
//            }
//        }
//    }
//
//    private fun setCustomTable(mainData: TablesModel): ArrayList<PopupItem> {
//        var data1 = arrayListOf<PopupItem>()
//        if (mainData.questCode == "201.8.2" && mainData.lanID == currentLanguage && mainData.questId == "3934") {
//            numInt = Utility.getIntegerValue(num)
//            data1 = ArrayList(numInt)
//
//            for (i in 1..numInt) {
//                val lookUpModel = PopupItem()
//                val lookUpModel1 = LookUpModel()
//                lookUpModel.position = i
//                lookUpModel.id = "$i"
//                lookUpModel.name = "المسكن $i"
//                lookUpModel.colType = "المسكن $i"
//                lookUpModel.col1 = "المسكن $i"
//                lookUpModel.seqNumber = i.toString()
//
//                lookUpModel1.lookUpListId = " $i"
//                lookUpModel1.listName = "المسكن $i"
//                data1.add(lookUpModel)
//            }
//        }
//        return data1
//    }
//
//    private fun initializeTable(
//        mFilterByTable: GsonDataItem,
//        mainData: TablesModel,
//        typeTable: Int,
//        lookupInsideTable: String,
//        localItem: TablesModel,
//        headerTitleForTables: ArrayList<TablesModel>,
//        requiredFlagArray: ArrayList<TablesModel>,
//    ) {
//        if (localItem.questLookUpId != "") {
//            val result: ArrayList<PopupItem>? = if (currentSection.partLevel == "2") {
//                lookUpViewModel.getLookUpMethodWithUpdate(
//                    localItem.questLookUpId.toInt(),
//                    currentLanguage, currentMember.id,
//                    mainData.questId, partLevel = currentSection.partLevel
//                )
//            } else {
//                lookUpViewModel.getLookUpMethodWithUpdate(
//                    localItem.questLookUpId.toInt(),
//                    currentLanguage, SessionClass.currentFamily.id,
//                    mainData.questId, partLevel = currentSection.partLevel
//                )
//            }
//
//
//
//            if (result != null) {
//                for (i in result.indices) {
//                    val columnName = arrayListOf<SaveData>()
//                    columnName.add(
//                        0,
//                        SaveData(
//                            tableName = mFilterByTable.tableName,
//                            columnName = "QUESTION_ID",
//                            value = mainData.questId,
//                            whereCondition = mFilterByTable.whereCondition
//                        )
//                    )
//                    columnName.add(
//                        1,
//                        SaveData(
//                            columnName = "SAMPLE_ID",
//                            value = SessionClass.currentFamily.id
//                        )
//                    )
//                    if (currentSection.partLevel == "2")
//                        columnName.add(
//                            2,
//                            SaveData(
//                                columnName = "F_M_ID",
//                                value = currentMember.id
//                            )
//                        )
//                    columnName.add(
//                        SaveData(
//                            columnName = "SEQ",
//                            value = result[i].seqNumber
//                        )
//                    )
//                    columnName.add(
//                        SaveData(
//                            columnName = "F_MD_ID",
//                            value = Utils.getUserID(mSqlHelper) + Utils.getStampTimeId()
//                        )
//                    )
//                    columnName.add(
//                        SaveData(
//                            columnName = "LOOKUP_LIST_ID",
//                            value = result[i].id.toString()
//                        )
//                    )
//                    columnName.add(
//                        SaveData(
//                            columnName = "delete_status",
//                            value = "1"
//                        )
//                    )
//                    if (!dynamicViewModel.insertDataForTables(true, columnName)) {
//                        binding.root.showSnackBar("Error ")
//                        break
//                    }
//                }
//            }
//            val data = dynamicViewModel.getDataForTable(
//                gsonDataItem = mFilterByTable,
//                tableType = typeTable
//            )
//            if (data.isNotEmpty()) {
//                data[0].tableName = mFilterByTable.tableName
//                data[0].whereCondition = mFilterByTable.whereCondition
//                data[0].columnNameOriginal = mFilterByTable.columnNameOriginal
//                putDataIntoRecyclerView(
//                    data,
//                    mainData,
//                    typeTable,
//                    lookupInsideTable,
//                    headerTitleForTables,
//                    requiredFlagArray
//                )
//            }
//        }
////        if (data.isEmpty()) {
////            if (localItem.questLookUpId != "") {
////                val result = lookUpViewModel.getLookUpMethodWithUpdate(
////                    localItem.questLookUpId.toInt(),
////                    currentLanguage,SessionClass.currentFamily.id,
////                    mainData.questId
////                )
////                if (result != null) {
////                    for (i in result.indices) {
////                        val columnName = arrayListOf<SaveData>()
////                        columnName.add(
////                            0,
////                            SaveData(
////                                tableName = mFilterByTable.tableName,
////                                columnName = "QUESTION_ID",
////                                value = mainData.questId,
////                                whereCondition = mFilterByTable.whereCondition
////                            )
////                        )
////                        columnName.add(
////                            1,
////                            SaveData(
////                                columnName = "SAMPLE_ID",
////                                value = SessionClass.currentFamily.id
////                            )
////                        )
////                        if (currentSection.partLevel == "2")
////                            columnName.add(
////                                2,
////                                SaveData(
////                                    columnName = "F_M_ID",
////                                    value = currentMember.id
////                                )
////                            )
////                        columnName.add(
////                            SaveData(
////                                columnName = "SEQ",
////                                value = result[i].seqNumber
////                            )
////                        )
////                        columnName.add(
////                            SaveData(
////                                columnName = "F_MD_ID",
////                                value = Utils.getUserID(mSqlHelper) + Utils.getStampTimeId()
////                            )
////                        )
////                        columnName.add(
////                            SaveData(
////                                columnName = "LOOKUP_LIST_ID",
////                                value = result[i].id.toString()
////                            )
////                        )
////                        columnName.add(
////                            SaveData(
////                                columnName = "delete_status",
////                                value = "1"
////                            )
////                        )
////                        if (!dynamicViewModel.saveData(true, columnName)) {
////                            binding.root.showSnackBar("Error ")
////                            break
////                        }
////                    }
////                    initializeTable(
////                        mFilterByTable,
////                        mainData,
////                        typeTable,
////                        lookupInsideTable,
////                        localItem, headerTitleForTables, requiredFlagArray = requiredFlagArray
////                    )
////                }
////            }
////        } else {
////            data[0].tableName = mFilterByTable.tableName
////            data[0].whereCondition = mFilterByTable.whereCondition
////            data[0].columnNameOriginal = mFilterByTable.columnNameOriginal
////            putDataIntoRecyclerView(
////                data,
////                mainData,
////                typeTable,
////                lookupInsideTable,
////                headerTitleForTables,
////                requiredFlagArray
////            )
////        }
//    }
//
//    private fun initializeTable1(
//        mFilterByTable: GsonDataItem,
//        mainData: TablesModel,
//        typeTable: Int,
//        lookupInsideTable: String,
//        headerTitleForTables: ArrayList<TablesModel>,
//        requiredFlagArray: ArrayList<TablesModel>,
//    ) {
//        val data = dynamicViewModel.getDataForTableCustom(
//            gsonDataItem = mFilterByTable,
//            tableType = typeTable
//        )
//        if (data.isNotEmpty()) {
//            data[0].tableName = mFilterByTable.tableName
//            data[0].whereCondition = mFilterByTable.whereCondition
//            data[0].columnNameOriginal = mFilterByTable.columnNameOriginal
//            putDataIntoRecyclerView(
//                data,
//                mainData,
//                typeTable,
//                lookupInsideTable,
//                headerTitleForTables,
//                requiredFlagArray = requiredFlagArray
//            )
//        } else {
//            //This array for custom Table
//            val emptyArray = arrayListOf<MultiCheckModel>()
//            val multiCheckModel = MultiCheckModel()
//            multiCheckModel.lookupListId = "1"
//            multiCheckModel.questId = "3934"
//            multiCheckModel.tableName = mFilterByTable.tableName
//            multiCheckModel.whereCondition = mFilterByTable.whereCondition
//            multiCheckModel.columnNameOriginal = mFilterByTable.columnNameOriginal
//            emptyArray.add(multiCheckModel)
//            putDataIntoRecyclerViewCustomQuestion(
//                emptyArray,
//                mainData,
//                lookupInsideTable,
//                headerTitleForTables
//            )
//        }
//        valueChangeToColumn = true
//    }
//
//    private fun putDataIntoRecyclerView(
//        result: ArrayList<MultiCheckModel>,
//        mainData: TablesModel,
//        typeTable: Int,
//        lookupInsideTable: String,
//        headerTitleForTables: ArrayList<TablesModel>,
//        requiredFlagArray: ArrayList<TablesModel>,
//    ) {
//        val customView = customRecyclerView.drawAndPutDataIntoRecyclerView(
//            result = result,
//            mainData = mainData,
//            activity = requireActivity(),
//            lookupInsideTable = lookupInsideTable,
//            lookUpViewModel = lookUpViewModel,
//            mSqlHelper = mSqlHelper,
//            typeTable = typeTable,
//            headerTitleForTables = headerTitleForTables,
//            requiredFlagArray = requiredFlagArray,
//            enableDisableRowInsideTable = enableDisableRowInsideTable,
//
//            spinnerListener = object : SpinnerListener {
//                override fun resetValue() {
//                    // This value for first time don't trigger that action.
//                    if (valueChange)
//                        run breakLoop@{
//                            globalAllQuestions.forEach {
//                                if (it.customAttributes?.questDbColumnName == "c_302_4") {
//                                    it.customAttributes?.value = ""
//                                    it.editTextView?.typeView?.text?.clear()
//                                    return@breakLoop
//                                }
//                            }
//                        }
//                }
//            }, checkBoxListener = object : CheckBoxListener {
//                override fun hideAndShow(multiCheckModel: MultiCheckModel) {
//                    // This value for first time don't trigger that action.
//                    if (valueChange) {
//                        if (multiCheckModel.isCheck) {
//                            run breakLoop@{
//                                globalAllQuestions.forEach {
//                                    if (it.customAttributes?.questDbColumnName == "q_308_2") {
//                                        it.customAttributes?.value = ""
//                                        it.editTextView?.typeView?.text?.clear()
//                                        it.editTextView?.typeView?.visibility = View.VISIBLE
//                                        it.title?.visibility = View.VISIBLE
//                                        it.questionsRule.value = true
//                                        return@breakLoop
//                                    }
//                                }
//                            }
//                        } else {
//                            run breakLoop@{
//                                globalAllQuestions.forEach {
//                                    if (it.customAttributes?.questDbColumnName == "q_308_2") {
//                                        it.customAttributes?.value = ""
//                                        it.editTextView?.typeView?.text?.clear()
//                                        it.editTextView?.typeView?.visibility = View.GONE
//                                        it.title?.visibility = View.GONE
//                                        it.questionsRule.value = false
//                                        return@breakLoop
//                                    }
//                                }
//                            }
//                        }
//                    }
//
//                }
//
//            })
//        binding.linearLayoutCompat.addView(customView.title)
//        binding.linearLayoutCompat.addView(customView.typeView)
//        addDataToGlobalArrayRecyclerView(customView, mainData = mainData)
//
//        valueChange = true
//    }
//
//    private fun putDataIntoRecyclerViewCustomQuestion(
//        result: ArrayList<MultiCheckModel>,
//        mainData: TablesModel,
//        lookupInsideTable: String,
//        headerTitleForTables: ArrayList<TablesModel>,
//    ) {
//        var lookupListIds = ArrayList<PopupItem>()
//        //Get lookup for adapter
//        if (lookupInsideTable != null && lookupInsideTable != "") {
//            val getLookUpSync =
//                lookUpViewModel.getLookUpSync(lookupInsideTable.toInt(), currentLanguage)
//            val itemOne = PopupItem()
//            itemOne.id = "-1"
//            itemOne.position = 0
//            itemOne.name = getString(R.string.hint_specify)
//            getLookUpSync.add(0, itemOne)
//            lookupListIds = getLookUpSync
//        }
//        val customView = CustomView<RecyclerView>()
//        customView.customAttributes = CustomAttributes()
//        customView.customAttributes?.questCode = mainData.questCode
//
//        if (currentMember != null)
//            customView.customAttributes?.questName =
//                mainData.questName.replace("الفرد", currentMember.memberFirstName)
//                    .replace("الاسم", SessionClassMain.currentMember.memberFirstName)
//        else
//            customView.customAttributes?.questName = mainData.questName
//
//        result[0].lookupListIds = lookupListIds
//        customView.recyclerViewData = result
//        customView.headerTitleForTables = headerTitleForTables
//        multiCheckModels = result
//        customView.typeView = customRecyclerView.customDefaultRecyclerView(
//            requireActivity(),
//            mSqlHelper,
//            customView, enableDisableRowInsideTable
//        )
//        customView.title = drawLabel(true, mainData)
//        binding.linearLayoutCompat.addView(customView.typeView)
//        addDataToGlobalArrayRecyclerView(customView, mainData = mainData)
//    }
//
//    private fun saveData() {
//        numberOfQueryMember.forEach { numberOfQuery ->
//            //Key columns in any statement
//            val keyArray = ArrayList<SaveData>()
//            keyArray.add(
//                0, SaveData(
//                    tableName = numberOfQuery.tableName,
//                    columnName = "SAMPLE_ID",
//                    value = SessionClass.currentFamily.id,
//                    whereCondition = numberOfQuery.whereCondition
//                )
//            )
//            if (numberOfQuery.tableName.uppercase() == WHS_FAMILY_MEMBER_TAB)
//                keyArray.add(
//                    1, SaveData(
//                        tableName = numberOfQuery.tableName,
//                        columnName = "F_M_ID",
//                        value = currentMember.id,
//                        whereCondition = numberOfQuery.whereCondition
//                    )
//                )
//            //If column name in numberOfQueryMember array is equal column name in globalCustomAttributes array take the value
//            numberOfQuery.columnNameOriginal?.forEach { columnName ->
//                var columnsWithValues: SaveData
//                globalAllQuestions.filter { it.customAttributes?.questDbColumnName != "" }
//                    .forEachIndexed { _, customAttributes ->
//                        if (columnName.uppercase() == customAttributes.customAttributes?.questDbColumnName?.uppercase()) {
//                            columnsWithValues = SaveData(
//                                tableName = numberOfQuery.tableName,
//                                columnName = columnName,
//                                value = customAttributes.customAttributes?.value.toString(),
//                                whereCondition = numberOfQuery.whereCondition
//                            )
//                            numberOfQuery.columnsWithValues.add(columnsWithValues)
//                        }
//                    }
//            }
//            numberOfQuery.columnsWithValues.addAll(keyArray)
//            if (!dynamicViewModel.saveData(insert = false, numberOfQuery.columnsWithValues))
//                binding.root.showSnackBar("Error ")
//        }
//
//        globalAllQuestions.forEach {
//            if (it.customAttributes?.adapterValues != null) {
//                var tableName = ""
//                var whereCondition = ""
//                var column1 = ""
//                var column2 = ""
//                var column3 = ""
//                it.customAttributes?.adapterValues?.forEachIndexed { index, multiCheckModel ->
//                    val columnNameSecondTable = ArrayList<SaveData>()
//                    if (index == 0) {
//                        multiCheckModel.columnNameOriginal?.forEachIndexed { indexColumnName, columnName ->
//                            if (indexColumnName == 0)
//                                column1 = columnName
//                            if (indexColumnName == 1)
//                                column2 = columnName
//                            if (indexColumnName == 2)
//                                column3 = columnName
//                        }
//                        tableName = multiCheckModel.tableName
//                        whereCondition =
//                            multiCheckModel.whereCondition + " and SAMPLE_ID = '${SessionClass.currentFamily.id}'"
//                    }
//                    if (column1 != "")
//                        columnNameSecondTable.add(
//                            SaveData(
//                                columnName = column1,
//                                value = multiCheckModel.value1,
//                                whereCondition = whereCondition + " and LOOKUP_LIST_ID = '${multiCheckModel.lookupListId}'"
//                            )
//                        )
//                    if (column2 != "" && column2 != "question_id")
//                        columnNameSecondTable.add(
//                            SaveData(
//                                columnName = column2,
//                                value = multiCheckModel.value2,
//                                whereCondition = whereCondition + " and LOOKUP_LIST_ID = '${multiCheckModel.lookupListId}'"
//                            )
//                        )
//                    if (column3 != "" && (column3 == "c_204_3_txt" || column3 == "c_302_3"))
//                        columnNameSecondTable.add(
//                            SaveData(
//                                columnName = column3,
//                                value = multiCheckModel.value3,
//                                whereCondition = whereCondition + " and LOOKUP_LIST_ID = '${multiCheckModel.lookupListId}'"
//                            )
//                        )
//                    columnNameSecondTable.add(
//                        SaveData(
//                            columnName = "LOOKUP_LIST_TXT",
//                            value = multiCheckModel.lookupListTxt,
//                            whereCondition = whereCondition + " and LOOKUP_LIST_ID = '${multiCheckModel.lookupListId}'"
//                        )
//                    )
//                    dynamicViewModel.updateDataForTables(
//                        false,
//                        columnNameSecondTable,
//                        tableName
//                    )
//                }
//            }
//        }
//    }
//
//    private fun setFragmentListenerForLookUpsFragment(
//        customView: CustomView<EditText>?,
//    ) {
//        setFragmentResultListener(
//            LookUpSearchFragment_FRAGMENT_RESULT_KEY
//        ) { requestKey, result ->
//            if (requestKey == LookUpSearchFragment_FRAGMENT_RESULT_KEY) {
//                val bundle = result.getParcelable<LookUpModel>(LookUpSearchFragment_FRAGMENT_DATA)
//                if (bundle != null) {
//                    customView?.typeView?.setText(bundle.listName)
//                    customView?.customAttributes?.value = bundle.lookUpListId
//                    //  if (customView?.customAttributes?.questDbColumnName == "c_302_4")
//                    //valueChange = true
//                    checkRuleMovement()
//                }
//            }
//        }
//    }
//
//    private fun setFragmentListenerForPlacesFragment(
//        customView: CustomView<EditText>?,
//    ) {
//        setFragmentResultListener(
//            AllPlaceHaraSearchFragment_FRAGMENT_RESULT_KEY
//        ) { requestKey, result ->
//            if (requestKey == AllPlaceHaraSearchFragment_FRAGMENT_RESULT_KEY) {
//                val bundle =
//                    result.getParcelable<LookUpModel>(AllPlaceHaraSearchFragment_FRAGMENT_DATA)
//                if (bundle != null) {
//                    customView?.typeView?.setText(bundle.listName)
//                    customView?.customAttributes?.value = bundle.lookUpListId
//                    val type = customView?.customAttributes?.questDbColumnName?.substring(6)
//                    when (type) {
//                        "admin" -> {
//                            run breakLoop@{
//                                globalAllQuestions.forEach {
//                                    if (it.tablesModel.questUiType == ViewType.REGIONS.type || it.tablesModel.questUiType == ViewType.PLACE.type || it.tablesModel.questUiType == ViewType.HARA.type)
//                                        if (it.customAttributes?.value != null) {
//                                            it.customAttributes?.value = ""
//                                            it.editTextView?.typeView?.text?.clear()
//                                        }
//                                }
//
//                            }
//                        }
//
//                        "region" -> {
//                            run breakLoop@{
//                                globalAllQuestions.forEach {
//                                    if (it.tablesModel.questUiType == ViewType.PLACE.type || it.tablesModel.questUiType == ViewType.HARA.type)
//                                        if (it.customAttributes?.value != null) {
//                                            it.customAttributes?.value = ""
//                                            it.editTextView?.typeView?.text?.clear()
//                                        }
//                                }
//                            }
//                        }
//
//                        "place" -> {
//                            run breakLoop@{
//                                globalAllQuestions.forEach {
//                                    if (it.tablesModel.questUiType == ViewType.HARA.type)
//                                        if (it.customAttributes?.value != null) {
//                                            it.customAttributes?.value = ""
//                                            it.editTextView?.typeView?.text?.clear()
//                                            return@breakLoop
//                                        }
//                                }
//
//                            }
//                        }
//                    }
//                    checkRuleMovement()
//                }
//            }
//        }
//    }
//
//    private fun runErrorsRoles(vararg questionIds: Int) {
//        var result: ErrorDetails? = null
//        if (currentSection.partLevel == "1") {
//            result = errorRolesVieWModel.runBusinessRulesCall(
//                questionIds = questionIds,
//                memberId = ""
//            )
//        } else if (currentSection.partLevel == "2") {
//            result = errorRolesVieWModel.runBusinessRulesCall(
//                questionIds = questionIds,
//                memberId = currentMember.id
//            )
//        }
//        if (result == null) {
//            if (currentSection.partLevel == "1") {
//                if (!updateMemberAndSectionVieWModel.updateSectionStatus(
//                        status = SectionStatusEnum.Completed.numericType
//                    )
//                ) {
//                    binding.root.showSnackBar(getString(R.string.err_in_local_db))
//                }
//            } else if (currentSection.partLevel == "2") {
//                if (!updateMemberAndSectionVieWModel.updateMemberStatus(
//                        status = SectionStatusEnum.Completed.numericType
//                    )
//                ) {
//                    binding.root.showSnackBar(getString(R.string.err_in_local_db))
//                } else {
//                    updateMemberAndSectionVieWModel.updateMemberInSections(
//                        sections = sectionAfterCurrentSection,
//                        status = SectionStatusEnum.UnComplete.numericType
//                    )
//                    updateMemberAndSectionVieWModel.updateSectionsAfterThatSection(
//                        sectionIds = sectionAfterCurrentSection,
//                        status = SectionStatusEnum.UnComplete.numericType
//                    )
//                }
//            }
//            findNavController().safeNavigateUp()
//        } else {
//            if (result.errorType == 1) {
//                if (currentSection.partLevel == "1") {
//                    if (!updateMemberAndSectionVieWModel.updateSectionStatus(
//                            status = SectionStatusEnum.UnComplete.numericType
//                        )
//                    ) {
//                        binding.root.showSnackBar(getString(R.string.err_in_local_db))
//                    }
//                } else if (currentSection.partLevel == "2") {
//                    if (!updateMemberAndSectionVieWModel.updateMemberStatus(
//                            status = SectionStatusEnum.UnComplete.numericType
//                        )
//                    ) {
//                        binding.root.showSnackBar(getString(R.string.err_in_local_db))
//                    }
//                }
//                Utility.playToastSound(activity)
//                DialogUtil.showErrorDialog(requireContext(), result.msg)
//            } else {
//                DialogUtil.showMessageWithYesNoMaterialDesign(
//                    requireContext(), getString(R.string.warning), result.msg
//                ) { _, _ ->
//                    if (currentSection.partLevel == "1") {
//                        if (!updateMemberAndSectionVieWModel.updateSectionStatus(
//                                status = SectionStatusEnum.Completed.numericType
//                            )
//                        ) {
//                            binding.root.showSnackBar(getString(R.string.err_in_local_db))
//                        }
//                    } else if (currentSection.partLevel == "2") {
//                        if (!updateMemberAndSectionVieWModel.updateMemberStatus(
//                                status = SectionStatusEnum.Completed.numericType
//                            )
//                        ) {
//                            binding.root.showSnackBar(getString(R.string.err_in_local_db))
//                        } else {
//                            updateMemberAndSectionVieWModel.updateMemberInSections(
//                                sections = sectionAfterCurrentSection,
//                                status = SectionStatusEnum.UnComplete.numericType
//                            )
//                            updateMemberAndSectionVieWModel.updateSectionsAfterThatSection(
//                                sectionIds = sectionAfterCurrentSection,
//                                status = SectionStatusEnum.UnComplete.numericType
//                            )
//                        }
//                    }
//                    findNavController().safeNavigateUp()
//                }
//            }
//        }
//    }
//}