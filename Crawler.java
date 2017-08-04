package crawler;

import org.apache.commons.io.FilenameUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Crawler {

	public static void saveUrl(final String filename, final String urlString)
			throws MalformedURLException, IOException {
		BufferedInputStream in = null;
		FileOutputStream fout = null;
		try {
			in = new BufferedInputStream(new URL(urlString).openStream());
			fout = new FileOutputStream(filename);

			final byte data[] = new byte[1024];
			int count;
			while ((count = in.read(data, 0, 1024)) != -1) {
				fout.write(data, 0, count);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (fout != null) {
				fout.close();
			}
		}
	}

	public static String generateUrl(String year,String month)
	{

		return "http://mail-archives.apache.org/mod_mbox/maven-users/"+year+month+".mbox";
	}

	public static void main(String args[]){
		String URL = "";
		String year = "2016";
		String  directory = FilenameUtils.normalize(System.getProperty("user.home") + File.separator + "Desktop"+ File.separator + "Mail Archive"+File.separator);
		for(int i=1;i<=12;i++)
		{
			String month = "";
			if(i<10)
			month = "0"+i;
			else
				month = month+i;
			
			String savedirectory = directory+"Mail"+year+month+".txt";
			URL = generateUrl(year,month);
			try{
				saveUrl(savedirectory,URL);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

	}
}