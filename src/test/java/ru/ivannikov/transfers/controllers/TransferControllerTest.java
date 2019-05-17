package ru.ivannikov.transfers.controllers;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.ivannikov.transfers.TestData;
import ru.ivannikov.transfers.data.Data;
import spark.utils.IOUtils;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.fail;
import static spark.Spark.*;

public class TransferControllerTest {

    @Before
    public void init() {
        final TestData testData = new TestData();
        Data.accountList = TestData.accountList;

        TransfersController transfersController = new TransfersController();
        transfersController.initialize();
        awaitInitialization();
    }

    @After
    public void after() {
        stop();
    }

    @Test
    public void getTransferTestOk() {
        final String get = "/transfer?from=1&to=2&amount=9&currency=RUB";
        String body = this.getResponse(get);
        Assert.assertEquals(body, "success");

    }

    @Test
    public void getTransferTestException() {
        final String get = "/transfer?from=1&to=2&amount=-9&currency=RUB";
        String body = this.getResponse(get);
        Assert.assertEquals(body, "abnormal amount");
    }

    private String getResponse(String get) {
        String body = "";
        try {
            URL url = new URL("http://localhost:4567" + get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.connect();

            body = IOUtils.toString(connection.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Sending request failed: " + e.getMessage());
        }
        return body;
    }
}
