<#include "../include/imports.ftl">

<#if users??>
    <#if users?has_content>
        <#list users as user>
          <p>${user}</p>
        </#list>
    <#else>
      <div>
        <h3>No results for: ${query?html}</h3>
      </div>
    </#if>
<#else>
  <h3>Please fill in a search term.</h3>
</#if>