
# Transaction Automator

This project automates the creation and status management of transactions within the core module. It provides an end-to-end flow for processing transactions and updating their statuses using Selenium-based integration tests.

## Main Class

The primary class for this project is:
`src/test/java/com/udemy/spring/springselenium/integratedtests/PaymentFlowIntegrationTest.java`

## Test Flow

The main test method that orchestrates the transaction flow is run(), which performs the following steps:
```java
@Test
public void run() throws Exception {
    createTransaction();
    authenticate(); // required once
    String fileId = updateCutoff();
    List<String> transactionPtxList = getFileTransactionPtxList(fileId);
    runAchProcessor();

    authenticateOnSimulator(); // required once

    simulateStatus(transactionPtxList, SimulatorStatus.SENT);
    runAchProcessor();
    simulateStatus(transactionPtxList, SimulatorStatus.REJECTED);
    runAchProcessor();
}
```

## Steps in the Flow

1. Create a Transaction: Initiates a new transaction.
2. Authenticate: Required once to access the system.
3. Update Cutoff: Updates the cutoff time and retrieves a file ID.
4. Get Transactions: Retrieves the transaction list associated with the file.
5. Run ACH Processor: Processes the transactions.
6. Authenticate on Simulator: Required once to interact with the simulator.
7. Simulate Status: Updates the transaction status to SENT, REJECTED, RECEIVED, RETURNED and NOC, running the processor after each update.

## Dependencies

- Java
- Selenium
- Spring Framework

## Usage

1. Clone the repository.
2. Ensure all dependencies are installed.
3. Run `core` module with `paypro-us-ach-crossriver-simulator`
3. Run the PaymentFlowIntegrationTest class to execute the test.

`mvn test -Dtest=PaymentFlowIntegrationTest`
