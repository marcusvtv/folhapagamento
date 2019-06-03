package edu.ifce.folhapagamento.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class OcorrenciaFolha {
	   
		@Id
		@GeneratedValue
		private String descricao;
	    private float valor;
	    private Enum tipoOcorrencia;
	    
	    @OneToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "colaborador")
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
