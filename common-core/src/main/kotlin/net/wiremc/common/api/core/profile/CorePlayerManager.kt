package net.wiremc.common.api.core.profile

import net.wiremc.common.api.common.database.IDatabaseEntry

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

interface CorePlayerManager {

    fun getEntry(corePlayer: CorePlayer): IDatabaseEntry

}