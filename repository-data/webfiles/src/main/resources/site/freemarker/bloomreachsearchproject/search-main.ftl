<#include "../include/imports.ftl">

<#if users??>
    <#if users?has_content>
        <#list users as user>
          <p>${user.displayName}</p>
        </#list>
    <#else>
      <div>
        <h3>No results for: ${query?html}</h3>
      </div>
    </#if>
<#else>
  <h3>Please fill in a search term.</h3>
</#if>

<#if photos??>
  <#if photos?has_content>
    <#list photos as photo>
      <img src="data:image/png;base64,${photo}" alt="photo"/>
    </#list>
  </#if>
</#if>