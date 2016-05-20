package groupCheck;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseAAO {

	// final private String aaoCfg = "/opt/OpC_local/AutoAgentOnboarding/conf/aao_assignment.cfg";
	final private String aaoCfg = "/home/pala/ng/aao_assignment.cfg";

	public HashMap<String, String[]> parseDomains () {
		Boolean inSection = false;
		HashMap<String, String[]> aaoData = new HashMap<String, String[]>();
		
		Pattern ignore = Pattern.compile("^#|^\\s*$");
		Pattern assignment = Pattern.compile("\\s*(.*)\\s*=\\s*(.*)\\s*$");
		Pattern section = Pattern.compile("^\\s*\\[NodeGroup-Assignment_Customer\\]");
		
		OpenFile f = new OpenFile(aaoCfg);
		
		for (String line : f.getOMs()) {
			Matcher m = ignore.matcher(line);
			if (m.find()) {
				continue;
			}
			
			if (inSection){
				Matcher as = assignment.matcher(line);
				if (as.find()) {
					parseAssignments();
				}
			}
			
			Matcher ms = section.matcher(line);
			if (ms.find()) {
				inSection = true;
				continue;
			}
		}
		return aaoData;
	}
	
	private void parseAssignments () {
	}
	}
}
