public class Disciplina {
    private String nome;
    private String codigo;

    public Disciplina(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public String getNome() { return nome; }
    public String getCodigo() { return codigo; }

    public void setNome(String nome) { this.nome = nome; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    @Override
    public String toString() {
        return nome + "," + codigo;
    }

    public static Disciplina fromString(String line) {
        String[] parts = line.split(",");
        return new Disciplina(parts[0], parts[1]);
    }
}