@file:Suppress("PrivatePropertyName", "FunctionName", "KDocUnresolvedReference")

package net.wiremc.common.api.common

import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader
import java.io.UnsupportedEncodingException
import java.net.URL
import java.net.URLConnection
import java.net.URLEncoder
import java.nio.charset.Charset
import java.util.*


/**
 *
 * this doc was created on 26.05.2022
 * This class belongs to the wiremc-workspace project
 *
 * @author Generix030
 *
 */

abstract class Translator {

    companion object {

        /**
         * URL to query for Translation
         */
        private val GOOGLE_TRANSLATE_URL = "http://translate.google.com/translate_a/single"

        /**
         * Private to prevent instantiation
         */
        private fun GoogleTranslate() {}

        /**
         * Converts the ISO-639 code into a friendly language code in the user's default language
         * For example, if the language is English and the default locale is French, it will return "anglais"
         * Useful for UI Strings
         * @param languageCode The ISO639-1
         * @return The language in the user's default language
         */
        fun getDisplayLanguage(languageCode: String?): String? {
            return Locale(languageCode).displayLanguage
        }

        /** Completes the complicated process of generating the URL
         * @param sourceLanguage The source language
         * @param targetLanguage The target language
         * @param text The text that you wish to generate
         * @return The generated URL as a string.
         */
        @Throws(UnsupportedEncodingException::class)
        private fun generateURL(sourceLanguage: String, targetLanguage: String, text: String): String {
            val encoded: String = URLEncoder.encode(text, "UTF-8") //Encode
            val sb = StringBuilder()
            sb.append(GOOGLE_TRANSLATE_URL)
            sb.append("?client=webapp") //The client parameter
            sb.append("&hl=en") //The language of the UI?
            sb.append("&sl=") //Source language
            sb.append(sourceLanguage)
            sb.append("&tl=") //Target language
            sb.append(targetLanguage)
            sb.append("&q=")
            sb.append(encoded)
            sb.append("&multires=1") //Necessary but unknown parameters
            sb.append("&otf=0")
            sb.append("&pc=0")
            sb.append("&trs=1")
            sb.append("&ssel=0")
            sb.append("&tsel=0")
            sb.append("&kc=1")
            sb.append("&dt=t") //This parameters requests the translated text back.
            //Other dt parameters request additional information such as pronunciation, and so on.
            //TODO Modify API so that the user may request this additional information.
            sb.append("&ie=UTF-8") //Input encoding
            sb.append("&oe=UTF-8") //Output encoding
            sb.append("&tk=") //Token authentication parameter
            sb.append(generateToken(text))
            return sb.toString()
        }

        /**
         * Automatically determines the language of the original text
         * @param text represents the text you want to check the language of
         * @return The ISO-639 code for the language
         * @throws IOException if it cannot complete the request
         */
        @Throws(IOException::class)
        fun detectLanguage(text: String): String? {
            val urlText = generateURL("auto", "en", text)
            val url = URL(urlText) //Generates URL
            val rawData = urlToText(url) //Gets text from Google
            return findLanguage(rawData)
        }


        /**
         * Automatically translates text to a system's default language according to its locale
         * Useful for creating international applications as you can translate UI strings
         * @see GoogleTranslate.translate
         * @param text The text you want to translate
         * @return The translated text
         * @throws IOException if cannot complete request
         */
        @Throws(IOException::class)
        fun translate(text: String): String? {
            return translate(Locale.getDefault().language, text)
        }

        /**
         * Automatically detects language and translate to the targetLanguage.
         * Allows Google to determine source language
         * @see GoogleTranslate.translate
         * @param targetLanguage The language you want to translate into in ISO-639 format
         * @param text The text you actually want to translate
         * @return The translated text.
         * @throws IOException if it cannot complete the request
         */
        @Throws(IOException::class)
        fun translate(targetLanguage: String, text: String): String? {
            return translate("auto", targetLanguage, text)
        }

        /**
         * Translate text from sourceLanguage to targetLanguage
         * Specifying the sourceLanguage greatly improves accuracy over short Strings
         * @param sourceLanguage The language you want to translate from in ISO-639 format
         * @param targetLanguage The language you want to translate into in ISO-639 format
         * @param text The text you actually want to translate
         * @return the translated text.
         * @throws IOException if it cannot complete the request
         */
        @Throws(IOException::class)
        fun translate(sourceLanguage: String, targetLanguage: String, text: String): String? {
            val urlText = generateURL(sourceLanguage, targetLanguage, text)
            val url = URL(urlText)
            val rawData = urlToText(url) ?: return null //Gets text from Google
            val raw = rawData.split("\"".toRegex()).toTypedArray() //Parses the JSON
            return if (raw.size < 2) {
                null
            } else raw[1]
            //Returns the translation
        }

        /**
         * Converts a URL to Text
         * @param url that you want to generate a String from
         * @return The generated String
         * @throws IOException if it cannot complete the request
         */
        @Throws(IOException::class)
        private fun urlToText(url: URL): String {
            val urlConn: URLConnection = url.openConnection() //Open connection
            //Adding header for user agent is required. Otherwise, Google rejects the request
            urlConn.addRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:2.0) Gecko/20100101 Firefox/4.0")
            val r: Reader =
                InputStreamReader(urlConn.getInputStream(), Charset.forName("UTF-8")) //Gets Data Converts to string
            val buf = StringBuilder()
            while (true) { //Reads String from buffer
                val ch = r.read()
                if (ch < 0) break
                buf.append(ch.toChar())
            }
            return buf.toString()
        }


        /**
         * Searches RAWData for Language
         * @param RAWData the raw String directly from Google you want to search through
         * @return The language parsed from the rawData or en-US (English-United States) if Google cannot determine it.
         */
        private fun findLanguage(rawData: String): String? {
            var i = 0
            while (i + 5 < rawData.length) {
                val dashDetected = rawData[i + 4] == '-'
                if (rawData[i] == ',' && rawData[i + 1] == '"' && (rawData[i + 4] == '"' && rawData[i + 5] == ','
                            || dashDetected)
                ) {
                    if (dashDetected) {
                        val lastQuote = rawData.substring(i + 2).indexOf('"')
                        if (lastQuote > 0) return rawData.substring(i + 2, i + 2 + lastQuote)
                    } else {
                        val possible = rawData.substring(i + 2, i + 4)
                        if (containsLettersOnly(possible)) { //Required due to Google's inconsistent formatting.
                            return possible
                        }
                    }
                }
                i++
            }
            return null
        }

        /**
         * Checks if all characters in text are letters.
         * @param text The text you want to determine the validity of.
         * @return True if all characters are letter, otherwise false.
         */
        private fun containsLettersOnly(text: String): Boolean {
            for (i in 0 until text.length) {
                if (!Character.isLetter(text[i])) {
                    return false
                }
            }
            return true
        }

        /*************************** Cryptography section ************************************************
         ******************** Thank Dean1510 for the excellent code translation **************************/


        //TODO Possibly refactor code as utility class

        /*************************** Cryptography section ************************************************
         * Thank Dean1510 for the excellent code translation  */
        //TODO Possibly refactor code as utility class
        /**
         * This function generates the int array for translation acting as the seed for the hashing algorithm.
         */
        private fun TKK(): IntArray {
            return intArrayOf(0x6337E, 0x217A58DC + 0x5AF91132)
        }

        /**
         * An implementation of an unsigned right shift.
         * Necessary since Java does not have unsigned ints.
         * @param x The number you wish to shift.
         * @param bits The number of bytes you wish to shift.
         * @return The shifted number, unsigned.
         */
        private fun shr32(x: Int, bits: Int): Int {
            if (x < 0) {
                val x_l = 0xffffffffL + x + 1
                return (x_l shr bits).toInt()
            }
            return x shr bits
        }

        private fun RL(a: Int, b: String): Int { //I am not entirely sure what this magic does.
            var a = a
            var c = 0
            while (c < b.length - 2) {
                var d = b[c + 2].code
                d = if (d >= 65) d - 87 else d - 48
                d = if (b[c + 1] == '+') shr32(a, d) else a shl d
                a = if (b[c] == '+') a + (d and -0x1) else a xor d
                c += 3
            }
            return a
        }

        /**
         * Generates the token needed for translation.
         * @param text The text you want to generate the token for.
         * @return The generated token as a string.
         */
        private fun generateToken(text: String): String? {
            val tkk = TKK()
            val b = tkk[0]
            var e = 0
            var f = 0
            val d: MutableList<Int> = mutableListOf()
            while (f < text.length) {
                var g = text[f].code
                if (0x80 > g) {
                    d.add(e++, g)
                } else {
                    if (0x800 > g) {
                        d.add(e++, g shr 6 or 0xC0)
                    } else {
                        if (0xD800 == g and 0xFC00 && f + 1 < text.length && 0xDC00 == text[f + 1].code and 0xFC00) {
                            g = 0x10000 + (g and 0x3FF shl 10) + (text[++f].code and 0x3FF)
                            d.add(e++, g shr 18 or 0xF0)
                            d.add(e++, g shr 12 and 0x3F or 0x80)
                        } else {
                            d.add(e++, g shr 12 or 0xE0)
                            d.add(e++, g shr 6 and 0x3F or 0x80)
                        }
                    }
                    d.add(e++, g and 63 or 128)
                }
                f++
            }
            var a_i = b
            e = 0
            while (e < d.size) {
                a_i += d[e]
                a_i = RL(a_i, "+-a^+6")
                e++
            }
            a_i = RL(a_i, "+-3^+b+-f")
            a_i = a_i xor tkk[1]
            var a_l: Long = if (0 > a_i) {
                0x80000000L + (a_i and 0x7FFFFFFF)
            } else {
                a_i.toLong()
            }
            a_l %= Math.pow(10.0, 6.0).toLong()
            return String.format(Locale.US, "%d.%d", a_l, a_l xor b.toLong())
        }


    }

}