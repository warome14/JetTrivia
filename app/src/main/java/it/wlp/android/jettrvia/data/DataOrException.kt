package it.wlp.android.jettrvia.data

class DataOrException<T,E>(var data: T? = null, var loaded: Boolean? = null, var exception: E? = null) {
}