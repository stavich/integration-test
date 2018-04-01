package org.stavich.integration.integrationtest;

import java.util.Map;

public class PessoaTransformer {

    public static Pessoa transform(Map row){
        return Pessoa.builder()
                .id((long)row.get("ID"))
                .name((String) row.get("NAME"))
                .age((int)row.get("AGE"))
                .build();
    }
}
