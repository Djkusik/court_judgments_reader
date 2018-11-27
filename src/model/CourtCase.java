package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourtCase {

    @SerializedName("caseNumber")
    @Expose
    private String caseNumber;

    public CourtCase() {}

    public CourtCase(String caseNumber) {
        super();
        this.caseNumber = caseNumber;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }
}
