package modelo;

import java.util.ArrayList;


public class Ferramenta extends ModeloBase {

    /**
     * atributos
     */
    private String marca;
    private double custo;


    /**
     * construtor de objeto sem parametros
     */
    public Ferramenta() {
        this(0, "", "", 0);
    }

    /**
     * construtor com parametros
     */
    public Ferramenta(int id, String nome, String marca, double custo) {
        super(id, nome);
        this.marca = marca;
        this.custo = custo;

    }

    /**
     * getters e setters
     */
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    @Override
    public String toString() {
        return super.toString() + "marca=" + marca + ", custo=" + custo;
    }
}
