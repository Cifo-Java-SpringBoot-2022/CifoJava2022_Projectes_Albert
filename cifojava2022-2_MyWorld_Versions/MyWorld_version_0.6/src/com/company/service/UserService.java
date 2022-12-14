package com.company.service;

import com.company.model.User;

import java.util.ArrayList;

public class UserService {

    public static int isCardNumber(long cardNumber, ArrayList<User> users) {
        //find out if cardNumber exists in users
        int position = -1;
        for (User user : users) {
            if (user.getCard().getNumber() == cardNumber) position = users.indexOf(user);
        }
        return position;
    }

    public static int isCardNumberError(int cardNumber, ArrayList<User> users) {
        //find out if cardNumber exists in users
        //position will value : -3, -2, -1, 0..n
        // if cardNumber is right : 0 .. n (position within array users)
        // if card does not exist : -1
        // if card exists but card number is wrong : -2
        // if there are some error undefined : -3
        int position = -3;
        for (User user : users) {
            boolean isCard = user.getCard() != null;
            if (isCard) {
                if (user.getCard().getNumber() == cardNumber) {
                    position = users.indexOf(user);
                } else position = -2;
            } else position = -1;
        }
        return position;
    }

    public static boolean isCardNumberBool(int cardNumber, ArrayList<User> users) {
        boolean isCard = false;
        for (User user : users) {
            if (user.getCard().getNumber() == cardNumber) isCard = true;
        }
        return isCard;
    }

    public static User isCardNumberUser(int cardNumber, ArrayList<User> users) {
        User isUser = null;
        for (User user : users) {
            if (user.getCard().getNumber() == cardNumber) isUser = user;
        }
        return isUser;
    }

    public static void updatePin(int newPin, ArrayList<User> users, int position) {
        users.get(position).getCard().setPin(newPin);
    }

    public static boolean isEnoughAmount(ArrayList<User> users, int position, Double amount) {
        //check if is enough money in origin card
        boolean isMoney = users.get(position).getCard().getAmount() >= amount;
        return isMoney;
    }

    public static void makeTransfer(int originPosition, int destinationPosition, Double amount, ArrayList<User> users) {
        //rest this qty amount from origin
        users.get(originPosition).getCard().removeAmount(amount);
        //add this qty amount from destination
        users.get(destinationPosition).getCard().addAmount(amount);
    }

    public static void makeDeposit(int position, Double amount, ArrayList<User> users) {
        //add this qty amount from position
        users.get(position).getCard().addAmount(amount);
    }

}