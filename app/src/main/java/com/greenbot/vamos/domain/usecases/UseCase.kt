package com.greenbot.vamos.domain.usecases

interface UseCase<PARAMS, DATA> {

    fun execute(params: PARAMS): DATA
}