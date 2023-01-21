package com.ab21.core.annotations

import javax.inject.Qualifier

/**
 * This qualifier identifies the version name string of the app. It is intended for dependency
 * injection purposes.
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Flavor
