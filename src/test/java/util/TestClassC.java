package util;

import java.util.Objects;

public class TestClassC {
    private int Id;
    public int getId() {
        return Id;
    }
    public void setId(int newId) {
        this.Id = newId;
    }
    public TestClassC() {
    }
    public TestClassC(int newId) {
        this.Id = newId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestClassC that = (TestClassC) o;
        return Id == that.Id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}
