package com.holat.login.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText

fun AppCompatEditText.checkDotTextWatcher() {
    var test = ""
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            try {
                if (p0.toString().isNotEmpty()) {
                    if (p0.toString().length != p1) {
                        val dot: Char = p0.toString()[p1]
                        if (dot == '.') {
                            if (!test.contains(".")) {
                                test = p0.toString()
                            } else {
                                val afterChange = p0.toString().removeRange(p1, p1 + 1)
                                test = afterChange
                                removeTextChangedListener(this)
                                setText(afterChange)
                                setSelection(p1)
                                addTextChangedListener(this)
                            }
                        } else
                            test = p0.toString()
                    }
                }
            } catch (e: Exception) {

            }

        }

        override fun afterTextChanged(p0: Editable?) {

        }
    })
}

fun AppCompatEditText.checkDotTextWatcher(text: String, variable: (String) -> Unit) {
    var enterText = text
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            try {
                if (p0.toString().isNotEmpty()) {
                    if (p0.toString().length != p1) {
                        val dot: Char = p0.toString()[p1]
                        if (dot == '.') {
                            if (!enterText.contains(".")) {
                                enterText = p0.toString()
                            } else {
                                val afterChange = p0.toString().removeRange(p1, p1 + 1)
                                enterText = afterChange
                                removeTextChangedListener(this)
                                setText(afterChange)
                                setSelection(p1)
                                addTextChangedListener(this)
                            }
                        } else
                            enterText = p0.toString()
                    } else
                        enterText = p0.toString()
                } else
                    enterText = p0.toString()
                variable(enterText)
            } catch (e: Exception) {

            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }
    })
}

fun AppCompatEditText.setTextWatcher(variable: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            try {
                variable(p0.toString())
            } catch (e: Exception) {

            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }
    })
}

fun AppCompatEditText.setTextWatcher(variable: (String) -> Unit, afterTextChanged: () -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            try {
                variable(p0.toString())
            } catch (e: Exception) {

            }
        }

        override fun afterTextChanged(p0: Editable?) {
            afterTextChanged()

        }
    })
}

fun AppCompatEditText.checkDotTextWatcher(
    text: String,
    variable: (String) -> Unit,
    afterTextChanged: () -> Unit
) {
    var enterText = text
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            try {
                if (p0.toString().isNotEmpty()) {
                    if (p0.toString().length != p1) {
                        val dot: Char = p0.toString()[p1]
                        if (dot == '.') {
                            if (!enterText.contains(".")) {
                                enterText = p0.toString()
                            } else {
                                val afterChange = p0.toString().removeRange(p1, p1 + 1)
                                enterText = afterChange
                                removeTextChangedListener(this)
                                setText(afterChange)
                                setSelection(p1)
                                addTextChangedListener(this)
                            }
                        } else
                            enterText = p0.toString()
                    } else
                        enterText = p0.toString()
                } else
                    enterText = p0.toString()
                variable(enterText)
            } catch (e: Exception) {

            }
        }

        override fun afterTextChanged(p0: Editable?) {
            afterTextChanged()
        }
    })
}

fun AppCompatEditText.checkDotAndMinsTextWatcher(
    text: String,
    variable: (String) -> Unit,
    afterTextChanged: () -> Unit
) {
    var enterText = text
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            try {
                if (p0.toString().isNotEmpty()) {
                    if (p0.toString().length != p1) {
                        val dot: Char = p0.toString()[p1]
                        if (dot == '.') {
                            if (!enterText.contains(".")) {
                                enterText = p0.toString()
                            } else {
                                val afterChange = p0.toString().removeRange(p1, p1 + 1)
                                enterText = afterChange
                                removeTextChangedListener(this)
                                setText(enterText)
                                setSelection(p1)
                                addTextChangedListener(this)
                            }
                        } else if (dot == '-') {
                                if (!enterText.contains("-")) {
                                    if (p1 > 0) {
                                        val afterChange = p0.toString().removeRange(p1, p1 + 1)
                                        enterText = dot + afterChange
                                        removeTextChangedListener(this)
                                        setText(enterText)
                                        setSelection(p1+ 1)
                                        addTextChangedListener(this)
                                    } else
                                    enterText = p0.toString()
                                } else {
                                    val afterChange = p0.toString().removeRange(p1, p1 + 1)
                                    enterText = afterChange
                                    removeTextChangedListener(this)
                                    setText(enterText)
                                    setSelection(p1)
                                    addTextChangedListener(this)
                                }
                        } else
                            enterText = p0.toString()
                    } else
                        enterText = p0.toString()
                } else
                    enterText = p0.toString()
                variable(enterText)
            } catch (e: Exception) {

            }
        }

        override fun afterTextChanged(p0: Editable?) {
            afterTextChanged()
        }
    })
}

fun AppCompatEditText.setOnEditorActionListener() {
    setOnKeyListener(object : View.OnKeyListener {
        override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
            if (p2?.action == KeyEvent.ACTION_DOWN || p1 == KeyEvent.KEYCODE_ENTER) {
                if (p2?.keyCode == KeyEvent.KEYCODE_NUMPAD_DOT) {
                    showSnackBar("text: $${p2.keyCode}")
                    return true
                }


            }
            return false
        }


    })
}