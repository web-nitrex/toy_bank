package com.company;

import java.util.concurrent.atomic.AtomicLong;

public class BackSystemBank {

    private AtomicLong balance = new AtomicLong();

    public void runTransaction(Request request, int idHandler)
    {
        switch (request.getRequestType())
        {
            case CREDIT:
                if (balance.get()>=request.getAmount())
                {
                    balance.addAndGet(-request.getAmount());

                    System.out.println("Бэк система: "+request+" УСПЕШНО ВЫПОЛНЕНА. Получена от Обработчик заявок №"+idHandler+". Баланс банка: "+balance);
                }
                else {
                    System.out.println("Бэк система: " + request + " НЕ ВЫПОЛНЕНА. Получена от Обработчик заявок №" + idHandler + ". Сумма больше баланса банка. Баланс банка: " + balance);
                }
                break;
            case REPAYMENT:
                balance.addAndGet(request.getAmount());
                System.out.println("Бэк система: "+request+" УСПЕШНО ВЫПОЛНЕНА. Получена от Обработчик заявок №"+idHandler+". Баланс банка: "+balance);
                break;
            default:
                System.out.println("ОШИБКА ТРАНЗАКЦИИ!");
        }

    }

}
