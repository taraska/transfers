package ru.ivannikov.transfers;

import ru.ivannikov.transfers.controllers.TransfersController;

public class Application {
    public static void main(String[] args) {
        TransfersController transfersController = new TransfersController();
        transfersController.initialize();
    }
}
