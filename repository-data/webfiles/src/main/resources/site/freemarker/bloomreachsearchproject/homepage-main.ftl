<#include "../include/imports.ftl">

<@hst.setBundle basename="essentials.homepage"/>
<div>
  <h1><@fmt.message key="homepage.title" var="title"/>${title?html}</h1>
  <p><@fmt.message key="homepage.text" var="text"/>${text?html}</p>
  <#if !hstRequest.requestContext.channelManagerPreviewRequest>
    <p>
      [This text can be edited
      <a href="http://localhost:8080/cms/?1&path=/content/documents/administration/labels/homepage" target="_blank">here</a>.]
    </p>
  </#if>

  <h5>
    <a href="https://login.microsoftonline.com/common/oauth2/v2.0/authorize?client_id=465121b4-cb68-47ea-94af-268de5585d75&response_type=code&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fsite%2Frest%2Fauth%2Fresponse&response_mode=query&scope=openid%20offline_access%20https%3A%2F%2Fgraph.microsoft.com%2Fuser.read&state=12345">
      Click here to login with microsoft
    </a>
  </h5>

</div>
<div>
  <@hst.include ref="container"/>
</div>