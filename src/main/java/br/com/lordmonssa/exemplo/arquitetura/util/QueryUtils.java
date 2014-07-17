package br.com.lordmonssa.exemplo.arquitetura.util;

import java.util.Map;

import javax.persistence.Query;


public class QueryUtils {

	public static void addParameters(Query query, Map<String, Object> parametros) {
		if (!Util.isNullOuVazio(parametros) && !Util.isNullOuVazio(query)) {
			for (String key : parametros.keySet()) {
				query.setParameter(key, parametros.get(key));
			}
		}
	}
}
