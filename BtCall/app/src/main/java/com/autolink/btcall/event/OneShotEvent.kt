package com.autolink.btcall.event

sealed class OneShotEvent {
    object NavigateToResults : OneShotEvent()
}
