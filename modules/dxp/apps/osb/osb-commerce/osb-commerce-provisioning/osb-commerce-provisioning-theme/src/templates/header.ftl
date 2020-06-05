<header id="banner">
	<div class="mb-5 navbar navbar-commerce navbar-commerce-md navbar-top py-3">
		<div class="container user-personal-bar">
			<div class="align-items-center autofit-row">
				<div class="col-4">
					<a class="${logo_css_class} align-items-center d-md-inline-flex d-sm-none d-none logo-md" href="${site_default_url}" title="<@liferay.language_format arguments="" key="go-to-x" />">
						<img alt="${logo_description}" class="mr-2" height="56" src="${site_logo}" />

						<#if show_site_name>
							<h2 class="h2 mb-0 text-dark" role="heading" aria-level="1">${site_name}</h2>
						</#if>
					</a>
				</div>

				<#assign preferences = freeMarkerPortletPreferences.getPreferences({"portletSetupPortletDecoratorId": "barebone", "destination": "/search"}) />

				<div class="col-4">
					<#include "${full_templates_path}/navigation.ftl" />
				</div>

				<div class="col-4 text-right">
					<div class="d-md-inline-flex">
						<#if show_header_search>
							<div class="justify-content-md-end mr-4 navbar-form" role="search">
								<@liferay.search_bar default_preferences="${preferences}" />
							</div>
						</#if>
					</div>

					<div class="d-md-inline-flex">
						<@liferay.user_personal_bar />
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="navbar navbar-commerce navbar-commerce-xs navbar-expand-md navbar-light pb-3">
		<div class="container">
			<a class="${logo_css_class} align-items-center d-inline-flex d-md-none logo-xs" href="${site_default_url}" rel="nofollow">
				<img alt="${logo_description}" class="mr-2" height="56" src="${site_logo}" />

				<#if show_site_name>
					<h2 class="font-weight-bold h2 mb-0 text-dark">${site_name}</h2>
				</#if>
			</a>

			<#include "${full_templates_path}/navigation.ftl" />
		</div>
	</div>
</header>