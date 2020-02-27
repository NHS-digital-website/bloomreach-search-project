<#include "../include/imports.ftl">

<#-- @ftlvariable name="query" type="java.lang.String" -->
<#-- @ftlvariable name="users" type="java.util.List" -->
<#if users??>
  <#if users?has_content>
    <div>
        <#-- @ftlvariable name="user" type="com.nhsd.model.User" -->
        <#list users as user>
          <h4>${user.displayName}</h4>
          <p><strong>Username:</strong> ${user.name}</p>
          <p><strong>Email:</strong> ${user.email}</p>
          <br />
        </#list>
    </div>
  <#else>
    <h3>No results.</h3>
  </#if>
<#else>
  <h3>Please fill in a search term.</h3>
</#if>