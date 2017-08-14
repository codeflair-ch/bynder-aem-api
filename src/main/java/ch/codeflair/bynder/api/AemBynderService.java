package ch.codeflair.bynder.api;
/**
 * Copyright 2017 codeflair Gmbh (http://www.codeflair.ch)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 *  rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 *  permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 *  Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *  OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 *  OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import org.apache.sling.api.resource.Resource;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.util.List;


/**
 * Interface to access the AEM Bynder Service.
 */
public interface AemBynderService {

    /**
     * virtual content path under which bynder assets are referenced
     */
    String CONTENT_PATH_BYNDER = "/bynder/";
    /**
     * JCR property for the bynder media id
     */
    String PROPERTY_BYNDER_MEDIA_ID = "bynderMediaId";

    /**
     * JCR property for the bynder focus point
     */
    String PROPERTY_BYNDER_FOCUSPOINT = "bynderFocuspoint";


    /**
     * JCR property for missing bynder derivatives
     */
    String PROPERTY_MISSING_BYNDER_DERIVATIVES = "bynderMissingDerivatives";


    /**
     * JCR property for bynder dynamic derivatives
     */
    String PROPERTY_BYNDER_DYNAMIC_DERIVATIVES = "bynderDynamicDerivatives";


    /**
     * JCR property for bynder static derivatives
     */
    String PROPERTY_BYNDER_RENDITIONS_INITIALIZED = "bynderRenditionsInitialized";

    /**
     * Injects an SSL context and trustManager into the AEM Bynder service. If set, all outgoing http connections use
     * the ssl context, making it possible to implement mutual SSL with a client certificate.
     *
     * @param sslContext      the sslContext to be used for outgoing connections
     * @param sslTrustManager ssl trust manager to be used for outgoing connections
     */
    void setSslContext(SSLContext sslContext, X509TrustManager sslTrustManager);

    /**
     * to be called when a bynder asset is added to an AEM component.
     * This will initialize the static and dynamic renditions for the asset
     *
     * @param bynderReference the virtual path to the bynder asset int the jcr repo ("/bynder/...")
     * @param resource        AEM resource to which the component was added.
     * @return the bynder media id of the bynder asset
     */
    String processBynderMediaItem(String bynderReference, Resource resource);

    /**
     * @param bynderMediaId  the bynder media id of the bynder asset
     * @param derivativeName the derivate to retrieve
     * @param resource       the resource for which to get the derivate
     * @return url to the bynder derivate in the bynder cdn.
     */
    String getDerivative(String bynderMediaId, String derivativeName, Resource resource);

    /**
     * returns the configured focus point for the bynder image. The focus point is available after
     * invoking processBynderMediaItem on the resource.
     *
     * @param bynderMediaId the bynder media id of the bynder asset
     * @param resource      the resource for which to get the focus point
     * @return the focus point data.
     */
    BynderFocusPoint getFocuspoint(String bynderMediaId, Resource resource);

    /**
     * returns the missing bynder (static) derivatives for a given resource. The missing resources will be available
     * after
     * invoking processBynderMediaItem on the resource.
     * @param resource the resource containing a bynder reference
     * @return a list of all static derivatives configured either in the global (OSGi) or component configuration which
     * are not found on the currently set bynder asset.
     */
    List<String> getMissingDerivatives(Resource resource);
}
