package com.eCommerce.searchBar;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.transport.Header;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.util.List;

@Configuration
public class ElasticSearchConfiguration
{
    @Bean
    public RestClient getRestClient() {

        HttpClientConfigCallback httpClientConfigCallback = httpClientBuilder ->
                httpClientBuilder
                        // this request & response header manipulation helps get around newer (>=7.16) versions
                        // of elasticsearch-java client not working with older (<7.14) versions of Elasticsearch
                        // server
                        .setDefaultHeaders(
                                List.of(
                                        new BasicHeader(
                                                HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString())))
                        .addInterceptorLast(
                                (HttpResponseInterceptor)
                                        (response, context) ->
                                                response.addHeader("X-Elastic-Product", "Elasticsearch"));
        var restClient =
                RestClient.builder(new HttpHost("localhost", 9200))
                        .setHttpClientConfigCallback(httpClientConfigCallback)
                        .build();


//        RestClient restClient = RestClient.builder(
//                new HttpHost("localhost", 9200))
//                .setDefaultHeaders(new BasicHeader[]{
//                        new BasicHeader("Content-type", "application/json"),
//                        new BasicHeader("X-Elastic-Product", "Elasticsearch")
//                })
//                .build();
        return restClient;
    }

    @Bean
    public  ElasticsearchTransport getElasticsearchTransport() {
        return new RestClientTransport(
                getRestClient(), new JacksonJsonpMapper());
    }


    @Bean
    public ElasticsearchClient getElasticsearchClient(){
        ElasticsearchClient client = new ElasticsearchClient(getElasticsearchTransport());
        return client;
    }

}