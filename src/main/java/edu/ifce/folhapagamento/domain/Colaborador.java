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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.criteria.Fetch;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;


@Entity	
public class Colaborador {

	@Id
	@GeneratedValue
    private int codigo;

	@NotNull(message = "Nome é obrigatório")
	@Size(min=4, max=30, message="O nome deve ter entre 4 e 30 caracteres")
	private String nome;
	
    private String endereco;
    private String telefone;
    private String bairro;
    
    @Basic
    @Size(min=8,max=8, message = "O CEP deverá ter 8 caracteres")
    private String cep;
    
    @NotNull(message = "O CPF deverá ser preenchido")
    @Size(min =11,max = 11, message = "O CPF deverá conter 8 caracteres")
    private String cpf;
    
    @NotNull(message="O Salário deverá ser preenchido")
    @Range(min=1, message = "Valor não pode ser inferior a 1,00")
    private float salarioAtual;
    
    
    @OneToMany(
    		mappedBy = "colaborador", 
    		targetEntity = OcorrenciaFolha.class, 
    		cascade = CascadeType.ALL,
    		fetch = FetchType.LAZY
    )
    private List<OcorrenciaFolha> ocorrencias = new ArrayList<OcorrenciaFolha>();
     
    public List<OcorrenciaFolha> getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(ArrayList<OcorrenciaFolha> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }


    public Colaborador() {
    	
    }
    
    public Colaborador(int codigo, String nome, String endereco, String telefone, String bairro, String cep, String cpf, float salarioAtual) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.bairro = bairro;
        this.cep = cep;
        this.cpf = cpf;
        this.salarioAtual = salarioAtual;
    }

   //getters and setters  

	public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
