package com.tecknobit.kinfo.model.desktop.operatingsystem.protocols

interface NetworkParams {
    val hostName: String
    val domainName: String
    val dnsServers: Array<String>
    val ipv4DefaultGateway: String
    val ipv6DefaultGateway: String
}