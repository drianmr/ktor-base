package com.drianmr.ktor.core.api.route

/**
 * Defines the contract for route definitions.
 *
 * Implementations should provide a way to install routes within a routing system.
 */
interface RouteDefinition {

    /**
     * Installs the route.
     */
    fun install()
}
