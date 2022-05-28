package net.wiremc.common.api.common

import net.wiremc.common.api.CoreAPI
import net.wiremc.common.api.common.utils.DefaultTask
import java.util.concurrent.Callable
import java.util.concurrent.Future

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

class CommunicationPromiseImpl<T>: ICommunicationPromise<T> {

    private lateinit var task: DefaultTask<T>

    override fun get(): T {
        val futureResponse: Future<T> = CoreAPI.getInstance().getCompletableFutureExecutor().submit(Callable {
            return@Callable task.handle()
        })
        return futureResponse.get()
    }

    override fun task(task: DefaultTask<T>) {
        this.task = task
    }

}