package com.example.designpatterns.console

//Useful for building classes with complex constructors via provision of a Fluent API

data class BankAccount private constructor(
    val number: Long,
    val name: String,
    val branch: String,
    val balance: Double,
    val interestRate: Double
) {
        class Builder(private val number: Long) {
            private var name: String = ""
            private var branch: String = ""
            private var balance: Double = 0.0
            private var interestRate: Double = 0.0

            fun withName(name: String): Builder {
                this.name = name
                return this
            }

            fun atBranch(branch: String): Builder {
                this.branch = branch
                return this
            }

            fun openingBalance(balance: Double): Builder {
                this.balance = balance
                return this
            }

            fun withInterestRate(rate: Double): Builder {
                this.interestRate = rate
                return this
            }

            fun build(): BankAccount {
                return BankAccount(
                    number,
                    name,
                    branch,
                    balance,
                    interestRate
                )
            }

        }
}

fun main() {
    val account = BankAccount.Builder(1232)
        .withName("Julie")
        .atBranch("London")
        .openingBalance(100.0)
        .withInterestRate(2.5)
        .build()
    println(account)
}