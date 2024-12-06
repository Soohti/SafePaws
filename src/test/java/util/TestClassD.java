package util;

import java.util.Objects;

public class TestClassD {
    private int Id;
    private String username;

    public int getId() {
        return Id;
    }

    public void setId(int newId) {
        this.Id = newId;
    }

    public String getName() {
        return username;
    }

    public void setName(String newName) {
        this.username = newName;
    }

    public TestClassD() {
    }

    public TestClassD(String newName) {
        this.username = newName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TestClassD that = (TestClassD) o;
        return Id == that.Id && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, username);
    }
}
