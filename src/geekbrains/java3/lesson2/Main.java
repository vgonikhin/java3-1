package geekbrains.java3.lesson2;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement psi;
    private static Scanner sc;

    public static void main(String[] args) throws Exception {
        ResultSet res;
        String command;
        sc = new Scanner(System.in);

        connect();
        stmt = connection.createStatement();

        initializeTable(10000);
        while(true) {
            System.out.println("Enter command: ");
            command = sc.nextLine();
            if(command.equals(""))break;
            else {
                String commandWords[] = command.split(" ");

                if (commandWords.length == 2 && commandWords[0].equals("cost")) {
                    try {
                        res = stmt.executeQuery("SELECT cost FROM Products WHERE title = '" + commandWords[1] + "'");
                        System.out.println("The cost of " + commandWords[1] + " is " + res.getInt("COST"));
                    } catch (Exception e) {
                        System.out.println("No product under this name");
                    }
                } else if (commandWords.length == 3 && (commandWords[0].equals("prodbycost") || commandWords[0].equals("pbc"))) {
                    try {
                        int start = Integer.parseInt(commandWords[1]);
                        int end = Integer.parseInt(commandWords[2]);
                        if(start<=end)
                            printTable("Products", start, end);
                        else
                            printTable("Products", end, start);
                    } catch (NumberFormatException e){
                        System.out.println("Wrong number format");
                    }
                } else if (commandWords.length == 3 && (commandWords[0].equals("setcost") || commandWords[0].equals("sc"))){
                    try {
                        int newPrice = Integer.parseInt(commandWords[2]);
                        if(stmt.executeUpdate("UPDATE Products SET cost = " + newPrice + " WHERE title = '" + commandWords[1] + "'")==1)
                            System.out.println("The cost of " + commandWords[1] + " is set to " + commandWords[2]);
                        else
                            System.out.println("No product under this name");
                    } catch (NumberFormatException e){
                        System.out.println("Wrong number format");
                    } catch (Exception e){
                        System.out.println("No product under this name");
                    }
                }

                else if (command.equals("exit")) {
                    System.out.println("Thanks for using this program");
                    break;
                } else {
                    System.out.println("Unknown command");
                    break;
                }
            }
        }


        disconnect();
    }

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:MainDbNew.db");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void initializeTable(int tableSize) throws Exception{
        stmt.execute("CREATE TABLE IF NOT EXISTS Products (\n" +
                "    ID    INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                "    PRODID INTEGER, \n" +
                "    TITLE TEXT,\n" +
                "    COST INTEGER);");

        stmt.execute("DELETE FROM Products");
        stmt.execute("UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME='Products';");

        connection.setAutoCommit(false);

        psi = connection.prepareStatement("INSERT INTO Products (prodid, title, cost) VALUES (?, ?, ?)");
        for (int i = 1; i <= tableSize; i++) {
            psi.setInt(1, i);
            psi.setString(2,"product" + i);
            psi.setInt(3, i * 10);
            psi.addBatch();
        }

        psi.executeBatch();
        connection.setAutoCommit(true);
    }

    public static void printTable(String tableName) throws Exception {
        ResultSet res = stmt.executeQuery("SELECT * FROM " + tableName);
        while (res.next()) {
            System.out.println(
                    res.getInt("ID")
                            + " " + res.getInt("PRODID")
                            + " " + res.getString("TITLE")
                            + " " + res.getInt("COST")
            );
        }
    }

    public static void printTable(String tableName, int start, int end) throws Exception {
        ResultSet res = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE cost>= " + start + " AND cost<= " + end);
        while (res.next()) {
            System.out.println(
                    res.getInt("ID")
                            + " " + res.getInt("PRODID")
                            + " " + res.getString("TITLE")
                            + " " + res.getInt("COST")
            );
        }
    }
}

