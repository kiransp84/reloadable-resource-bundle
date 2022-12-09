import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ConfigBasedResourceBundle extends ResourceBundle.Control {
	
	ConfigBasedResourceBundle() {
		super();
		if( ROOT_FOLDER == null ) {
			logWarning(" -DbundleRoot VM argument is missing. Proceeding to use fallback ");
		}
	}
	private void logit( Object p ) {
		if( LOGS_ENABLED) System.out.println(p);
	}
	
	private void logWarning( Object p ) {
		System.err.println(" WARNING : "+p);
	}
	
	public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
	        throws IllegalAccessException, InstantiationException, IOException {
		
		if (!format.equals("java.properties")) {
	        return null;
	    }
		
		String bundleName = toBundleName(baseName, locale);
		
		logit(" baseName "+baseName);
		logit(" locale "+locale.toString());
		logit(" format "+format);
		logit(" reload "+reload);
		logit(" bundleName "+bundleName);
		
		ResourceBundle bundle = null;
		
		InputStreamReader reader = null;
	    FileInputStream fis = null;
	    
	    try {
	    	String propertiesFileName = bundleName+".properties";	    	
	        File file = new File( ROOT_FOLDER , propertiesFileName);
	        
	        if (file.isFile()) { // Also checks for existance
	        	logit("Reading from File ----"+propertiesFileName);
	        	LAST_MODIFIIED_TIME.put(bundleName+".properties",file.lastModified());
	            fis = new FileInputStream(file);
	            reader = new InputStreamReader(fis, Charset.forName("UTF-8"));
	            bundle = new PropertyResourceBundle(reader);
	        }
	    } finally {
	        if( reader != null ) reader.close();
	        if( fis != null ) fis.close();
	    }
	    return bundle;
	    
	}
	
	public static final long CACHE_EXPIRATION_MILLIS = 5000;  
	public static final boolean LOGS_ENABLED = false;
	public static final String ROOT_FOLDER = System.getProperty("bundleRoot");
	
	
	@Override
	public long getTimeToLive(String baseName, Locale locale) {
		return CACHE_EXPIRATION_MILLIS;
	}
	
    private String toResourceName0(String bundleName, String suffix) {
        // application protocol check
        if (bundleName.contains("://")) {
            return null;
        } else {
            return toResourceName(bundleName, suffix);
        }
    }
    
    private static HashMap<String,Long> LAST_MODIFIIED_TIME = new HashMap<>();
	
    public boolean needsReload(String baseName, Locale locale,
            String format, ClassLoader loader,
            ResourceBundle bundle, long loadTime) {
    	    	
    	if (bundle == null) {
            throw new NullPointerException();
        }
        if (format.equals("java.class") || format.equals("java.properties")) {
            format = format.substring(5);
        }
                
        String resourceName = toResourceName0(toBundleName(baseName, locale), format);
        
        logit(" inside needsReload "+resourceName);
        
        File file = new File(ROOT_FOLDER, resourceName);
        if (file.isFile()) { // Also checks for existance
        	boolean hasChanged = LAST_MODIFIIED_TIME.get(resourceName) != file.lastModified();
        	logit(" inside needsReload returns "+hasChanged);
        	return hasChanged;
        }
	    
        return true;
    }

}


