package edu.csuft.wxl.spider;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 抓取页面中影片信息的爬虫（任务）
 * 
 * @author wxljllm
 *
 */
public class Spider implements Runnable {
	
	/**
	 * 页面的路径（url）
	 */
	String url;
	
	/**
	 * 存储抓取的数据
	 */
	ArrayList<Film> list=new ArrayList<Film>();
	
	/**
	 * 创建爬虫
	 * @param url 页面的路径
	 * @param list 存储数据的列表
	 */
	public Spider(String url, ArrayList<Film> list) {
		super();
		this.url = url;
		this.list = list;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//获得执行该任务的线程的名称
		String name=Thread.currentThread().getName();
		System.out.println(name+"线程，开始："+url);
		
		//jsoup
		try {
			Document doc=Jsoup.connect(url).get();
			
			//从文档（树木）中查找节点
			Elements es = doc.select(".grid_view .item");
			// 遍历元素列表
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
				try {
					f.quote = e.select(".quote span").first().text();
				} catch (NullPointerException e2) {
					// TODO: handle exception
				}
				list.add(f);
//				System.out.println(f);
			}
			System.out.println(name+"线程，完成："+url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
