package com.gbw.dnslog;

import com.gbw.dnslog.config.DnslogConfig;
import com.gbw.dnslog.dnsserver.UDPServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

@Configuration
@SpringBootApplication
@MapperScan("com.gbw.dnslog.mapper")
@EnableScheduling
public class GBWDNSLogApplication {

    public static void main(String[] args) throws Exception{

        Options opts = new Options();
        opts.addOption("domain",true,"dnslog domain");
        opts.addOption("webDomain", true, "dnslog web domain");
        opts.addOption("ip", true, "dnslog ip address");
        opts.addOption("signal", true, "dnslog signal");
        opts.addOption("help", false, "Print usage");

        CommandLine cliParser = new GnuParser().parse(opts, args);


        if(cliParser.hasOption("help")||!cliParser.hasOption("domain")){

            new HelpFormatter().printHelp("GBWDNSLogApplication", opts);
            System.exit(0);
        }

        DnslogConfig.dnslogDomain = cliParser.getOptionValue("domain");
        DnslogConfig.managerDomain = cliParser.getOptionValue("webDomain");
        DnslogConfig.ip = cliParser.getOptionValue("ip");
        DnslogConfig.signal = cliParser.getOptionValue("signal");


        ConfigurableApplicationContext context = SpringApplication.run(GBWDNSLogApplication.class, args);

        UDPServer udpServer = context.getBean(UDPServer.class);

        udpServer.start();
    }

}
