/*
 * To change this license header, choose License Headers in Project Properties.
 *
 * and open the template in the editor.
 */
package net.thevpc.nmail;

import java.util.Arrays;

import net.thevpc.nmail.util.NMailUtils;

/**
 *
 * @author taha.bensalah@gmail.com
 */
public class NMailBodyContent extends AbstractNMailBody implements Cloneable {

    private byte[] value;

    public NMailBodyContent(byte[] value, String contentType, boolean expandable) {
        super(contentType, expandable);
        this.value = value;
    }

    @Override
    public NMailBodyContent copy() {
        NMailBodyContent r = (NMailBodyContent) super.copy();
        if (r.value != null) {
            r.value = new byte[r.value.length];
            System.arraycopy(value, 0, r.value, 0, value.length);
        }
        return r;
    }

    public byte[] getByteArray() {
        return value;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 53 * hash + Arrays.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        final NMailBodyContent other = (NMailBodyContent) obj;
        if (!Arrays.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String s = null;
        if (getContentType() == null ||
                NMailUtils.isTextContentType(getContentType())) {
            s = (value == null) ? "" : new String(value);
        } else {
            s = (value == null) ? "" : ("binary<" + value.length + ">");
        }
        return "body{" + getContentType() + ";" + getOrder() + ";" + getPosition() + " ; content=" + s + '}';
    }

}
