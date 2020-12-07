package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //создаём бэк систему банка
        BackSystemBank backSystemBank = new BackSystemBank();

        //создаем фронтальную систему
        FrontalSystem frontalSystem = new FrontalSystem();

        //создаем список запросов для каждого клиента
        Request[] requests = {  new Request("Клиент №1", 10000,RequestType.REPAYMENT),
                                new Request("Клиент №2", 15000,RequestType.REPAYMENT),
                                new Request("Клиент №3", 20000,RequestType.REPAYMENT),
                                new Request("Клиент №4", 5000,RequestType.CREDIT),
                                new Request("Клиент №5", 150000,RequestType.CREDIT)};

        //создаем список клиентов
        List<Thread> clients = new ArrayList<>();

        //создаем клиента и устанавливаем ему соответствующий запрос
        for(Request req : requests)
            clients.add(new Client(frontalSystem,req));

        //запускаем потоки клиентов на выполнение
        for(Thread th : clients)
            th.start();

        //создаем обработчики заявок от клиента
        RequestHandler rh1 = new RequestHandler(1,frontalSystem,backSystemBank);
        RequestHandler rh2 = new RequestHandler(2,frontalSystem,backSystemBank);

        //запускаем обработчики заявок
        rh1.start();
        rh2.start();

    }
}
