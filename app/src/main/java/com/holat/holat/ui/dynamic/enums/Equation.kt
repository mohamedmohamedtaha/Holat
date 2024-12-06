package com.holat.holat.ui.dynamic.enums

enum class Equation(val type: String) {
    EQUAL("="),
    NOT_EQUAL("!="),
    IN("in"),
    NOT_IN("not_in"),
    EMPTY(""),
    NOT_EMPTY("not_empty"),
    GREATER_THAN(">"),
    LESS_THAN("<"),
    IS_NULL("is_null"),
    IS_NOT_NULL("is_not_null"),
    START_WITH("startWith")
}