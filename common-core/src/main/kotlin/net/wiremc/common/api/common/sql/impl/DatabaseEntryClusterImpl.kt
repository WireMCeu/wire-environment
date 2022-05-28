package net.wiremc.common.api.common.sql.impl

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

class DatabaseEntryClusterImpl(private val unit: DatabaseUnit): DatabaseEntryCluster {

    private val listOf: MutableList<DatabaseEntry> = mutableListOf()

    override fun entries(): Collection<DatabaseEntry> {
        return this.listOf
    }

    override fun unit(): DatabaseUnit {
        return this.unit
    }

    override fun register(entry: DatabaseEntry) {
        this.listOf.add(entry)
    }

}