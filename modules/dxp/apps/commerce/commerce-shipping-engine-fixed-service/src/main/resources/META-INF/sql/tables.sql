create table CShippingFixedOptionRel (
	CShippingFixedOptionRelId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceShippingMethodId LONG,
	commerceShippingFixedOptionId LONG,
	commerceWarehouseId LONG,
	commerceCountryId LONG,
	commerceRegionId LONG,
	zip VARCHAR(75) null,
	weightFrom DOUBLE,
	weightTo DOUBLE,
	fixedPrice DOUBLE,
	rateUnitWeightPrice DOUBLE,
	ratePercentage DOUBLE
);

create table CommerceShippingFixedOption (
	commerceShippingFixedOptionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceShippingMethodId LONG,
	name STRING null,
	description STRING null,
	amount DOUBLE,
	priority DOUBLE
);