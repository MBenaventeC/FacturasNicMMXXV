/*
 * An XML document type.
 * Localname: getToken
 * Namespace: http://www.sii.cl/SiiDte
 * Java type: cl.sii.siiDte.GetTokenDocument
 *
 * Automatically generated - do not modify.
 */
package cl.sii.siiDte.impl;
/**
 * A document containing one getToken(@http://www.sii.cl/SiiDte) element.
 *
 * This is a complex type.
 */
public class GetTokenDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements cl.sii.siiDte.GetTokenDocument
{
    private static final long serialVersionUID = 1L;
    
    public GetTokenDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName GETTOKEN$0 = 
        new javax.xml.namespace.QName("http://www.sii.cl/SiiDte", "getToken");
    
    
    /**
     * Gets the "getToken" element
     */
    public cl.sii.siiDte.GetTokenDocument.GetToken getGetToken()
    {
        synchronized (monitor())
        {
            check_orphaned();
            cl.sii.siiDte.GetTokenDocument.GetToken target = null;
            target = (cl.sii.siiDte.GetTokenDocument.GetToken)get_store().find_element_user(GETTOKEN$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "getToken" element
     */
    public void setGetToken(cl.sii.siiDte.GetTokenDocument.GetToken getToken)
    {
        synchronized (monitor())
        {
            check_orphaned();
            cl.sii.siiDte.GetTokenDocument.GetToken target = null;
            target = (cl.sii.siiDte.GetTokenDocument.GetToken)get_store().find_element_user(GETTOKEN$0, 0);
            if (target == null)
            {
                target = (cl.sii.siiDte.GetTokenDocument.GetToken)get_store().add_element_user(GETTOKEN$0);
            }
            target.set(getToken);
        }
    }
    
    /**
     * Appends and returns a new empty "getToken" element
     */
    public cl.sii.siiDte.GetTokenDocument.GetToken addNewGetToken()
    {
        synchronized (monitor())
        {
            check_orphaned();
            cl.sii.siiDte.GetTokenDocument.GetToken target = null;
            target = (cl.sii.siiDte.GetTokenDocument.GetToken)get_store().add_element_user(GETTOKEN$0);
            return target;
        }
    }
    /**
     * An XML getToken(@http://www.sii.cl/SiiDte).
     *
     * This is a complex type.
     */
    public static class GetTokenImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements cl.sii.siiDte.GetTokenDocument.GetToken
    {
        private static final long serialVersionUID = 1L;
        
        public GetTokenImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName ITEM$0 = 
            new javax.xml.namespace.QName("http://www.sii.cl/SiiDte", "item");
        private static final javax.xml.namespace.QName SIGNATURE$2 = 
            new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "Signature");
        private static final javax.xml.namespace.QName VERSION$4 = 
            new javax.xml.namespace.QName("", "version");
        
        
        /**
         * Gets the "item" element
         */
        public cl.sii.siiDte.GetTokenDocument.GetToken.Item getItem()
        {
            synchronized (monitor())
            {
                check_orphaned();
                cl.sii.siiDte.GetTokenDocument.GetToken.Item target = null;
                target = (cl.sii.siiDte.GetTokenDocument.GetToken.Item)get_store().find_element_user(ITEM$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "item" element
         */
        public void setItem(cl.sii.siiDte.GetTokenDocument.GetToken.Item item)
        {
            synchronized (monitor())
            {
                check_orphaned();
                cl.sii.siiDte.GetTokenDocument.GetToken.Item target = null;
                target = (cl.sii.siiDte.GetTokenDocument.GetToken.Item)get_store().find_element_user(ITEM$0, 0);
                if (target == null)
                {
                    target = (cl.sii.siiDte.GetTokenDocument.GetToken.Item)get_store().add_element_user(ITEM$0);
                }
                target.set(item);
            }
        }
        
        /**
         * Appends and returns a new empty "item" element
         */
        public cl.sii.siiDte.GetTokenDocument.GetToken.Item addNewItem()
        {
            synchronized (monitor())
            {
                check_orphaned();
                cl.sii.siiDte.GetTokenDocument.GetToken.Item target = null;
                target = (cl.sii.siiDte.GetTokenDocument.GetToken.Item)get_store().add_element_user(ITEM$0);
                return target;
            }
        }
        
        /**
         * Gets the "Signature" element
         */
        public org.w3.x2000.x09.xmldsig.SignatureType getSignature()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.w3.x2000.x09.xmldsig.SignatureType target = null;
                target = (org.w3.x2000.x09.xmldsig.SignatureType)get_store().find_element_user(SIGNATURE$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "Signature" element
         */
        public void setSignature(org.w3.x2000.x09.xmldsig.SignatureType signature)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.w3.x2000.x09.xmldsig.SignatureType target = null;
                target = (org.w3.x2000.x09.xmldsig.SignatureType)get_store().find_element_user(SIGNATURE$2, 0);
                if (target == null)
                {
                    target = (org.w3.x2000.x09.xmldsig.SignatureType)get_store().add_element_user(SIGNATURE$2);
                }
                target.set(signature);
            }
        }
        
        /**
         * Appends and returns a new empty "Signature" element
         */
        public org.w3.x2000.x09.xmldsig.SignatureType addNewSignature()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.w3.x2000.x09.xmldsig.SignatureType target = null;
                target = (org.w3.x2000.x09.xmldsig.SignatureType)get_store().add_element_user(SIGNATURE$2);
                return target;
            }
        }
        
        /**
         * Gets the "version" attribute
         */
        public java.math.BigDecimal getVersion()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(VERSION$4);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_default_attribute_value(VERSION$4);
                }
                if (target == null)
                {
                    return null;
                }
                return target.getBigDecimalValue();
            }
        }
        
        /**
         * Gets (as xml) the "version" attribute
         */
        public org.apache.xmlbeans.XmlDecimal xgetVersion()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlDecimal target = null;
                target = (org.apache.xmlbeans.XmlDecimal)get_store().find_attribute_user(VERSION$4);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlDecimal)get_default_attribute_value(VERSION$4);
                }
                return target;
            }
        }
        
        /**
         * Sets the "version" attribute
         */
        public void setVersion(java.math.BigDecimal version)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(VERSION$4);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(VERSION$4);
                }
                target.setBigDecimalValue(version);
            }
        }
        
        /**
         * Sets (as xml) the "version" attribute
         */
        public void xsetVersion(org.apache.xmlbeans.XmlDecimal version)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlDecimal target = null;
                target = (org.apache.xmlbeans.XmlDecimal)get_store().find_attribute_user(VERSION$4);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlDecimal)get_store().add_attribute_user(VERSION$4);
                }
                target.set(version);
            }
        }
        /**
         * An XML item(@http://www.sii.cl/SiiDte).
         *
         * This is a complex type.
         */
        public static class ItemImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements cl.sii.siiDte.GetTokenDocument.GetToken.Item
        {
            private static final long serialVersionUID = 1L;
            
            public ItemImpl(org.apache.xmlbeans.SchemaType sType)
            {
                super(sType);
            }
            
            private static final javax.xml.namespace.QName SEMILLA$0 = 
                new javax.xml.namespace.QName("http://www.sii.cl/SiiDte", "Semilla");
            
            
            /**
             * Gets the "Semilla" element
             */
            public java.lang.String getSemilla()
            {
                synchronized (monitor())
                {
                    check_orphaned();
                    org.apache.xmlbeans.SimpleValue target = null;
                    target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SEMILLA$0, 0);
                    if (target == null)
                    {
                      return null;
                    }
                    return target.getStringValue();
                }
            }
            
            /**
             * Gets (as xml) the "Semilla" element
             */
            public org.apache.xmlbeans.XmlString xgetSemilla()
            {
                synchronized (monitor())
                {
                    check_orphaned();
                    org.apache.xmlbeans.XmlString target = null;
                    target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(SEMILLA$0, 0);
                    return target;
                }
            }
            
            /**
             * Sets the "Semilla" element
             */
            public void setSemilla(java.lang.String semilla)
            {
                synchronized (monitor())
                {
                    check_orphaned();
                    org.apache.xmlbeans.SimpleValue target = null;
                    target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SEMILLA$0, 0);
                    if (target == null)
                    {
                      target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(SEMILLA$0);
                    }
                    target.setStringValue(semilla);
                }
            }
            
            /**
             * Sets (as xml) the "Semilla" element
             */
            public void xsetSemilla(org.apache.xmlbeans.XmlString semilla)
            {
                synchronized (monitor())
                {
                    check_orphaned();
                    org.apache.xmlbeans.XmlString target = null;
                    target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(SEMILLA$0, 0);
                    if (target == null)
                    {
                      target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(SEMILLA$0);
                    }
                    target.set(semilla);
                }
            }
        }
    }
}
