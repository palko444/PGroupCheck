package groupCheck;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import runComm.CommandExecutor;
import runComm.CommandResult;

public class Parseht {

	public HashMap<String, HashMap<String[], ArrayList<String>[]>> parseHt() {
		HashMap<String, HashMap<String[], ArrayList<String>[]>> htData = new HashMap<String, HashMap<String[], ArrayList<String>[]>>();
		CommandResult cspht = null;
		try {
			// cspht = CommandExecutor.exec(new String[] { "/opt/OV/bin/OpC/call_sqlplus.sh", "h_t" }, 5);
			cspht = CommandExecutor.exec(new String[] { "cat", "/home/pala/ng/ng_o" }, 5000);
		} catch (IOException io) {
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

	private HashMap<String, HashMap<String[], ArrayList<String>[]>> parseLine(String lines) {
		HashMap<String, HashMap<String[], ArrayList<String>[]>> htData = new HashMap<String, HashMap<String[], ArrayList<String>[]>>();
		Pattern p = Pattern.compile("(\\S*)\\s+NG\\s+(C_.*|P_.*)");

		for (String line : lines.split("\n")) {
			Matcher m = p.matcher(line);
			if (m.matches()) {
				String fqdn = m.group(1);
				String domain = getDomain(fqdn);
				ArrayList<String> group = new ArrayList<String>();

			}

		}

		return htData;
	}

	private String getDomain(String fqdn) {
		String[] s = fqdn.split("\\.", 2);
		return s[1];
	}
}
