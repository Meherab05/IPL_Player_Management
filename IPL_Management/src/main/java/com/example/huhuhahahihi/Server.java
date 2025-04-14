package com.example.huhuhahahihi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    private int port;
    private static final Object lock = new Object();
    public List<Player> transferWindowPlayers = new ArrayList<>();
    public volatile static Map<String, List<Player>> clMap2 = new HashMap<>();
    final String INPUT_FILE_NAME = "src/main/java/com/example/huhuhahahihi/players.txt";

    public Server(int port) {
        this.port = port;
    }

    public void startServer() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Input file not found: " + INPUT_FILE_NAME, e);
        }

        while (true) {
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException("Error reading from input file", e);
            }

            if (line == null) break;

            String[] s = line.split(",");
            if (s.length < 8 || s[4].trim().isEmpty()) {
                System.err.println("Invalid player data: " + line);
                continue;
            }

            String clubName = s[4].toLowerCase(); // Normalize the club name to lowercase

            synchronized (lock) {
                clMap2.putIfAbsent(clubName, new ArrayList<>()); // Ensure a list exists for the club
                clMap2.get(clubName).add(new Player(s[0].toUpperCase(), s[1], Integer.parseInt(s[2]),
                            Double.parseDouble(s[3]), s[4], s[5], s[6], Long.parseLong(s[7])));

            }
        }

        try {
            if (br != null) br.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing the BufferedReader", e);
        }

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port: " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler implements Runnable {
        private NetworkUtil networkUtil;

        public ClientHandler(Socket socket) {
            try {
                this.networkUtil = new NetworkUtil(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Object request = networkUtil.read();
                    if (request instanceof String) {
                        processRequest((String) request);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    networkUtil.closeConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void processRequest(String request) {
            String[] tokens = request.split(":", 3);
            String command = tokens[0];
            String param1 = tokens.length > 1 ? tokens[1] : "";
            String param2 = tokens.length > 2 ? tokens[2] : "";

            try {
                switch (command) {
                    case "LOGIN":
                        loginClub(param1);
                        break;

                    case "REGISTER":
                        registerClub(param1);
                        break;

                    case "SHOW":
                        showPlayers(param1);
                        break;

                    case "SHOW_TRANSFER":
                        showTransferWindow();
                        break;

                    case "SELL":
                        sellPlayer(param1, param2);
                        break;

                    case "BUY":
                        buyPlayer(param1, param2);
                        break;

                    default:
                        networkUtil.write("ERROR: Unknown command");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void loginClub(String clubName) throws IOException {
            System.out.println("Received LOGIN request for club: " + clubName);

            if (clMap2.containsKey(clubName.toLowerCase())) {
                System.out.println("Login successful for club: " + clubName);
                networkUtil.write("SUCCESS");
            } else {
                System.out.println("Club does not exist: " + clubName);
                networkUtil.write("ERROR: Club does not exist.");
            }
        }

        private void registerClub(String clubName) {
            if (clMap2.containsKey(clubName.toLowerCase())) {
                try {
                    networkUtil.write("ERROR: Club already exists.");
                } catch (IOException e) {
                }
            } else {
                clMap2.putIfAbsent(clubName.toLowerCase(), new ArrayList<>());
                try {
                    networkUtil.write("SUCCESS");
                } catch (IOException e) {

                }
            }
        }

        private void showPlayers(String clubName) throws IOException {
            synchronized (lock) {
                List<Player> players = clMap2.getOrDefault(clubName.toLowerCase(), new ArrayList<>());
                networkUtil.write(players);
            }
        }

        private void showTransferWindow() throws IOException {
            synchronized (lock) {
                networkUtil.write(transferWindowPlayers);
            }
        }

        private void sellPlayer(String clubName, String playerName) throws IOException {


                    for(Player p:clMap2.getOrDefault(clubName.toLowerCase(),new ArrayList<>())){
                        if(p.getName().equalsIgnoreCase(playerName)){

                            transferWindowPlayers.add(p);
                            break;
                        }


            }
        }

        private void buyPlayer(String playerName, String clubName) throws IOException {
            synchronized (lock) {

                if(playerName==null) {networkUtil.write("ERROR: Player name cannot be null!");}
else {
                    boolean flag = false;
                    for (Player p : transferWindowPlayers) {
                        if (p.getName().equalsIgnoreCase(playerName)) {
                            clMap2.get(p.getClub().toLowerCase()).remove(p);
                            p.setClub(clubName.toLowerCase());
                            clMap2.putIfAbsent(clubName.toLowerCase(), new ArrayList<>());
                            clMap2.get(clubName.toLowerCase()).add(p);
                            transferWindowPlayers.remove(p);
                            flag = true;
                            break;
                        }
                    }



                }}}
    }

    public static void main(String[] args) {
        Server server = new Server(1234);
        server.startServer();
    }
}