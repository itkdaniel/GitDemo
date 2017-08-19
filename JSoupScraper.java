package jSoup_Test;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

// CryptoCurrency WebScraper (Coinmarketcap.com)
public class JSoupScraper {

	public static void main(String[] args) throws ParseException {
		Scanner keyboard = new Scanner(System.in);
		try{
			Document doc = Jsoup.connect("https://coinmarketcap.com/all/views/all/").userAgent("Chrome/59.0.3071.115").get();
			Elements tempCoinName = doc.select("td.no-wrap.currency-name");
			Elements tempCoinPrice = doc.select("a.price");
			// Implement hash-map for coinName-coinPrice (key-value)
			Map<String, Float> coinPriceMap = new HashMap<String, Float>();
			
			// Scrape and insert prices into Array for later processing and output
			String[] coinPrices = new String[1000];
			int j = 0;
			for(Element price : tempCoinPrice){
				coinPrices[j++] = price.select("a").first().text();
				//coinPrices[j++] = price.getElementsByTag("a").first().text();
				
			}
			System.out.println(j);	//check boundary
			System.out.println();
			
			Number number;
			
			 System.out.println();
			// Scrape and process names of Coins = Price
			 
			int i = 0;
			for(Element coin: tempCoinName){
				number = NumberFormat.getCurrencyInstance(Locale.US).parse(coinPrices[i]);
				String coinName = coin.select("a").first().text();
				//if(number.floatValue() < 200)
				System.out.println(i + " " + coinName + " = " + coinPrices[i]);
				//System.out.println(i + " " + coin.getElementsByTag("a").first().text() + " = " + coinPrices[i++]);
				i++;
				// Insert coin-price pair into map
				coinPriceMap.put(coinName, number.floatValue());
				
				//if(i == 541) // since last entry is null (dont know why)
					//break;
			}
			System.out.println();
			
			float coinsOverPrice;
			System.out.println("Show coins over price of: ");
			coinsOverPrice = keyboard.nextFloat();
			
			// Display Coins Over $x
			System.out.printf("Coins Over $%.2f: \n", coinsOverPrice);
			
			// Iterate/loop through Map/HasMap
			for(String key : coinPriceMap.keySet()){
				
				if(coinPriceMap.get(key) > coinsOverPrice){
					System.out.println(key + " = " + coinPriceMap.get(key));
				}	
			}
			
			// Search for price of coin (Ethereum)
			System.out.println("Enter Coin to Search For: ");
			String coinToSearchFor;
			coinToSearchFor = keyboard.next();
			System.out.println(coinToSearchFor + " = " + coinPriceMap.get(coinToSearchFor));
			
			/*for(Element coin: tempCoinName){
				number = NumberFormat.getCurrencyInstance(Locale.US).parse(coinPrices[i]);
				if(number.floatValue() > 200){
				System.out.println(i + " " + coin.getElementsByTag("a").first().text() + " = " + coinPrices[i++]);
				}
			}*/
		}catch(IOException e){
			e.printStackTrace();
		}
		

	}

}
