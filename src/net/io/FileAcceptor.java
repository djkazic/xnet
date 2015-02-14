package net.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import main.Core;
import main.Utils;
import peer.Peer;

public class FileAcceptor implements Runnable {
	
	private Peer peer;
	private String filename;
	
	public FileAcceptor(Peer peer, String filename) {
		this.peer = peer;
		this.filename = filename;
	}
	
	public void run() {
		try {
			byte[] mybytearray = new byte[1024];
		    InputStream is = peer.ps.getInputStream();
		    FileOutputStream fos = new FileOutputStream(Utils.defineDir() + "\\" + filename);
		    BufferedOutputStream bos = new BufferedOutputStream(fos);
		    int bytesRead = is.read(mybytearray, 0, mybytearray.length);
		    bos.write(mybytearray, 0, bytesRead);
		    bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Core.mainWindow.out("File transfer of \"" + filename + "\" successful");
	}
}
