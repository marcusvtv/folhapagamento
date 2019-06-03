package edu.ifce.folhapagamento.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Colaborador {

	@Id
	@GeneratedValue
    private int codigo;
    private String nome, edenreco, telefone, bairro, cep, cpf;
    private float salarioAtual;
    
  //  @OneToMany(cascade = CascadeType.ALL, mappedBy = "colaborador")
    private ArrayList<OcorrenciaFolha> ocorrencias = new ArrayList<OcorrenciaFolha>();
    
    public List<OcorrenciaFolha> getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(ArrayList<OcorrenciaFolha> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }


    public Colaborador(int codigo, String nome, String edenreco, String telefone, String bairro, String cep, String cpf, float salarioAtual) {
        this.codigo = codigo;
        this.nome = nome;
        this.edenreco = edenreco;
        this.telefone = telefone;
        this.bairro = bairro;
        this.cep = cep;
        this.cpf = cpf;
        this.salarioAtual = salarioAtual;
    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEdenreco() {
        return edenreco;
    }

    public void setEdenreco(String edenreco) {
        this.edenreco = edenreco;
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

    public static float calcularSalario(Colaborador x){
        return x.getSalarioAtual() + x.totalProventos() - x.totalDescontos();
    }

    public void inserirOcorrencias(OcorrenciaFolha o) {
        ocorrencias.add(o);
    }

    public float totalProventos(){
        float somaProventos = 0;
        for (int i = 0; i < ocorrencias.size(); i++){
            if(ocorrencias.get(i).getX() == EnumTipoOcorrencia.PROVENTO) {
                somaProventos += ocorrencias.get(i).getValor();
            }
        }
        return somaProventos;
    }
    public float totalDescontos(){
        float somaDescontos = 0;
        for (int i = 0; i < ocorrencias.size(); i++){
            if(ocorrencias.get(i).getX() == EnumTipoOcorrencia.DESCONTO) {
                somaDescontos += ocorrencias.get(i).getValor();
            }
        }
        return somaDescontos;
    }




}
