package com.flower.base.utils

abstract class BaseMapper<in T, out R> {
    abstract fun map(value: T): R
}
