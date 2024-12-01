package org.cs3343.safepaws.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to indicate a one-to-one relationship between entities.
 * This annotation should be applied to fields that represent the foreign key
 * in the relationship.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OneToOne {
    /**
     * The name of the column that holds the foreign key.
     *
     * @return the column name
     */
    TableSchema.Column columnName();

    /**
     * The name of the table that the foreign key references.
     *
     * @return the table name
     */
    TableSchema.Name tableName();
}
/*
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OneToOne {
    String columnName();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Entity {
    String tableName();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Id {
}*/
