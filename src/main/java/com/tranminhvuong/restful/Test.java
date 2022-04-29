package com.tranminhvuong.restful;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

public class Test {

    public static int tinhTong(List<Integer> list) {
        return list.stream().reduce(0, (accumulator, value) -> {
            return accumulator + value;
        });
    }

    public static int max(List<Integer> list) {
        return list.stream().max(Comparator.comparing(Integer::valueOf)).get();
    }

    public static int converChatToInt(String s) {
        if(s.equalsIgnoreCase("R"))
            return 4;
        else if(s.equalsIgnoreCase("C"))
            return 3;
        else if (s.equalsIgnoreCase("B"))
            return 2;
        else
            return 1;
    }

    public static Card convertToCard(String s) {
        String[] a = s.split("");
        return new Card(Integer.parseInt(a[0]), converChatToInt(a[1]));
    }

    public static Player convertToPlayer(List<String> list, String name) {
        Player player = new Player();
        List<Card> cards = new ArrayList<>();
        list.forEach(a -> {
            cards.add(convertToCard(a));
        });
        int sum = 0;
        for (Card card : cards) {
            sum = sum + card.getNumber();
        }

        cards.sort((a,b) -> a.getChat() - b.getChat());

        Card chatMax = new Card();

        List<Integer> listChat = new ArrayList<>();
        cards.forEach(a -> {
            listChat.add(a.getNumber());
        });
        int max = max(listChat);
        List<Card> listChatMax = cards.stream().filter(value -> value.getNumber() == max).collect(Collectors.toList());


        return player;
    }

//    public static List<List<Integer>> tach (List<String> list) {
//
//        List<Integer> values = new ArrayList<>();
//        List<Integer> chat = new ArrayList<>();
//
//        list.forEach(s -> {
//            String[] a = s.split("");
//            values.add(Integer.parseInt(a[0]));
//            chat.add(converChatToInt(a[1]));
//        });
//
//        List<List<Integer>> results = new ArrayList<>();
//        results.add(values);
//        results.add(chat);
//        return results;
//    }

    public static String baCay(List<String> A, List<String> B, List<String> C ,List<String> D) {
//        List<List<Integer>> tachA = tach(A);
//        List<List<Integer>> tachB = tach(B);
//        List<List<Integer>> tachC = tach(C);
//        List<List<Integer>> tachD = tach(D);

//        Player a = new Player("A", tachA.get(0), tachA.get(1), tinhTong(tachA.get(0)));
//        Player b = new Player("B", tachB.get(0), tachB.get(1), tinhTong(tachB.get(0)));
//        Player c = new Player("C", tachC.get(0), tachC.get(1), tinhTong(tachC.get(0)));
//        Player d = new Player("D", tachD.get(0), tachD.get(1), tinhTong(tachD.get(0)));

//        List<Player> players = Arrays.asList(a, b, c, d);
//        int max = max(Arrays.asList(a.getSum(), b.getSum(), c.getSum(), d.getSum()));
//
//        List<Player> players1 = new ArrayList<>();
//        for (int i = 0; i < players.size(); i++) {
//            if(players.get(i).getSum() == max) {
//                players1.add(players.get(i));
//            }
//        }
//
//        if(players1.size() == 1) {
//            return players1.get(0).getName();
//        } else {
//            List<Integer> listChat = new ArrayList<>();
//            for (int i = 0; i < players1.size(); i++) {
//                listChat.add(max(players1.get(i).getChat()));
//            }
//            int maxChat = max(listChat);
//            List<Player> players2 = new ArrayList<>();
//            for (int i = 0; i < players1.size(); i++) {
//                if(max(players1.get(i).getChat()) == maxChat) {
//                    players2.add(players1.get(i));
//                }
//            }
//
//            if(players2.size() == 1) {
//                return players2.get(0).getName();
//            } else {
//                List<Integer> listValue = new ArrayList<>();
//                for(int i=0; i< players2.size(); i++) {
//                    if(players2.get(i).getValues().stream().filter(x -> x == 1).findFirst().orElse(null) != null) {
//                        return players2.get(i).getName();
//                    }
//                    else {
//                        listValue.add(max(players2.get(i).getValues()));
//                    }
//                }
//
//                int maxValue = max(listValue);
//
//                for(int i=0; i< players2.size(); i++) {
//                    if(max(players2.get(i).getValues()) == maxValue) {
//                        return players2.get(i).getName();
//                    }
//                }
//            }
//
//        }


        return "ok";
    }

    public static void main(String[] args) {
        List<String> A = Arrays.asList("9R", "3B", "5T"); // = 17
        List<String> B = Arrays.asList("8R", "4B", "5T"); // = 17
        List<String> C = Arrays.asList("7C", "1B", "6T"); // = 14
        List<String> D = Arrays.asList("8R", "1B", "9T"); // = 17
        System.out.println(baCay(A, B, C, D));


    }

//    @Getter
//    @Setter
//    @AllArgsConstructor
//    public static class Player{
//        private String name;
//        private List<Integer> values;
//        private List<Integer> chat;
//        private int sum;
//    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Player{
        private String name;
        private List<Card> cards;
        private int sum;
        private Card chatMax;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Card{
        private Integer number;
        private Integer chat;
    }

}