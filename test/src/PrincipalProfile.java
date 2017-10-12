
import java.lang.String;

/**
 * Created by Luke on 18.09.2017.
 */
public class PrincipalProfile {
    private String njs;
    private String subUnitNumber;
    private int index;

    public PrincipalProfile(){

    }

    public PrincipalProfile(String njs, String subUnitNumber, int index) {
        this.njs = njs;
        this.subUnitNumber = subUnitNumber;
        this.index = index;
    }


    public String getNjs() {
        return njs;
    }

    public void setNjs(String njs) {
        this.njs = njs;
    }

    public String getSubUnitNumber() {
        return subUnitNumber;
    }

    public void setSubUnitNumber(String subUnitNumber) {
        this.subUnitNumber = subUnitNumber;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "PrincipalProfile{" +
                "njs='" + njs + '\'' +
                ", subUnitNumber='" + subUnitNumber + '\'' +
                ", index=" + index +
                '}';
    }
}
