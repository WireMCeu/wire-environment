package net.wiremc.common.api.common.modules

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

interface CoreModuleRegistry {

    fun insert(rawModule: RawModule, module: CommonModule): CoreModuleRegistry

    fun read(): Map<RawModule, CommonModule>

    fun size(): Int

}