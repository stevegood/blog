<%@ page import="org.stevegood.blog.sec.Requestmap" %>



<div class="fieldcontain ${hasErrors(bean: requestmapInstance, field: 'configAttribute', 'error')} ">
	<label for="configAttribute">
		<g:message code="requestmap.configAttribute.label" default="Config Attribute" />
		
	</label>
	<g:textField name="configAttribute" value="${requestmapInstance?.configAttribute}" />

</div>

<div class="fieldcontain ${hasErrors(bean: requestmapInstance, field: 'httpMethod', 'error')} ">
	<label for="httpMethod">
		<g:message code="requestmap.httpMethod.label" default="Http Method" />
		
	</label>
	<g:select name="httpMethod" from="${org.springframework.http.HttpMethod?.values()}" keys="${org.springframework.http.HttpMethod.values()*.name()}" required="" value="${requestmapInstance?.httpMethod?.name()}" />

</div>

<div class="fieldcontain ${hasErrors(bean: requestmapInstance, field: 'url', 'error')} ">
	<label for="url">
		<g:message code="requestmap.url.label" default="Url" />
		
	</label>
	<g:textField name="url" value="${requestmapInstance?.url}" />

</div>

