package org.stavich.integration.integrationtest;

import org.apache.tomcat.util.net.AprEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;
import org.springframework.integration.scheduling.PollerMetadata;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class ReadFromDatabase {

    private static final String QUERY = "SELECT * FROM TEST WHERE STATUS = 'READY'";
    private static final String UPDATE = "UPDATE TEST SET STATUS = 'DONE' WHERE ID = (:ID)";

    @Autowired
    private DataSource dataSource;

    @Bean
    public MessageSource<?> databaseSource(){
        JdbcPollingChannelAdapter pollingChannelAdapter = new JdbcPollingChannelAdapter(dataSource, QUERY);
        pollingChannelAdapter.setUpdateSql(UPDATE);
        pollingChannelAdapter.setUpdatePerRow(true);
        return pollingChannelAdapter;
    }

    @Bean
    public IntegrationFlow sendToOut(){
        return IntegrationFlows
                .from(
                        databaseSource(),
                        c -> c.poller(Pollers.fixedDelay(500)))
                .split()
                .transform(PessoaTransformer::transform)
                .log()
                .handle(System.out::println)
                .get();
    }


}
