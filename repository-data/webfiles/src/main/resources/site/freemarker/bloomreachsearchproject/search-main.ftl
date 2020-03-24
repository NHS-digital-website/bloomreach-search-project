<#include "../include/imports.ftl">

<#if users??>
    <#if users?has_content>
        <#list users as user>
          <h5>${user.displayName}</h5>
          <p><strong>Mobile phone: </strong>${user.mobilePhone}</p>
          <p><strong>Office location: </strong>${user.officeLocation}</p>
          <p><strong>Email: </strong>${user.userPrincipalName}</p>
          <img src="data:image/png;base64,${user.photo}" alt="photo"/>
          <br/>
        </#list>
    <#else>
      <div>
        <h3>No results for: ${query?html}</h3>
      </div>
    </#if>
<#else>
  <h3>Please fill in a search term.</h3>
</#if>