package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class Judge {

    @SerializedName("name")
    @Expose
    private final String name;
    @SerializedName("function")
    @Expose
    private final String function;
    @SerializedName("specialRoles")
    @Expose
    private final List<String> specialRoles;

    public Judge(Builder builder) {
        this.name = builder.name;
        this.function = builder.function;
        this.specialRoles = builder.specialRoles;
    }

    public static class Builder {

        private String name;
        private String function;
        private List<String> specialRoles;

        public static Builder newInstance() {
            return new Builder();
        }

        private Builder() {
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setFunction(String function) {
            this.function = function;
            return this;
        }

        public Builder setSpecialRoles(List<String> specialRoles) {
            this.specialRoles = specialRoles;
            return this;
        }

        public Judge build() {
            return new Judge(this);
        }
    }

    public String getName() {
        return name;
    }

    public String getFunction() {
        return function;
    }

    public List<String> getSpecialRoles() {
        return specialRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Judge judge = (Judge) o;
        return Objects.equals(name, judge.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, function, specialRoles);
    }
}
