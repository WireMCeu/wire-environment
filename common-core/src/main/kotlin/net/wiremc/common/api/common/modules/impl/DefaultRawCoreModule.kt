package net.wiremc.common.api.common.modules.impl

import net.wiremc.common.api.common.modules.Construction
import net.wiremc.common.api.common.modules.RawModule
import java.io.File

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

class DefaultRawCoreModule(private val construction: Construction,private val source: File): RawModule {
    override fun construction(): Construction {
        return this.construction
    }

    override fun source(): File {
        return this.source
    }
}