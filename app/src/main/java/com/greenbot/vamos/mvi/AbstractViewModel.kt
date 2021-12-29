package com.greenbot.vamos.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class AbstractViewModel<I : MviIntent, S : MviViewState, E : MviSingleEvent> :
    MviViewModel<I, S, E>, ViewModel() {

    private val eventChannel = Channel<E>(Channel.UNLIMITED)
    private val intentMutableFlow = MutableSharedFlow<I>(extraBufferCapacity = 64)

    final override val singleEvent: Flow<E> get() = eventChannel.receiveAsFlow()

    final override suspend fun processIntent(intent: I) = intentMutableFlow.emit(intent)

    protected suspend fun sendEvent(event: E) = eventChannel.send(event)
    protected val intentFlow: SharedFlow<I> get() = intentMutableFlow

}