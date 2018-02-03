package org.team484.api.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import edu.wpi.first.wpilibj.Timer;

/**
 * ProfileLogger records the displacement, power output, and velocity of the robot as it undergoes rapid
 * acceleration and deceleration back to 0 speed. This data can then be used to create a motion profile auto.
 * Interrupt the thread to stop logging.
 */
public abstract class ProfileLogger extends Thread {
	public abstract double getDistance();
	public abstract double getRate();
	public abstract double getOutput();
	
	//------------------------ Constants ------------------------
	private static final String FILE_NAME_PREFIX = "ROBOT_PROF_";
	private static final String FILE_EXTENSION = ".csv";
	private static final String[] saveDirectories = {
			"/U/",
			"/V/",
			"/media/sda",
			"/media/sdb",
	};
	
		
	private File activeSaveDirectory;
	private File outputFile;
	private PrintWriter writer;
	private String name;
	
	private long waitTime;
	
	/**
	 * Creates a new ProfileLogger instance with a specified time to wait between recording logs. After an
	 * instance is created and the robot is about to accelerate run start method to start
	 * logging.
	 * @param msBetweenLogs - Milliseconds between log entries.
	 */
	public ProfileLogger(long msBetweenLogs, String name) {
		waitTime = msBetweenLogs;
		this.name = name;
	}
	
	/**
	 * Runs the logger until the thread is interrupted. To run the logger in a separate thread, use start
	 * instead of run.
	 */
	@Override
	public void run() {
		if (!setActiveSaveDirectory()) return;
		if (!createWriter()) return;
		
		StringBuilder outputString = new StringBuilder();
		writeLine(outputString);
		
		long loopStart = System.currentTimeMillis();
		double fpgaTimeStart = Timer.getFPGATimestamp();
		while(!Thread.interrupted()) {
			double fpgaTime = Timer.getFPGATimestamp() - fpgaTimeStart;
			double distance = getDistance();
			double rate = getRate();
			double output = getOutput();
			if (output != 0) {
				outputString.append(output + "," + distance + "," + rate + "," + fpgaTime + "\n");
			}
			writeLine(outputString);
			try {
				long time = System.currentTimeMillis() - loopStart;
				Thread.sleep(Math.max(waitTime - time, 0));
				loopStart = System.currentTimeMillis();
			} catch (InterruptedException e) {
				break;
			}
			
			//reset time if output is 0
			if (output == 0) {
				fpgaTimeStart = Timer.getFPGATimestamp();
			}
		}
		closeWriter();
	}
	
	/**
	 * Finds the best directory to write the log files to.
	 * @return - If it was successful in finding a flash drive.
	 */
	private boolean setActiveSaveDirectory() {
		activeSaveDirectory = null;
		for (String directory : saveDirectories) {
			File fileDirObj = new File(directory);
			if (fileDirObj.exists()) {
				activeSaveDirectory = fileDirObj;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Creates the print writer object used to write to the log file.
	 * @return - If the creation of the writer was successful.
	 */
	private boolean createWriter() {
		String fileName = FILE_NAME_PREFIX + name + FILE_EXTENSION;
		outputFile = new File(activeSaveDirectory.getAbsolutePath() + "/" + fileName);
		try {
			writer = new PrintWriter(outputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Writes a line to the writer and clears the string builder cache.
	 * @param line - The line to write.
	 */
	private void writeLine(StringBuilder line) {
		line.setLength(Math.max(line.length() - 1, 0));
		if (writer != null) {
			writer.write(line.toString() + "\n");
		}
		line.setLength(0);
	}
	
	/**
	 * Flushes and closes the print writer.
	 */
	private void closeWriter() {
		writer.flush();
		writer.close();
	}
}
