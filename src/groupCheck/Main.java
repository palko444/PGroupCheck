package groupCheck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ParseAAO aao = new ParseAAO();
		ParsehNg ng = new ParsehNg();
		HashMap<String, ArrayList<String>> aaoData = aao.parseDomains();
		HashMap<String, DomainData> ngData = ng.parseHt();
		
		ArrayList<String> ngDomains = new ArrayList<String>(ngData.keySet());
		ArrayList<String> aaoDomains = new ArrayList<String>(aaoData.keySet());
		Compare.aaoMissingDomains(ngDomains, aaoDomains);
		
		

		for (Map.Entry<String, DomainData> ngEntry : ngData.entrySet()) {
			String ngDomain = ngEntry.getKey();
			DomainData dt = ngEntry.getValue();
			// System.out.println("domain: " + domain + " bsm flag: " + dt.bsmEnabled);

			for (Map.Entry<String, ArrayList<String>> ngEntry1 : dt.fqdns.entrySet()) {
				String fqdn = ngEntry1.getKey();
				ArrayList<String> groups = ngEntry1.getValue();

				for (Map.Entry<String, ArrayList<String>> aaoEntry : aaoData.entrySet()) {
					String aaoDomain = aaoEntry.getKey();
					ArrayList<String> pGroups = aaoEntry.getValue();
					// System.out.println("domain: " + domain + ", groups: " + pGroups);

				}

				// System.out.println("fqdn: " + fqdn + ", groups: " + groups);
			}
		}

	}

}
