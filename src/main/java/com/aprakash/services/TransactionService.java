package com.aprakash.services;

import com.aprakash.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.sql.*;

@Component
public class TransactionService {

    private static final ZoneId DEFAULT_ZONE = ZoneId.systemDefault();

    private final JdbcTemplate jdbcTemplate;

    private final String bankSlogan;

    public TransactionService(@Value("${bank.slogan}") String bankSlogan, JdbcTemplate jdbcTemplate) {
        this.bankSlogan = bankSlogan;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public List<Transaction> findAll() {
        return jdbcTemplate.query("SELECT id, amount, timestamp, transactionDetails, slogan, receiving_user FROM TRANSACTIONS",
                TransactionService::mapRow);
    }

    @Transactional
    public List<Transaction> findByReceivingUserId(String userId) {
        return jdbcTemplate.query(con -> {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT tx.id, tx.amount, tx.timestamp, tx.transactionDetails, tx.slogan, tx.receiving_user FROM TRANSACTIONS tx WHERE tx.receiving_user = ?");
            ps.setString(1, userId);
            return ps;
        }, TransactionService::mapRow);
    }

    public Transaction create(Integer amount, String transaction_details, String receivingUser) {
        ZonedDateTime timestamp = LocalDateTime.now().atZone(DEFAULT_ZONE);
        Transaction transaction = new Transaction(amount, transaction_details, timestamp, bankSlogan, receivingUser);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO TRANSACTIONS (amount, timestamp, transactionDetails, slogan, receiving_user) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, transaction.getAmount());
            ps.setTimestamp(2, Timestamp.valueOf(transaction.getTimestamp().toLocalDateTime()));
            ps.setString(3, transaction.getTransactionDetails());
            ps.setString(4, transaction.getSlogan());
            ps.setString(5, transaction.getReceivingUser());
            return ps;
        }, keyHolder);

        String uuid = !keyHolder.getKeys().isEmpty() ? keyHolder.getKeys().values().iterator().next().toString() : null;
        transaction.setId(uuid);
        return transaction;
    }

    private static Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transaction tx = new Transaction();
        tx.setId(rs.getObject("id").toString());
        tx.setAmount(rs.getInt("amount"));
        tx.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime().atZone(DEFAULT_ZONE));
        tx.setTransactionDetails(rs.getString("transactionDetails"));
        tx.setSlogan(rs.getString("slogan"));
        tx.setReceivingUser(rs.getString("receiving_user"));
        return tx;
    }

}
