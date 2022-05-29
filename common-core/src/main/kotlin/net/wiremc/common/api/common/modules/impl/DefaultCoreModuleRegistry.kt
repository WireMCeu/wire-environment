package net.wiremc.common.api.common.modules.impl

import net.wiremc.common.api.common.modules.CommonModule
import net.wiremc.common.api.common.modules.CoreModuleRegistry
import net.wiremc.common.api.common.modules.RawModule

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

class DefaultCoreModuleRegistry: CoreModuleRegistry {

    private val mapOf: MutableMap<RawModule, CommonModule> = mutableMapOf()

    override fun insert(rawModule: RawModule, module: CommonModule): CoreModuleRegistry {
        this.mapOf[rawModule] = module
        return this
    }

    override fun read(): Map<RawModule, CommonModule> {
        return this.mapOf
    }

    override fun size(): Int {
        return this.mapOf.size
    }


}