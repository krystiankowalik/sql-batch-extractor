package com.github.krystiankowalik.sqlbatchextractor.service.impl;

import com.github.krystiankowalik.sqlbatchextractor.model.HostInfo;
import com.github.krystiankowalik.sqlbatchextractor.service.BaseUrlProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BaseUrlProviderImpl implements BaseUrlProvider {

    private Environment environment;


    public HostInfo getHostInfo() throws UnknownHostException {
        return new HostInfo(
                InetAddress.getLocalHost().getHostName(),
                InetAddress.getLocalHost().getHostAddress(),
                InetAddress.getLoopbackAddress().getHostName(),
                InetAddress.getLoopbackAddress().getHostAddress(),
                environment.getProperty("server.port")

        );

        // Local address


    }

    public String getBaseUrl() {
        try {
            return "http://" +
                    getHostInfo().getRemoteAddress() +
                    ":" +
                    getHostInfo().getServerPort() +
                    "/";

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
