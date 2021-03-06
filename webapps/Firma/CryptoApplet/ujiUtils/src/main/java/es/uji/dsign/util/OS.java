package es.uji.dsign.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;

public class OS
{
	
	public static byte[] getBytesFromFile(File file) throws IOException 
	{
		InputStream is = new FileInputStream(file);

		// Get the size of the file
		long length = file.length();

		if (length > Integer.MAX_VALUE) {
			// File is too large
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int)length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "+file.getName());
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
	}

	
	public static byte[] inputStreamToByteArray(InputStream in) throws IOException
	{
		byte[] buffer = new byte[2048];
		int length = 0;

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		while ((length = in.read(buffer)) >= 0)
		{
			baos.write(buffer, 0, length);
		}

		return baos.toByteArray();
	}
	
	public static String getOS()
	{
		return System.getProperty("os.name").toLowerCase();
	}

	public static boolean isWindowsXP()
	{
		return (getOS().indexOf("windows xp") > -1);
	}

	public static boolean isWindows2000()
	{
		return (getOS().indexOf("windows 2000") > -1);
	}

	public static boolean isWindows2003()
	{
		return (getOS().indexOf("windows 2003") > -1);
	}

	public static boolean isWindowsNT()
	{
		return (getOS().indexOf("nt") > -1);
	}
	
	public static boolean isMac()
	{
		return (getOS().indexOf("mac") > -1);
	}

	public static boolean isLinux()
	{
		return (getOS().indexOf("linux") > -1);
	}

	public static boolean isWindowsUpperEqualToNT()
	{
		return (getOS().indexOf("vista") > -1 || getOS().indexOf("nt") > -1 || getOS().indexOf("windows 2000") > -1 || getOS().indexOf("windows xp") > -1 || getOS().indexOf("windows 2003") > -1);
	}
}
