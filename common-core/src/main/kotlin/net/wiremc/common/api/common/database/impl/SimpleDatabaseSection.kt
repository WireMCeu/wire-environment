package net.wiremc.common.api.common.database.impl

import net.wiremc.common.api.common.database.IDatabaseEntry
import net.wiremc.common.api.common.database.IDatabaseSection

/**
 *
 * this doc was created on 21.05.2022
 * This class belongs to the wiremc-workspace project
 *
 * @author Generix030
 *
 */

class SimpleDatabaseSection: IDatabaseSection {

    private lateinit var table: String

    override fun bind(): IDatabaseSection {
        TODO("Not yet implemented")
    }

    override fun entry(value: String): IDatabaseEntry {
        return SimpleDatabaseEntry(this, value)
    }

    override fun table(name: String): IDatabaseSection {
        this.table = name
        return this
    }

    override fun table(): String {
        return this.table
    }

    override fun columns(vararg value: String): IDatabaseSection {
        TODO("Not yet implemented")
    }

}