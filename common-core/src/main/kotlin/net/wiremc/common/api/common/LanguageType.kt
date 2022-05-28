package net.wiremc.common.api.common

/**
 *
 * this doc was created on 26.05.2022
 * This class belongs to the wiremc-workspace project
 *
 * @author Generix030
 *
 */

enum class LanguageType(private val s: String) {

    ENGLISH("en"), GERMAN("de"), CHINESE("zh"), FRENCH("fr"), ITALIAN("it"), RUSSIAN("ru"), UKRAINIAN("uk"), SPANISH("es");

    override fun toString(): String {
        return this.s
    }

}