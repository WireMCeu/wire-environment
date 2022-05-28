package net.wiremc.common.api.common.modules

import java.io.File

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

interface CoreModuleLoader {

    fun getRegisteredModules(): Collection<RawModule>

    fun loadModules(collection: Collection<RawModule>)

}