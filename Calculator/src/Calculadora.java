import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Calculadora extends JFrame implements ActionListener{
	private static final long serialVersionUID = 4781031710737270239L;
	
	private Botones[] btn;
	private String[] simbolos= {"C", "\u221A", "/", "\u2190", "(", ")", "7", "8", "9", "*","s3","s4", "4", "5", "6", "+", "s5", "s6", "1", "2", "3", "-","s7", "s8","±", "0", ".", "=", "%", "s10"};
	private Operaciones op = new Operaciones();
	private ArrayList<String> cadenaCal = new ArrayList<String>();
	private ArrayList<Float> cadenaNum = new ArrayList<Float>();
	
	private StringBuilder strMostrar  = new StringBuilder(), strResumen = new StringBuilder();
	private boolean haysimbolo=false, haypunto=false, primerNum=true;
	private int pAb, pCe;
	
	private JPanel contentPane;
	private JLabel lblMostrar;
	private JTextField lblResumen;

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
		this.setResizable(false); // BLOQUEAR EL CAMBIO DE RESOLUCION DE LA VENTANA.
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
		lblMostrar.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.gray), new EmptyBorder(10,10,10,10)));
		lblMostrar.setBounds(10, 11, 270, 68);
		contentPane.add(lblMostrar);
		
		JButton expandir = new JButton("\u21F1");
		expandir.addActionListener(new ActionListener() {
			boolean exp = false;
			public void actionPerformed(ActionEvent e) {
				if (exp) {
					setBounds(getX(), getY(), 305, 478);
					lblMostrar.setBounds(10, 11, 270, 68);
					lblResumen.setBounds(12, 13, 133, 35);
					expandir.setText("\u21F1");
					exp=false;
				}
				else {
					setBounds(getX(), getY(), 445, 478);
					lblResumen.setBounds(12, 13, 233, 35);
					lblMostrar.setBounds(10, 11, 410, 68);
					expandir.setText("\u21F2");
					exp=true;
				}
				
			}
		});
		expandir.setFont(new Font("SansSerif", Font.PLAIN, 15));
		expandir.setBackground(new Color(238, 238, 238));
		expandir.setBorder(null);
		expandir.setBounds(11, 48, 30, 30);
		expandir.setMargin(new Insets(0,0,0,0));
		expandir.setFocusable(false);
		setFont(new Font("Tahoma", Font.PLAIN,18));
		contentPane.add(expandir);
		
		lblResumen = new JTextField("");
		lblResumen.setEditable(false);
		lblResumen.setForeground(Color.LIGHT_GRAY);
		lblResumen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblResumen.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		lblResumen.setBounds(12, 13, 133, 35);
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
	
	public void borrarLast() {
		if (!strMostrar.toString().isEmpty()) { 	//Si no está vacía hará algo
			int tama = strMostrar.length()-1;
			if (strMostrar.charAt(tama)=='.') {
				haypunto=false;
			}
/*			else if (!siEsNum(""+lblMostrar.getText())) { //ARREGLAR EL PODER BORRAR SIMBOLOS
				cadenaCal.remove(cadenaCal.size()-1);
			}
*/			strMostrar.deleteCharAt(tama);
		}
		else {
			primerNum=true;
		}
	}
	
	public void actualizarlbl(JLabel lbl) {
		if (lbl.equals(lblMostrar)) {
			lbl.setText(strMostrar.toString());
		}
		else {
			lbl.setText(strResumen.toString());
		}
		
	}
	
	public void actualizarlbl(JTextField lbl) {
		if (lbl.equals(lblMostrar.toString())) {
			lbl.setText(strMostrar.toString());
		}
		else {
			lbl.setText(strResumen.toString());
		}
		
	}
	
	public void pulsarSimbolos(String s) {
		if (s!="=") {
			cadenaCal.add(s);
		}
		if (s!="(" && s!=")") {
			cadenaNum.add(Float.parseFloat(lblMostrar.getText()));
			strResumen.append(strMostrar.toString() + s);
			strMostrar.delete(0, strMostrar.length());
			actualizarlbl(lblResumen);
		}
		else {
			lblMostrar.setText(lblMostrar.getText()+s);
		}
	}
	
	public void quitarPirmerCero() {
		if (strMostrar.toString().equals("0")) {
			strMostrar.deleteCharAt(0);
			actualizarlbl(lblMostrar);
		}
	}
	
	public char lastCharMostrar(StringBuilder label) {
		char last=label.charAt(label.length()-1);
		return last;
	}
	
	public void limpiarC() {
		pAb=0;
		pCe=0;
		cadenaCal.clear();
		lblMostrar.setText("");
		lblResumen.setText("");
		strMostrar.delete(0, strMostrar.length());
		strResumen.delete(0, strResumen.length());
		cadenaCal.clear();
		cadenaNum.clear();
		haypunto=false;
		haysimbolo=false;
		primerNum=true;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Botones a = (Botones)e.getSource();
		String s = a.getSimbolo();
		
		if (a.isNumero()) {
			if (primerNum) {
				primerNum=false;
				lblMostrar.setText("");
			}
			quitarPirmerCero();
			strMostrar.append(s);
			actualizarlbl(lblMostrar);
			haysimbolo=false;
			
			
		} 
		else {
			if (s!= "=" && s!="C") {
				switch (s) {
				case "±":	//NUMERO NEGATIVO NO COMPLETADO
					cadenaCal.add(s);
					lblResumen.setText(lblResumen.getText() + lblMostrar.getText()+"-"); //no tiene que suceder si pones un nÃºmero negativo hasta que se introduzca otro simbolo
					lblMostrar.setText(lblMostrar.getText()+"-");
					haysimbolo=true;
					primerNum=true;
					break;
				case ".": //DONE
					if (!haypunto && !primerNum) {
						strMostrar.append(s);
						lblMostrar.setText(lblMostrar.getText() + "" + s);
						haypunto=true;
					}
					else if (primerNum) {
						strMostrar.append("0"+s);
						actualizarlbl(lblMostrar);
						haypunto=true;
						primerNum=false;
					}
					break;
/*				case "(":	//parentesis pero arreglar la eliminación del primer parentesis al poner un número
					pulsarSimbolos(s);
					pAb++;
					break;
				case ")":
					if (pAb>pCe && siEsNum(""+lastCharMostrar(strMostrar))) {
						pulsarSimbolos(s);
						pCe++;
					}
					break;
*/				case "\u2190":
					borrarLast();
					actualizarlbl(lblMostrar);
					break;
					
				default: //cualquier simbolo sin caso
					int tama=strMostrar.length()-1;
					char last = strMostrar.charAt(tama);
					if (!haysimbolo && !primerNum && last!='.') {
						if (lblMostrar.getText()!="") { //RESUELVE "BUG"
							pulsarSimbolos(s);
							//lblResumen.setText(lblResumen.getText() + lblMostrar.getText()+s);
							haypunto=false;
							lblMostrar.setText(s);
							haysimbolo=true;
							primerNum=true;
						}

					}
					break;
				}
			}
			else if (s=="C") {
				limpiarC();
			}
			else {
				pulsarSimbolos(s);
				System.out.println(cadenaNum.toString() + " " + cadenaCal.toString());
				
			}
		}
		
	}
}