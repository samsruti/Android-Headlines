package com.byjus.headlines.assignment.samsruti.repository

import com.byjus.headlines.assignment.samsruti.base.BaseMockServerTest
import com.byjus.headlines.assignment.samsruti.di.AppComponent
import com.byjus.headlines.assignment.samsruti.di.MockedAppComponentTest
import org.koin.core.context.startKoin

abstract class BaseRepositoryMockTest : BaseMockServerTest() {
    override fun setUp() {
        super.setUp()
        startKoin {
            val mockedAppComponent = MockedAppComponentTest()
            modules(
                listOf(
                    AppComponent().viewModelModule,
                    mockedAppComponent.getRetrofitComponent(mockServer.url("/").toString()),
                    mockedAppComponent.getRepositoryComponent(daoMocked)
                )
            )
        }
    }
}