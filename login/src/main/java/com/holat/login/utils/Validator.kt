package com.holat.login.utils

import android.util.Patterns
import android.view.View
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import javax.inject.Inject

class Validator @Inject constructor() {

    private var subject: String? = null
    private var editText: TextView? = null
    private var messageBuilders: MutableList<MessageBuilder> = ArrayList()
    private var textInputLayout: TextInputLayout? = null

    fun submit(s: TextView): Validator {
        subject = s.text.toString().trim { it <= ' ' }
        editText = s
        messageBuilders = ArrayList()
        return this
    }

    fun submit(s: TextView, textInputLayout: TextInputLayout): Validator {
        subject = s.text.toString()
        this.textInputLayout = textInputLayout
        messageBuilders = ArrayList()
        return this
    }


    fun checkEmpty(): MessageBuilder {
        return MessageBuilder(Type.EMPTY)
    }

    fun checkEmptyWithoutTrim(): MessageBuilder {
        return MessageBuilder(Type.EMPTY_WITHOUT_TRIM)
    }

    fun checkValidEmail(): MessageBuilder {
        return MessageBuilder(Type.EMAIL)
    }
    fun checkValidWebsite(): MessageBuilder {
        return MessageBuilder(Type.WEB_SITE)
    }

    fun checkZero(): MessageBuilder {
        return MessageBuilder(Type.ZERO)
    }

    fun checkValidPhone(): MessageBuilder {
        return MessageBuilder(Type.PHONE)
    }

    fun checkMaxDigits(max: Int): MessageBuilder {

        return MessageBuilder(Type.MAX_LENGTH, max)
    }

    fun checkMinDigits(min: Int): MessageBuilder {
        return MessageBuilder(Type.MIN_LENGTH, min)
    }
    fun checkStartWith(startWith: String): MessageBuilder {
        return MessageBuilder(Type.START_FIVE,startWith)
    }
    fun checkWithoutPadding(max:Int): MessageBuilder {
        return MessageBuilder(Type.NOT_PADDING,max)
    }
    fun checkZeroDigits(): MessageBuilder {
        return MessageBuilder(Type.ZERO_DIGITS)
    }

    fun matchString(s: String): MessageBuilder {
        return MessageBuilder(Type.MATCH, s)
    }

    fun matchNotString(s: String): MessageBuilder {
        return MessageBuilder(Type.MATCH_NOT, s)
    }

    fun matchPatter(patter: String): MessageBuilder {
        return MessageBuilder(Type.PATTERN_MATCH, patter)
    }

    @Throws(ApplicationException::class)
    fun check(): Boolean {
        if (editText!!.visibility == View.VISIBLE) {
            for (builder in messageBuilders) {

                try {
                    when (builder.type) {
                        Type.EMPTY -> isEmpty(subject, builder.message, true)
                        Type.EMPTY_WITHOUT_TRIM -> isEmpty(subject, builder.message, false)
                        Type.EMAIL -> isValidEmail(subject!!, builder.message)
                        Type.WEB_SITE -> isValidWebsite(subject!!, builder.message)
                        Type.MAX_LENGTH -> checkMaxDigits(subject!!, builder.validLength, builder.message)
                        Type.MIN_LENGTH -> checkMinDigits(subject!!, builder.validLength, builder.message)
                        Type.START_FIVE -> checkStartWith(subject!!,builder.match, builder.message)
                        Type.NOT_PADDING -> checkWithoutPadding(subject!!,builder.validLength, builder.message)
                        Type.ZERO_DIGITS -> checkZeroDigits(subject!!, builder.message)
                        Type.MATCH -> match(subject, builder.match, builder.message)
                        Type.PATTERN_MATCH -> matchPatter(subject, builder.match, builder.message)
                        Type.MATCH_NOT -> matchNot(subject, builder.match, builder.message)
                        Type.ZERO -> match(subject, builder.match, builder.message)
                        // Type.PHONE -> match(subject, builder.match, builder.message)
                        else -> {}
                    }

                    if (textInputLayout != null) {
                        textInputLayout!!.error = null
                        //Log.e("Validation", "No error");
                    }

                } catch (e: ApplicationException) {

                    editText!!.requestFocus()
                    /*setUpperHintColor(R.color.colorAppRed);*/
                    if (textInputLayout != null) {
                        //Log.e("Validation", " Error ");
                        if (!textInputLayout!!.isErrorEnabled)
                            textInputLayout!!.isErrorEnabled = true
                        textInputLayout!!.error = e.localizedMessage
                    }
                    e.type = ApplicationException.Type.VALIDATION
                    throw e
                }


            }
            return true
        } else {
            return true
        }
    }

    /*    private void setUpperHintColor(int color) {
            try {
                Field field = textInputLayout.getClass().getDeclaredField("mFocusedTextColor");
                field.setAccessible(true);
                int[][] states = new int[][]{
                        new int[]{}
                };
                int[] colors = new int[]{
                        color
                };
                ColorStateList myList = new ColorStateList(states, colors);
                field.set(textInputLayout, myList);
                Method method = textInputLayout.getClass().getDeclaredMethod("updateLabelState", boolean.class);
                method.setAccessible(true);
                method.invoke(textInputLayout, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    @Throws(ApplicationException::class)
    private fun isEmpty(subject: String?, errorMessage: String?, byTrimming: Boolean) {
        var subjectVar: String = subject ?: throw ApplicationException(errorMessage)

        if (byTrimming)
            subjectVar = subjectVar.trim { it <= ' ' }

        if (subjectVar.isEmpty())
            throw ApplicationException(errorMessage)
    }

    @Throws(ApplicationException::class)
    private fun isValidEmail(subject: String, errorMessage: String?) {
        if (!subject.matches(Patterns.EMAIL_ADDRESS.pattern().toRegex()))
            throw ApplicationException(errorMessage)
    }

    @Throws(ApplicationException::class)
    private fun isValidWebsite(subject: String, errorMessage: String?) {
        if (!subject.matches(Patterns.DOMAIN_NAME.pattern().toRegex()))
            throw ApplicationException(errorMessage)
    }
    @Throws(ApplicationException::class)
    private fun checkMinDigits(subject: String, min: Int, errorMessage: String?) {
        if (subject.length < min)
            throw ApplicationException(errorMessage)
    }
    @Throws(ApplicationException::class)
    private fun checkWithoutPadding(subject: String, min: Int, errorMessage: String?) {
        if (subject.trim().length < min)
            throw ApplicationException(errorMessage)
    }

    @Throws(ApplicationException::class)
    private fun checkStartWith(subject: String,startWith:String?, errorMessage: String?) {
        if (startWith == null || !subject.startsWith(startWith))
            throw ApplicationException(errorMessage)
    }
    @Throws(ApplicationException::class)
    private fun checkMaxDigits(subject: String, max: Int, errorMessage: String?) {
        if (subject.length > max)
            throw ApplicationException(errorMessage)
    }

    @Throws(ApplicationException::class)
    private fun match(subject: String?, toMatch: String?, errorMessage: String?) {
        if (toMatch == null || subject != toMatch)
            throw ApplicationException(errorMessage)
    }
    @Throws(ApplicationException::class)
    private fun checkZeroDigits(subject: String, errorMessage: String?) {
        if (subject == "00000000" ||subject == "000000000" || subject == "0000000000")
            throw ApplicationException(errorMessage)
    }
    @Throws(ApplicationException::class)
    private fun matchNot(subject: String?, toMatch: String?, errorMessage: String?) {
        if (toMatch == null || subject == toMatch)
            throw ApplicationException(errorMessage)
    }

    @Throws(ApplicationException::class)
    private fun matchPatter(subject: String?, pattern: String?, errorMessage: String?) {

        if (subject == null || !subject.matches(pattern!!.toRegex()))
            throw ApplicationException(errorMessage)
    }


    enum class Type {
        EMPTY, EMAIL, WEB_SITE,MIN_LENGTH, MAX_LENGTH, MATCH, MATCH_NOT, PATTERN_MATCH, EMPTY_WITHOUT_TRIM, PHONE, ZERO, START_FIVE,NOT_PADDING,ZERO_DIGITS
    }

    inner class MessageBuilder {
        val type: Type
        var message: String? = null
            private set

        internal var validLength: Int = 0
        internal var match: String? = null

        internal constructor(type: Type) {
            this.type = type
        }

        internal constructor(type: Type, validLength: Int) {
            this.type = type
            this.validLength = validLength
        }

        internal constructor(type: Type, match: String) {
            this.type = type
            this.match = match
        }
        fun errorMessage(message: String): Validator {
            this.message = message
            messageBuilders.add(this)
            return this@Validator
        }
        fun errorMessage(message: Int): Validator {
            this.message = editText!!.context.getString(message)
            messageBuilders.add(this)
            return this@Validator
        }


    }
}