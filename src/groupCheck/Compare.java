package groupCheck;

import java.util.ArrayList;

public class Compare {

	public static void aaoMissingDomains(ArrayList<String> ngDomains, ArrayList<String> aaoDomains) {

		// List<String> sourceList = new ArrayList<String>(ngDomains);
		// List<String> destinationList = new ArrayList<String>(aaoDomains);
		// sourceList.removeAll(aaoDomains);
		// destinationList.removeAll(ngDomains);
		// System.out.println("ngdomains: " + sourceList);
		// System.out.println("aaodomains: " + destinationList);

	}

	public static Boolean nodesWithBsmFlag(ArrayList<String> groups) {

		if (groups.contains("P_PROD_OMI") && (!groups.contains("P_PROD"))) {
			return true;
		}
		if (groups.contains("P_RTP_OMI") && (!groups.contains("P_RTP"))) {
			return true;
		}
		return false;
	}
}
