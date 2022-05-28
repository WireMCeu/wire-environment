package net.wiremc.common.api.exceptions.common

import net.wiremc.common.api.exceptions.EmptyInterfaceException

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

class EmptyModuleException(module: String): EmptyInterfaceException("the module $module could not be loaded") {
}