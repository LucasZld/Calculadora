import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;

public class Botones extends JButton {
	private static final long serialVersionUID = 3371129195730517565L;

	private String simbolo;
	private float num;
	private boolean numero;
	
	public Botones(String text) {
		super(text);
		simbolo = text;
		try {
			num = Float.parseFloat(text);
			numero = true;
		} catch (Exception e) {
			numero = false;
		}
		setFont(new Font("Tahoma", Font.PLAIN,18));
		setMargin(new Insets(0,0,0,0));
		setBackground(Color.darkGray);
		setForeground(Color.WHITE);
		setFocusable(false);
		setColor();
	}
	
	public String getSimbolo() {
		return simbolo;
	}

	public boolean isNumero() {
		return numero;
	}
	
	public float getNum() {
		return num;
	}
	
	public void setColor() {
		if (!isNumero()) {
			switch (simbolo) {
			case "=":
				setBackground(Color.darkGray);
				break;
			case "C":
				setBackground(Color.red);
				setForeground(Color.WHITE);
				break;
			default:
				setBackground(Color.WHITE);
				setForeground(Color.BLACK);
				break;
			}
		}
	}
}
