package br.com.felipemira.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Ignore;

@Ignore
@AllArgsConstructor
@Getter
public enum AppHost {

     
     Local("Local", 1);
     
     private String descricao;
     private Integer valor;
     
     public static AppHost getEnum(Integer valor) {

    	 AppHost resultado = null;
          for (AppHost re : AppHost.values()) {
               if (re.valor.compareTo(valor) == 0) {
                    resultado = re;
                    break;
               }
          }
          return resultado;
     }
}
