package com.hediapps.domain.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

@Document(collection = "users")
public class User implements ClientDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String firstName;

	private String lastName;

	private String clientId;
	private String clientSecret;

	private Set<String> resourceIds;

	private Set<String> scope;

	private Set<String> authorizedGrantTypes;

	private Set<Role> authorities = new HashSet<Role>();

	public User() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getClientId() {
		return clientId;
	}

	@Override
	public Set<String> getResourceIds() {
		return resourceIds;
	}

	@Override
	public boolean isSecretRequired() {
		return true;
	}

	@Override
	public String getClientSecret() {
		return clientSecret;
	}

	@Override
	public boolean isScoped() {
		return true;
	}

	@Override
	public Set<String> getScope() {
		return scope;
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
		return null;
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		return 1000;
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		return 1000;
	}

	@Override
	public boolean isAutoApprove(String scope) {
		return true;
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		return null;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return Collections.unmodifiableCollection(authorities);
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public void setResourceIds(Set<String> resourceIds) {
		this.resourceIds = resourceIds;
	}

	public void setScope(Set<String> scope) {
		this.scope = scope;
	}

	public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}
}
