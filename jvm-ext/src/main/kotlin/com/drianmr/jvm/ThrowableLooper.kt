package com.drianmr.jvm

import kotlin.reflect.KClass

/**
 * A class designed to handle exceptions, checking for specific Throwable types
 * and invoking corresponding handlers.
 *
 * @property cause The root cause Throwable.
 */
class ThrowableLooper(private val cause: Throwable) {

    /**
     * Handlers for specific Throwable subclasses.
     */
    val handler = mutableMapOf<KClass<*>, (Throwable) -> Unit>()

    /**
     * Handler if no [handler] was invoked
     */
    private var _onElse: () -> Unit = {}

    /**
     * Registers a handler function for a specific [Throwable] subclass.
     *
     * This function allows you to define a handler for a specific Throwable subclass
     * using a lambda function that takes an instance of that subclass.
     *
     * @param T The [Throwable] subclass for which the handler is registered.
     * @param handler The lambda function that will be invoked when an instance of type `T` is encountered
     * during the looping process.
     */
    inline fun <reified T : Throwable> on(noinline handler: (T) -> Unit) {
        @Suppress("UNCHECKED_CAST")
        this.handler[T::class] = handler as (Throwable) -> Unit
    }

    /**
     * Handler if no [handler] was invoked
     */
    fun onElse(handler: () -> Unit) {
        _onElse = handler
    }

    /**
     * Initiates the looping process to check for registered handlers based on the cause and its cause chain.
     *
     * This function iterates through the cause chain (including the root cause) and checks if there are
     * any registered handlers for the specific Throwable subclasses encountered. If a matching handler is
     * found, it's invoked with the corresponding [Throwable] instance.
     */
    fun check() {
        val nestedCause = mutableListOf(cause)
        val nestedCauseRef = mutableListOf(cause::class)

        var last: Throwable? = cause
        do {
            last = last?.cause

            if (last != null) {
                nestedCause.add(last)
                nestedCauseRef.add(last::class)
            }
        } while (last != null)

        assert(nestedCause.size == nestedCauseRef.size)

        var handlerInvoked = false

        handler.forEach { (key, action) ->
            if (key in nestedCauseRef) {
                val instance = nestedCause[nestedCauseRef.indexOf(key)]
                action(instance)
                handlerInvoked = true
            }
        }
        if (!handlerInvoked) {
            _onElse()
        }
    }
}
