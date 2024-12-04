package util;

import org.cs3343.safepaws.util.OneToOne;
import org.cs3343.safepaws.util.TableSchema;

import java.util.Objects;

public class TestClassB extends TestClassA {
    @OneToOne(tableName = TableSchema.Name.TestTableB,
            columnName = TableSchema.Column.Id)
    private TestClassC TestF;

    public TestClassC getTestF() {
        return TestF;
    }

    public void setTestF(TestClassC newForeign) {
        this.TestF = newForeign;
    }

    public TestClassB() {
    }
    public TestClassB(int newId, TestClassC newForeign, TestState newState, int newChangeV) {
        super(newId, newState, newChangeV);
        this.TestF = newForeign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false; // Check equality of the superclass properties

        TestClassB that = (TestClassB) o;
        return Objects.equals(TestF, that.TestF);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode(); // Include the hash code of the superclass
        result = 31 * result + (TestF != null ? TestF.hashCode() : 0);
        return result;
    }
}
