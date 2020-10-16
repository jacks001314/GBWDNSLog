package com.gbw.dnslog.service;

import com.gbw.dnslog.mapper.WeblogMapper;
import com.gbw.dnslog.model.WebLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class WeblogService {

    @Autowired
    private WeblogMapper weblogMapper;

    public void addWeblog(WebLog webLog) {
        weblogMapper.insert(webLog);
    }

    public List getAllWeblog(String userDomain) {
        return weblogMapper.getAll(userDomain);
    }

    public void delOneWeblog(String id) {
        weblogMapper.deleteOneByID(id);
    }

    public void delAllWeblog(int logid) {
        weblogMapper.deleteAllBylogID(logid);
    }

    public WebLog getWeblogByID(String id) {
        return weblogMapper.selectWeblogByID(id);
    }

    public List getWeblogByHost(String host) {return weblogMapper.selectWebLogByHost(host); }
}
