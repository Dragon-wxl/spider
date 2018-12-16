package edu.csuft.wxl.spider;

import java.awt.List;
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
		// 目标
		String url = "https://movie.douban.com/top250";

		// 使用jsoup抓起去文档
		// 创建一个一年影片的列表
		ArrayList<Film> list = new ArrayList<>();

		try {
			Document doc = Jsoup.connect(url).get();

			Elements es = doc.select(".grid_view .item");

		
			for (Element e : es) {
				Film f = new Film();
				// 每一部影片
				// 获取标题
				Element t1 = e.select(".title").first();
//				System.out.println(t1.text());
				f.title = t1.text();
				// 获取相关信息
				Element t2 = e.select(".bd p").first();
//				System.out.println(t2.text());
				f.info = t2.text();
				// 获取评分
				Element t3 = e.select(".rating_num").first();
				String str1 = t3.text().toString();
				double dou = Double.parseDouble(str1);
//				System.out.println(dou);
				f.rating = dou;
				// 获取评分人数
				Element t4 = e.select(".bd .star span").last();
				String str = t4.text().toString();
				String str2 = str.substring(0, str.length() - 3);
				int x = Integer.parseInt(str2);
//				System.out.println(x);
				f.num = x;
				// 获取排名
				Element t5 = e.select(".pic em").first();
//				System.out.println(t5.text());
				f.id = Integer.parseInt(t5.text().toString());
				// 获取图片地址
				Elements t6 = e.select("img[src]");
//				System.out.println(t6.attr("src"));
				f.poster = t6.attr("src");
				// 获取短评
				Element t7 = e.select(".quote .inq").first();
//				System.out.println(t7.text());
				f.quote = t7.text();
				list.add(f);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Film lists:list) {
			    System.out.print(lists.title);
			    System.out.print(lists.info);
			    System.out.print(lists.id);
			    System.out.print(lists.rating);
			    System.out.print(lists.num);
			    System.out.print(lists.poster);
			    System.out.print(lists.quote);
			    System.out.println();

			    }
		
		
		
	}
}
