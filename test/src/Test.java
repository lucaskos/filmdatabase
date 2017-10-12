import java.lang.String;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Luke on 18.09.2017.
 */
public class Test {


    public static void main(String[] args) {

        Auth auth = new Auth();

        String kontekst = "njs:0, subunitnumber:0;njs:2, subunitnumber:2 ; njs:1234 , subunitnumber:432423523";
        String profile = "o1,o2,o3,o4,z1,z2";

        Map<String, String> ldapData = new HashMap<>();
        ldapData.put("kontekst", kontekst);
        ldapData.put("profile", profile);
        auth = convertLDAPData(ldapData);

        System.out.println(auth.toString());

    }

    private static Auth convertLDAPData(Map<String, String> ldapData) {
        Auth auth = new Auth();
        Set<PrincipalProfile> kontekstList = new HashSet<>();
        Set<String> profileList = new HashSet();
        for (Map.Entry<String, String> stringStringMap : ldapData.entrySet()) {
            int indexOfChar = 0;
            String value = stringStringMap.getValue();

            if (stringStringMap.getKey().equalsIgnoreCase("kontekst")) {
                value.toLowerCase().trim();
                while (!value.equalsIgnoreCase("")) {
                    String njs = null;
                    String subUnitNumber = null;
                    int index = 0;

                    System.out.println(value);
                    value.toLowerCase();
                    if (value.startsWith("njs")) {
                        value.trim();
                        value = value.replaceFirst("njs:", "").trim();
                        indexOfChar = value.indexOf(",");
                        njs = value.substring(0, indexOfChar);
                        value = value.replace(new String(njs + ","), "");
                        System.out.println(value);
                    }
                    if (value.trim().startsWith("subunitnumber")) {
                        value.trim();
                        value = value.replaceFirst("subunitnumber:", "");
                        indexOfChar = value.indexOf(";");
                        if (indexOfChar > 0) {
                            subUnitNumber = value.substring(0, indexOfChar);
                            value = value.substring(indexOfChar + 1).trim();
                        } else {
                            subUnitNumber = value.substring(0, value.length()).trim();
                            value = null;
                        }
                    }
                    if (njs != "" && subUnitNumber != "") {
                        kontekstList.add(new PrincipalProfile(njs.trim(), subUnitNumber.trim(), index));
                        index++;
                    }

                    if(value == "" || value == null)
                        break;
                }
                System.out.println(kontekstList.toString());
            }
            auth.setKontekstSet(kontekstList);
            if (stringStringMap.getKey().equalsIgnoreCase("profile")) {

                while (value.length() > 2) {
                    java.lang.String singleProfile = value.substring(0, 2).trim();
                    profileList.add(singleProfile);
                    value = value.substring(2, value.length()).trim();
                    value = value.replaceFirst("," , "").trim();
                }
                profileList.add(value.substring(0, value.length()));
                auth.setProfileSet(profileList);
            }

        }
        auth.setKontekstSet(kontekstList);
        auth.setProfileSet(profileList);
        return auth;
    }
}
