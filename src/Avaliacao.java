public class Avaliacao {

    private String codigo;
    private String nome;
    private String sexo;
    private String curriculum;
    private String interesse;
    private String atuacao;

    // Getters e Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public String getInteresse() {
        return interesse;
    }

    public void setInteresse(String interesse) {
        this.interesse = interesse;
    }

    public String getAtuacao() {
        return atuacao;
    }

    public void setAtuacao(String atuacao) {
        this.atuacao = atuacao;
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", sexo='" + sexo + '\'' +
                ", curriculum='" + curriculum + '\'' +
                ", interesse='" + interesse + '\'' +
                ", atuacao='" + atuacao + '\'' +
                '}';
    }
}
