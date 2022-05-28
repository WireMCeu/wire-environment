package net.wiremc.common.api.common.sql

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

interface DatabaseEntryCluster {

    fun entries(): Collection<DatabaseEntry>

    fun unit(): DatabaseUnit

    fun register(entry: DatabaseEntry)

}