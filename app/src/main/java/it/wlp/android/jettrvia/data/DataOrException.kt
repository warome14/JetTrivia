package it.wlp.android.jettrvia.data

class DataOrException<T,E>(var data: T? = null, var loading: Boolean? = null, var exception: E? = null) {
}