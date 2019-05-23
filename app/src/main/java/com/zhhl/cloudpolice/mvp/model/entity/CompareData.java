package com.zhhl.cloudpolice.mvp.model.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by miao on 2018/11/12.
 */
public class CompareData {


    @XStreamAlias("RBSPMessage")
    public static class RBMPMessage implements Serializable {

        private String Version;

        private String ServiceID;

        @XStreamAlias("Method")
        private Methods Method;

        public String getVersion() {
            return Version;
        }

        public void setVersion(String version) {
            Version = version;
        }

        public String getServiceID() {
            return ServiceID;
        }

        public void setServiceID(String serviceID) {
            ServiceID = serviceID;
        }

        public Methods getMethod() {
            return Method;
        }

        public void setMethod(Methods method) {
            Method = method;
        }
    }

    @XStreamAlias("Method")
    public static class Methods {

//        @XStreamAlias("Name")
//        String Name;

        @XStreamAlias("Items")
        Items Items;

        public CompareData.Items getItems() {
            return Items;
        }

        public void setItems(CompareData.Items items) {
            Items = items;
        }
    }

    @XStreamAlias("Items")
    public static class Items {
        @XStreamAlias("Item")
        Item item;

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }
    }

    @XStreamAlias("Item")
    public static class Item {
        @XStreamAlias("Value")
        Values Value;

        public Values getValue() {
            return Value;
        }

        public void setValue(Values value) {
            Value = value;
        }
    }

    @XStreamAlias("Value")
    public static class Values {
        @XStreamImplicit(itemFieldName = "Row")
        ArrayList<Row> row;

        public ArrayList<Row> getRow() {
            return row;
        }

        public void setRow(ArrayList<Row> row) {
            this.row = row;
        }
    }


    @XStreamAlias("Row")
    public static class Row {
        @XStreamImplicit(itemFieldName = "Data")
        ArrayList<String> data;

        public ArrayList<String> getData() {
            return data;
        }

        public void setData(ArrayList<String> data) {
            this.data = data;
        }
    }
}
