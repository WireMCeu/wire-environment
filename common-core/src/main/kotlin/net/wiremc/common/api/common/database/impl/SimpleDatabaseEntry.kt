package net.wiremc.common.api.common.database.impl

import net.wiremc.common.api.common.database.IDatabaseEntry
import net.wiremc.common.api.common.database.IDatabaseSection
import net.wiremc.common.api.CoreAPI
import java.sql.SQLException
import java.util.concurrent.CompletableFuture

/**
 *
 * this doc was created on 21.05.2022
 * This class belongs to the wiremc-workspace project
 *
 * @author Generix030
 *
 */

class SimpleDatabaseEntry(private val section: IDatabaseSection, private val str: String): IDatabaseEntry {

    private lateinit var defaultKeysSet: Array<String>
    private lateinit var defaultValuesSet: Array<String>
    private var runAsynchronous: Boolean = false

    private val temp: MutableMap<String, String> = mutableMapOf()

    override fun property(key: String, value: String): IDatabaseEntry {
        when(this.runAsynchronous) {
            true -> {
                this.runAsynchronous = false
                CompletableFuture.runAsync() {
                    try {
                        when(CoreAPI.getInstance().getDatabase().existsInTable(section.table(), key, value)) {
                            true -> {
                                CoreAPI.getInstance()
                                    .getDatabase()
                                    .updateInTable(section.table(), "baseEntry", str, key, value)
                            }
                            else -> {
                                CoreAPI.getInstance()
                                    .getDatabase()
                                    .addMoreInTable(section
                                        .table(), defaultKeysSet
                                        .toList(), defaultValuesSet
                                        .toList())
                                property(key, value)
                            }
                        }
                    } catch (_: Exception) {
                        try {
                            CoreAPI.getInstance().getDatabase().newColumn(this
                                .section
                                .table(), key)
                            CoreAPI.getInstance()
                                .getDatabase()
                                .updateInTable(section.table(), "baseEntry", str, key, value)
                        } catch (exception: SQLException) {
                            exception.printStackTrace()
                        }
                    }
                }
            }
            else -> {
                try {
                    when(CoreAPI.getInstance().getDatabase().existsInTable(section.table(), key, value)) {
                        true -> {
                            CoreAPI.getInstance()
                                .getDatabase()
                                .updateInTable(section.table(), "baseEntry", str, key, value)
                        }
                        else -> {
                            CoreAPI.getInstance()
                                .getDatabase()
                                .addMoreInTable(section
                                    .table(), defaultKeysSet
                                    .toList(), defaultValuesSet
                                    .toList())
                            property(key, value)
                        }
                    }
                } catch (_: Exception) {
                    try {
                        CoreAPI.getInstance().getDatabase().newColumn(this
                            .section
                            .table(), key)
                        CoreAPI.getInstance()
                            .getDatabase()
                            .updateInTable(section.table(), "baseEntry", str, key, value)
                    } catch (exception: SQLException) {
                        exception.printStackTrace()
                    }
                }
            }
        }
        return this
    }

    override fun property(key: String): String {
        return CoreAPI
            .getInstance()
            .getDatabase().
            getEntryFromTable(this
                .section
                .table(), "baseEntry", str, key)
    }

    override fun contains(key: String): IDatabaseEntry {
        throw java.lang.UnsupportedOperationException("Coming soon")
    }

    override fun <T> propertyOBJ(key: String, value: T): IDatabaseEntry {
        return property(key, CoreAPI.getInstance().getGson().toJson(value))
    }

    override fun <T> propertyOBJ(key: String, clazz: Class<*>): T {
        @Suppress("UNCHECKED_CAST")
        return CoreAPI.getInstance().getGson().fromJson(this.property(key), clazz) as T
    }

    override fun propertyTemp(key: String, value: String): IDatabaseEntry {
        this.temp[key] = value
        return this
    }

    override fun propertyTemp(key: String): String {
        return this.temp[key]!!
    }

    override fun <T> propertyTempOBJ(key: String, value: T): IDatabaseEntry {
        this.temp[key] = CoreAPI.getInstance().getGson().toJson(value)
        return this
    }

    override fun <T> propertyTempOBJ(key: String, clazz: Class<*>): T {
        @Suppress("UNCHECKED_CAST")
        return CoreAPI.getInstance().getGson().fromJson(this.temp[key], clazz) as T
    }

    override fun pushTemp(vararg temps: String): IDatabaseEntry {
        val sL: MutableList<String> = mutableListOf()
        for (temp in temps) {
            for (entry in this.temp) {
                if (entry.key == temp) {
                    this.property(entry.key, entry.value)
                    sL.add(entry.key)
                }
            }
        }
        for (s in sL) this.temp.remove(s)
        return this
    }

    override fun pushTemp(): IDatabaseEntry {
        for (entry in this.temp) {
            this.property(entry.key, entry.value)
        }
        this.temp.clear()
        return this
    }

    override fun defaultSet(keys: Array<String>, values: Array<String>): IDatabaseEntry {
        this.defaultKeysSet = keys
        this.defaultValuesSet = values
        return this
    }

    override fun runAsync(): IDatabaseEntry {
        this.runAsynchronous = !this.runAsynchronous
        return this
    }

    override fun runAsync(b: Boolean): IDatabaseEntry {
        this.runAsynchronous = b
        return this
    }


}