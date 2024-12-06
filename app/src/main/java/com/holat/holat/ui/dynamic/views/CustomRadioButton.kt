package com.holat.holat.ui.dynamic.views

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.widget.RadioButton
import android.widget.RadioGroup
import com.holat.holat.R
import com.holat.holat.ui.dynamic.models.GlobalView
import com.holat.holat.ui.dynamic.models.SaveData
import com.holat.holat.ui.dynamic.models.TablesModel
import com.holat.holat.ui.dynamic.views.initialize.initializeLinearLCRadioButton
import com.holat.holat.utils.listener.OtherListener

fun RadioGroup.makeRadioGroupEmpty(
    radioGroup: RadioGroup,
    onCheckedChangeListener: RadioGroup.OnCheckedChangeListener,
) {
    radioGroup.setOnCheckedChangeListener(null)
    radioGroup.clearCheck()
    radioGroup.setOnCheckedChangeListener(onCheckedChangeListener)
}

class CustomRadioButton {
    private fun createRadioButton(
        context: Context,
        tablesModel: TablesModel, otherListener: OtherListener,
    ): RadioGroup {
        val layoutParams = initializeLinearLCRadioButton()
        val rb = arrayOfNulls<RadioButton>(tablesModel.lookUpValues!!.size)
        val rg = RadioGroup(context) //create the RadioGroup
        rg.orientation = RadioGroup.VERTICAL //or RadioGroup.HORIZONTAL
        rg.gravity = Gravity.START
        if (tablesModel.lookUpValues != null) {
            tablesModel.lookUpValues?.forEachIndexed { index, it ->
                rb[index] = RadioButton(context)
                rb[index]?.text = it.listName
                rb[index]?.id = it.lookUpListId.toInt()
                rb[index]?.buttonDrawable = null
                rb[index]?.setPadding(10, 5, 10, 5)
                // rb[index]?.setTextColor(app.statest.login.R.drawable.custom_color_radio_button)
                rb[index]?.setBackgroundResource(R.drawable.custom_radio_button)
                rb[index]?.gravity = Gravity.CENTER
                rb[index]?.layoutParams = layoutParams
                when (tablesModel.lookUpValues?.size) {
                    2 -> {
                        when (it.lookUpListId) {
                            tablesModel.lookUpValues?.get(0)?.lookUpListId -> {
                                if (tablesModel.value == tablesModel.lookUpValues?.get(0)?.lookUpListId.toString())
                                    rb[index]?.isChecked = true
                            }

                            tablesModel.lookUpValues?.get(1)?.lookUpListId -> {
                                if (tablesModel.value == tablesModel.lookUpValues?.get(1)?.lookUpListId.toString())
                                    rb[index]?.isChecked = true
                            }
                        }
                    }

                    3 -> {
                        when (it.lookUpListId) {
                            tablesModel.lookUpValues?.get(0)?.lookUpListId -> {
                                if (tablesModel.value == tablesModel.lookUpValues?.get(0)?.lookUpListId.toString())
                                    rb[index]?.isChecked = true
                            }

                            tablesModel.lookUpValues?.get(1)?.lookUpListId -> {
                                if (tablesModel.value == tablesModel.lookUpValues?.get(1)?.lookUpListId.toString())
                                    rb[index]?.isChecked = true
                            }

                            tablesModel.lookUpValues?.get(2)?.lookUpListId -> {
                                if (tablesModel.value == tablesModel.lookUpValues?.get(2)?.lookUpListId.toString())
                                    rb[index]?.isChecked = true
                            }
                        }
                    }

                    4 -> {
                        when (it.lookUpListId) {
                            tablesModel.lookUpValues?.get(0)?.lookUpListId -> {
                                if (tablesModel.value == tablesModel.lookUpValues?.get(0)?.lookUpListId.toString())
                                    rb[index]?.isChecked = true
                            }

                            tablesModel.lookUpValues?.get(1)?.lookUpListId -> {
                                if (tablesModel.value == tablesModel.lookUpValues?.get(1)?.lookUpListId.toString())
                                    rb[index]?.isChecked = true
                            }

                            tablesModel.lookUpValues?.get(2)?.lookUpListId -> {
                                if (tablesModel.value == tablesModel.lookUpValues?.get(2)?.lookUpListId.toString())
                                    rb[index]?.isChecked = true
                            }

                            tablesModel.lookUpValues?.get(3)?.lookUpListId -> {
                                if (tablesModel.value == tablesModel.lookUpValues?.get(3)?.lookUpListId.toString())
                                    rb[index]?.isChecked = true
                            }
                        }
                    }

                    5 -> {
                        when (it.lookUpListId) {
                            tablesModel.lookUpValues?.get(0)?.lookUpListId -> {
                                if (tablesModel.value == tablesModel.lookUpValues?.get(0)?.lookUpListId.toString())
                                    rb[index]?.isChecked = true
                            }

                            tablesModel.lookUpValues?.get(1)?.lookUpListId -> {
                                if (tablesModel.value == tablesModel.lookUpValues?.get(1)?.lookUpListId.toString())
                                    rb[index]?.isChecked = true
                            }

                            tablesModel.lookUpValues?.get(2)?.lookUpListId -> {
                                if (tablesModel.value == tablesModel.lookUpValues?.get(2)?.lookUpListId.toString())
                                    rb[index]?.isChecked = true
                            }

                            tablesModel.lookUpValues?.get(3)?.lookUpListId -> {
                                if (tablesModel.value == tablesModel.lookUpValues?.get(3)?.lookUpListId.toString())
                                    rb[index]?.isChecked = true
                            }

                            tablesModel.lookUpValues?.get(4)?.lookUpListId -> {
                                if (tablesModel.value == tablesModel.lookUpValues?.get(4)?.lookUpListId.toString())
                                    rb[index]?.isChecked = true
                            }
                        }
                    }

                    6 -> {
                        when (it.lookUpListId) {
                            tablesModel.lookUpValues?.get(0)?.lookUpListId -> {
                                if (tablesModel.value == tablesModel.lookUpValues?.get(0)?.lookUpListId.toString())
                                    rb[index]?.isChecked = true
                            }

                            tablesModel.lookUpValues?.get(1)?.lookUpListId -> {
                                if (tablesModel.value == tablesModel.lookUpValues?.get(1)?.lookUpListId.toString())
                                    rb[index]?.isChecked = true
                            }

                            tablesModel.lookUpValues?.get(2)?.lookUpListId -> {
                                if (tablesModel.value == tablesModel.lookUpValues?.get(2)?.lookUpListId.toString())
                                    rb[index]?.isChecked = true
                            }

                            tablesModel.lookUpValues?.get(3)?.lookUpListId -> {
                                if (tablesModel.value == tablesModel.lookUpValues?.get(3)?.lookUpListId.toString())
                                    rb[index]?.isChecked = true
                            }

                            tablesModel.lookUpValues?.get(4)?.lookUpListId -> {
                                if (tablesModel.value == tablesModel.lookUpValues?.get(4)?.lookUpListId.toString())
                                    rb[index]?.isChecked = true
                            }

                            tablesModel.lookUpValues?.get(5)?.lookUpListId -> {
                                if (tablesModel.value == tablesModel.lookUpValues?.get(5)?.lookUpListId.toString())
                                    rb[index]?.isChecked = true
                            }
                        }
                    }
                }

                rg.setOnCheckedChangeListener(
                    onCheckedChangeListener(
                        tablesModel,
                        otherListener
                    )
                )
                rg.addView(rb[index])
            }
        }
        return rg
    }

    private fun onCheckedChangeListener(
        tablesModel: TablesModel,
        otherListener: OtherListener,
    ) =
        RadioGroup.OnCheckedChangeListener { _, p1 ->
            when (tablesModel.lookUpValues?.size) {
                2 -> {
                    when (p1) {
                        tablesModel.lookUpValues?.get(0)?.lookUpListId?.toInt() -> {
                            tablesModel.value =
                                tablesModel.lookUpValues?.get(0)?.lookUpListId.toString()
                        }

                        tablesModel.lookUpValues?.get(1)?.lookUpListId?.toInt() -> {
                            tablesModel.value =
                                tablesModel.lookUpValues?.get(1)?.lookUpListId.toString()
                        }
                    }
                    otherListener.other(tablesModel)
                }

                3 -> {
                    when (p1) {
                        tablesModel.lookUpValues?.get(0)?.lookUpListId?.toInt() -> {
                            tablesModel.value =
                                tablesModel.lookUpValues?.get(0)?.lookUpListId.toString()
                        }

                        tablesModel.lookUpValues?.get(1)?.lookUpListId?.toInt() -> {
                            tablesModel.value =
                                tablesModel.lookUpValues?.get(1)?.lookUpListId.toString()
                        }

                        tablesModel.lookUpValues?.get(2)?.lookUpListId?.toInt() -> {
                            tablesModel.value =
                                tablesModel.lookUpValues?.get(2)?.lookUpListId.toString()
                        }
                    }
                    otherListener.other(tablesModel)
                }

                4 -> {
                    when (p1) {
                        tablesModel.lookUpValues?.get(0)?.lookUpListId?.toInt() -> {
                            tablesModel.value =
                                tablesModel.lookUpValues?.get(0)?.lookUpListId.toString()
                        }

                        tablesModel.lookUpValues?.get(1)?.lookUpListId?.toInt() -> {
                            tablesModel.value =
                                tablesModel.lookUpValues?.get(1)?.lookUpListId.toString()
                        }

                        tablesModel.lookUpValues?.get(2)?.lookUpListId?.toInt() -> {
                            tablesModel.value =
                                tablesModel.lookUpValues?.get(2)?.lookUpListId.toString()
                        }

                        tablesModel.lookUpValues?.get(3)?.lookUpListId?.toInt() -> {
                            tablesModel.value =
                                tablesModel.lookUpValues?.get(3)?.lookUpListId.toString()
                        }
                    }
                    otherListener.other(tablesModel)
                }

                5 -> {
                    when (p1) {
                        tablesModel.lookUpValues?.get(0)?.lookUpListId?.toInt() -> {
                            tablesModel.value =
                                tablesModel.lookUpValues?.get(0)?.lookUpListId.toString()
                        }

                        tablesModel.lookUpValues?.get(1)?.lookUpListId?.toInt() -> {
                            tablesModel.value =
                                tablesModel.lookUpValues?.get(1)?.lookUpListId.toString()
                        }

                        tablesModel.lookUpValues?.get(2)?.lookUpListId?.toInt() -> {
                            tablesModel.value =
                                tablesModel.lookUpValues?.get(2)?.lookUpListId.toString()
                        }

                        tablesModel.lookUpValues?.get(3)?.lookUpListId?.toInt() -> {
                            tablesModel.value =
                                tablesModel.lookUpValues?.get(3)?.lookUpListId.toString()
                        }

                        tablesModel.lookUpValues?.get(4)?.lookUpListId?.toInt() -> {
                            tablesModel.value =
                                tablesModel.lookUpValues?.get(4)?.lookUpListId.toString()
                        }
                    }
                    otherListener.other(tablesModel)
                }

                6 -> {
                    when (p1) {
                        tablesModel.lookUpValues?.get(0)?.lookUpListId?.toInt() -> {
                            tablesModel.value =
                                tablesModel.lookUpValues?.get(0)?.lookUpListId.toString()
                        }

                        tablesModel.lookUpValues?.get(1)?.lookUpListId?.toInt() -> {
                            tablesModel.value =
                                tablesModel.lookUpValues?.get(1)?.lookUpListId.toString()
                        }

                        tablesModel.lookUpValues?.get(2)?.lookUpListId?.toInt() -> {
                            tablesModel.value =
                                tablesModel.lookUpValues?.get(2)?.lookUpListId.toString()
                        }

                        tablesModel.lookUpValues?.get(3)?.lookUpListId?.toInt() -> {
                            tablesModel.value =
                                tablesModel.lookUpValues?.get(3)?.lookUpListId.toString()
                        }

                        tablesModel.lookUpValues?.get(4)?.lookUpListId?.toInt() -> {
                            tablesModel.value =
                                tablesModel.lookUpValues?.get(4)?.lookUpListId.toString()
                        }

                        tablesModel.lookUpValues?.get(5)?.lookUpListId?.toInt() -> {
                            tablesModel.value =
                                tablesModel.lookUpValues?.get(5)?.lookUpListId.toString()
                        }
                    }
                    otherListener.other(tablesModel)
                }
            }
        }

    suspend fun drawRadioButton(
        allAnswersFirstTable: ArrayList<SaveData>,
        globalView: GlobalView,
        activity: Activity,
        //lookUpViewModel: LookUpViewModel,
        checkRuleMovementListener: OtherListener,
        drawView: suspend (GlobalView) -> Unit
    ):GlobalView {
        if (globalView.tablesModel.questLookUpId.isNotEmpty()) {
//            val result = lookUpViewModel.getLookUpModelMethod(
//                globalView.tablesModel.questLookUpId.toInt(),
//                "1"
//            )
//            if (result != null) {
//                globalView.tablesModel.lookUpValues = result
//                allAnswersFirstTable.forEach {
//                    if (it.columnName.lowercase() == globalView.tablesModel.questDbColumnName.lowercase())
//                        globalView.tablesModel.value = it.value
//                }
//                globalView.radioGroupView = CustomView()
//                globalView.radioGroupView?.title = activity.drawLabelOrTitle(true, globalView)
//                globalView.radioGroupView?.typeView = createRadioButton(
//                    context = activity,
//                    tablesModel = globalView.tablesModel,
//                    otherListener = checkRuleMovementListener)
//                drawView(globalView)
//            }
        }
        return globalView
    }
}