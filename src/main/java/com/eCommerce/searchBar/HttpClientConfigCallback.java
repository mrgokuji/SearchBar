package com.eCommerce.searchBar;

import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;

public interface HttpClientConfigCallback {
    /**
     * Allows to customize the {@link CloseableHttpAsyncClient} being created and used by the {@link RestClient}.
     * Commonly used to customize the default {@link org.apache.http.client.CredentialsProvider} for authentication
     * or the {@link SchemeIOSessionStrategy} for communication through ssl without losing any other useful default
     * value that the {@link RestClientBuilder} internally sets, like connection pooling.
     */
    HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder);
}
