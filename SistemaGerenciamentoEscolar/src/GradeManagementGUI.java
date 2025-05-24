import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class GradeManagementGUI extends JFrame {
    private JTable tableAlunos, tableDisciplinas, tableNotas;
    private DefaultTableModel modelAlunos, modelDisciplinas, modelNotas;
    private java.util.List<Aluno> alunos = new ArrayList<>();
    private java.util.List<Disciplina> disciplinas = new ArrayList<>();
    private java.util.List<Nota> notas = new ArrayList<>();

    public GradeManagementGUI() {
        setTitle("Sistema de Gerenciamento Escolar");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Alunos", criarPainelAlunos());
        tabbedPane.addTab("Disciplinas", criarPainelDisciplinas());
        tabbedPane.addTab("Notas", criarPainelNotas());

        add(tabbedPane);
        carregarDados();
        setVisible(true);
    }

    private JPanel criarPainelAlunos() {
        JPanel panel = new JPanel(new BorderLayout());
        modelAlunos = new DefaultTableModel(new Object[]{"Nome", "Matrícula"}, 0);
        tableAlunos = new JTable(modelAlunos);
        panel.add(new JScrollPane(tableAlunos), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        JTextField nomeField = new JTextField(10);
        JTextField matriculaField = new JTextField(10);
        JButton adicionarBtn = new JButton("Adicionar");
        JButton excluirBtn = new JButton("Excluir");

        adicionarBtn.addActionListener(e -> {
            String nome = nomeField.getText();
            String matricula = matriculaField.getText();
            if (!nome.isEmpty() && !matricula.isEmpty()) {
                Aluno a = new Aluno(nome, matricula);
                alunos.add(a);
                modelAlunos.addRow(new Object[]{nome, matricula});
                salvarAlunos();
                nomeField.setText("");
                matriculaField.setText("");
            }
        });

        excluirBtn.addActionListener(e -> {
            int row = tableAlunos.getSelectedRow();
            if (row >= 0) {
                alunos.remove(row);
                modelAlunos.removeRow(row);
                salvarAlunos();
            }
        });

        tableAlunos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tableAlunos.getSelectedRow();
                    Aluno aluno = alunos.get(row);
                    String novoNome = JOptionPane.showInputDialog("Novo nome:", aluno.getNome());
                    String novaMatricula = JOptionPane.showInputDialog("Nova matrícula:", aluno.getMatricula());
                    if (novoNome != null && novaMatricula != null) {
                        aluno.setNome(novoNome);
                        aluno.setMatricula(novaMatricula);
                        modelAlunos.setValueAt(novoNome, row, 0);
                        modelAlunos.setValueAt(novaMatricula, row, 1);
                        salvarAlunos();
                    }
                }
            }
        });

        inputPanel.add(new JLabel("Nome:"));
        inputPanel.add(nomeField);
        inputPanel.add(new JLabel("Matrícula:"));
        inputPanel.add(matriculaField);
        inputPanel.add(adicionarBtn);
        inputPanel.add(excluirBtn);
        panel.add(inputPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel criarPainelDisciplinas() {
        JPanel panel = new JPanel(new BorderLayout());
        modelDisciplinas = new DefaultTableModel(new Object[]{"Nome", "Código"}, 0);
        tableDisciplinas = new JTable(modelDisciplinas);
        panel.add(new JScrollPane(tableDisciplinas), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        JTextField nomeField = new JTextField(10);
        JTextField codigoField = new JTextField(10);
        JButton adicionarBtn = new JButton("Adicionar");
        JButton excluirBtn = new JButton("Excluir");

        adicionarBtn.addActionListener(e -> {
            String nome = nomeField.getText();
            String codigo = codigoField.getText();
            if (!nome.isEmpty() && !codigo.isEmpty()) {
                Disciplina d = new Disciplina(nome, codigo);
                disciplinas.add(d);
                modelDisciplinas.addRow(new Object[]{nome, codigo});
                salvarDisciplinas();
                nomeField.setText("");
                codigoField.setText("");
            }
        });

        excluirBtn.addActionListener(e -> {
            int row = tableDisciplinas.getSelectedRow();
            if (row >= 0) {
                disciplinas.remove(row);
                modelDisciplinas.removeRow(row);
                salvarDisciplinas();
            }
        });

        tableDisciplinas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tableDisciplinas.getSelectedRow();
                    Disciplina d = disciplinas.get(row);
                    String novoNome = JOptionPane.showInputDialog("Novo nome:", d.getNome());
                    String novoCodigo = JOptionPane.showInputDialog("Novo código:", d.getCodigo());
                    if (novoNome != null && novoCodigo != null) {
                        d.setNome(novoNome);
                        d.setCodigo(novoCodigo);
                        modelDisciplinas.setValueAt(novoNome, row, 0);
                        modelDisciplinas.setValueAt(novoCodigo, row, 1);
                        salvarDisciplinas();
                    }
                }
            }
        });

        inputPanel.add(new JLabel("Nome:"));
        inputPanel.add(nomeField);
        inputPanel.add(new JLabel("Código:"));
        inputPanel.add(codigoField);
        inputPanel.add(adicionarBtn);
        inputPanel.add(excluirBtn);
        panel.add(inputPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel criarPainelNotas() {
        JPanel panel = new JPanel(new BorderLayout());
        modelNotas = new DefaultTableModel(new Object[]{"Matrícula", "Código Disciplina", "Nota"}, 0);
        tableNotas = new JTable(modelNotas);
        panel.add(new JScrollPane(tableNotas), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        JTextField matriculaField = new JTextField(10);
        JTextField codigoField = new JTextField(10);
        JTextField notaField = new JTextField(5);
        JButton adicionarBtn = new JButton("Adicionar");
        JButton excluirBtn = new JButton("Excluir");

        adicionarBtn.addActionListener(e -> {
            try {
                String matricula = matriculaField.getText();
                String codigo = codigoField.getText();
                double valor = Double.parseDouble(notaField.getText());
                Nota n = new Nota(matricula, codigo, valor);
                notas.add(n);
                modelNotas.addRow(new Object[]{matricula, codigo, valor});
                salvarNotas();
                matriculaField.setText("");
                codigoField.setText("");
                notaField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Nota inválida!");
            }
        });

        excluirBtn.addActionListener(e -> {
            int row = tableNotas.getSelectedRow();
            if (row >= 0) {
                notas.remove(row);
                modelNotas.removeRow(row);
                salvarNotas();
            }
        });

        tableNotas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tableNotas.getSelectedRow();
                    Nota nota = notas.get(row);
                    String novaNota = JOptionPane.showInputDialog("Nova nota:", nota.getValor());
                    if (novaNota != null) {
                        try {
                            double novoValor = Double.parseDouble(novaNota);
                            nota.setValor(novoValor);
                            modelNotas.setValueAt(novoValor, row, 2);
                            salvarNotas();
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(GradeManagementGUI.this, "Nota inválida");
                        }
                    }
                }
            }
        });

        inputPanel.add(new JLabel("Matrícula:"));
        inputPanel.add(matriculaField);
        inputPanel.add(new JLabel("Código Disciplina:"));
        inputPanel.add(codigoField);
        inputPanel.add(new JLabel("Nota:"));
        inputPanel.add(notaField);
        inputPanel.add(adicionarBtn);
        inputPanel.add(excluirBtn);
        panel.add(inputPanel, BorderLayout.SOUTH);
        return panel;
    }

    private void carregarDados() {
        carregarArquivo("alunos.txt", line -> {
            Aluno a = Aluno.fromString(line);
            alunos.add(a);
            modelAlunos.addRow(new Object[]{a.getNome(), a.getMatricula()});
        });

        carregarArquivo("disciplinas.txt", line -> {
            Disciplina d = Disciplina.fromString(line);
            disciplinas.add(d);
            modelDisciplinas.addRow(new Object[]{d.getNome(), d.getCodigo()});
        });

        carregarArquivo("notas.txt", line -> {
            Nota n = Nota.fromString(line);
            notas.add(n);
            modelNotas.addRow(new Object[]{n.getMatricula(), n.getCodigoDisciplina(), n.getValor()});
        });
    }

    private void carregarArquivo(String nomeArquivo, java.util.function.Consumer<String> consumer) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                consumer.accept(linha);
            }
        } catch (IOException e) {
            System.out.println("Arquivo " + nomeArquivo + " não encontrado. Será criado.");
        }
    }

    private void salvarAlunos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("alunos.txt"))) {
            for (Aluno a : alunos) writer.write(a.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarDisciplinas() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("disciplinas.txt"))) {
            for (Disciplina d : disciplinas) writer.write(d.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarNotas() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("notas.txt"))) {
            for (Nota n : notas) writer.write(n.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GradeManagementGUI();
    }
}