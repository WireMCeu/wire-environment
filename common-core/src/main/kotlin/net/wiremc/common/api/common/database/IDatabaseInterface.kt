package net.wiremc.common.api.common.database

/**
 *
 * this doc was created on 21.05.2022
 * This class belongs to the wiremc-workspace project
 *
 * @author Generix030
 *
 */

interface IDatabaseInterface {

    fun connect(database: String): IDatabaseInterface

    fun connect0(hostName: String, port: String, username: String, password: String, database: String): IDatabaseInterface

    fun createTable(tableName: String, vararg entries: String)

    fun newColumn(table: String, c: String)

    fun getEntryFromTable(specificTable: String, filteredKey: String, filteredVal: String, neededVal: String?): String

    fun updateInTable(specificTable: String, keyRow: String, keyValue: String, setRow: String, setValue: String)

    fun removeFromTable(specificTable: String, column: String, key: String)

    fun existsInTable(specificTable: String, key: String, value: String): Boolean

    fun updateAllInTable(specificTable: String, setRow: String, setValue: String)

    fun addMoreInTable(specificTable: String, keys: List<String>, values: List<String>)

    operator fun get(whereresult: String, where: String, select: String, database: String): Any?

    fun getSelectionAmount(table: String): Int?

}