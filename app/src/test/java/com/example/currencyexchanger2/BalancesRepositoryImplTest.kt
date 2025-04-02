package com.example.currencyexchanger2

import com.example.currencyexchanger2.currencyexchangescreen.managers.balances.BalancesManager
import com.example.currencyexchanger2.currencyexchangescreen.repository.BalancesRepositoryImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class BalancesRepositoryImplTest {

    private lateinit var balancesManager: BalancesManager
    private lateinit var balancesRepository: BalancesRepositoryImpl
    private lateinit var balancesFlow: StateFlow<Map<String, Double>>

    @Before
    fun setUp() {
        balancesManager = mockk(relaxed = true)
        balancesFlow = mockk()
        balancesRepository = BalancesRepositoryImpl(balancesManager)
        val initialBalances = mapOf("USD" to 50.0)

        every { balancesManager.balancesFlow } returns balancesFlow
        every { balancesFlow.value } returns initialBalances
    }

    @Test
    fun `addFunds should update the balance correctly`() = runBlocking {
        balancesRepository.addFunds("USD", 100.0)

        val expectedBalances = mapOf("USD" to 150.0)
        verify { balancesManager.updateBalances(expectedBalances) }
    }

    @Test
    fun `removeFunds should update the balance correctly`() = runBlocking {
        balancesRepository.removeFunds("USD", 25.0)


        val expectedBalances = mapOf("USD" to 25.0)
        verify { balancesManager.updateBalances(expectedBalances) }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `removeFunds should throw exception if balance is insufficient`() {
        balancesRepository.removeFunds("USD", 100.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `addFunds should throw exception if amount is negative`() {
        balancesRepository.addFunds("USD", -100.0)
    }

}