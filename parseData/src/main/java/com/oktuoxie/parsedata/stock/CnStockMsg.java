package com.oktuoxie.parsedata.stock;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.oktuoxie.parsedata.entity.Article;

public class CnStockMsg {

	public List getArticles(){
		//http://news.cnstock.com/
		return null;
	}

	/**
	 *  ��ȡhttp://news.cnstock.com/�е�ͷ������
	 * @return Article
	 * @throws ParserException
	 * @throws UnsupportedEncodingException
	 */
	public Article getTopNews() throws ParserException, UnsupportedEncodingException{
		Parser parser = new Parser("http://news.cnstock.com/"); 
	    /*** ��Ϸ�ʽ���ұ�ǩ
	    */
		Article article = new Article();
	    AndFilter filter = 
	      new AndFilter( 
	                    new TagNameFilter("div"), 
	                   new HasAttributeFilter("class","main-focus") 
	    ); 
	    NodeList nodes = parser.parse(filter);
	    String content = new String(nodes.toHtml().getBytes("ISO8859-1"),"UTF-8");
	    //��Ҫ���ݲ���
	    String mainContent = content.substring(content.indexOf("<h1>")+4,content.indexOf("</h1>"));
	    //ȡ��url
	   int urlStart = mainContent.indexOf("<a href=\"");
	   int urlEnd = mainContent.indexOf("\" target=\"_blank\" ");
	   String url = mainContent.substring(urlStart+14, urlEnd);
	   article.setUrl(url);
	   //ȡ��title
	   int titleStart = mainContent.indexOf("\">");
	   int titleEnd = mainContent.indexOf("</a>");
	   String title = mainContent.substring(titleStart+2,titleEnd);
	   title += " [����ͷ��]";
	   article.setTitle(title);
	   //ȡ��description
	   int desStart = content.indexOf("<p class=\"des\">");
	   int desEnd = content.indexOf("</p>");
	   String desc = content.substring(desStart+15,desEnd).trim();
	   article.setDescription(desc);
	   return article;
	}
	
	/**
	 * ��ȡhttp://news.cnstock.com/news/sns_yw�е�ͷ������(Ҫ��)
	 * @return
	 * @throws ParserException
	 * @throws UnsupportedEncodingException
	 */
	public List<Article> getDailyYW() throws ParserException, UnsupportedEncodingException{
		Parser parser = new Parser("http://news.cnstock.com/news/sns_yw"); 
	    /*** ��Ϸ�ʽ���ұ�ǩ
	    */
		List<Article> list = new ArrayList<Article>();
	    AndFilter filter = 
	      new AndFilter( 
	                    new TagNameFilter("ul"), 
	                   new HasAttributeFilter("class","new-list article-mini") 
	    ); 
	    NodeList nodes = parser.parse(filter);
	    String content = new String(nodes.toHtml().getBytes("ISO8859-1"),"UTF-8");
	    content= content.substring(34).trim();
	    Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sf.format(date);
		if(content.contains(today)){
			String []arrs = content.split("</a></li>");
			for(String line : arrs){
				if(line.contains(today)){
					line = line.trim();
					Article article = new Article();
					int urlStart = line.indexOf("<a href=\"");
					int urlEnd = line.indexOf("\" target=");
					String url = line.substring(urlStart+9,urlEnd);
					article.setUrl(url);
					int titleStart = line.lastIndexOf("\">");
					String title = line.substring(titleStart+2);
					article.setTitle(title);
					list.add(article);
					break;
				}
			}
		}else{
			return null;
		}
	    return list;
	}
	
	public static void main(String []args) throws ParserException, UnsupportedEncodingException{
		List<Article> articles = new CnStockMsg().getDailyYW();
		for(Article article : articles){
			System.out.println(article.getTitle());
		}
	}
}
