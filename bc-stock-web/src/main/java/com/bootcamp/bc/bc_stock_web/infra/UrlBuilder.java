package com.bootcamp.bc.bc_stock_web.infra;

import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

public class UrlBuilder {

  public static String get(Protocol protocol, String domain, String endpoint) {
    return UriComponentsBuilder.newInstance().scheme(protocol.getProtocal())
        .host(domain).path(endpoint).build(false).toUriString();
  }

  public static String get(Protocol protocol, String domain, String endpoint,
      String... pathSegments) {
    return UriComponentsBuilder.newInstance().scheme(protocol.getProtocal())
        .host(domain).path(endpoint).pathSegment(pathSegments).build(false).toUriString();
  }

  public static String get(Protocol protocol, String domain, String endpoint,
      String queryParm, String queryValue) {
    return UriComponentsBuilder.newInstance().scheme(protocol.getProtocal())
        .host(domain).path(endpoint).queryParam(queryParm, queryValue)
        .build(false).toUriString();
  }

  public static String get(Protocol protocol, String domain, String endpoint,
      String queryParam, String queryValue, String... pathSegments) {
    return UriComponentsBuilder.newInstance().scheme(protocol.getProtocal())
        .host(domain).path(endpoint).pathSegment(pathSegments)
        .queryParam(queryParam, queryValue).build(false).toUriString();
  }

  public static String get(Protocol protocol, String domain, String endpoint,
      MultiValueMap<String, String> params) {
    return UriComponentsBuilder.newInstance().scheme(protocol.getProtocal())
        .host(domain).path(endpoint).queryParams(params).build(false)
        .toUriString();
  }

  public static String get(Protocol protocol, String domain, String endpoint,
      MultiValueMap<String, String> params, String... pathSegments) {
    return UriComponentsBuilder.newInstance().scheme(protocol.getProtocal())
        .host(domain).path(endpoint).pathSegment(pathSegments)
        .queryParams(params).build(false).toUriString();
  }
}
