package com.example.rasoiyyalogin.model;

public class ResObj {

        private float code;
        private String meta = null;
        private Data DataObject;


        // Getter Methods

        public float getCode() {
            return code;
        }

        public String getMeta() {
            return meta;
        }

        public Data getData() {
            return DataObject;
        }

        // Setter Methods

        public void setCode(float code) {
            this.code = code;
        }

        public void setMeta(String meta) {
            this.meta = meta;
        }

        public void setData(Data dataObject) {
            this.DataObject = dataObject;
        }
    }

