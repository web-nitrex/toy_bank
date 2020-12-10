package com.company;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FrontalSystem {
    //очередь заявок
    private int QUEUE_SIZE = 2;
    private BlockingQueue<Request> requests;

    FrontalSystem()
    {
        requests = new ArrayBlockingQueue<>(QUEUE_SIZE,true);
    }

    //принимаем запрос от клиента и добавляем его в очередь если в очереди есть место
    public void acceptRequest(Request request)
    {

        try {
            requests.put(request);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println(request.getClientThreadName()+": "+request+" отправлена в банк");

    }

    //получаем запрос из очереди
    public Request getRequest()
    {
        Request req = null;

        try {
            req = requests.take();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        return req;
    }
}
