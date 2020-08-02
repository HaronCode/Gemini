package com.haroncode.gemini.sample.presentation.routing

import com.haroncode.gemini.android.connection.DelegateConnectionRuleFactory
import com.haroncode.gemini.connection.ConnectionRule
import com.haroncode.gemini.dsl.connectTo
import com.haroncode.gemini.dsl.connectionFactory
import com.haroncode.gemini.dsl.observeOn
import com.haroncode.gemini.sample.domain.system.SchedulersProvider
import com.haroncode.gemini.sample.ui.MainFragment
import javax.inject.Inject

class MainConnectionFactory @Inject constructor(
    private val store: MainStore,
    private val schedulersProvider: SchedulersProvider
) : DelegateConnectionRuleFactory<MainFragment>() {

    override val connectionRuleFactory: ConnectionRule.Factory<MainFragment> = connectionFactory { view ->
        rule { view connectTo store }
        rule { store connectTo view observeOn schedulersProvider.ui() }
        autoDispose { store } // magic is here )))
    }
}
