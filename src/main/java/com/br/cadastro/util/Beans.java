package com.br.cadastro.util;

import com.br.cadastro.exceptions.ValidationException;
import com.br.cadastro.model.JsonErrors;

public class Beans {

    public static boolean isLong(String valor) {
        try {
            Long.parseLong(valor);
            return true;
        } catch (NumberFormatException n) {
            throw new ValidationException("O valor {0}, não é um numero valido", valor);
        }
    }

    public static boolean isEmpty(String valor) {
        return valor == null || valor.trim().length() == 0;
    }

    public static boolean isNotEmpty(String valor) {
        return !isEmpty(valor);
    }

    public static JsonErrors validarLongValue(String value) {
        if (isLong(value)) {
            return new JsonErrors(false, "");
        }

        return null;
    }
}
