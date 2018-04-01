package org.stavich.integration.integrationtest;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
class Pessoa {
    private long id;
    private String name;
    private int age;
}
