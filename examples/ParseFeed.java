/*
 * Copyright 2015 William Seemann
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.xml.sax.SAXException;
import com.rometools.rome.io.FeedException;
import wseemann.media.rssfeedparser.FeedParser;

public class ParseFeed {

	public static void main(String [] args) throws SAXException {
		URL url;
		
		try {
			url = new URL("Enter RSS feed URL here");
			
			FeedParser parser = new FeedParser();    
		    parser.parse(url.toString());
		    
		    System.out.println(parser.getFirstEntryLink());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FeedException e) {
			e.printStackTrace();
		}
	}	
}
