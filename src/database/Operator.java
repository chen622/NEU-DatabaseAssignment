package database;

import data.Account;
import data.DVD;
import data.Rental;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Operator {

    private static Database database;

    public static void init() {
        database = new Database();
    }

    public static boolean rentDVD(int memberID, int entityID) {
        String[] queries = SQLBuilder.rentDVD(memberID, entityID);
        return database.execute(queries[0]) && database.execute(queries[1]) && database.execute(queries[2]);
    }

    public static boolean returnDVD(int entityID, String toLibrary) {
        return database.execute(SQLBuilder.returnDVD(entityID, toLibrary));
    }


    public static DVD[] dvds(String library, String title, String genre) throws SQLException {
        String whereCondition = "WHERE "
                + ((library == null || library.equals("") ? "" : "library_name LIKE " + library + " AND")
                + (title == null || title.equals("") ? "" : "title LIKE " + title + " AND")
                + (genre == null || genre.equals("") ? "" : "genre=" + genre));

        if(whereCondition.endsWith("AND"))whereCondition.substring(whereCondition.length()-4,whereCondition.length()-1);
        if(whereCondition.endsWith("WHERE "))whereCondition="";
        // WHERE CONDITION 直接写为数据库的条件 为空则返回所有
        ResultSet resultSet = database.executeQuery(
                "SELECT *, (SELECT GROUP_CONCAT(cast.cast) FROM cast WHERE cast.prop_id=dvd_entity.prop_id) as cast, (SELECT GROUP_CONCAT(genre_type.genre_type_name) as genres FROM genre_type,dvd_genre WHERE genre_type.genre_type_id=dvd_genre.genre_type_id AND dvd_genre.prop_id=dvd_entity.prop_id) as genre FROM dvd_entity JOIN dvd_prop ON dvd_entity.prop_id=dvd_prop.prop_id "
                        + ((whereCondition == null || whereCondition.equals("")) ? "" : whereCondition)
        );
        DVD[] dvds = new DVD[resultSet.getFetchSize()];
        int i = 0;
        while (resultSet.next()) {
            dvds[i] = new DVD(
                    resultSet.getInt("entity_id"),
                    resultSet.getString("title"),
                    resultSet.getString("release_date"),
                    resultSet.getString("director"),
                    resultSet.getString("genre"),
                    resultSet.getString("cast"),
                    resultSet.getString("library_name")
            );
            i++;
        }
        return dvds;
    }

    public static Rental[] rentals(Integer memberID, String library, String title) throws SQLException {
        String whereCondition = "WHERE "
                + ((library == null || library.equals("") ? "" : "library_name LIKE " + library + " AND")
                + (title == null || title.equals("") ? "" : "title LIKE " + title + " AND")
                + (memberID == null || memberID.equals("") ? "" : "member_id=" + memberID));

        if(whereCondition.endsWith("AND"))whereCondition.substring(whereCondition.length()-4,whereCondition.length()-1);
        if(whereCondition.endsWith("WHERE "))whereCondition="";
        ResultSet resultSet = database.executeQuery(
                "SELECT * FROM rental,member,dvd_entity,dvd_prop WHERE rental.member_id=member.member_id AND rental.entity_id=dvd_entity.entity_id AND dvd_entity.prop_id=dvd_prop.prop_id"
                        + ((whereCondition == null || whereCondition.equals("")) ? "" : whereCondition)
        );
        Rental[] rentals = new Rental[resultSet.getFetchSize()];
        int i = 0;
        while (resultSet.next()) {
            rentals[i] = new Rental(
                    resultSet.getString("library_name"),
                    resultSet.getString("title"),
                    resultSet.getString("member_name"),
                    resultSet.getString("date_taken_from")
            );
            i++;
        }
        return rentals;
    }

    public static Account[] accounts(Integer memberID) throws SQLException {
        ResultSet resultSet = database.executeQuery(
                "SELECT member.member_id,member_name,member_address,category,balance,COUNT(*) AS total_times,SUM(money) AS amount_paid FROM rental LEFT JOIN member ON rental.member_id=member.member_id"
                + (memberID==null?"":"WHERE member.member_id=1")
        );
        Account[] accounts = new Account[resultSet.getFetchSize()];
        int i = 0;
        while (resultSet.next()) {
            accounts[i] = new Account(
                    resultSet.getString("member_name"),
                    resultSet.getString("category"),
                    resultSet.getFloat("balance"),
                    resultSet.getFloat("amount_paid"),
                    resultSet.getInt("total_times")
            );
            i++;
        }
        return accounts;
    }
}
