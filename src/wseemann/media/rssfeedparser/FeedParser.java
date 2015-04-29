package wseemann.media.rssfeedparser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.xml.sax.InputSource;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;

public class FeedParser {

	private String mFirstEntryLink;
	
	public FeedParser() {
		
	}
	
	public void parse(String url) throws MalformedURLException, IOException, IllegalArgumentException, FeedException {
		SyndFeed feed = null;		
		InputStream is = null;

		try {
			if (url == null) {
				throw new IllegalArgumentException("URL cannot be null");
			}
			
			URLConnection openConnection = new URL(url).openConnection();			
			is = new URL(url).openConnection().getInputStream();
			
			if("gzip".equals(openConnection.getContentEncoding())){
				is = new GZIPInputStream(is);
			}			
			
			InputSource source = new InputSource(is);			
			SyndFeedInput input = new SyndFeedInput();
			feed = input.build(source);

			List<SyndEntry> entries = feed.getEntries();
			Iterator<SyndEntry> itEntries = entries.iterator();

			while (itEntries.hasNext()) {
				SyndEntry entry = (SyndEntry) itEntries.next();
				//System.out.println("Title: " + entry.getTitle());
				//System.out.println("Link: " + entry.getLink());
				//System.out.println("Author: " + entry.getAuthor());
				//System.out.println("Publish Date: " + entry.getPublishedDate());
				//System.out.println("Description: " + entry.getDescription().getValue());
				//System.out.println();
				
				mFirstEntryLink = entry.getLink();
				break;
			}
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}		
	}
	
	public String getFirstEntryLink() {
		return mFirstEntryLink;
	}
}
