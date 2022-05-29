package net.wiremc.common.api.common.context

import java.io.File

/**
 * this java doc created on 29.05.2022
 * this file belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

interface ContextCoreConfiguration {

    fun<T> insert(property: String, any: T): ContextCoreConfiguration

    fun clear(): ContextCoreConfiguration

    fun<T> get(property: String): T

    fun remove(property: String): ContextCoreConfiguration

    fun file(): File

    fun load(): ContextCoreConfiguration

}