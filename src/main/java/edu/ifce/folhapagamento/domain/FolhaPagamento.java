package edu.ifce.folhapagamento.domain;

import java.util.ArrayList;
import java.util.List;

public class FolhaPagamento {
	private int mes, ano;
    private float totalDescontos, totalProventos;
    private List<Colaborador> Colaboradores = new ArrayList<>();

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
        return Colaboradores;
    }

    public void setColaboradores(List<Colaborador> colaboradores) {
        this.Colaboradores = colaboradores;
    }

    public float somaOcorrencias() {
        float total = 0, somaProventos = 0f, somaDescontos = 0f;
        for (int i = 0; i < Colaboradores.size(); i++) {
            for (int j = 0; j < Colaboradores.get(i).getOcorrencias().size(); j++) {
                if (Colaboradores.get(i).getOcorrencias().get(j).getX() == EnumTipoOcorrencia.PROVENTO) {
                    this.totalProventos += Colaboradores.get(i).getOcorrencias().get(j).getValor();
                } else if (Colaboradores.get(i).getOcorrencias().get(j).getX() == EnumTipoOcorrencia.DESCONTO) {
                    this.totalDescontos += Colaboradores.get(i).getOcorrencias().get(j).getValor();
                }
                total = (this.totalProventos - this.totalDescontos);
            }
        }
        return total;
    }

    public float calcularFolha() {
        float totalsoma = 0;
        for (int i = 0; i < Colaboradores.size(); i++) {
            totalsoma += Colaboradores.get(i).getSalarioAtual() + this.somaOcorrencias();
        }
        return totalsoma;
    }

    public void inserirColaboradores (Colaborador c){
        Colaboradores.add(c);
    }

}
