package com.example.designpatterns.console

//Facade - provision of a simpler interface for complex classes

//Complex classes
class AccountChecker(private val accountNumber: Int) { fun checkAccount() = true }
class FundsChecker(private val accountNumber: Int) { fun checkFunds() = true }
class PinCodeChecker(private val accountNumber: Int, private val pin: Int){ fun validatePincode() = true }
class DebitEngine(accountNumber: Int) { fun withDraw() = true }

//User-friendly Facade
class ATM {
    fun withdrawMoney(accountNumber: Int, pin: Int) {
        val accountChecker = AccountChecker(accountNumber)
        val fundsChecker = FundsChecker(accountNumber)
        val pinCodeChecker = PinCodeChecker(accountNumber, pin)
        val debitEngine = DebitEngine(accountNumber)

        if(accountChecker.checkAccount() &&
                fundsChecker.checkFunds() &&
                pinCodeChecker.validatePincode()) {

            if(debitEngine.withDraw())
                println("Withdrawal successful.")
        }
    }
}

fun main() {

    val atm = ATM()
    atm.withdrawMoney(553412112, 1234)

}