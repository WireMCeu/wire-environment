@file:Suppress("UNCHECKED_CAST")

package net.wiremc.common.api.common.sql.impl

import net.wiremc.common.api.CoreAPI
import net.wiremc.common.api.common.sql.DatabaseEntry
import net.wiremc.common.api.common.sql.ICommunicationPromiseSQL
import java.util.concurrent.Callable
import java.util.concurrent.Future

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

class CommunicationPromiseSQLImpl<T>(private val entry: DatabaseEntry, private val key: String, private val returnType: Class<T>): ICommunicationPromiseSQL<T> {

    override fun get(): T {
        when (this.returnType) {
            String::class.java -> {
                return CoreAPI
                    .getInstance()
                    .getDatabase()
                    .getEntryFromTable(entry
                        .unit()
                        .table(), "core", entry
                        .entry(), key) as T
            }
            else -> {
                val r: String = CoreAPI
                    .getInstance()
                    .getDatabase()
                    .getEntryFromTable(entry
                        .unit()
                        .table(), "core", entry
                        .entry(), key)
                return CoreAPI
                    .getInstance()
                    .getGson()
                    .fromJson(r, returnType)
            }
        }
    }

    override fun future(): T {
        when (this.returnType) {
            String::class.java -> {
                val resultFuture: Future<String> = CoreAPI.getInstance().getCompletableFutureExecutor().submit(Callable {
                    return@Callable CoreAPI
                        .getInstance()
                        .getDatabase()
                        .getEntryFromTable(entry
                            .unit()
                            .table(), "core", entry
                            .entry(), key)
                })
                return resultFuture.get() as T
            }
            else -> {
                val resultFuture: Future<String> =
                    CoreAPI.getInstance().getCompletableFutureExecutor().submit(Callable {
                        return@Callable CoreAPI
                            .getInstance()
                            .getDatabase()
                            .getEntryFromTable(entry
                                .unit()
                                .table(), "core", entry
                                .entry(), key)
                    })
                return CoreAPI
                    .getInstance()
                    .getGson()
                    .fromJson(resultFuture.get(), returnType)
            }
        }
    }

    override fun fromHashed(): T {
        val impl = this.entry as DatabaseEntryImpl
        when(impl.hashed.containsKey(this.key)) {
            true -> return impl.hashed[this.key] as T
            else -> {
                when(this.returnType) {
                    String::class.java -> {
                        return CoreAPI
                            .getInstance()
                            .getDatabase()
                            .getEntryFromTable(entry
                                .unit()
                                .table(), "core", entry
                                .entry(), key) as T
                    }
                    else -> {
                        val r: String = CoreAPI
                            .getInstance()
                            .getDatabase()
                            .getEntryFromTable(entry
                                .unit()
                                .table(), "core", entry
                                .entry(), key)
                        return CoreAPI
                            .getInstance()
                            .getGson()
                            .fromJson(r, returnType)
                    }
                }
            }
        }
    }


}