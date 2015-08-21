/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bdnewslinks;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author yousufkhan
 */
public class BdnewsLinks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DataBase database = new DataBase();
//        
        
        ArrayList<String> dataLinks = new ArrayList<String>();
        String tableName = "sports";
        String searchQuery = "খেলা";
        boolean flag = true;
        int i = 1;

        try {
            Connection connection = database.CreateConntection("bdnews");
            try {
                Statement stmtement = database.CreateStatement(connection);

                while (flag) {

                    try {
                        Onepage onepage = new Onepage();
                        dataLinks = onepage.getLinks("http://bangla.bdnews24.com/search/simple.do;jsessionid=E0780D248F840A9B46ADC2002D55E871.pre23?destinationSectionId=80&publicationName=bangla&sortString=score&sortOrder=desc&sectionId=68&articleTypes=news-bn+news-district&pageNumber="+i+"&pageLength=50&searchString="+searchQuery);

                    } catch (Exception e) {
                        System.out.println("terminate");
                        flag = false;
                        break;
                    }

                    for (String link : dataLinks) {
                        try {
                            database.insertData(stmtement, tableName, link);
                            connection.commit();
                            System.out.println(link);
                        } catch (Exception e) {
                            flag = false;
                        }
                    }
                    i++;

                }

                stmtement.close();
                connection.commit();
                connection.close();
            } catch (Exception e) {
                connection.close();
            }

        } catch (Exception e) {
            System.out.println("Failed to create connection.");
        }

    }
    
}
