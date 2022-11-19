package dev.mtage.tryspringdatajpa.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author mtage
 * @since 2020/11/28 16:04
 */
@Data
@Builder
public class Student {
    private long id;
    private String name;
    private int age;
}
