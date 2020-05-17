import java.io.File;
import java.io.FileOutputStream;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.BranchTrackingStatus;
import org.eclipse.jgit.lib.StoredConfig;

public class Updater {

	private static Git git;
	
	public static void refresh() {
		
		try {
			
			// check repository
			git = Git.open(new File("."));
			
			// check origin
			StoredConfig config = git.getRepository().getConfig();
			config.load();
			
			if (
				config.getString("remote", "origin", "url") == null ||
				!config.getString("remote", "origin", "url").equals(Settings.urlRepository)
			) {
				
				throw new Exception();
			}
		}
		
		// if not...
		catch (Exception e1) {
			
			try {
				
				update();
			}
			
			catch (Exception e2) {
				
				Main.startupErrors = true;
			}
		}
	}
	
	public static boolean check() {
		
		try {
			
			return BranchTrackingStatus.of(git.getRepository(), "master").getBehindCount() > 0;
		}
		
		catch (Exception e) {
			
			Main.log(Main.errorLog, e);
			Main.gui.error(Main.gui.window, "ERR_UPDATE");
		}
		
		return false;
	}
	
	public static void update() throws Exception {
			
		// make sure there is no tmp folder yet
		if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
		
			Runtime.getRuntime().exec("cmd /c rmdir /s /q " + Settings.tmpFile).waitFor();
		}
		
		else {
			
			new ProcessBuilder("rm", "-rf", Settings.tmpFile).start();
		}
		
		// clone origin into tmp dir
		Git.cloneRepository().setURI(Settings.urlRepository).setDirectory(new File(Settings.tmpFile)).call();
		
		// move files and restart
		moveFiles();
	}
	
	private static void moveFiles() {
		
		try {
			
			File file;
			String code;
			FileOutputStream output;
			
			// windows
			if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
				
				file = new File(Settings.tmpFile + ".bat");
				code = "cp -rf tmp/* tmp/.* . & rmdir /s /q tmp & java -jar Gidoi.jar";
				
				output = new FileOutputStream(file);
				output.write(code.getBytes());
				output.close();
				file.setExecutable(true);
				
				Runtime.getRuntime().exec("cmd /c" + file.getName());
				System.exit(0);
			}
			
			// linux or mac
			else {
				
				file = new File(Settings.tmpFile + ".sh");
				code = "#!/bin/sh\ncp -rf tmp/* tmp/.* .\nrm -rf tmp\njava -jar Gidoi.jar";
				
				output = new FileOutputStream(file);
				output.write(code.getBytes());
				output.close();
				file.setExecutable(true);
				
				new ProcessBuilder(file.toURI().getPath()).start();
				System.exit(0);
			}
		}
		
		catch (Exception e) {
			
			Main.log(Main.errorLog, e);
		}
	}
}