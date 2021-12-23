package com.greenbot.vamos.domain

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <T> the input type
 * @param <D> the domain type
 */
interface Mapper<T, D> {

    fun mapToDomain(type: T): D

    fun mapFromDomain(type: D): T
}