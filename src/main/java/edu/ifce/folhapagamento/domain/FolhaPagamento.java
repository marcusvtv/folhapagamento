package edu.ifce.folhapagamento.domain;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class FolhaPagamento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int mes, ano;
    private float totalDescontos, totalProventos;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name="colaborador_id")
    private List<Colaborador> colaboradores = new ArrayList<>();

    public FolhaPagamento(int mes, int ano) {
        this.mes = mes;
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public float getTotalDescontos() {
        return totalDescontos;
    }

    public void setTotalDescontos(float totalDescontos) {
        this.totalDescontos = totalDescontos;
    }

    public float getTotalProventos() {
        return totalProventos;
    }

    public void setTotalProventos(float totalProventos) {
        this.totalProventos = totalProventos;
    }

    
    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public float somaOcorrencias() {
        float total = 0, somaProventos = 0f, somaDescontos = 0f;
        for (int i = 0; i < colaboradores.size(); i++) {
            for (int j = 0; j < colaboradores.get(i).getOcorrencias().size(); j++) {
                if (colaboradores.get(i).getOcorrencias().get(j).getTipoOcorrencia() == EnumTipoOcorrencia.PROVENTO) {
                    this.totalProventos += colaboradores.get(i).getOcorrencias().get(j).getValor();
                } else if (colaboradores.get(i).getOcorrencias().get(j).getTipoOcorrencia() == EnumTipoOcorrencia.DESCONTO) {
                    this.totalDescontos += colaboradores.get(i).getOcorrencias().get(j).getValor();
                }
                total = (this.totalProventos - this.totalDescontos);
            }
        }
        return total;
    }

    public float calcularFolha() {
        float totalsoma = 0;
        for (int i = 0; i < colaboradores.size(); i++) {
            totalsoma += colaboradores.get(i).getSalarioAtual() + this.somaOcorrencias();
        }
        return totalsoma;
    }

    public void inserirColaboradores (Colaborador c){
        colaboradores.add(c);
    }

}
