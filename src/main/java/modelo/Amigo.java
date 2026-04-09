package modelo;

import dao.AmigoDAO;
import java.util.ArrayList;

public class Amigo extends ModeloBase {

    /**
     * atributos
     */
    private String telefone;
    private AmigoDAO dao;

    /**
     * construtor
     */
    public Amigo() {
        this(0, "", "");
    }

    public Amigo(int id, String nome, String telefone) {
        super(id, nome);
        this.telefone = telefone;

        dao = new AmigoDAO();
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

    public ArrayList<Amigo> getMinhaLista() {
        return dao.getMinhaLista();
    }

    /**
     * Cadastra novo amigo
     */
    public boolean insertAmigoBD(String nome, String telefone) {
        int id = dao.maiorID() + 1;
        Amigo objeto = new Amigo(id, nome, telefone);
        dao.insertAmigoBD(objeto);
        return true;
    }

    /**
     * Deleta um amigo especÍfico pelo seu campo ID
     */
    public boolean deleteAmigoBD(int id) {
        dao.deleteAmigoBD(id);
        return true;
    }

    /**
     * Edita um amigo especÍfico pelo seu campo ID
     */
    public boolean updateAmigoBD(int id, String nome, String telefone) {
        Amigo objeto = new Amigo(id, nome, telefone);
        dao.updateAmigoBD(objeto);
        return true;
    }

    /**
     * procura o indice de objeto da MinhaLista que contem o ID enviado.
     */
    private int procuraIndice(int id) {
        int indice = -1;
        for (int i = 0; i < dao.minhaLista.size(); i++) {
            if (dao.minhaLista.get(i).getId() == id) {
                indice = i;
            }
        }
        return indice;
    }

    /**
     * carrega dados de um amigo especÍfico pelo seu ID.
     */
    public Amigo carregaAmigo(int id) {
        int indice = this.procuraIndice(id);
        return dao.minhaLista.get(indice);
    }

    /**
     * retorna o maior ID da nossa base de dados
     */
    public int maiorID() {
        return dao.maiorID();
    }

}