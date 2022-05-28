package net.wiremc.common.api.common.database

/**
 *
 * this doc was created on 21.05.2022
 * This class belongs to the wiremc-workspace project
 *
 * @author Generix030
 *
 */

interface IDatabaseEntry {

    fun property(key: String, value: String): IDatabaseEntry

    fun property(key: String): String

    fun contains(key: String): IDatabaseEntry

    fun<T> propertyOBJ(key: String, value: T): IDatabaseEntry

    fun<T> propertyOBJ(key: String, clazz: Class<*>): T

    fun propertyTemp(key: String, value: String): IDatabaseEntry

    fun propertyTemp(key: String): String

    fun<T> propertyTempOBJ(key: String, value: T): IDatabaseEntry

    fun<T> propertyTempOBJ(key: String, clazz: Class<*>): T

    fun pushTemp(vararg temps: String): IDatabaseEntry

    fun pushTemp(): IDatabaseEntry

    fun defaultSet(keys: Array<String>, values: Array<String>): IDatabaseEntry

    fun runAsync(): IDatabaseEntry

    fun runAsync(b: Boolean): IDatabaseEntry

}