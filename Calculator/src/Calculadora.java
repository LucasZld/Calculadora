import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Calculadora extends JFrame implements ActionListener{
	private static final long serialVersionUID = 4781031710737270239L;
	
	private Botones[] btn;
	private String[] simbolos= {"C", "\u221A", "/", "\u2190", "(", ")", "7", "8", "9", "*","s3","s4", "4", "5", "6", "+", "s5", "s6", "1", "2", "3", "-","s7", "s8","%", "0", ".", "=", "s9", "s10"};
	private Operaciones op = new Operaciones();
	private ArrayList<String> cadenaCal = new ArrayList<String>();
	private StringBuilder cadena1;
	private boolean haysimbolo=false, haypunto=false, primerNum=true, pParentesis=false, sParentesis=false;
	
	private JPanel contentPane;
	private JLabel lblMostrar;
	private JLabel lblResumen;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculadora frame = new Calculadora();
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
	public Calculadora() {
		this.setResizable(false); // BLOQUEAR EL CAMBIO DE RESOLUCION DE LA VENTANA
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 305, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		lblMostrar = new JLabel("", SwingConstants.RIGHT);
		lblMostrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMostrar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		lblMostrar.setBounds(10, 11, 270, 68);
		contentPane.add(lblMostrar);
		
		JButton expandir = new JButton("\u21F1");
		expandir.addActionListener(new ActionListener() {
			boolean exp = false;
			public void actionPerformed(ActionEvent e) {
				if (exp) {
					setBounds(100, 100, 305, 478);
					lblMostrar.setBounds(10, 11, 270, 68);
					expandir.setText("\u21F1");
					exp=false;
				}
				else {
					setBounds(100, 100, 445, 478);
					lblMostrar.setBounds(10, 11, 410, 68);
					expandir.setText("\u21F2");
					exp=true;
				}
				
			}
		});
		expandir.setFont(new Font("SansSerif", Font.PLAIN, 15));
		expandir.setBackground(Color.LIGHT_GRAY);
		expandir.setBounds(10, 49, 30, 30);
		expandir.setMargin(new Insets(0,0,0,0));
		expandir.setFocusable(false);
		setFont(new Font("Tahoma", Font.PLAIN,18));
		contentPane.add(expandir);
		
		lblResumen = new JLabel("");
		lblResumen.setForeground(Color.LIGHT_GRAY);
		lblResumen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblResumen.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		lblResumen.setBounds(10, 11, 133, 39);
		lblResumen.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(lblResumen);
		
		
		btn = new Botones[30];
		
		for (int i = 0; i < btn.length; i++) {
			btn[i]=new Botones(simbolos[i]);
			btn[i].setBounds(10+70*(i%6), 90+70*(i/6), 60, 60);
			contentPane.add(btn[i]);
			btn[i].addActionListener(this);
		}
		
		
	}
	
	public boolean siEsNum(String cad) {
		try {
			Float.parseFloat(cad);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void limpiarC() {
		cadenaCal.clear();
		lblMostrar.setText("");
		lblResumen.setText("");
		haypunto=false;
		haysimbolo=false;
	}
	
	public void borrarLast() {
		if (!cadenaCal.isEmpty()) { 	//si no está vacía hará algo
			int last=cadenaCal.size()-1;
			if (cadenaCal.get(last)==".") {
				haypunto=false;
			}
			cadenaCal.remove(last);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		Botones a = (Botones)e.getSource();
		
		if (a.isNumero()) {
			System.out.println("si");
			if (primerNum) {
				primerNum=false;
				lblMostrar.setText("");
			}
			cadenaCal.add(s);
			lblMostrar.setText(lblMostrar.getText() + "" + s);
			haysimbolo=false;
			
		} 
		else {
			if (s!= "=" && s!="C") {
				switch (s) {
				case "-":	//NUMERO NEGATIVO NO COMPLETADO
					cadenaCal.add(s);
					lblResumen.setText(lblResumen.getText() + lblMostrar.getText()+s); //no tiene que suceder si pones un número negativo hasta que se introduzca otro simbolo
					lblMostrar.setText(lblMostrar.getText()+s);
					haysimbolo=true;
					primerNum=true;
					break;
				case ".":
					if (!haypunto && !primerNum) {
						cadenaCal.add(s);
						lblMostrar.setText(lblMostrar.getText() + "" + s);
						haypunto=true;
					}
					
					break;
				case "\u2190":
					borrarLast();
					lblMostrar.setText(cadenaCal.toString());

					break;
				
					
				default: //cualquier simbolo sin caso
					if (!haysimbolo && !primerNum) {
						cadenaCal.add(s);
						lblResumen.setText(lblResumen.getText() + lblMostrar.getText()+s);
						lblMostrar.setText(s);
						haysimbolo=true;
						primerNum=true;
					}
					break;
				}
			}
			
		}
		
		if (s=="C") {
			limpiarC();
		}
	
		
	}
}


