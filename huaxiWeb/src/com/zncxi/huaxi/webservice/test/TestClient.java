package com.zncxi.huaxi.webservice.test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.zncxi.huaxi.webservice.dto.SceneDTO;
import com.zncxi.huaxi.webservice.dto.controldev.OperaterDTO;

public class TestClient {

	static final String baseUrl = "http://localhost:8080/huaxiWeb/api";

	public static void main(String[] args) throws Exception {
		String getPath = "/video/list";
//		String postPath = "/controlDev/operate";
		String authCode = "1";

		// POST param
		OperaterDTO addDto1 = new OperaterDTO();
		addDto1.controlIds = "20150305001-1,20150305001-2";
		addDto1.orderState = 0;
	

		// GET param
		MultivaluedMap<String, String> paramMap = new MultivaluedMapImpl();
		paramMap.add("sceneId", "64238f7f-0010-47c1-9e08-5192a824b0c0");


		TestClient TestClient = new TestClient();
		TestClient.testGET(getPath, authCode, paramMap, new SceneDTO());
//		TestClient.testPOST(postPath, authCode, addDto1);
	}

	@SuppressWarnings("unchecked")
	public <T> void testGET(String path, String authCode,
			MultivaluedMap<String, String> paramMap, T dtoT) throws Exception {
		Client client = Client.create();
		WebResource r = client.resource(baseUrl);
		ClientResponse response = r.path(path).queryParams(paramMap)
				.accept(MediaType.APPLICATION_JSON)
				.header("authCode", authCode).get(ClientResponse.class);
		String res = response.getEntity(String.class);
		System.out.println(response.getStatus());
		System.out.println(res);

		Gson gson = new Gson();
		List<T> dtoList = gson.fromJson(res, new TypeToken<List<T>>() {
		}.getType());
		Field[] fields = dtoT.getClass().getFields();
		for (T dto : dtoList) {
			for (Field field : fields) {
				String fieldName = field.getName();
				System.out.println(fieldName + ":"
						+ ((Map<String, Object>) dto).get(fieldName));
			}
		}

	}

	public void testPOST(String path, String authCode, Object objDTO) {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);

		Client client = Client.create(clientConfig);
		WebResource r = client.resource(baseUrl);

		ClientResponse response = r.path(path)
				.accept(MediaType.APPLICATION_JSON)
				.header("authCode", authCode).entity(objDTO)
				.post(ClientResponse.class);
		System.out.println(response.getStatus());

	}

//	public void test() {
//		Map<String, String> map = new HashMap<String, String>();
//
//		HttpHeaders requestHeaders = new HttpHeaders();
//		requestHeaders.set("authCode", "aaaa");
//		requestHeaders.setAccept(Collections
//				.singletonList(new org.springframework.http.MediaType(
//						"application", "json")));
//		HttpEntity<String> requestEntity = new HttpEntity<String>(
//				requestHeaders);
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.getMessageConverters().add(
//				new StringHttpMessageConverter());
//
//		ResponseEntity<String> responseEntity = null;
//		responseEntity = restTemplate
//				.exchange(
//						"http://192.168.1.114:8090/xsas/api/germplasmCulture/list?beginDate=20100202&endDate=20100202&page=1",
//						HttpMethod.GET, requestEntity, String.class, map);
//
//	}

}
