package com.company;

public class Client  extends Thread{
    private FrontalSystem frontalSystem;
    private Request request;

    Client(FrontalSystem fs, Request request)
    {
        this.frontalSystem=fs;
        this.request = request;
    }

    @Override
    public void run()
    {
        //отправляем запрос в очередь запросов
        frontalSystem.acceptRequest(request);
    }
}
