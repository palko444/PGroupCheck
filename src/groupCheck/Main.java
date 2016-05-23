package groupCheck;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ParseAAO a = new ParseAAO();
		Parseht b = new Parseht();
		HashMap<String, String[]> aa = a.parseDomains();
		HashMap<String, DomainData> bb = b.parseHt();
		

//		for (Map.Entry<String, String[]> entry : aa.entrySet()) {
//			String domain = entry.getKey();
//			String[] pGroups = entry.getValue();
//
//			System.out.print("domain: " + domain + ", groups: ");
//			for (String dm : pGroups) {
//				System.out.print(dm + ", ");
//			}
//			System.out.println();

//		}
		
		for (Map.Entry<String,DomainData> entry : bb.entrySet()) {
			String domain = entry.getKey();
			DomainData dd = entry.getValue();
			System.out.println("domain: " + domain + " bsm flag: " + dd.bsmEnabled);
		}

	}

}
