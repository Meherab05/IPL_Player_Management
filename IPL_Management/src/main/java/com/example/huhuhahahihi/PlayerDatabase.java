package com.example.huhuhahahihi;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class PlayerDatabase {
    public static List<Player> players = new ArrayList<>();
    public volatile static Map<String, List<Player>> clMap = new HashMap<>();

    static void addPlayer(Player p) {
        players.add(p);
        clMap.putIfAbsent(p.getClub().toLowerCase(), new ArrayList<>());
        clMap.get(p.getClub().toLowerCase()).add(p);
    }

    static Player searchPlayerByName(String name) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                return players.get(i);

            }
        }
        return null;

    }

    static List<Player> searchPlayerByCountry(String country, String club) {
        List<Player> list = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getCountry().toLowerCase().equals(country.toLowerCase())
                    && (club.toLowerCase().equals("any")
                            || players.get(i).getClub().toLowerCase().equals(club.toLowerCase()))) {
                list.add(players.get(i));
            }
        }
        return list;
    }

    static List<Player> searchPlayerByPosition(String position) {
        List<Player> list = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getPosition().toLowerCase().equals(position.toLowerCase())) {
                list.add(players.get(i));
            }
        }
        return list;
    }

    static List<Player> searchPlayerBySalary(long start, long end) {
        List<Player> list = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getSalary() <= end && players.get(i).getSalary() >= start) {
                list.add(players.get(i));
            }
        }
        return list;
    }

    static Map<String, Integer> displayCount() {
        Map<String, Integer> mp = new HashMap<>();
        for (int i = 0; i < players.size(); i++) {
            mp.put(players.get(i).getCountry().toUpperCase(),
                    mp.getOrDefault(players.get(i).getCountry().toUpperCase(), 0) + 1);
        }
        return mp;

    }

    static List<Player> searchByClub(String club, int option) {
        List<Player> p = clMap.get(club.toLowerCase());
        List<Player> list = new ArrayList<>();
        if(p==null) return list;

        if (option == 1) {
            long mx = -1;
            for (Player x : p) {
                mx = Math.max(mx, x.getSalary());
            }
            for (Player x : p) {
                if (x.getSalary() == mx)
                    list.add(x);
            }
        } else if (option == 2) {
            double mx = -1;
            for (Player x : p) {
                mx = Math.max(mx, x.getAge());
            }
            for (Player x : p) {
                if (x.getAge() == mx)
                    list.add(x);
            }
        } else if (option == 3) {
            double mx = -1;
            for (Player x : p) {
                mx = Math.max(mx, x.getHeight());
            }
            for (Player x : p) {
                if (x.getHeight() == mx)
                    list.add(x);
            }
        }
        return list;
    }

    static Long clubSalary(String club) {
        List<Player> p = clMap.get(club.toLowerCase());

        Long ans = 0l;
        if(p==null)return ans;
        for (Player x : p) {
            ans = ans + (52 * x.getSalary());
        }

        return ans;

    }
}