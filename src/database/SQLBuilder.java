package database;

public class SQLBuilder {



    public String buildWhereCase(){

        return "";
    }

    public String queryDVDEntityByLibAndTitle(String libraryName,String title){
        String query="SELECT count(*) FROM dvd_entity WHERE";
        if(libraryName!=null){
            query += "library_name=\""+libraryName+"\"\n AND";
        }
        if(title!=null){
            query += "prop_id IN \n" +
                    "(SELECT prop_id FROM dvd_prop WHERE title LIKE \""+title+"\")";
        }
        return query;
    }

    public String queryAllDVDStockByLib(){
        return "SELECT library_name, title, director,  count(*) as stock \n" +
                "FROM dvd_entity JOIN dvd_prop ON dvd_entity.prop_id = dvd_prop.prop_id\n" +
                "GROUP BY dvd_prop.prop_id,library_name;";
    }

}