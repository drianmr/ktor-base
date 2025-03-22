@file:Suppress("NOTHING_TO_INLINE")

package com.drianmr.dagger.ext

import kotlin.reflect.KProperty
import dagger.Lazy as DaggerLazy

/**
 * Provides delegated property access for lazy dependency injection.
 */
inline operator fun <T> DaggerLazy<T>.getValue(thisRef: Any?, property: KProperty<*>): T = get()
