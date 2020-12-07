package com.company;

public class BackSystemBank {
    private long balance=0;

    public synchronized void runTransaction(Request request, int idHandler)
    {
        switch (request.getRequestType())
        {
            case CREDIT:
                if (balance>=request.getAmount())
                {
                    balance-=request.getAmount();
                    System.out.println("Бэк система: "+request+" УСПЕШНО ВЫПОЛНЕНА. Получена от Обработчик заявок №"+idHandler+". Баланс банка: "+balance);
                }
                else {
                    System.out.println("Бэк система: " + request + " НЕ ВЫПОЛНЕНА. Получена от Обработчик заявок №" + idHandler + ". Сумма больше баланса банка. Баланс банка: " + balance);
                }
                break;
            case REPAYMENT:
                balance+=request.getAmount();
                System.out.println("Бэк система: "+request+" УСПЕШНО ВЫПОЛНЕНА. Получена от Обработчик заявок №"+idHandler+". Баланс банка: "+balance);
                break;
            default:
                System.out.println("ОШИБКА ТРАНЗАКЦИИ!");
        }

    }

}
