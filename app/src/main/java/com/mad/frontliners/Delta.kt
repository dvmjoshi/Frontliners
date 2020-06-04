package com.mad.frontliners

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan

//Concept named parameters
class Delta(langName: String, langColor: String, start: Int) : SpannableString(langName) {
    init {
        setSpan(
            ForegroundColorSpan(Color.parseColor(langColor)),
            start,
            langName.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}