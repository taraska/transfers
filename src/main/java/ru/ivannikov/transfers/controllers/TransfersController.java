package ru.ivannikov.transfers.controllers;

import com.google.gson.Gson;
import ru.ivannikov.transfers.models.TransferRequest;
import ru.ivannikov.transfers.services.TransferService;

import static spark.Spark.*;

public class TransfersController {
    private TransferService transferService = new TransferService();

    public void initialize() {
        get("/transfer", (request, response) -> {
            try {
                int from = Integer.parseInt(request.queryParams("from"));
                int to = Integer.parseInt(request.queryParams("to"));
                double amount = Double.parseDouble(request.queryParams("amount"));
                if (amount <= 0) {
                    throw new Exception("abnormal amount");
                }

                String currency = request.queryParams("currency");

                return transferService.transfer(from, to, amount, currency);
            } catch (Exception e) {
                return e.getMessage();
            }

        });

        get("/history", (request, response) -> {
            Gson gson = new Gson();
            return gson.toJson(transferService.getHistory());
        });

        post("/transfer", (request, response) -> {
            TransferRequest transfer = new Gson().fromJson(request.body(), TransferRequest.class);

            return transferService.transfer(transfer.getFrom(),
                    transfer.getTo(),
                    transfer.getAmount(),
                    transfer.getCurrency());
        });
    }
}
