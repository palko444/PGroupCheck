package groupCheck;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseAAO {
	
//	final private String aaoCfg = "/opt/OpC_local/AutoAgentOnboarding/conf/aao_assignment.cfg";
	final private String aaoCfg = "/home/pala/ng/aao_assignment.cfg";
	private HashMap<String, String[]> aaoData = new HashMap<String, String[]>();
	

	public void parseDomains () {
		Boolean inSection = false;
		String ignore = "^#|^\\s*$";
		String domains = "\\s*.*\\s*=\\s*.*\\s*$";
		String section = "^\\s*\\[NodeGroup-Assignment_Customer\\]";
		
		Pattern ig = Pattern.compile(ignore);
		Pattern dm = Pattern.compile(domains);
		Pattern s = Pattern.compile(section);
		OpenFile f = new OpenFile(aaoCfg);
		String[] cfgFile = f.getOMs();
		
		for (String line : cfgFile) {
			Matcher m = ig.matcher(line);
			if (m.find()) {
				continue;
			}
			
//			Matcher ms = section.matches(line);
			System.out.println(line);
			
			
		}
		
		
	}
}
