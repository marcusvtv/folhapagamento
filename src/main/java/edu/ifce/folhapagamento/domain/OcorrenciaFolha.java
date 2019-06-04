package edu.ifce.folhapagamento.domain;

import edu.ifce.folhapagamento.domain.Colaborador;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity
public class OcorrenciaFolha {
	   
		@Id
		@GeneratedValue
		private int id_ocorrencia;
		
		@NotNull(message="Deve preencher descrição")
		@Size(min = 10, max = 50)
		private String descricao;
		
		@NotNull(message="Deve preencher Valor")
		@Range(min=1, message="Valor não pode ser inferior a 1,00")
	    private float valor;
		
		private Enum tipoOcorrencia;
	    
		@NotNull(message="Deve preencher o colaborador")
		@ManyToOne  
		@JoinColumn(name="colaborador_id")
		private Colaborador colaborador;
	    		
	    public Colaborador getColaborador() {
			return colaborador;
		}
	    public void setColaborador(Colaborador colaborador) {
			this.colaborador = colaborador;
		}
	    
	    public OcorrenciaFolha(String descricao,float valor,Enum tipoOcorrencia){
	        this.descricao=descricao;
	        this.valor=valor;
	        this.tipoOcorrencia=tipoOcorrencia;
	    }
	    public String getDescricao(){
	        return this.descricao;
	    }
	    public float getValor(){
	        return this.valor;
	    }
	    public Enum getTipoOcorrencia(){
	        return this.tipoOcorrencia;
	    }
	    public void setTipoOcorrencia(Enum tipoOcorrencia){
	        this.tipoOcorrencia=tipoOcorrencia;
	    }
	    public void setDescricao(String descricao){
	        this.descricao=descricao;
	    }
	    public void setValor(float valor){
	        this.valor=valor;
	    }

}
