package com.br.cadastro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResponse {

    private int statusCode;
    private String message;
    private Object body;

    public static JsonResponse ok(Object o) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setStatusCode(200);
        jsonResponse.setBody(o);
        jsonResponse.setMessage("ok");
        return jsonResponse;
    }

    public static JsonResponse delete(String message) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setStatusCode(200);
        jsonResponse.setBody(null);
        jsonResponse.setMessage(message);
        return jsonResponse;
    }

    public static JsonResponse error(String message) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setStatusCode(400);
        jsonResponse.setBody(null);
        jsonResponse.setMessage(message);
        return jsonResponse;
    }

    public static JsonResponse error(JsonErrors jsonErrors) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setStatusCode(400);
        jsonResponse.setBody(null);
        jsonResponse.setMessage(jsonErrors.getError());
        return jsonResponse;
    }
}
