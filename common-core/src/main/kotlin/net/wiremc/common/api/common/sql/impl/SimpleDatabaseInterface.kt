package net.wiremc.common.api.common.sql.impl

import com.google.common.collect.Lists
import net.wiremc.common.api.CoreAPI
import net.wiremc.common.api.common.console.MSG
import net.wiremc.common.api.common.sql.IDatabaseInterface
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.util.concurrent.CopyOnWriteArrayList

/**
 *
 * this doc was created on 21.05.2022
 * This class belongs to the wiremc-workspace project
 *
 * @author Generix030
 *
 */

class SimpleDatabaseInterface(private val coreAPI: CoreAPI): IDatabaseInterface {

    private lateinit var hostName: String
    private lateinit var port: String
    private lateinit var username: String
    private lateinit var password: String
    private lateinit var database: String

    private var connected: Boolean = false
    private lateinit var connection: Connection

    override fun connect(database: String): IDatabaseInterface {
        this.database = database
        connect0("127.0.0.1", "3306", "network", "Lighdo183", database)
        return this
    }

    override fun connect0(
        hostName: String,
        port: String,
        username: String,
        password: String,
        database: String,
    ): IDatabaseInterface {
        this.hostName = hostName
        this.port = port
        this.username = username
        this.password = password
        this.database = database
        bind0()
        return this
    }

    private fun bind0() {
        when (connected) {
            false -> {
                try {
                    this.connection = DriverManager.getConnection("jdbc:mysql://" + this.hostName + ":" + this.port + "/" + this.database + "?autoReconnect=true", this.username, this.password)
                    coreAPI.getCoreConsole().write("A database connection could be set up", MSG.ERROR)
                } catch (exception: SQLException) {
                    exception.printStackTrace()
                    coreAPI.getCoreConsole().write("An unknown error occurred while connecting to database", MSG.ERROR)
                }
            }
            else -> {
                coreAPI.getCoreConsole().write("This interface is already connected", MSG.ERROR)
            }
        }
    }

    override fun createTable(tableName: String, vararg entries: String) {
        val stringBuilder = StringBuilder()
        stringBuilder.append("CREATE TABLE IF NOT EXISTS `$tableName` (`")
        var counter = 0
        val var5: Array<out String> = entries
        val var6 = entries.size
        for (var7 in 0 until var6) {
            val string = var5[var7]
            if (counter + 1 >= entries.size) {
                stringBuilder.append("$string` text)")
                break
            }
            stringBuilder.append("$string` text, `")
            ++counter
        }
        try {
            val preparedStatement = connection.prepareStatement(stringBuilder.toString())
            preparedStatement.execute()
        } catch (var9: SQLException) {
            var9.printStackTrace()
        }
    }

    override fun newColumn(table: String, c: String) {
        try {
            val command = "ALTER TABLE $table ADD COLUMN IF NOT EXISTS $c text"
            val preparedStatement = this.connection.prepareStatement(command)
            preparedStatement.execute()
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }

    private fun getResult(query: String?): ResultSet? {
        return try {
            val ps = connection.prepareStatement(query)
            ps.executeQuery()
        } catch (var4: SQLException) {
            var4.printStackTrace()
            null
        }
    }

    override fun getEntryFromTable(specificTable: String, filteredKey: String, filteredVal: String, neededVal: String?): String {
        val query = "SELECT * FROM $specificTable WHERE $filteredKey='$filteredVal'"
        val resultSet = getResult(query)
        val list: CopyOnWriteArrayList<*> = Lists.newCopyOnWriteArrayList<Any>()

        try {
            while (resultSet!!.next()) {
                list.add(resultSet.getString(neededVal) as Nothing?)
            }
        } catch (var9: SQLException) {
            return ""
        }

        return if (list.isEmpty()) "" else (list[0] as String)
    }

    override fun updateInTable(specificTable: String, keyRow: String, keyValue: String, setRow: String, setValue: String) {
        val query = "UPDATE $specificTable SET $setRow='$setValue' WHERE $keyRow='$keyValue'"
        try {
            val preparedStmt = connection.prepareStatement(query)
            preparedStmt.execute()
        } catch (var8: SQLException) {
            var8.printStackTrace()
        }
    }

    override fun removeFromTable(specificTable: String, column: String, key: String) {
        val query = "DELETE FROM $specificTable WHERE $column='$key'"
        try {
            val preparedStmt = connection.prepareStatement(query)
            preparedStmt.execute()
        } catch (var6: SQLException) {
            var6.printStackTrace()
        }
    }

    override fun existsInTable(specificTable: String, key: String, value: String): Boolean {
        val rs = getResult("SELECT * FROM $specificTable WHERE $key='$value'")
        return try {
            rs!!.next()
        } catch (var6: SQLException) {
            var6.printStackTrace()
            false
        }
    }

    override fun updateAllInTable(specificTable: String, setRow: String, setValue: String) {
        val query = "UPDATE $specificTable SET $setRow='$setValue'"
        try {
            val preparedStmt = connection.prepareStatement(query)
            preparedStmt.execute()
        } catch (var6: SQLException) {
            var6.printStackTrace()
        }
    }

    override fun addMoreInTable(specificTable: String, keys: List<String>, values: List<String>) {
        val queryBuilder = java.lang.StringBuilder()
        queryBuilder.append("INSERT INTO $specificTable(")
        var i: Int = 0
        while (i < keys.size) {
            if (keys.size - 1 != i) {
                queryBuilder.append(keys[i] + ", ")
            } else {
                queryBuilder.append(keys[i] + ")")
            }
            ++i
        }
        queryBuilder.append(" VALUES ('")
        i = 0
        while (i < values.size) {
            if (values.size - 1 != i) {
                queryBuilder.append(values[i] + "', '")
            } else {
                queryBuilder.append(values[i] + "')")
            }
            ++i
        }
        try {
            val preparedStmt = connection.prepareStatement(queryBuilder.toString())
            preparedStmt.execute()
        } catch (var7: SQLException) {
            var7.printStackTrace()
        }
    }

    override operator fun get(whereresult: String, where: String, select: String, database: String): Any? {
        val rs = getResult("SELECT $select FROM $database WHERE $where='$whereresult'")
        var o: Any? = null
        try {
            while (rs!!.next()) {
                o = rs.getObject(select)
            }
            rs.close()
        } catch (var8: SQLException) {
            var8.printStackTrace()
        }
        return o
    }

    override fun getSelectionAmount(table: String): Int? {
        var count = 0
        val resultSet = getResult("SELECT * FROM $table")
        try {
            while (resultSet!!.next()) {
                ++count
            }
            resultSet.close()
        } catch (e: SQLException) {
            e.printStackTrace()
            try {
                resultSet!!.close()
            } catch (exception: SQLException) {
                exception.printStackTrace()
            }
        } finally {
            try {
                resultSet!!.close()
            } catch (e2: SQLException) {
                e2.printStackTrace()
            }
        }
        return count
    }


}