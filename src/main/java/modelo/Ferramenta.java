package modelo;

import java.util.ArrayList;
import dao.FerramentaDAO;

public class Ferramenta extends ModeloBase {

    /**
     * atributos
     */
    private String marca;
    private double custo;
    private FerramentaDAO dao;

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
        dao = new FerramentaDAO();
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

    
    /**
     * retorna a lista de ferramentas
     */
    public ArrayList<Ferramenta> getMinhaLista() {
        return dao.getMinhaLista();
    }

    /**
     * cadastra nova ferramenta
     */
    public boolean insertFerramentaBD(String nome, String marca, double custo) {
        int id = dao.maiorID() + 1;
        Ferramenta objeto = new Ferramenta(id, nome, marca, custo);
        dao.insertFerramentaBD(objeto);
        return true;
    }

    /**
     * deleta uma ferramenta especifica pelo id
     */
    public boolean deleteFerramentaBD(int id) {
        dao.deleteFerramentaBD(id);
        return true;
    }

    /**
     * edita uma ferramenta especifica pelo id
     */
    public boolean updateFerramentaBD(int id, String nome, String marca, double custo) {
        Ferramenta objeto = new Ferramenta(id, nome, marca, custo);
        dao.updateFerramentaBD(objeto);
        return true;
    }

    /**
     * procura o indice de objeto da minhaLista que tem o id enviado
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
     * carrega dados de uma ferramenta especifica pelo id
     */
    public Ferramenta carregaFerramenta(int id) {
        int indice = this.procuraIndice(id);
        return dao.minhaLista.get(indice);
    }

    /**
     * retorna o maior id do BD
     */
    public int maiorID() {
        return dao.maiorID();
    }
}
