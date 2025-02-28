package br.com.silvanogpm.kepify.di

import br.com.silvanogpm.kepify.ui.screen.home.HomeViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { HomeViewModel(get()) }
}