package net.wiremc.common.api.common.sql

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

interface DatabaseEntry {

    fun insert(key: String, value: String): DatabaseEntry

    fun receive(key: String): ICommunicationPromiseSQL<String>

    fun defaults(list: MutableList<String>): DatabaseEntry

    fun unit(): DatabaseUnit

    fun entry(): String

}