package groupCheck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        ArrayList<String> nodesWithMissingGroup = new ArrayList<String>();
        ParseAAO aao = new ParseAAO();
        ParsehNg ng = new ParsehNg();
        Set<String> aaoDomains = aao.parseDomains().keySet();
        // HashMap<String, ArrayList<String>> aaoData = aao.parseDomains();
        HashMap<String, DomainData> ngData = ng.parseHt();

        Set<String> ngDomains = ngData.keySet();
        // ArrayList<String> aaoDomains = new ArrayList<String>(aaoData.keySet());
        // Compare.aaoMissingDomains(ngDomains, aaoDomains);

        Set<String> missingAao = missingAao(aaoDomains, ngDomains);
        if (!missingAao.isEmpty()) {
            System.out.println();
            System.out.println("WARNING: These domains are missing in /opt/OpC_local/AutoAgentOnboarding/conf/aao_assignment.cfg");
            for (String domain : missingAao) {
                System.out.println(domain);
            }

        }

        for (Map.Entry<String, DomainData> ngEntry : ngData.entrySet()) {
            // String ngDomain = ngEntry.getKey();
            DomainData dt = ngEntry.getValue();

            for (Map.Entry<String, ArrayList<String>> ngEntry1 : dt.fqdns.entrySet()) {
                String fqdn = ngEntry1.getKey();
                ArrayList<String> groups = ngEntry1.getValue();

                // System.out.println("flag: " + dt.bsmEnabled + " , domain: " + ngDomain + " , fqdn: " + fqdn + " , groups: " + groups);
                if ((!excludeBsmNodes(fqdn)) && Compare.nodesWithBsmFlag(groups)) {
                    nodesWithMissingGroup.add(fqdn);
                }

                // for (Map.Entry<String, ArrayList<String>> aaoEntry : aaoData.entrySet()) {
                // String aaoDomain = aaoEntry.getKey();
                // ArrayList<String> pGroups = aaoEntry.getValue();
                // }
            }

        }

        if (!nodesWithMissingGroup.isEmpty()) {
            System.out.println();
            System.out.println("WARNING: These nodes are missing P_PROD or P_RTP group: ");
            for (String node : nodesWithMissingGroup) {
                System.out.println(node);
            }
        }

    }

    public static Set<String> missingAao(Set<String> aaoDomains, Set<String> ngDomains) {

        Set<String> domains = new HashSet<String>();
        for (String ngDomain : ngDomains) {
            if (!containsDomain(ngDomain, aaoDomains)) {
                domains.add(ngDomain);
            }
        }

        return excludeDomains(domains);
    }

    public static boolean containsDomain(String domain, Set<String> aaoDomains) {

        for (String aaoDomain : aaoDomains) {
            if (domain.contains(aaoDomain)) {
                return true;
            }
        }

        return false;
    }

    public static Set<String> excludeDomains(Set<String> domains) {

        // domains.remove("ssn.hpe.com|omc.hp.com|emea.omc.hp.com");
        domains.remove("ssn.hpe.com");
        domains.remove("omc.hp.com");
        domains.remove("emea.omc.hp.com");
        domains.remove("omc.emea.hp.com");
        domains.remove("gre.omc.hp.com");

        return domains;
    }

    public static Boolean excludeBsmNodes(String fqdn) {
        List<String> bsmNodes = Arrays.asList("ibsmpv03.gre.omc.hp.com", "ibsmpv01.gre.omc.hp.com", "ibsmpv02.gre.omc.hp.com", "emea-bsmpdata-v.gre.omc.hp.com");

        return bsmNodes.contains(fqdn) ? true : false;
    }

}
