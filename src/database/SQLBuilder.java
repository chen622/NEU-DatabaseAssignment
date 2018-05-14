package database;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SQLBuilder {



    public static final String buildWhereCase(){

        return "";
    }

    public static final String queryDVDEntityCountByLibAndTitle(String libraryName,String title){
        String query="SELECT library_name, title, count(*) FROM dvd_entity WHERE";
        if(libraryName!=null){
            query += "library_name=\""+libraryName+"\"\n AND";
        }
        if(title!=null){
            query += "prop_id IN \n" +
                    "(SELECT prop_id FROM dvd_prop WHERE title LIKE \""+title+"\") GROUP BY dvd_prop.prop_id,library_name";
        }
        return query;
    }

    public static final String queryAllDVDStockByLib(){
        return "SELECT library_name, title, director,  count(*) as stock \n" +
                "FROM dvd_entity JOIN dvd_prop ON dvd_entity.prop_id = dvd_prop.prop_id\n" +
                "GROUP BY dvd_prop.prop_id,library_name";
    }


    public static final String queryAllAccount(){
        return "SELECT * FROM member";
    }

    public static final String[] rentDVD(int memberID, int entityID){
        String[] queries = new String[2];
        queries[0]="INSERT INTO rental\n" +
                "  (member_id,entity_id,date_taken_from,library_taken_from,money)" +
                "VALUES\n" +
                "  ("+memberID+", "+entityID+", CURDATE(), (SELECT library_name" +
                "    FROM dvd_entity" +
                "    WHERE dvd_entity.entity_id="+entityID+"), null);";
        queries[1]="UPDATE dvd_entity SET library_name=null WHERE entity_id="+entityID;
        return queries;
    }

    public static final String[] returnDVD(int rentalID, String toLibrary){
        String[] queries = new String[3];
        queries[0]="UPDATE rental r JOIN" +
                "(SELECT price FROM member_category WHERE category_id = (SELECT category FROM member WHERE member_id=(SELECT member_id FROM rental WHERE rental.rental_id="+rentalID+"))) p" +
                " SET date_return_on=CURDATE(),library_return_on=\""+toLibrary+"\",money=DATEDIFF(date_taken_from,CURDATE())*p.price WHERE rental_id="+rentalID+"";
        queries[1]="UPDATE member " +
                "SET balance=balance-(SELECT money FROM rental WHERE rental.rental_id="+rentalID+") " +
                "WHERE member.member_id=(SELECT member_id FROM rental WHERE rental.rental_id="+rentalID+")";
        queries[2]="UPDATE dvd_entity SET library_name=(SELECT library_return_on FROM rental WHERE rental.rental_id="+rentalID+")" +
                "WHERE entity_id=(SELECT entity_id FROM rental WHERE rental.rental_id="+rentalID+");";
        return queries;
    }

    public static PreparedStatement getAccountDetails(int memberID){
        return null;
    }

    public static String today(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    }
}