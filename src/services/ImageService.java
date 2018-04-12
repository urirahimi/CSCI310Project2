package services;

import static org.apache.http.protocol.HTTP.USER_AGENT;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/*
 * A static class to abstract all the HTTP logic behind obtaining Images
 */
public class ImageService
{
	// The public interface exposed. Returns a list of images given a topic name
	// (query)
	public static List<BufferedImage> getImages (String query)
	{
		String url = "https://api.qwant.com/api/search/images?count=50&q=" + query;
		String jsonString = getJSONStringFromQwantRequest(url);
		JsonArray imageJson = loadJsonStringToArray(jsonString);
		List<BufferedImage> images = loadBufferedImagesFromJson(imageJson);
		return images;
 /*
		String[] EXTENSIONS = new String[] {
				"jpg", "jpeg", "gif", "png", "bmp" // and other formats you need
		};
		
	    // filter to identify images based on their extensions
	    FilenameFilter IMAGE_FILTER = new FilenameFilter() {
	        @Override
	        public boolean accept(final File dir, final String name) {
	            for (final String ext : EXTENSIONS) {
	                if (name.endsWith("." + ext)) {
	                    return (true);
	                }
	            }
	            return (false);
	        }
	    };
	    
	    List<BufferedImage> images = new ArrayList<>();
	    if (dir.isDirectory()) { // make sure it's a directory
            for (final File f : dir.listFiles(IMAGE_FILTER)) {
                BufferedImage img = null;
                try {
                    img = ImageIO.read(f);
                    images.add(img);
                } catch (final IOException e) {
                    // handle errors here
                }
            }
        }
	    assert(images.size() == 30);
		return images;*/
	}

	// Helper function to perform JSON logic to obtain a JSONString from url
	private static String getJSONStringFromQwantRequest (String url)
	{
		URL obj = null;

		try {
			obj = new URL(url);
		}
		catch (Exception e) {
		}

		HttpURLConnection con = null;
		StringBuffer response = new StringBuffer();
		try {
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			// add request header
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.addRequestProperty("Cookie", "name1=Denim");
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			while (responseCode != 200) {
				System.out.println("Sending");
				con = (HttpURLConnection) obj.openConnection();
				con.setRequestMethod("GET");
				// add request header
				con.setRequestProperty("User-Agent", USER_AGENT);
				con.addRequestProperty("Cookie", "name1=Denim");
				responseCode = con.getResponseCode();
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		}
		catch (IOException e) {
		}
		return response.toString();
	}

	// Helper function to load JSON to a JSONArray containing the contents of the
	// image
	private static JsonArray loadJsonStringToArray (String jsonString)
	{
		JsonObject jsonObject = null;
		JsonParser parser = null;
		JsonArray out = null;
		try {
			parser = new JsonParser();
			jsonObject = parser.parse(jsonString).getAsJsonObject();
			JsonObject data = jsonObject.get("data").getAsJsonObject();
			JsonElement elements = data.get("result");
			out = elements.getAsJsonObject().get("items").getAsJsonArray();
		}
		catch (Exception e) {
		}
		return out;
	}

	// Helper function to load the JsonArray into a List of BufferedImage
	private static List<BufferedImage> loadBufferedImagesFromJson (JsonArray jsonArray)
	{
		List<BufferedImage> buffImages = new ArrayList<>();
		try {
			// Initially, the image retrieval was done sequentially. However, to meet time
			// constraint, parallelization is required
			ExecutorService executor = Executors.newFixedThreadPool(30);
			// Starts the worker thread
			for (int i = 0; i < jsonArray.size(); ++i) {
				String url = jsonArray.get(i).getAsJsonObject().get("media").getAsString();
				Runnable worker = new MyRunnable(url, buffImages);
				executor.execute(worker);
			}
			// Sets the thread to parallelize Image downloading from URL
			executor.shutdown();
			while (!executor.isTerminated()) {
				// Once 30 images is obtained, force termination
				if (buffImages.size() >= 30) {
					break;
				}
			}
			executor.shutdownNow();
		}
		catch (Exception e) {
		}
		return buffImages;
	}

	// A private static class to support parallelization
	private static class MyRunnable implements Runnable
	{
		private final String url;
		// A reference to the images is passed to each worker
		private List<BufferedImage> images;

		MyRunnable(String url, List<BufferedImage> images)
		{
			this.url = url;
			this.images = images;
		}

		@Override
		public void run ()
		{
			try {
				// Sets a connection and set the appropriate header to stimulate a browser
				URL URL = new URL(url);
				final HttpURLConnection connection = (HttpURLConnection) URL.openConnection();
				connection.setRequestProperty("User-Agent",
				        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
				InputStream input = new BufferedInputStream(connection.getInputStream());
				BufferedImage image = ImageIO.read(input);
				// null-checks to ensure valid images are added
				if (image != null) {
					images.add(image);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}