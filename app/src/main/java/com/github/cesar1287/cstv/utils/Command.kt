package com.github.cesar1287.cstv.utils

sealed class Command {
    class Loading(val value: Boolean): Command()
    class Error(val error: Int? = null): Command()
}