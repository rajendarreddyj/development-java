
package com.rajendarreddyj.jaxwswebservice.calc;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java element interface generated in the
 * com.rajendarreddyj.jaxwswebservice.calc package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation for XML content.
 * The Java representation of XML content can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory methods for each of these are provided in
 * this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddResponse_QNAME = new QName("http://calc.jaxwswebservice.rajendarreddyj.com/", "addResponse");
    private final static QName _Add_QNAME = new QName("http://calc.jaxwswebservice.rajendarreddyj.com/", "add");
    private final static QName _Sub_QNAME = new QName("http://calc.jaxwswebservice.rajendarreddyj.com/", "sub");
    private final static QName _SubResponse_QNAME = new QName("http://calc.jaxwswebservice.rajendarreddyj.com/", "subResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package:
     * com.rajendarreddyj.jaxwswebservice.calc
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Add }
     */
    public Add createAdd() {
        return new Add();
    }

    /**
     * Create an instance of {@link Sub }
     */
    public Sub createSub() {
        return new Sub();
    }

    /**
     * Create an instance of {@link SubResponse }
     */
    public SubResponse createSubResponse() {
        return new SubResponse();
    }

    /**
     * Create an instance of {@link AddResponse }
     */
    public AddResponse createAddResponse() {
        return new AddResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://calc.jaxwswebservice.rajendarreddyj.com/", name = "addResponse")
    public JAXBElement<AddResponse> createAddResponse(final AddResponse value) {
        return new JAXBElement<>(_AddResponse_QNAME, AddResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Add }{@code >}}
     */
    @XmlElementDecl(namespace = "http://calc.jaxwswebservice.rajendarreddyj.com/", name = "add")
    public JAXBElement<Add> createAdd(final Add value) {
        return new JAXBElement<>(_Add_QNAME, Add.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sub }{@code >}}
     */
    @XmlElementDecl(namespace = "http://calc.jaxwswebservice.rajendarreddyj.com/", name = "sub")
    public JAXBElement<Sub> createSub(final Sub value) {
        return new JAXBElement<>(_Sub_QNAME, Sub.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://calc.jaxwswebservice.rajendarreddyj.com/", name = "subResponse")
    public JAXBElement<SubResponse> createSubResponse(final SubResponse value) {
        return new JAXBElement<>(_SubResponse_QNAME, SubResponse.class, null, value);
    }

}
