package com.autolink.btcall.action

sealed class UiAction {
    class SearchInput(val input: String) : UiAction()
}
