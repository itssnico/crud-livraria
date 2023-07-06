package br.edu.unicid.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.edu.unicid.model.Leitor;
import br.edu.unicid.dao.LeitorDAO;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.TextArea;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField txtCodLeitor;
	private JComboBox cmbTipoLeitor;
	private JButton btnNovo;
	private JButton btnSalvar;
	private JButton btnConsultar;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnListar;
	private TextArea txtListar;
	private JLabel lblMensagem;
	private JTextField txtNomeLeitor;
	private Leitor leitor;
	private LeitorDAO dao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\nicol\\Downloads\\livro.png"));
		setTitle("Manuten\u00E7\u00E3o Leitor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 471);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("C\u00F3digo do Leitor");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(22, 27, 116, 17);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Nome do Leitor");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(22, 74, 110, 17);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Tipo de Leitor");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(22, 128, 98, 17);
		contentPane.add(lblNewLabel_2);

		txtCodLeitor = new JTextField();
		txtCodLeitor.setBounds(307, 26, 287, 22);
		contentPane.add(txtCodLeitor);
		txtCodLeitor.setColumns(10);

		cmbTipoLeitor = new JComboBox();
		cmbTipoLeitor.setModel(new DefaultComboBoxModel(
				new String[] { "Selecione uma op\u00E7\u00E3o", "Aluno", "Professor", "Administrativo" }));
		cmbTipoLeitor.setBounds(307, 127, 287, 22);
		contentPane.add(cmbTipoLeitor);

		btnNovo = new JButton("Novo "); // Mesma função que o Limpar
		btnNovo.setForeground(new Color(204, 51, 51));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCodLeitor.setText(null);
				txtNomeLeitor.setText(null);
				txtListar.setText("");
				cmbTipoLeitor.setSelectedIndex(0);

			}
		});
		btnNovo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNovo.setBounds(10, 180, 89, 36);
		contentPane.add(btnNovo);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setForeground(new Color(204, 51, 51));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					leitor = new Leitor();
					leitor.setCodLeitor(Integer.parseInt(txtCodLeitor.getText()));
					leitor.setNomeLeitor(txtNomeLeitor.getText());
					leitor.setTipoLeitor((String) cmbTipoLeitor.getSelectedItem());
					dao = new LeitorDAO();
					dao.salvar(leitor);
					lblMensagem.setText("Salvo com sucesso");
				} catch (Exception erro) {
					lblMensagem.setText("Erro ao salvar: " + erro);
				}
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalvar.setBounds(109, 180, 89, 36);
		contentPane.add(btnSalvar);

		btnConsultar = new JButton("Consultar");
		btnConsultar.setForeground(new Color(204, 51, 51));
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					leitor = new Leitor();

					int cod = Integer.parseInt(txtCodLeitor.getText());

					dao = new LeitorDAO();
					leitor = dao.consultar(cod);
					// append serve tipo setText, para aparecer o o texto na textArea
					txtListar.append("Código do leitor:  " + leitor.getCodLeitor() + "\n");
					txtListar.append("Nome do leitor:  " + leitor.getNomeLeitor() + "\n");
					txtListar.append("Tipo de leitor:  " + leitor.getTipoLeitor() + "\n");
				} catch (Exception erro) {
					lblMensagem.setText("Erro ao consultar: " + erro);
				}
			}
		});
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConsultar.setBounds(208, 180, 89, 36);
		contentPane.add(btnConsultar);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setForeground(new Color(204, 51, 51));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					leitor = new Leitor();
					leitor.setCodLeitor(Integer.parseInt(txtCodLeitor.getText()));
					leitor.setNomeLeitor(txtNomeLeitor.getText());
					leitor.setTipoLeitor((String) cmbTipoLeitor.getSelectedItem());
					dao = new LeitorDAO();
					dao.alterar(leitor); // mudar alterar
					lblMensagem.setText("Alterado com sucesso");// mudar alterado
				} catch (Exception erro) {
					lblMensagem.setText("Erro ao alterar " + erro); // mudar para alterar
				}
			}
		});
		btnAlterar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAlterar.setBounds(307, 180, 89, 36);
		contentPane.add(btnAlterar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setForeground(new Color(204, 51, 51));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int codLeitor = Integer.parseInt(txtCodLeitor.getText());
					dao = new LeitorDAO();
					dao.excluir(codLeitor);
					lblMensagem.setText("Excluído com sucesso");
				} catch (Exception erro) {
					lblMensagem.setText("Erro ao excluir" + erro);
				}
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExcluir.setBounds(406, 180, 89, 36);
		contentPane.add(btnExcluir);

		btnListar = new JButton("Listar");
		btnListar.setForeground(new Color(204, 51, 51));
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<Leitor> lista = new ArrayList<Leitor>();
					dao = new LeitorDAO();
					lista = dao.listarTodos();
					for (Leitor leitor : lista) {
						txtListar.append("Código do Leitor: " + leitor.getCodLeitor() + "\n");
						txtListar.append("Nome do Leitor: " + leitor.getNomeLeitor() + "\n");
						txtListar.append("Tipo de Leitor: " + leitor.getTipoLeitor() + "\n");
						txtListar.append("----------------------------------------------\n");
					}
				} catch (Exception erro) {
					lblMensagem.setText("Erro ao listar: " + erro);
				}
			}
		});
		btnListar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnListar.setBounds(505, 180, 89, 36);
		contentPane.add(btnListar);

		txtListar = new TextArea();
		txtListar.setBounds(10, 222, 586, 152);
		contentPane.add(txtListar);

		lblMensagem = new JLabel("");
		lblMensagem.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, Color.GRAY, Color.GRAY));
		lblMensagem.setBounds(10, 392, 584, 29);
		contentPane.add(lblMensagem);

		txtNomeLeitor = new JTextField();
		txtNomeLeitor.setColumns(10);
		txtNomeLeitor.setBounds(307, 73, 287, 22);
		contentPane.add(txtNomeLeitor);
	}
}