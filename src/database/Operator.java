package database;

import data.DVD;
import data.Rental;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Operator {

    private static Database database;

    public static void init(){
        database=new Database();
    }

    public static boolean rentDVD(int memberID, int entityID){
        String[] queries= SQLBuilder.rentDVD(memberID, entityID);
        return database.execute(queries[0]) && database.execute(queries[1]) && database.execute(queries[2]);
    }

    public static boolean returnDVD(int entityID, String toLibrary){
        return database.execute(SQLBuilder.returnDVD(entityID, toLibrary));
    }


    public static DVD[] dvds(String whereCondition) throws SQLException {
        // WHERE CONDITION 直接写为数据库的条件 为空则返回所有
        ResultSet resultSet=database.executeQuery(
                "SELECT *, (SELECT GROUP_CONCAT(cast.cast) FROM cast WHERE cast.prop_id=dvd_entity.prop_id) as cast, (SELECT GROUP_CONCAT(genre_type.genre_type_name) as genres FROM genre_type,dvd_genre WHERE genre_type.genre_type_id=dvd_genre.genre_type_id AND dvd_genre.prop_id=dvd_entity.prop_id) as genre FROM dvd_entity JOIN dvd_prop ON dvd_entity.prop_id=dvd_prop.prop_id "
                + ((whereCondition==null||whereCondition.equals(""))?"":whereCondition)
        );
        DVD[] dvds=new DVD[resultSet.getFetchSize()];
        int i =0;
        while(resultSet.next()){
            dvds[i]=new DVD(
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

    public static Rental[] rentals(String whereCondition) throws SQLException {
        ResultSet resultSet=database.executeQuery(
                "SELECT * FROM rental,member,dvd_entity,dvd_prop WHERE rental.member_id=member.member_id AND rental.entity_id=dvd_entity.entity_id AND dvd_entity.prop_id=dvd_prop.prop_id"
                + ((whereCondition==null||whereCondition.equals(""))?"":whereCondition)
        );
        Rental[] rentals=new Rental[resultSet.getFetchSize()];
        int i =0;
        while(resultSet.next()){
            rentals[i]=new Rental(
                    resultSet.getString("library_name"),
                    resultSet.getString("title"),
                    resultSet.getString("member_name"),
                    resultSet.getString("date_taken_from")
            );
            i++;
        }
        return rentals;
    }
}
