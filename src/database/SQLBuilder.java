package database;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
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
        String[] queries = new String[3];
        queries[0]="INSERT INTO rental (member_id,entity_id,date_taken_from ,library_taken_from,money ) VALUES " +
                "("+memberID+","
                +entityID+ ","
                +DateFormat.getDateInstance().format(new Date())+"," +
                "(SELECT library_name FROM dvd_entity WHERE entity_id="+entityID+")," +
                "(SELECT price FROM category WHERE category_id=(SELECT category FROM member WHERE member_id="+memberID+"))" +
                ")";
        queries[1]="UPDATE dvd_entity SET library_name=null WHERE entity_id="+entityID;

        queries[2]="UPDATE member SET balance=balance-(SELECT price FROM category WHERE category_id=(SELECT category FROM member WHERE member_id=\"+memberID+\"))";
        return queries;
    }

    public static final String returnDVD(int entityID, String toLibrary){
        return "UPDATE rental SET (date_return_on,library_return_on) VALUES ("
                +DateFormat.getDateInstance().format(new Date())+"," + toLibrary+
                ") WHERE date_return_on=NULL AND library_return_on=NULL AND entity_id="+entityID;
    }

    public static PreparedStatement getAccountDetails(int memberID){
        return null;
    }
}