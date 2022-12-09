
import java.util.Locale;

public class Tester {

	public static final long SLEEP_FOR = 10000;
	
	private static void sleep(long value ) {
		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

		Locale l = new Locale("es","ES");
		Locale lnew = new Locale("en","US");
		
		System.out.println(new TranslateTextHelper().translate( l , "", "Jurassic World"));
		//sleep(SLEEP_FOR);		
		System.out.println(new TranslateTextHelper().translate( l , "", "Jurassic Park"));
		//sleep(SLEEP_FOR);		
		System.out.println(new TranslateTextHelper().translate( l , "", "Lost World"));
		//sleep(SLEEP_FOR);
		
		//sleep(SLEEP_FOR);
		System.out.println(new TranslateTextHelper().translate( lnew , "", "Jurassic World"));
		//sleep(SLEEP_FOR);
		System.out.println(new TranslateTextHelper().translate( lnew , "",  "Jurassic Park"));
		//sleep(SLEEP_FOR);
		System.out.println(new TranslateTextHelper().translate( lnew , "", "Lost World"));		
		//sleep(SLEEP_FOR);


	}

}
