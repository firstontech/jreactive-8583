package org.jreactive.iso8583.example;

import com.solab.iso8583.IsoMessage;
import net.jcip.annotations.NotThreadSafe;
import org.jreactive.iso8583.client.Iso8583Client;
import org.jreactive.iso8583.server.Iso8583Server;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@NotThreadSafe
public abstract class AbstractIT {

    @Autowired
    protected Iso8583Client<IsoMessage> client;

    @Autowired
    protected Iso8583Server<IsoMessage> server;

    @Before
    public void before() throws Exception {
        configureServer(server);
        server.init();
        server.start();

        configureClient(client);
        client.init();
        client.connect();
    }

    protected void configureClient(Iso8583Client<IsoMessage> client) {
        // to be overridden in tests
    }

    protected void configureServer(Iso8583Server<IsoMessage> server) {
        // to be overridden in tests
    }

    @After
    public void after() throws Exception {
        client.shutdown();
        server.shutdown();
    }
}
