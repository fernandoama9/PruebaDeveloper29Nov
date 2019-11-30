package com.loyalty.prueba.demo.interfaces;

@FunctionalInterface
public interface IProcess<T, U> {
    @SuppressWarnings("unchecked")
    T process(U... params);
}