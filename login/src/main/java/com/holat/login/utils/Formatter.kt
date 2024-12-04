package com.holat.login.utils

import android.util.Log
import androidx.annotation.NonNull
import androidx.annotation.StringDef
import java.text.DateFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.pow
import kotlin.math.roundToInt

object Formatter {
    internal var suffixes = arrayOf("th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
        //    10    11    12    13    14    15    16    17    18    19
        "th", "th", "th", "th", "th", "th", "th", "th", "th", "th",
        //    20    21    22    23    24    25    26    27    28    29
        "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
        //    30    31
        "th", "st")//    0     1     2     3     4     5     6     7     8     9

    // CREATE DateFormatSymbols WITH ALL SYMBOLS FROM (DEFAULT) Locale
    private val symbols: DateFormatSymbols = DateFormatSymbols(Locale.getDefault())

    const val JAN_2017 = "MMM / yyyy"
    const val MM_YY = "MM/yy"
    const val MM_YYYY = "MM / yyyy"
    const val MMM = "MMM"
    const val MM = "MM"
    const val DD = "dd"
    const val MMMM = "MMMM"
    const val YYYY = "yyyy"
    const val MMMM_YYYY = "MMMM yyyy"
    const val MMM_YYYY = "MMM,yyyy"
    const val DD_MM_YYYY = "dd/MM/yyyy"
    const val DD_MMM_YYYY = "dd MMM yyyy"
    const val DD_MMMM_YYYY_FULL = "dd MMMM, yyyy"
    const val DD_MMMM_YYYY = "dd MMMM yyyy"
     const val EE_DD_MMMM_YYYY = "EEE     dd MMMM yyyy"

    const val YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd hh:mm:ss"
    const val YYYY_MM_DD_HH_MM_SS_24 = "yyyy-MM-dd HH:mm:ss"
    const val DD_MM_YYYY_HH_MM_SS_24 = "dd/MM/yyyy HH:mm:ss"
    const val YYYY_MM_DD_hh_mm_aa = "yyyy-MM-dd hh:mm aa"
    const val DD_MMMM_YYYY_hh_mm_aa = "dd MMMM yyyy hh:mm aa"
    const val DD_MMM_YYYY_hh_mm_aa = "dd/MM/yyyy hh:mm aa"
    const val CALL_LOG_TIME = "dd MMM, yyyy - hh:mm aa"
    const val YYYY_MM_DD_T_HH_MM_SSS_Z = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'"
    val EEE_MMM_DD_HH_MM_SS_ZZZ_YYYY = "EEE MMM dd HH:mm:ss zzz yyyy"
    const val YYYY_MM_DD_T_HH_MM_SSS_Z_24 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val YYYY_MM_DD = "yyyy-MM-dd"
    const val YYYYMMDD = "yyyyMMdd"
    const val MM_DD_YYYY = "MM/dd/yyyy"
    private const val UTC = "UTC"
    const val DD_MMM_YYYY_SLASH_HH_MM_AA="dd MMM, yyyy | hh:mm aa"
    const val HH_mm = "HH:mm"
    const val hh_mm = "hh:mm"
    const val HH_mm_ss = "hh:mm:ss"
    const val hh_mm_aa = "hh:mm aa"

    private val SECOND: Long = 1000
    private val MINUTE = SECOND * 60
    private val HOUR = MINUTE * 60
    private val DAY = HOUR * 24
    private val WEEK = DAY * 7
    private val MONTH = DAY * 30
    private val YEAR = MONTH * 12

    private val inputTimeZone = UTC

    init {
        // OVERRIDE SOME symbols WHILE RETAINING OTHERS
        symbols.amPmStrings = arrayOf("am", "pm")
    }

    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    @StringDef(MM_DD_YYYY, YYYY_MM_DD, JAN_2017, MM_YYYY, DD_MMM_YYYY, MM_YY, YYYY_MM_DD_HH_MM_SS, YYYY_MM_DD_T_HH_MM_SSS_Z, YYYY_MM_DD_hh_mm_aa, CALL_LOG_TIME, DD_MMMM_YYYY_hh_mm_aa, DD_MMM_YYYY_hh_mm_aa, DD_MM_YYYY, MM, MMM, MMMM, YYYY, DD_MMMM_YYYY, DD_MMMM_YYYY_FULL, HH_mm, HH_mm_ss, hh_mm_aa,EE_DD_MMMM_YYYY)
    annotation class FORMAT

    fun round(v: Double, places: Int): Double {
        var value = v
        if (places < 0) throw IllegalArgumentException()

        val factor = Math.pow(10.0, places.toDouble()).toLong()
        value *= factor
        val tmp = Math.round(value)
        return tmp.toDouble() / factor
    }

    fun round(v: Double): String {
        var value = v
        val factor = 10.0.pow(2.0).toLong()
        value *= factor
        val tmp = value.roundToInt()
        val valueV = tmp.toDouble() / factor
        return String.format(Locale.US, "%.2f", valueV)
    }

    /**
     * Check if two calendar objects has same date,month,year
     *
     * @param first
     * @param second
     * @return
     */
    fun matches(first: Calendar, second: Calendar): Boolean {
        val fDay = first.get(Calendar.DAY_OF_MONTH)
        val fMonth = first.get(Calendar.MONTH)
        val fYear = first.get(Calendar.YEAR)
        val sDay = second.get(Calendar.DAY_OF_MONTH)
        val sMonth = second.get(Calendar.MONTH)
        val sYear = second.get(Calendar.YEAR)
        return fDay == sDay && fMonth == sMonth && fYear == sYear
    }

    fun format(locale: Locale, datetime: String, @FORMAT inFormat: String, @FORMAT outFormat: String, isUtc: Boolean): String? {
        try {
            val input = SimpleDateFormat(inFormat, locale)
            if (isUtc)
                input.timeZone = TimeZone.getTimeZone(inputTimeZone)
            val date = input.parse(datetime)
            val output = SimpleDateFormat(outFormat, locale)
            output.timeZone = TimeZone.getDefault()
            output.dateFormatSymbols = symbols

            return output.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    @JvmOverloads
    fun format(datetime: String, @FORMAT inFormat: String, @FORMAT outFormat: String, isUtc: Boolean = false): String? {
        return format(Locale.US, datetime, inFormat, outFormat, isUtc)
    }

    @JvmOverloads
    fun format(datetime: Calendar, @FORMAT outFormat: String, isUtc: Boolean = false): String? {
        try {
            val date = datetime.time
            val output = SimpleDateFormat(outFormat, Locale.US)
            if (isUtc)
                output.timeZone = TimeZone.getTimeZone(inputTimeZone)
            else
                output.timeZone = TimeZone.getDefault()

            output.dateFormatSymbols = symbols

            return output.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    fun format(date: Date, @FORMAT outFormat: String): String? {
        try {
            val output = SimpleDateFormat(outFormat, Locale.US)
            output.dateFormatSymbols = symbols
            output.timeZone = TimeZone.getDefault()
            return output.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    @JvmOverloads
    fun getCalendar(datetime: String, @FORMAT inFormat: String, isUtc: Boolean = false): Calendar? {
        try {
            val output = SimpleDateFormat(inFormat, Locale.US)
            output.dateFormatSymbols = symbols
            if (isUtc)
                output.timeZone = TimeZone.getTimeZone(inputTimeZone)
            val calendar = Calendar.getInstance()
            calendar.timeZone = TimeZone.getDefault()
            val date = output.parse(datetime)
            if (date != null)
                calendar.time = date
            return calendar
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    fun covertTimeToText(dataDate: String): String? {
        var convTime: String? = null
        val prefix = ""
        val suffix = "Ago"

        try {
            val dateFormat = SimpleDateFormat(YYYY_MM_DD_HH_MM_SS,Locale.US)
            dateFormat.timeZone = TimeZone.getTimeZone("GMT");
            val pasTime = dateFormat.parse(dataDate)

            val nowTime = Date()

            val dateDiff = nowTime.time - pasTime.time

            val second = TimeUnit.MILLISECONDS.toSeconds(dateDiff)
            val minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff)
            val hour = TimeUnit.MILLISECONDS.toHours(dateDiff)
            val day = TimeUnit.MILLISECONDS.toDays(dateDiff)

            if (second < 60) {
                convTime = second.toString() + " Seconds " + suffix
            } else if (minute < 60) {
                convTime = minute.toString() + " Minutes " + suffix
            } else if (hour < 24) {
                convTime = hour.toString() + " Hours " + suffix
            } else if (day >= 7) {
                if (day > 30) {
                    convTime = (day / 30).toString() + " Months " + suffix
                } else if (day > 360) {
                    convTime = (day / 360).toString() + " Years " + suffix
                } else {
                    convTime = (day / 7).toString() + " Week " + suffix
                }
            } else if (day < 7) {
                convTime = day.toString()+ " Days " + suffix
            }

        } catch (e: ParseException) {
            e.printStackTrace()
            Log.e("ConvTimeE", e.message.toString())
        }

        return convTime

    }

    /*public static String getPrettyTime(String lastTime, @FORMAT String inFormat) {
        return getPrettyTime(Locale.ENGLISH, "", lastTime, inFormat)
    }
    public static String getPrettyTime(String lastTime, @FORMAT String inFormat, boolean isUtc) {
        return getPrettyTime(Locale.ENGLISH, "", "", lastTime, inFormat, isUtc)
    }
    public static String getPrettyTime(Locale locale, String lastTime, @FORMAT String inFormat) {
        return getPrettyTime(locale, "", lastTime, inFormat)
    }
    public static String getPrettyTime(Locale locale, String prefix, String datetime, @FORMAT String inFormat) {
        return getPrettyTime(locale, prefix, datetime, inFormat, false)
    }
    public static String getPrettyTime(Locale locale, String prefix, String datetime, @FORMAT String inFormat, boolean isUtc) {
        return getPrettyTime(locale, prefix, "", datetime, inFormat, isUtc)
    }*/

    /*fun getPrettyTime(context: Context, locale: Locale, @NonNull formattedString: String, @Nullable datetime: String?, @FORMAT inFormat: String, isUtc: Boolean): String {
        try {
            val date = getCalendar(datetime!!, inFormat, isUtc)!!.time
            //Initialize both calendar with set time
            val calendarDate = Calendar.getInstance()
            calendarDate.time = date
            val current = Calendar.getInstance()
            //DebugLog.i("InsertDate::::"+ Formatter.format(calendarDate, DD_MMMM_YYYY_hh_mm_aa))
            //DebugLog.i("CurrentDate::::"+ Formatter.format(current, DD_MMMM_YYYY_hh_mm_aa))
            val difference = current.timeInMillis - calendarDate.timeInMillis
            val year = difference / YEAR
            val month = difference / MONTH
            val weeks = difference / WEEK
            val day = difference / DAY
            val hour = difference / HOUR
            val minute = difference / MINUTE
            val second = difference / SECOND
            var time: String? = null
            if (year > 0)
                time = String.format(locale, "%d %s", year, if (year > 1) context.getString(R.string.years_ago) else context.getString(R.string.year_ago))
            else if (month > 0)
                time = String.format(locale, "%d %s", month, if (month > 1) context.getString(R.string.months_ago) else context.getString(R.string.month_ago))
            else if (weeks > 0)
                time = String.format(locale, "%d %s", weeks, if (weeks > 1) context.getString(R.string.weeks_ago) else context.getString(R.string.week_ago))
            else if (day > 0)
                time = String.format(locale, "%d %s", day, if (day > 1) context.getString(R.string.days_ago) else context.getString(R.string.day_ago))
            else if (hour > 0)
                time = String.format(locale, "%d %s", hour, if (hour > 1) context.getString(R.string.hours_ago) else context.getString(R.string.hour_ago))
            else if (minute > 0)
                time = String.format(locale, "%d %s", minute, if (minute > 1) context.getString(R.string.minutes_ago) else context.getString(R.string.minute_ago))
            else if (second > 0)
                time = String.format(locale, "%d %s", second, if (second > 1) context.getString(R.string.seconds_ago) else context.getString(R.string.second_ago))
            return if (time != null) String.format(formattedString, time) else context.getString(R.string.just_now)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }*/

    fun getTimeorDate(datetime: String, @FORMAT inFormat: String, isUtc: Boolean): String? {
        try {
            //Initialize both calendar with set time
            val calendarDate = getCalendar(datetime, inFormat, isUtc)
            val current = Calendar.getInstance()

            return if (matches(calendarDate!!, current)) {
                format(calendarDate.time, hh_mm_aa)
            } else {
                format(calendarDate.time, CALL_LOG_TIME)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }

    /*fun getDuration(context: Context, locale: Locale, formattedString: String): String {
        try {
            val tokens = formattedString.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val hour = Integer.parseInt(tokens[0])
            val minute = Integer.parseInt(tokens[1])
            val second = Integer.parseInt(tokens[2])
            //long difference = 3600 * hour + 60 * minute + second
            var time: String? = null
            if (hour > 0)
                time = String.format(locale, "%d %s", hour, if (hour > 1) context.getString(R.string.hours) else context.getString(R.string.hour))
            else if (minute > 0)
                time = String.format(locale, "%d %s", minute, if (minute > 1) context.getString(R.string.minutes) else context.getString(R.string.minute))
            else
                time = String.format(locale, "%d %s", second, if (second > 1) context.getString(R.string.seconds) else context.getString(R.string.second))
            return if (time != null) String.format("%s", time) else ""
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }*/

    fun getDateString(@FORMAT outFormat: String): String? {
        return format(Calendar.getInstance(), outFormat)
    }

    fun convert(timestamp: String): String? {
        var localTime: String? = null
        try {
            val time = java.lang.Long.valueOf(timestamp)
            val cal = Calendar.getInstance()
            val tz = cal.timeZone

            /* debug: is it local time? */
            // DebugLog.d("Time zone: ", tz.getDisplayName())

            /* date formatter in local timezone */
            val sdf = SimpleDateFormat(YYYY_MM_DD_HH_MM_SS)
            sdf.timeZone = tz

            /* print your timestamp and double check it's the date you expect */
            // I assume your timestamp is in seconds and you're converting to milliseconds?
            localTime = sdf.format(Date(time * 1000))
            //DebugLog.d("Time: ", localTime)
            return localTime
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }

        return ""
    }

    /*fun getChatTime(context: Context, lastTime: String, @FORMAT inFormat: String, isUtc: Boolean): String? {
        try {
            val input = SimpleDateFormat(inFormat, Locale.US)
            if (isUtc)
                input.timeZone = TimeZone.getTimeZone(inputTimeZone)
            val date = input.parse(lastTime)
            //Initialize both calendar with set time
            val calendarDate = Calendar.getInstance()
            calendarDate.time = date
            val current = Calendar.getInstance()
            resetSeconds(current)
            val difference = Math.abs(calendarDate.timeInMillis - current.timeInMillis)
            val second = difference / SECOND
            if (second == 0L)
                return context.getString(R.string.just_now)
            return if (matches(current, calendarDate))
                format(calendarDate, hh_mm_aa)
            else
                format(calendarDate, CALL_LOG_TIME)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }*/

    fun resetSeconds(calendar: Calendar) {
        calendar.set(Calendar.MILLISECOND, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
    }

    fun getAge(@NonNull date: Calendar): Long {
        val age: Long = Calendar.getInstance().timeInMillis - date.timeInMillis
        return age / YEAR
    }
     fun getCurrentData(value: Int): String {
        val calender = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat(DD_MM_YYYY, Locale.US)
        calender.add(Calendar.DAY_OF_YEAR, value)
         Log.e("Dateee",simpleDateFormat.format(calender.time))
        return simpleDateFormat.format(calender.time)
    }
    fun getCurrentDate(): String {
        val calender = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat(DD_MM_YYYY_HH_MM_SS_24, Locale.ENGLISH)
        return simpleDateFormat.format(calender.time)
    }
    fun compareDates(oldDate:String):Boolean{
        var value = false
        try {
            val inPutFormat = SimpleDateFormat(DD_MM_YYYY, Locale.ENGLISH)
            val currentDateAfterFormat = inPutFormat.parse(getCurrentDate())
            val oldDateAfterFormat = inPutFormat.parse(oldDate)
            value = currentDateAfterFormat > oldDateAfterFormat
        }catch (e:Exception){
            e.printStackTrace()
        }
        return value
    }

    fun getStampTimeID(): String {
        var fullDate= ""
        val c = Calendar.getInstance()
        val hour = String.format(Locale.ENGLISH,"%02d", c[Calendar.HOUR_OF_DAY])
        val mint = String.format(Locale.ENGLISH,"%02d", c[Calendar.MINUTE])
        val sec = String.format(Locale.ENGLISH,"%02d", c[Calendar.SECOND])
        val milliSec = String.format(Locale.ENGLISH,"%02d", c[Calendar.MILLISECOND])
        //      val numberEnglish =  NumberFormat.getInstance(Locale("en","SA"))
//        val hour = numberEnglish.format( c[Calendar.HOUR_OF_DAY])
//        val mint = numberEnglish.format( c[Calendar.MINUTE])
//        val sec = numberEnglish.format( c[Calendar.SECOND])
//        val milliSec = numberEnglish.format(c[Calendar.MILLISECOND])
        val todayDate = (c[Calendar.YEAR] * 10000 + (c[Calendar.MONTH] + 1) * 100 + c[Calendar.DAY_OF_MONTH])
        val dateString = todayDate.toString()
        try {
            val year = String.format(Locale.ENGLISH,"%04d", dateString.substring(0, 4).toInt())
            val month = String.format(Locale.ENGLISH,"%02d", dateString.substring(4, 6).toInt())
            val day = String.format(Locale.ENGLISH,"%02d", dateString.substring(6, 8).toInt())
            fullDate = day + month + year + hour + mint + sec + milliSec
        } catch (e: java.lang.Exception) {
            e.message
        }
        return fullDate
    }
}