package com.autolink.btcall.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.autolink.btcall.action.UiAction
import com.autolink.btcall.data.DataRepository
import com.autolink.btcall.data.ViewState
import com.autolink.btcall.event.OneShotEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val searchService: DataRepository
) {
    private val coroutineScope = MainScope()
    var permissionIsGranted by mutableStateOf(false)

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState())
    val viewState = _viewState.asStateFlow()

    private val _navigateToResults = Channel<OneShotEvent>(Channel.BUFFERED)
    val navigateToResults = _navigateToResults

    fun onAction(uiAction: UiAction) {
        when(uiAction) {
            is UiAction.SearchInput -> {
                coroutineScope.launch {
                    _viewState.value = _viewState.value.copy(isLoading = true)

                    val result = withContext(Dispatchers.IO) {
                        // searchService to do something. For example request API.
                    }

                    //_viewState.value = _viewState.value.copy(result = result.data.datas, key = uiAction.input)
                    _viewState.value = _viewState.value.copy(key = uiAction.input)
                    _navigateToResults.send(OneShotEvent.NavigateToResults)

                    _viewState.value = _viewState.value.copy(isLoading = false)
                }
            }
        }
    }

    fun permissionAction(isGranted: Boolean) {
        permissionIsGranted = isGranted
    }
}