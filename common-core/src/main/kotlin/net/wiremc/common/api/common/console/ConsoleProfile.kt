package net.wiremc.common.api.common.console

/**
 *
 * this doc was created on 21.05.2022
 * This class belongs to the wiremc-workspace project
 *
 * @author Generix030
 *
 */

interface ConsoleProfile {

    fun write(msg: String, type: MSG = MSG.INFO): ConsoleProfile

}