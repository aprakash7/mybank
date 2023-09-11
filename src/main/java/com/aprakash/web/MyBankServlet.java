package com.aprakash.web;

import com.aprakash.context.Application;
import com.aprakash.model.Transaction;
import com.aprakash.services.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class MyBankServlet extends HttpServlet {
    private TransactionService transactionService = Application.transactionService;
    private ObjectMapper objectMapper = Application.objectMapper;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().equalsIgnoreCase("/transactions")) {
            Integer amt = Integer.valueOf(request.getParameter("amount"));
            String transactionDetails = request.getParameter("transaction_details");

            Transaction transaction = transactionService.create(amt, transactionDetails);

            response.setContentType("application/json; charset=UTF-8");
            String json = objectMapper.writeValueAsString(transaction);
            response.getWriter().print(json);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().equalsIgnoreCase("/")) {
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print("Response provided successfully");
        } else if (request.getRequestURI().equalsIgnoreCase("/transactions")) {
            response.setContentType("application/json; charset=UTF-8");
            List<Transaction> transactions = transactionService.findAll();
            response.getWriter().print(objectMapper.writeValueAsString(transactions));
        }
    }
}
