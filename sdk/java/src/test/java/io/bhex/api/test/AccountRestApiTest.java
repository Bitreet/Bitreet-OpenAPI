package io.bhex.api.test;

import io.bhex.api.client.BHexApiClientFactory;
import io.bhex.api.client.BHexApiRestClient;
import io.bhex.api.client.constant.BHexConstants;
import io.bhex.api.client.domain.account.*;
import io.bhex.api.client.domain.account.request.*;
import io.bhex.api.test.constant.Constants;

import java.util.List;

public class AccountRestApiTest {


    public static void main(String[] args) {

        BHexApiClientFactory factory = BHexApiClientFactory.newInstance(Constants.ACCESS_KEY, Constants.SECRET_KEY);
        BHexApiRestClient client = factory.newRestClient();

        System.out.println("\n ------limit buy-----");
        NewOrderResponse response1 = client.newOrder(NewOrder.limitBuy("BTCUSDT", TimeInForce.GTC,"0.01","5678.9"));
        System.out.println(response1);
//
        System.out.println("\n ------limit sell-----");
        NewOrderResponse response2 = client.newOrder(NewOrder.limitSell("BTCUSDT",TimeInForce.GTC,"0.01","1001"));
        System.out.println(response2);

        System.out.println("\n ------market buy-----");
        NewOrderResponse response3 = client.newOrder(NewOrder.marketBuy("BTCUSDT","10"));
        System.out.println(response3);

        System.out.println("\n ------market sell-----");
        NewOrderResponse response4 = client.newOrder(NewOrder.marketSell("BTCUSDT","0.01"));
        System.out.println(response4);

        System.out.println("\n ------get order status-----");
        Order order = client.getOrderStatus(new OrderStatusRequest(response1.getOrderId()));
        System.out.println(order);

        System.out.println("\n ------cancel order-----");
        CancelOrderResponse cancelOrderResponse = client.cancelOrder(new CancelOrderRequest(response1.getOrderId()));
        System.out.println(cancelOrderResponse);

        System.out.println("\n ------get open orders-----");
        List<Order> openOrderList = client.getOpenOrders(new OpenOrderRequest("BTCUSDT",5));
        System.out.println(openOrderList);
//
        System.out.println("\n ------get history orders-----");
        List<Order> historyOrderList = client.getHistoryOrders(new HistoryOrderRequest());
        System.out.println(historyOrderList);

        System.out.println("\n ------get account information-----");
        Account account = client.getAccount(BHexConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());
        System.out.println(account);
        System.out.println(account.getBalances());
        System.out.println(account.getAssetBalance("ETH"));

        System.out.println("\n ------get trades -----");
        List<Trade> tradeList = client.getMyTrades(new MyTradeRequest(2));
        System.out.println(tradeList);

        System.out.println("\n ------get deposit order -----");
        List<DepositOrder> list = client.getDepositOrders(new DepositOrderRequest());
        System.out.println(list);

    }

}
