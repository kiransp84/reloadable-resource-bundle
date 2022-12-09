import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class ResourceBundleHelper {
	
	private ResourceBundleHelper() {
				
	}
	

	public static ResourceBundle getResourceBundle(Locale loc) {
		return ResourceBundle.getBundle("OneTimeDesc", loc , new ConfigBasedResourceBundle());
	}
	
	
}
