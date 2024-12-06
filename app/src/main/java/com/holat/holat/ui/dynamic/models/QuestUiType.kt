package com.holat.holat.ui.dynamic.models

sealed class QuestUiType
data class Text(val title:String?):QuestUiType()
