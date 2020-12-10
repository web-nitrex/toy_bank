package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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


        //создаем пул клиентских потоков
        ExecutorService executorClients = Executors.newFixedThreadPool(requests.length);

        //создаем клиентов, устанавливаем ему соответствующий запрос и запускаем потоки клиентов на выполнение
        for(Request req : requests)
            executorClients.submit(new Client(frontalSystem,req));

        //создаем пул потоков обработчиков запросов и запускаем их
        ExecutorService executorRequestHandler = Executors.newFixedThreadPool(2);
        executorRequestHandler.submit(new RequestHandler(1,frontalSystem,backSystemBank));
        executorRequestHandler.submit(new RequestHandler(2,frontalSystem,backSystemBank));

        executorClients.shutdown();
        executorRequestHandler.shutdown();

    }
}
