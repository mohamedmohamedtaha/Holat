package com.holat.holat.ui.dynamic.models

data class FormProperties(
    val columns: List<String>,
    val condition: String,
    val question_id: String = "",
    val global_pk_column_name: String,
    val table_name: String,
    val table_pk_column_name: String
)