package com.zhhl.cloudpolice;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.Serializable;

/**
 * Created by miao on 2018/11/9.
 */

@XStreamAlias("RBSPMessage")
public class SevenData {
    static {
        XStream xStream = new XStream(new DomDriver());
        xStream.ignoreUnknownElements();
        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(new Class[]{R.class});
        xStream.processAnnotations(R.class);
//        String s = xStream.toXML(r);
//        System.out.println(s);


        R r = (R) xStream.fromXML(
                "<?xml version='1.0' encoding='UTF-8'?>" +
                        "<RBSPMessage>\n" +
                        "  <ServiceID>C00-00000013</ServiceID>\n" +
                        "  <Method>QueryToXml2</Method>\n" +
                        "</RBSPMessage>");
        System.out.println(r.Method);
    }

    @XStreamAlias("Version")
    private String Version;

    @XStreamAlias("RBSPMessage")
//    @XStreamAliasType("RBSPMessage")
    static class R implements Serializable {

        //        @XStreamAlias("?xml")
//        private String xml;
        private String Version;

        private String ServiceID;

        @XStreamAlias("Method")
        public String Method;


    }
}
