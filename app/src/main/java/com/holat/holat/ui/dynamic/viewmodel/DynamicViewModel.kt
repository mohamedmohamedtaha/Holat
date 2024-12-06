package com.holat.holat.ui.dynamic.viewmodel

//@HiltViewModel
//class DynamicViewModel @Inject constructor(private val dynamicRepository: DynamicRepository) :
//    BaseViewModel() {
//    private val _getDataLiveData = MutableLiveData<ArrayList<TablesModel>?>()
//    val getData get() = _getDataLiveData
//    fun getData(partId: String, lanId: String) {
//        viewModelScope.launch {
//            _getDataLiveData.value = dynamicRepository.getData(partId, lanId)
//        }
//    }
//
//    fun saveData(insert:Boolean,columnNames: ArrayList<SaveData>): Boolean {
//        return callDatabase {
//            dynamicRepository.saveData(insert,columnNames)
//        }
//    }
//    fun insertDataForTables(insert:Boolean, columnNames: ArrayList<SaveData>): Boolean {
//        return callDatabase {
//            dynamicRepository.insertDataForTables(insert,columnNames)
//        }
//    }
//    fun updateDataForTables(insert:Boolean, columnNames: ArrayList<SaveData>, tableName: String) {
//        viewModelScope.launch {
//            dynamicRepository.saveDataForTables(insert,columnNames, tableName)
//        }
//
//    }
//    private val _getDataPart1LiveData = MutableLiveData<ArrayList<SaveData>?>()
//    val getDataPart1LiveData get() = _getDataPart1LiveData
//    fun getDataPart1(gsonDataItem: GsonDataItem) {
//        viewModelScope.launch {
//            _getDataPart1LiveData.value = dynamicRepository.getDataPart1(gsonDataItem)
//        }
//    }
//
//    fun getDataForTable(gsonDataItem: GsonDataItem,tableType:Int): ArrayList<MultiCheckModel> {
//        var resultFinal = ArrayList<MultiCheckModel>()
//        viewModelScope.launch {
//            val result : Deferred<ArrayList<MultiCheckModel>?> = async {
//                dynamicRepository.getDataForTables(gsonDataItem,tableType)
//            }
//            resultFinal = result.await() ?: resultFinal
//        }
//        return resultFinal
//    }
//    fun getDataForTableCustom(gsonDataItem: GsonDataItem,tableType:Int): ArrayList<MultiCheckModel> {
//        var resultFinal = ArrayList<MultiCheckModel>()
//        viewModelScope.launch {
//            val result : Deferred<ArrayList<MultiCheckModel>?> = async {
//                dynamicRepository.getDataForTablesCustom(gsonDataItem,tableType)
//            }
//            resultFinal = result.await() ?: resultFinal
//        }
//        return resultFinal
//    }
//
//}