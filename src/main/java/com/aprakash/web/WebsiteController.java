package com.aprakash.web;

import com.aprakash.services.TransactionService;
import com.aprakash.web.forms.TransactionForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class WebsiteController {

    private final TransactionService transactionService;

    private final String bankSlogan;

    public WebsiteController(TransactionService transactionService, @Value("${bank.slogan}") String bankSlogan) {
        this.transactionService = transactionService;
        this.bankSlogan = bankSlogan;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("slogan", bankSlogan);
        return "index.html";
    }

    @GetMapping("/account/{userId}")
    public String transactionList(Model model, @PathVariable("userId") String userId) {
        buildAccountPageModel(model, userId);
        model.addAttribute("txForm", new TransactionForm());
        return "accounts.html";
    }

    @PostMapping("/account/{userId}")
    public String newTransaction(@ModelAttribute("txForm") @Valid TransactionForm txForm,
                                 BindingResult bindingResult,
                                 @PathVariable("userId") String userId,
                                 Model model) {

        buildAccountPageModel(model, userId);

        if (bindingResult.hasErrors()) {
            model.addAttribute("txError", true);
            return "accounts.html";
        }

        createTransaction(txForm);
        return "redirect:/account/" + userId;
    }

    private void createTransaction(TransactionForm txForm) {
        transactionService.create(txForm.getAmount(), txForm.getTransactionDetails(), txForm.getReceivingUser());
    }

    private void buildAccountPageModel(Model model, String userId) {
        model.addAttribute("userId", userId);
        model.addAttribute("transactions", transactionService.findByReceivingUserId(userId));
    }

}
