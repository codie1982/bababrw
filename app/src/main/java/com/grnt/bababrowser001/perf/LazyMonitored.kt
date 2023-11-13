package com.grnt.bababrowser001.perf

fun <T> lazyMonitored(initializer: () -> T): Lazy<T> = lazy(initializer)
