package fai.broker.models;

import it.swdes.models.AbstractModel;
import it.swdes.models.Attribute;

public class UploadTaskProperty extends AbstractModel {


    public static int OID;
    public static int KEY;
    public static int VALUE;

    public static final Attribute[] attributes = buildAttributes(UploadTaskProperty.class, new Attribute[]{ //@formatter:off
            new Attribute("oid", null, Long.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
            new Attribute("key", null, String.class, null, false, Attribute.NOREF, true, Attribute.NO_OPTION),
            new Attribute("value", null, String.class, null, false, Attribute.NOREF, false, Attribute.NO_OPTION),
    }); //@formatter:on


    /**
     * CONSTRUCTOR
     **/
    public UploadTaskProperty() {
    }


    /**
     * OID
     **/
    public boolean setOid(Long oid) {
        return setLong(OID, oid);
    }

    public Long getOid() {
        return getLong(OID);
    }


    /**
     * KEY
     **/
    public boolean setKey(String key) {
        return setString(KEY, key);
    }

    public String getKey() {
        return getString(KEY);
    }


    /**
     * VALUE
     **/
    public boolean setValue(String descr) {
        return setString(VALUE, descr);
    }

    public String getValue() {
        return getString(VALUE);
    }
}
