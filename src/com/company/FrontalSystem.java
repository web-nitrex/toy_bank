package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class FrontalSystem {
    //очередь заявок
    private Queue<Request> requests;

    FrontalSystem()
    {
        requests = new LinkedList<>();
    }

    //принимаем запрос от клиента и добавляем его в очередь если в очереди есть место
    public synchronized void acceptRequest(Request request)
    {
        try {
            while (requests.size()>=2)
                wait();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        requests.add(request);

        System.out.println(request.getClientThreadName()+": "+request+" отправлена в банк");

        notifyAll();

    }

    //получаем запрос из очереди
    public synchronized Request getRequest()
    {
        try {
            while (requests.size()==0)
                wait();
        }
        catch (InterruptedException e) {
                e.printStackTrace();
            }

        notifyAll();

        return requests.poll();
    }
}
