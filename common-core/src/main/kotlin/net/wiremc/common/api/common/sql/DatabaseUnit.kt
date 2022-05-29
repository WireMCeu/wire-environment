package net.wiremc.common.api.common.sql

import net.wiremc.common.api.CoreAPI
import net.wiremc.common.api.common.sql.impl.DatabaseUnitImpl
import java.util.function.Consumer

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

interface DatabaseUnit {

    companion object {

        @JvmStatic
        fun unit(coreAPI: CoreAPI): DatabaseUnit {
            return DatabaseUnitImpl(coreAPI)
        }

    }

    fun newEntry(value: String): DatabaseEntry

    fun columns(vararg columns: String): DatabaseUnit

    fun defaults(listOf: MutableList<String>): DatabaseUnit

    fun table(name: String): DatabaseUnit

    fun entryCluster(): DatabaseEntryCluster

    fun dispatchUnit(): DatabaseUnit

    fun table(): String

    fun onInsertNew(handler: Consumer<DatabaseEntry>): DatabaseUnit

    fun handler(): Consumer<DatabaseEntry>

}