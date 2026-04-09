package modelo;

import java.util.Date;

/**
 * Classe que representa um empréstimo.
 */
public class Emprestimo {

    /*
     * Atributos
     */
    private int id;
    private Date dtEmprestimo;
    private Date dtDevolucaoPrevista;
    private Date dtDevolucaoReal;
    private Amigo amigo;
    private Ferramenta ferramenta;

    /*
     * Construtores
     */
    public Emprestimo() {
        this(0, null, null, null, null, null);
    }

    public Emprestimo(int id, Date dtEmprestimo, Date dtDevolucaoPrevista,
            Date dtDevolucaoReal, Amigo amigo, Ferramenta ferramenta) {
        this.id = id;
        this.dtEmprestimo = dtEmprestimo;
        this.dtDevolucaoPrevista = dtDevolucaoPrevista;
        this.dtDevolucaoReal = dtDevolucaoReal;
        this.amigo = amigo;
        this.ferramenta = ferramenta;
    }

    /*
     * Getters e Setters
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDtEmprestimo() {
        return dtEmprestimo;
    }

    public void setDtEmprestimo(Date dtEmprestimo) {
        this.dtEmprestimo = dtEmprestimo;
    }

    public Date getDtDevolucaoPrevista() {
        return dtDevolucaoPrevista;
    }

    public void setDtDevolucaoPrevista(Date dtDevolucaoPrevista) {
        this.dtDevolucaoPrevista = dtDevolucaoPrevista;
    }

    public Date getDtDevolucaoReal() {
        return dtDevolucaoReal;
    }

    public void setDtDevolucaoReal(Date dtDevolucaoReal) {
        this.dtDevolucaoReal = dtDevolucaoReal;
    }

    public Amigo getAmigo() {
        return amigo;
    }

    public void setAmigo(Amigo amigo) {
        this.amigo = amigo;
    }

    public Ferramenta getFerramenta() {
        return ferramenta;
    }

    public void setFerramenta(Ferramenta ferramenta) {
        this.ferramenta = ferramenta;
    }

    /**
     * Verifica se o empréstimo ainda está ativo.
     */
    public boolean isAtivo() {
        return this.dtDevolucaoReal == null;
    }
}
