//package com.cornerfoodmarketwebsite;
//
//public class Assignment2 {
//    public static void main (String[] args) {
//        String username = "Angel";
//        String messageToClient;
//        try {
//            System.out.println("Is user allowed: " + isUserAllowed(username));
//            messageToClient = "You are allowed";
//        } catch (NotAllowedException e) {
//            messageToClient = e.getMessage();
//        }
//
//        return messageToClient;
//    }
//
//    public static boolean isUserAllowed (String username) throws NotAllowedException {
//        if (username.equals("Angel")) {
//            throw new NotAllowedException("Angel is not allowed");
//        } else {
//            return true;
//        }
//    }
//}
