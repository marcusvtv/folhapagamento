package edu.ifce.folhapagamento.domain;

import java.util.HashMap;
import java.util.Map;

public enum EnumTipoOcorrencia {
	PROVENTO(0), DESCONTO(1);

	 private int value;
	    private static Map<Object, Object> map = new HashMap<>();

	    private EnumTipoOcorrencia(int value) {
	        this.value = value;
	    }

	    static {
	        for (EnumTipoOcorrencia enumTipoOcorrencia : EnumTipoOcorrencia.values()) {
	            map.put(enumTipoOcorrencia.value, enumTipoOcorrencia);
	        }
	    }

	    public static EnumTipoOcorrencia valueOf(int enumTipoOcorrencia) {
	        return (EnumTipoOcorrencia) map.get(enumTipoOcorrencia);
	    }

	    public int getValue() {
	        return value;
	    }
	
	
	
}
