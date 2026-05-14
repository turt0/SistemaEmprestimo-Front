package modelo;


import java.util.ArrayList;

public class Amigo extends ModeloBase {

    /**
     * atributos
     */
    private String telefone;

    /**
     * construtor
     */
    public Amigo() {
        this(0, "", "");
    }

    public Amigo(int id, String nome, String telefone) {
        super(id, nome);
        this.telefone = telefone;
    }

    /**
     * get e set
     */
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Método toString para imprimir os detalhes do Amigo
     */
    @Override
    public String toString() {
        return super.toString() + "telefone=" + telefone;
    }



}