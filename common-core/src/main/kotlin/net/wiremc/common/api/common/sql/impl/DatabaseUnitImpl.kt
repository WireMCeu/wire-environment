package net.wiremc.common.api.common.sql.impl

import net.wiremc.common.api.CoreAPI
import net.wiremc.common.api.common.sql.DatabaseEntry
import net.wiremc.common.api.common.sql.DatabaseEntryCluster
import net.wiremc.common.api.common.sql.DatabaseUnit

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

class DatabaseUnitImpl: DatabaseUnit {

    private val entryCluster: DatabaseEntryCluster = DatabaseEntryClusterImpl(this)
    lateinit var defaults: MutableList<String>
    private lateinit var columns: MutableList<String>
    private lateinit var table: String

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
        CoreAPI.getInstance().getDatabase().newColumn(this.table, "core")
        return this
    }
}