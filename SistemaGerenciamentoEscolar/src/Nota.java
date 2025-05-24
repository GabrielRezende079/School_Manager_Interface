public class Nota {
    private String matricula;
    private String codigoDisciplina;
    private double valor;

    public Nota(String matricula, String codigoDisciplina, double valor) {
        this.matricula = matricula;
        this.codigoDisciplina = codigoDisciplina;
        this.valor = valor;
    }

    public String getMatricula() { return matricula; }
    public String getCodigoDisciplina() { return codigoDisciplina; }
    public double getValor() { return valor; }

    public void setValor(double valor) { this.valor = valor; }

    @Override
    public String toString() {
        return matricula + "," + codigoDisciplina + "," + valor;
    }

    public static Nota fromString(String line) {
        String[] parts = line.split(",");
        return new Nota(parts[0], parts[1], Double.parseDouble(parts[2]));
    }
}