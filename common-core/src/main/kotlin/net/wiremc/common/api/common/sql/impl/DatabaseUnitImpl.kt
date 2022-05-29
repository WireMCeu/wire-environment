package net.wiremc.common.api.common.sql.impl

import net.wiremc.common.api.CoreAPI
import net.wiremc.common.api.common.sql.DatabaseEntry
import net.wiremc.common.api.common.sql.DatabaseEntryCluster
import net.wiremc.common.api.common.sql.DatabaseUnit
import java.util.function.Consumer

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

class DatabaseUnitImpl(private val coreAPI: CoreAPI): DatabaseUnit {

    private val entryCluster: DatabaseEntryCluster = DatabaseEntryClusterImpl(this)
    lateinit var defaults: MutableList<String>
    private lateinit var columns: MutableList<String>
    private lateinit var table: String
    private var handler: Consumer<DatabaseEntry>? = null

    override fun newEntry(value: String): DatabaseEntry {
        val response: DatabaseEntry = DatabaseEntryImpl(this, value)
        this.entryCluster.register(response)
        return response
    }

    override fun columns(vararg columns: String): DatabaseUnit {
        this.columns = columns.toMutableList()
        for (column in columns) {
            CoreAPI.getInstance().getDatabase().newColumn(table, column)
        }
        return this
    }

    override fun defaults(listOf: MutableList<String>): DatabaseUnit {
        this.defaults = listOf
        return this
    }

    override fun table(name: String): DatabaseUnit {
        this.table = name
        return this
    }

    override fun table(): String {
        return this.table
    }

    override fun entryCluster(): DatabaseEntryCluster {
        return this.entryCluster
    }

    override fun dispatchUnit(): DatabaseUnit {
        coreAPI.getDatabase().createTable(this.table, "core")
        return this
    }

    override fun onInsertNew(handler: Consumer<DatabaseEntry>): DatabaseUnit {
        this.handler = handler
        return this
    }

    override fun handler(): Consumer<DatabaseEntry> = this.handler!!

}