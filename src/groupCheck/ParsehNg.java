package groupCheck;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import runComm.CommandExecutor;
import runComm.CommandResult;

public class ParsehNg {

	public HashMap<String, DomainData> parseHt() {
		HashMap<String, DomainData> ngData = new HashMap<String, DomainData>();
		CommandResult cspNg = null;
		try {
			// cspNg = CommandExecutor.exec(new String[] { "/opt/OV/bin/OpC/call_sqlplus.sh", "h_t" }, 5000);
			cspNg = CommandExecutor.exec(new String[] { "cat", "/home/pala/ng/ng_o" }, 5000);
		}
		catch (IOException io) {
			System.out.println("WARNING: Cannot run /opt/OV/bin/OpC/call_sqlplus.sh h_t");
			System.exit(1);
		}

		if (cspNg.rc != 0) {
			System.out.println("WARNING: Cannot run /opt/OV/bin/OpC/call_sqlplus.sh h_t. Return code: " + cspNg.rc );
			System.exit(1);
		}

		ngData = parseLine(cspNg.stdout);

		return ngData;
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
					nd = new DomainData();
					htData.put(domain, nd);
				} else {
					nd = htData.get(domain);
				}

				if (!nd.bsmEnabled) {
					nd.bsmEnabled = checkBsmFLag(group);
				}

				if (!nd.fqdns.containsKey(fqdn)) {
					ArrayList<String> cpg = new ArrayList<String>();
					nd.fqdns.put(fqdn, cpg);
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
		return fqdn.split("\\.", 2)[1];
	}

	private Boolean checkBsmFLag(String group) {
		return (group.equals("P_PROD_OMI") || group.equals("P_RTP_OMI")); 
	}
}
