package constants;

public enum APIResources {

	SEARCH_ELASTIC("/search/elastic/"), SEARCH_ELASTIC_AUTOCOMPLETE("/search/autocomplete/"),
	DYNAMIC_FILTER("/dynamic_filter"), INVENTORY_LOCATIONS("/inventory/"), CLICK_DATA_CAPTURE("/click_data_capture"),
	GET_SEARCH_BROWSING_HISTORY("/get/browsing_history"), LOGIN("/login"), GET_SHIPMENT_DATA("/get_shipment_data"),
	GET_HS_CODE("/search/hs_code"), MANUFACTURER_CONTACT_US("/manufacturer/contact_us");

	String resource;

	APIResources(String resource) {
		this.resource = resource;
	}

	public String getAPIResource() {
		return resource;
	}
}
