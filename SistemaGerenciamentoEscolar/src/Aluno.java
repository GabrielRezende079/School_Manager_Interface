public class Aluno {
    private String nome;
    private String matricula;

    public Aluno(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public String getNome() { return nome; }
    public String getMatricula() { return matricula; }

    public void setNome(String nome) { this.nome = nome; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    @Override
    public String toString() {
        return nome + "," + matricula;
    }

    public static Aluno fromString(String line) {
        String[] parts = line.split(",");
        return new Aluno(parts[0], parts[1]);
    }
}