import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class FichaAvaliacaoGUI extends JFrame {

    // --- Componentes da Interface Gráfica ---
    private JTextField fieldCodigo, fieldNome;
    private JRadioButton rbFeminino, rbMasculino;
    private ButtonGroup bgSexo;
    private JTextArea areaCV;
    private JComboBox<String> cbInteresse, cbAtuacao;

    // --- Estrutura de Dados ---
    private final List<Avaliacao> listaAvaliacoes = new ArrayList<>();
    private int registroAtual = -1;

    public FichaAvaliacaoGUI() {
        setTitle("Ficha de Avaliação");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(750, 600);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                sair();
            }
        });

        // --- Construção da GUI ---
        criarMenu();
        criarPainelPrincipal();
    }

    private void criarMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenu menuEditar = new JMenu("Editar");
        JMenu menuEnviar = new JMenu("Enviar");
        JMenuItem itemEmail = new JMenuItem("email");
        JMenuItem itemImpressora = new JMenuItem("impressora");
        JMenuItem itemSalvar = new JMenuItem("Salvar");
        JMenuItem itemSair = new JMenuItem("Sair");

        menuEnviar.add(itemEmail);
        menuEnviar.add(itemImpressora);
        menuArquivo.add(menuEnviar);
        menuArquivo.add(itemSalvar);
        menuArquivo.add(itemSair);
        menuBar.add(menuArquivo);
        menuBar.add(menuEditar);
        setJMenuBar(menuBar);

        // --- Listeners do Menu ---
        itemSalvar.addActionListener(e -> salvar());
        itemSair.addActionListener(e -> sair());
        itemEmail.addActionListener(e -> JOptionPane.showMessageDialog(this, "Funcionalidade de Enviar por E-mail não implementada.", "Informação", JOptionPane.INFORMATION_MESSAGE));
        itemImpressora.addActionListener(e -> JOptionPane.showMessageDialog(this, "Funcionalidade de Enviar para Impressora não implementada.", "Informação", JOptionPane.INFORMATION_MESSAGE));
    }

    private void criarPainelPrincipal() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel labelTitulo = new JLabel("Ficha de Avaliação", JLabel.CENTER);
        labelTitulo.setForeground(Color.RED);
        panel.add(labelTitulo, BorderLayout.NORTH);

        panel.add(criarPainelFormulario(), BorderLayout.CENTER);
        panel.add(criarPainelBotoes(), BorderLayout.SOUTH);

        add(panel);
    }

    private JPanel criarPainelFormulario() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);

        JLabel labelCodigo = new JLabel("Código :");
        labelCodigo.setBounds(10, 20, 60, 25);
        fieldCodigo = new JTextField();
        fieldCodigo.setBounds(80, 20, 300, 25);

        JLabel labelNome = new JLabel("Nome :");
        labelNome.setBounds(10, 55, 60, 25);
        fieldNome = new JTextField();
        fieldNome.setBounds(80, 55, 300, 25);

        JLabel labelSexo = new JLabel("Sexo :");
        labelSexo.setBounds(10, 90, 60, 25);
        rbFeminino = new JRadioButton("Feminino");
        rbFeminino.setBounds(80, 90, 90, 25);
        rbMasculino = new JRadioButton("Masculino");
        rbMasculino.setBounds(180, 90, 100, 25);
        bgSexo = new ButtonGroup();
        bgSexo.add(rbFeminino);
        bgSexo.add(rbMasculino);

        JLabel labelCV = new JLabel("Curriculum Vitae");
        labelCV.setForeground(Color.BLUE);
        labelCV.setBounds(10, 120, 200, 25);
        areaCV = new JTextArea();
        JScrollPane scrollCV = new JScrollPane(areaCV);
        scrollCV.setBounds(10, 145, 670, 100);

        JLabel labelAreas = new JLabel("Áreas");
        labelAreas.setForeground(Color.BLUE);
        labelAreas.setBounds(10, 250, 200, 25);

        JLabel labelInteresse = new JLabel("Interesse :");
        labelInteresse.setBounds(10, 280, 80, 25);
        cbInteresse = new JComboBox<>(new String[]{"Desenvolvedor", "Analista", "Tester"});
        cbInteresse.setBounds(90, 280, 200, 25);
        cbInteresse.setForeground(Color.RED);

        JLabel labelAtuacao = new JLabel("Atuação :");
        labelAtuacao.setBounds(320, 280, 80, 25);
        cbAtuacao = new JComboBox<>(new String[]{"Programação", "Gerência", "Suporte"});
        cbAtuacao.setBounds(400, 280, 200, 25);

        formPanel.add(labelCodigo);
        formPanel.add(fieldCodigo);
        formPanel.add(labelNome);
        formPanel.add(fieldNome);
        formPanel.add(labelSexo);
        formPanel.add(rbFeminino);
        formPanel.add(rbMasculino);
        formPanel.add(labelCV);
        formPanel.add(scrollCV);
        formPanel.add(labelAreas);
        formPanel.add(labelInteresse);
        formPanel.add(cbInteresse);
        formPanel.add(labelAtuacao);
        formPanel.add(cbAtuacao);
        formPanel.setPreferredSize(new Dimension(700, 320));

        return formPanel;
    }
    
    private JPanel criarPainelBotoes() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.GREEN);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

        JButton btnSalvar = new JButton("Salvar");
        JButton btnAnterior = new JButton("Anterior");
        JButton btnProximo = new JButton("Proximo");
        JButton btnNovo = new JButton("Novo");
        JButton btnCancelar = new JButton("Cancelar");

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnAnterior);
        buttonPanel.add(btnProximo);
        buttonPanel.add(btnNovo);
        buttonPanel.add(btnCancelar);

        // --- Listeners dos Botões ---
        btnNovo.addActionListener(e -> novo());
        btnSalvar.addActionListener(e -> salvar());
        btnAnterior.addActionListener(e -> anterior());
        btnProximo.addActionListener(e -> proximo());
        btnCancelar.addActionListener(e -> cancelar());
        
        return buttonPanel;
    }

    // --- Métodos de Lógica ---
    private void novo() {
        limparCampos();
        registroAtual = -1;
        JOptionPane.showMessageDialog(this, "Formulário pronto para uma nova entrada.", "Novo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void salvar() {
        if (fieldNome.getText().trim().isEmpty() || fieldCodigo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Os campos 'Código' e 'Nome' são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setCodigo(fieldCodigo.getText());
        avaliacao.setNome(fieldNome.getText());
        
        if (rbFeminino.isSelected()) {
            avaliacao.setSexo("Feminino");
        } else if (rbMasculino.isSelected()){
            avaliacao.setSexo("Masculino");
        } else {
            avaliacao.setSexo("Não especificado");
        }
        
        avaliacao.setCurriculum(areaCV.getText());
        avaliacao.setInteresse((String) cbInteresse.getSelectedItem());
        avaliacao.setAtuacao((String) cbAtuacao.getSelectedItem());

        if (registroAtual == -1) {
            listaAvaliacoes.add(avaliacao);
            registroAtual = listaAvaliacoes.size() - 1;
        } else {
            listaAvaliacoes.set(registroAtual, avaliacao);
        }

        System.out.println("Registro salvo: " + avaliacao);
        JOptionPane.showMessageDialog(this, "Dados salvos com sucesso!", "Salvo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void anterior() {
        if (listaAvaliacoes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há registros salvos.", "Navegação", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (registroAtual > 0) {
            registroAtual--;
            carregarDados(registroAtual);
        } else {
            JOptionPane.showMessageDialog(this, "Você já está no primeiro registro.", "Navegação", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void proximo() {
        if (listaAvaliacoes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há registros salvos.", "Navegação", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (registroAtual < listaAvaliacoes.size() - 1) {
            registroAtual++;
            carregarDados(registroAtual);
        } else {
            JOptionPane.showMessageDialog(this, "Você já está no último registro.", "Navegação", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cancelar() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Deseja limpar todos os campos? Qualquer alteração não salva será perdida.",
                "Cancelar",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            limparCampos();
            registroAtual = -1;
        }
    }

    private void sair() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Deseja realmente sair da aplicação?",
                "Confirmação de Saída",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void limparCampos() {
        fieldCodigo.setText("");
        fieldNome.setText("");
        bgSexo.clearSelection();
        areaCV.setText("");
        cbInteresse.setSelectedIndex(0);
        cbAtuacao.setSelectedIndex(0);
        fieldCodigo.requestFocus();
    }

    private void carregarDados(int index) {
        if (index >= 0 && index < listaAvaliacoes.size()) {
            Avaliacao avaliacao = listaAvaliacoes.get(index);
            fieldCodigo.setText(avaliacao.getCodigo());
            fieldNome.setText(avaliacao.getNome());
            
            if ("Feminino".equals(avaliacao.getSexo())) {
                rbFeminino.setSelected(true);
            } else {
                rbMasculino.setSelected(true);
            }
            
            areaCV.setText(avaliacao.getCurriculum());
            cbInteresse.setSelectedItem(avaliacao.getInteresse());
            cbAtuacao.setSelectedItem(avaliacao.getAtuacao());
        }
    }
}
