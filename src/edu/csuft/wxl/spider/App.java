package edu.csuft.wxl.spider;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @author wtao
 *
 */
public class App {

	// alt + /
	// ctrl + s
	public static void main(String[] args) {

		// 目标 URL
		String url = "https://movie.douban.com/top250";
		ArrayList<Film> list = new ArrayList<>();
		// 使用 JSOUP 抓去文档
		try {
			Document doc = Jsoup.connect(url).get();

			Elements es = doc.select(".grid_view .item");

			// 创建一个影片的列表
		

			for (Element e : es) {
			    Film f=new Film();
				Element t=e.select(".pic em").first();
				f.id=Integer.parseInt(t.text());
				f.poster=e.select("img").first().attr("src");
				f.info=e.select(".bd p").first().text();
				f.title=e.select(".title").first().text();
				f.rating=Double.parseDouble(e.select(".rating_num").first().text());
				String num=e.select(".star span").last().text();
				f.num=Integer.parseInt(num.substring(0,num.length()-3));
				f.quote=e.select(".inq").first().text();
				System.out.println(f);
				list.add(f);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
