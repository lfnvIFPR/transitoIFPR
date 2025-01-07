package projetosIFPR.transitoIFPR.estado;

import com.formdev.flatlaf.FlatDarculaLaf;

public class transitoIFPR
	extends FlatDarculaLaf
{
	public static final String NAME = "transitoIFPR";

	public static boolean setup() {
		return setup( new transitoIFPR() );
	}

	public static void installLafInfo() {
		installLafInfo( NAME, transitoIFPR.class );
	}

	@Override
	public String getName() {
		return NAME;
	}
}
