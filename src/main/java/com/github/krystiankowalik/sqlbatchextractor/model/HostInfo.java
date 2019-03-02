package com.github.krystiankowalik.sqlbatchextractor.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HostInfo {

    private String localName;
    private String localAddress;
    private String remoteName;
    private String remoteAddress;
    private String serverPort;
}
