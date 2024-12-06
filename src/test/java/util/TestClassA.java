package util;

import java.util.Objects;

public class TestClassA {
    private int Id;
    private int ChangeV;
    private TestState state;

    public enum TestState {
        TestA,
        TestB
    }

    public int getId() {
        return Id;
    }

    public void setId(int newId) {
        this.Id = newId;
    }

    public TestState getState() {
        return state;
    }

    public void setState(TestState newState) {
        this.state = newState;

    }

    public int getChangeV() {
        return ChangeV;
    }

    public void setChangeV(int newChangeV) {
        this.ChangeV = newChangeV;
    }

    public TestClassA() {
    }

    public TestClassA(int newId, TestState newState, int newChangeV) {
        this.Id = newId;
        this.state = newState;
        this.ChangeV = newChangeV;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TestClassA that = (TestClassA) o;
        return Id == that.Id &&
                ChangeV == that.ChangeV &&
                state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, ChangeV, state);
    }
}
