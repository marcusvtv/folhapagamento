package edu.ifce.folhapagamento.domain;

import java.util.ArrayList;
import java.util.List;
import edu.ifce.folhapagamento.domain.*;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.criteria.Fetch;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity	
public class Colaborador {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

	@NotNull(message = "Nome � obrigat�rio")
	@Size(min=4, max=30, message="O nome deve ter entre 4 e 30 caracteres")
	private String nome;
	
    private String endereco;
    private String telefone;
    private String bairro;
    
    @Basic
    @Size(min=8,max=8, message = "O CEP dever� ter 8 caracteres")
    private String cep;
    
    @NotNull(message = "O CPF dever� ser preenchido")
    @Size(min =11,max = 11, message = "O CPF dever� conter 8 caracteres")
    private String cpf;
    
    @NotNull(message="O Sal�rio dever� ser preenchido")
    @Range(min=1, message = "Valor n�o pode ser inferior a 1,00")
    private float salarioAtual;
    
    
    @OneToMany(
    		mappedBy = "colaborador", 
    		targetEntity = OcorrenciaFolha.class, 
    		cascade = CascadeType.ALL,
    		fetch = FetchType.LAZY
    )
    
    @JsonManagedReference
    private List<OcorrenciaFolha> ocorrencias = new ArrayList<OcorrenciaFolha>();
     
    public List<OcorrenciaFolha> getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(List<OcorrenciaFolha> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }


    public Colaborador() {
    	
    }
    
    public Colaborador(int codigo, String nome, String endereco, String telefone, String bairro, String cep, String cpf, float salarioAtual) {
        this.id = codigo;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.bairro = bairro;
        this.cep = cep;
        this.cpf = cpf;
        this.salarioAtual = salarioAtual;
    }

   //getters and setters  

    public String getNome() {
		return nome;
	}
    
    public void setNome(String nome) {
		this.nome = nome;
	}
    
	public int getId() {
        return id;
    }

    public void setId(int codigo) {
        this.id = codigo;
    }
    

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String edenreco) {
        this.endereco = edenreco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    
    public float getSalarioAtual() {
        return this.salarioAtual;
    }

    public void setSalarioAtual(float salarioAtual) {
        this.salarioAtual = salarioAtual;
    }

    public static float calcularSalario(Colaborador colaborador){
        return colaborador.getSalarioAtual() + colaborador.totalProventos() - colaborador.totalDescontos();
    }

    public void inserirOcorrencias(OcorrenciaFolha ocorrencia) {
        ocorrencias.add(ocorrencia);
    }

    public float totalProventos(){
        float somaProventos = 0;
        for (int i = 0; i < ocorrencias.size(); i++){
            if(ocorrencias.get(i).getTipoOcorrencia() == EnumTipoOcorrencia.PROVENTO) {
                somaProventos += ocorrencias.get(i).getValor();
            }
        }
        return somaProventos;
    }
    public float totalDescontos(){
        float somaDescontos = 0;
        for (int i = 0; i < ocorrencias.size(); i++){
            if(ocorrencias.get(i).getTipoOcorrencia() == EnumTipoOcorrencia.DESCONTO) {
                somaDescontos += ocorrencias.get(i).getValor();
            }
        }
        return somaDescontos;
    }

}
