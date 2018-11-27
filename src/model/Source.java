package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {

    @SerializedName("code")
    @Expose
    private final String code;
    @SerializedName("judgmentUrl")
    @Expose
    private final String judgmentUrl;
    @SerializedName("judgmentId")
    @Expose
    private final String judgmentId;
    @SerializedName("publisher")
    @Expose
    private final String publisher;
    @SerializedName("reviser")
    @Expose
    private final String reviser;
    @SerializedName("publicationDate")
    @Expose
    private final String publicationDate;

    public Source(Builder builder) {
        this.code = builder.code;
        this.judgmentUrl = builder.judgmentUrl;
        this.judgmentId = builder.judgmentId;
        this.publisher = builder.publisher;
        this.reviser = builder.reviser;
        this.publicationDate = builder.publicationDate;
    }

    public static class Builder {

        private String code;
        private String judgmentUrl;
        private String judgmentId;
        private String publisher;
        private String reviser;
        private String publicationDate;

        public static Builder newInstance() {
            return new Builder();
        }

        private Builder() {}

        public Builder setCode(String code) {
            this.code = code;
            return this;
        }

        public Builder setJudgmentUrl(String judgmentUrl) {
            this.judgmentUrl = judgmentUrl;
            return this;
        }

        public Builder setJudgmentId(String judgmentId) {
            this.judgmentId = judgmentId;
            return this;
        }

        public Builder setPublisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder setReviser(String reviser) {
            this.reviser = reviser;
            return this;
        }

        public Builder setPublicationDate(String publicationDate) {
            this.publicationDate = publicationDate;
            return this;
        }

        public Source build() {
            return new Source(this);
        }
    }

    public String getCode() {
        return code;
    }

    public String getJudgmentUrl() {
        return judgmentUrl;
    }

    public String getJudgmentId() {
        return judgmentId;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getReviser() {
        return reviser;
    }

    public String getPublicationDate() {
        return publicationDate;
    }
}
