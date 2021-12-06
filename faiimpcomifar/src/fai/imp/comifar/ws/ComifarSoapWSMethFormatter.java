package fai.imp.comifar.ws;

public class ComifarSoapWSMethFormatter {

	public static String formatGetDDTList() throws Exception {
		return formatGetDDTList(false);
	}

	public static String formatGetDDTList(boolean onlyParams) throws Exception {
		return (onlyParams == true ? "" : "DDTList")+"()";
	}
	
	public static String formatGetAvailibility() throws Exception {
		return formatGetAvailibility(false);
	}

	public static String formatGetAvailibility(boolean onlyParams) throws Exception {
		return (onlyParams == true ? "" : "disponibilit\u00E0")+"()";
	}

}
