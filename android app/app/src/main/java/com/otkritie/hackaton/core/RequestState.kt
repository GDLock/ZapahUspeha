package com.otkritie.hackaton.core

sealed interface RequestState {
    object Loading : RequestState
    object Success : RequestState
    class Failure(val message: String) : RequestState
}
