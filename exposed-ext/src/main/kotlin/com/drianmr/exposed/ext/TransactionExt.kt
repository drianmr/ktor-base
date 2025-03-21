package com.drianmr.exposed.ext

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import kotlin.coroutines.CoroutineContext

/**
 * Executes a block within a new suspended transaction, catching and wrapping any exceptions as a [Result].
 *
 * This function provides a convenient way to run a block of code inside a new suspended transaction
 * while handling potential exceptions gracefully.
 */
suspend inline fun <R> newSuspendedTransactionCatching(
    context: CoroutineContext = Dispatchers.IO,
    crossinline block: suspend Transaction.() -> R,
): Result<R> = runCatching {
    newSuspendedTransaction(context) {
        block()
    }
}
