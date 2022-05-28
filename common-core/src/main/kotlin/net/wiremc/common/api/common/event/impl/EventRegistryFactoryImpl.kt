package eu.wiremc.event.impl

import eu.wiremc.event.EventRegistry
import eu.wiremc.event.IEventRegistryFactory
import net.wiremc.common.api.common.event.impl.EventRegistryImpl

/**
 *
 * this doc was created on 22.05.2022
 * This class belongs to the wiremc-environment project
 *
 * @author Generix030
 *
 */

class EventRegistryFactoryImpl: IEventRegistryFactory {

    override fun create(): EventRegistry {
        return EventRegistryImpl()
    }

}