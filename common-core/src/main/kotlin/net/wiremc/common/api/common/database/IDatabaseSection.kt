package net.wiremc.common.api.common.database

import net.wiremc.common.api.common.database.impl.SimpleDatabaseSection

/**
 *
 * this doc was created on 21.05.2022
 * This class belongs to the wiremc-workspace project
 *
 * @author Generix030
 *
 */

interface IDatabaseSection {

    companion object {
        @JvmStatic
        fun sectionOf(vararg columns: String): IDatabaseSection {
            return SimpleDatabaseSection().columns(*columns)
        }
        @JvmStatic
        fun section(): IDatabaseSection {
            return SimpleDatabaseSection()
        }

    }

    fun bind(): IDatabaseSection

    fun entry(value: String): IDatabaseEntry

    fun table(name: String): IDatabaseSection

    fun table(): String

    fun columns(vararg value: String): IDatabaseSection

}