import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author A-8259
 *
 */
public class TranslateTextHelper {

	private static void logit( Object p ) {
		System.out.println(p);
	}
	
	public static String translate(Locale locale, String prefix , String value ) {
		//
		String translatedValue = null;
		
		// 
		try {
			ResourceBundle rb = ResourceBundleHelper.getResourceBundle(locale);
			translatedValue =  rb.getString(prefix+value);
		} catch( MissingResourceException miss ) {
			translatedValue = value;
		}
		
		logit(" Original Value : "+prefix+value+"     "+" translatedValue : "+translatedValue+"     ");
		return translatedValue;			
	}

}
