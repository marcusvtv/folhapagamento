package edu.ifce.folhapagamento.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class OcorrenciaFolha {
	   
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@PrimaryKeyJoinColumn
		private int id;
		
		@NotNull(message="Deve preencher descrição")
		@Size(min = 10, max = 50)
		private String descricao;
		
		@NotNull(message="Deve preencher Valor")
		@Range(min=1, message="Valor não pode ser inferior a 1,00")
	    private float valor;
		
		private EnumTipoOcorrencia tipoOcorrencia;
	    
		@NotNull(message="Deve preencher o colaborador")
		@ManyToOne  
		@JoinColumn(name="colaborador_id")
		@JsonBackReference
		private Colaborador colaborador;
		
	    public int getId() {
			return id;
		}
	    
	    public void setId(int id) {
			this.id = id;
		}
		
		
		public Colaborador getColaborador() {
			return colaborador;
		}
		
		public void setColaborador(Colaborador colaborador) {
			this.colaborador = colaborador;
		}
	    
	    
	    
	    public OcorrenciaFolha() {
	    	
	    }
	    
	    
	    public String getDescricao(){
	        return this.descricao;
	    }
	    public float getValor(){
	        return this.valor;
	    }
	    public EnumTipoOcorrencia getTipoOcorrencia(){
	        return this.tipoOcorrencia;
	    }
	    public void setTipoOcorrencia(EnumTipoOcorrencia tipoOcorrencia){
	        this.tipoOcorrencia=tipoOcorrencia;
	    }
	    public void setDescricao(String descricao){
	        this.descricao=descricao;
	    }
	    public void setValor(float valor){
	        this.valor=valor;
	    }

}

		
