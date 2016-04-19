package maze.gui;


import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.security.CodeSource;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@SuppressWarnings("rawtypes")
public class AIReflector<T> {
	private final static String suffix = ".class";

	private Map<String,Class> name2type;

	private FilenameFilter filter = new FilenameFilter(){public boolean accept(File dir, String name) {
		return name.endsWith(suffix);
	}};

	public AIReflector(Class superType, String packageName)  {
		this.name2type = new TreeMap<String,Class>();

		String targetDirName = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		File targetDir = new File(targetDirName + packageName.replace('.', File.separatorChar));
		
		if (!targetDir.isDirectory()) {
			if (targetDirName.endsWith(".jar")) {
				try {
					CodeSource src = getClass().getProtectionDomain().getCodeSource();

					if( src != null ) {
						URL jar = src.getLocation();
						ZipInputStream zip = new ZipInputStream( jar.openStream());
						ZipEntry ze = null;

						while( ( ze = zip.getNextEntry() ) != null ) {
							String entryName = ze.getName();
							if (entryName.startsWith("maze/ai/heuristics")&&entryName.endsWith(suffix)) {
								String name = entryName.substring(packageName.length()+1);
								name = name.substring(0, name.length() - suffix.length());
								
								try {
									Class type = Class.forName(packageName + "." + name);
									Object obj = type.newInstance();
									if (superType.isInstance(obj)) {
										name2type.put(name, type);
									}
									// If an exception is thrown, we omit the type.
									// Hence, ignore all three exceptions.
								} catch (ClassNotFoundException e) {
								} catch (InstantiationException e) {
								} catch (IllegalAccessException e) {
								}
							}
						}

					}
				}
				catch (Exception e) {System.err.println(e);}


			}
			else throw new IllegalArgumentException(targetDir + " is not a directory");
		}
		else {
			for (File f: targetDir.listFiles(filter)) {

				String name = f.getName();
				name = name.substring(0, name.length() - suffix.length());

				try {
					Class type = Class.forName(packageName + "." + name);
					Object obj = type.newInstance();
					if (superType.isInstance(obj)) {
						name2type.put(name, type);
					}
					// If an exception is thrown, we omit the type.
					// Hence, ignore all three exceptions.
				} catch (ClassNotFoundException e) {
				} catch (InstantiationException e) {
				} catch (IllegalAccessException e) {
				}
			}
		}
	}

	public ArrayList<String> getTypeNames() {
		return new ArrayList<String>(name2type.keySet());
	}

	public String toString() {
		String result = "Available:";
		for (String s: name2type.keySet()) {
			result += " " + s;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public T newInstanceOf(String typeName) throws InstantiationException, IllegalAccessException {
		return (T)name2type.get(typeName).newInstance();
	}
}
