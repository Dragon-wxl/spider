package edu.csuft.wxl.spider;



import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * 程序的入口
 * 	
 * @author wxljllm
 * 
 */
public class App {
// alt+/
	// ctrl+s
	public static void main(String[] args) {
		//目标
		String url="https://movie.douban.com/top250";
		
		//使用jsoup抓起去文档
		
		try {
			Document doc=Jsoup.connect(url).get();
			
			Elements es=doc.select(".grid_view .item");
			System.out.println(es.size());
			
			//创建一个一年影片的列表
			ArrayList<Film> list=new ArrayList<>();
			
			
			for(Element e :es) {
				//每一部影片
				Element t=e.select(".title").first();
				String num=e.select(".star span").last().text();
				System.out.println(t.text()+", "+num);
				System.out.println(t.text());
				//f.id
				//f.title
				list.add(null);
			}
			
//			String title=doc.title();
//			String data=doc.data();
//			
//			System.out.println(title);
//			System.out.println(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
