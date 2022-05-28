package net.wiremc.common.api.common.sql.impl

import net.wiremc.common.api.CoreAPI
import net.wiremc.common.api.common.sql.DatabaseEntry
import net.wiremc.common.api.common.sql.DatabaseUnit
import net.wiremc.common.api.common.sql.ICommunicationPromiseSQL

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

class DatabaseEntryImpl(private val unit: DatabaseUnitImpl,private val str: String): DatabaseEntry {

    val hashed: MutableMap<String, Any> = mutableMapOf()
    private var defaults: MutableList<String> = unit.defaults

    init {
    }

    override fun insert(key: String, value: String): DatabaseEntry {
        this.hashed[key] = value
        CoreAPI.getInstance().getDatabase().newColumn(this.unit.table(), key)
        CoreAPI.getInstance()
            .getDatabase()
            .updateInTable(this
                .unit
                .table(),
                "core",
                this.str,
                key, value)
        return this
    }

    override fun receive(key: String): ICommunicationPromiseSQL<String> {
        return CommunicationPromiseSQLImpl(this, key, String::class.java)
    }

    override fun defaults(list: MutableList<String>): DatabaseEntry {
        this.defaults = list
        return this
    }

    override fun unit(): DatabaseUnit {
        return this.unit
    }

    override fun entry(): String {
        return this.str
    }
}