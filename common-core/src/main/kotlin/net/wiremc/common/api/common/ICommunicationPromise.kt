package net.wiremc.common.api.common

import net.wiremc.common.api.common.utils.DefaultTask

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

interface ICommunicationPromise<T> {

    companion object {

        @JvmStatic
        fun<T> newPromise(): ICommunicationPromise<T> {
            return CommunicationPromiseImpl<T>()
        }

    }

    fun get(): T

    fun task(task: DefaultTask<T>)

}