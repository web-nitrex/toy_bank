package com.company;

public class Request {
    private String clientThreadName;
    private long amount;
    private RequestType requestType;

    Request(String name, long amount, RequestType type)
    {
        this.clientThreadName = name;
        this.amount = amount;
        this.requestType = type;
    }

    @Override
    public String toString()
    {
        StringBuilder res = new StringBuilder();
        res.append("Заявка{");
        res.append("clientThreadName='");
        res.append(clientThreadName);
        res.append("', amount=");
        res.append(amount);
        res.append(", requestType=");
        if (requestType==RequestType.CREDIT)
            res.append("CREDIT");
        else
            res.append("REPAYMENT");
        res.append("}");

        return res.toString();
    }

    public String getClientThreadName() {
        return clientThreadName;
    }

    public long getAmount() {
        return amount;
    }

    public RequestType getRequestType() {
        return requestType;
    }
}
