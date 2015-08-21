/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bdnewslinks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author yousufkhan
 */
public class Onepage {
    
    public ArrayList<String> getLinks(String url){
        ArrayList<String> dataLinks = new ArrayList<String>();
        //String url = "http://bangla.bdnews24.com/search/simple.do;jsessionid=E0780D248F840A9B46ADC2002D55E871.pre23?destinationSectionId=80&publicationName=bangla&sortString=score&sortOrder=desc&sectionId=68&articleTypes=news-bn+news-district&pageNumber=1&pageLength=5&searchString=কৃষি";
        Document doc;
        
        try{
            doc = Jsoup.connect(url).timeout(0).get();
            
            Elements contents = doc.getElementsByAttributeValueContaining("id", "search-result");
            //System.out.println(contents);
            
            if(!contents.isEmpty()){
                
                    for (Element content : contents) {
                        Elements allNews = content.getElementsByAttributeValueContaining("class", "article ");
                        //System.out.println(allNews);
                        for(Element eachNews : allNews){
                            Elements links = eachNews.getElementsByAttributeValueContaining("class", "summary");
                            
                            //System.out.println(links);
                            for (Element link : links) {
                              String linkHref = link.attr("onclick");
                                String state = linkHref.substring(linkHref.indexOf("'")+1, linkHref.lastIndexOf("'"));
                                System.out.println(state);
                              dataLinks.add(state);
                            }
                        }
                    }
                
            }
            else{
                System.out.println("Content NULL");
                throw new NullPointerException();
                 
            }
        }catch(Exception ex){
            System.out.println("jsoup can't connect.");
        }
        return dataLinks;
    }
    
    
    
}
