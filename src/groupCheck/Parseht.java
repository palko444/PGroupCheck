package groupCheck;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import runComm.CommandExecutor;
import runComm.CommandResult;

public class Parseht {

	public HashMap<String, DomainData> parseHt() {
		HashMap<String, DomainData> htData = new HashMap<String, DomainData>();
		CommandResult cspht = null;
		try {
			// cspht = CommandExecutor.exec(new String[] { "/opt/OV/bin/OpC/call_sqlplus.sh", "h_t" }, 5);
			cspht = CommandExecutor.exec(new String[] { "cat", "/home/pala/ng/ng_o" }, 5000);
		}
		catch (IOException io) {
			System.out.println("WARNING: Cannot run /opt/OV/bin/OpC/call_sqlplus.sh h_t");
			System.exit(1);
		}

		if (cspht.rc != 0) {
			System.out.println("WARNING: Cannot run /opt/OV/bin/OpC/call_sqlplus.sh h_t");
			System.exit(1);
		}

		htData = parseLine(cspht.stdout);

		return htData;
	}

	private HashMap<String, DomainData> parseLine(String lines) {
		HashMap<String, DomainData> htData = new HashMap<String, DomainData>();
		Pattern p = Pattern.compile("(\\S*)\\s+NG\\s+(C_.*|P_.*)");

		for (String line : lines.split("\n")) {
			Matcher m = p.matcher(line);
			if (m.matches()) {
				String fqdn = m.group(1);
				String domain = getDomain(fqdn);
				String group = m.group(2);
				DomainData nd;

				if (!htData.containsKey(domain)) {
					htData.put(domain, new DomainData());
					nd = htData.get(domain);
				} else {
					nd = htData.get(domain);
				}

				if (!nd.bsmEnabled) {
					nd.bsmEnabled = checkBSMFLag(group);

				}

				if (!nd.fqdns.containsKey(fqdn)) {
					nd.fqdns.put(fqdn, new ArrayList<String>());
					ArrayList<String> cpg = nd.fqdns.get(fqdn);
					cpg.add(group);
				} else {
					ArrayList<String> cpg = nd.fqdns.get(fqdn);
					cpg.add(group);
				}
			}

		}

		return htData;
	}

	private String getDomain(String fqdn) {
		String[] s = fqdn.split("\\.", 2);
		return s[1];
	}

	private Boolean checkBSMFLag(String group) {
		if (group.equals("P_PROD_OMI") || group.equals("P_RTP_OMI")) {
			return true;
		} else {
			return false;
		}
	}
}
