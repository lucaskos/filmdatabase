import java.lang.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Luke on 18.09.2017.
 */
class Auth {

    private Set<String> profileSet;
    private Set<PrincipalProfile> kontekstSet;

    public Auth() {

    }

    public Set<String> getProfileSet() {
        return profileSet;
    }

    public void setProfileSet(Set<String> profileSet) {
        this.profileSet = profileSet;
    }

    public Set<PrincipalProfile> getKontekstSet() {
        return kontekstSet;
    }

    public void setKontekstSet(Set<PrincipalProfile> kontekstSet) {
        this.kontekstSet = kontekstSet;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "profileSet=" + profileSet +
                ", kontekstSet=" + kontekstSet +
                '}';
    }
}
