# Currency Exchanger

This is a simple Currency Exchanger application designed to handle multiple currency exchanges. It allows users to convert one currency to another, starting with a balance of 1000 Euros (EUR). The app fetches the exchange rate via an API and supports commission fees for the exchange.

## Tech Stack

- **Jetpack Compose**: Used for building the user interface.
- **Retrofit**: Used for making API calls to get exchange rates.
- **Coroutines**: Used for handling asynchronous tasks.
- **Dagger Hilt**: Used for dependency injection.

## Features

1. **Currency Exchange**: The user can input an amount to be converted, select the currency to sell, and select the currency to buy. The conversion will display the result along with the updated balances.
   
2. **Commission Fee**: The first five currency exchanges are free. After that, a 0.7% commission fee is charged on the currency being traded. This fee is displayed in the result message and deducted from the balances.

3. **Expandable Architecture**: The app is designed to be easily expandable. Multiple interfaces have been implemented, which may not all be necessary at the moment, but this ensures scalability for future features.

4. **SOLID Principles**: The app has been designed with SOLID principles in mind, ensuring that each class and function has a single responsibility.

## Commission Fee Calculation

There was some uncertainty on whether the commission fee should be deducted before or after the currency conversion. In the example, “You have converted 100.00 EUR to 110.00 USD. Commission Fee - 0.70 EUR”, the fee is shown in EUR. Therefore, I decided to deduct the fee from the EUR balance first, then perform the currency conversion with the remaining amount.
