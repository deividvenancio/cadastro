package com.br.cadastro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JsonErrors {

    private boolean hasError;
    private String error;

    public JsonErrors(boolean hasError, JSONObject jsonObject) {
        this.hasError = hasError;
        this.error = jsonObject.toMap().toString();
    }

}
