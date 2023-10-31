package uniandes.dpoo.taller4.interfaz;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class PanelDetalles extends JPanel
{
	// ===============================================================
	// ATRIBUTOS
	// ===============================================================
	private JTextField txtJugadas;
	private JTextField txtJugador;

	// ===============================================================
	// CONSTRUCTOR
	// ===============================================================
	public PanelDetalles()
	{
		setLayout(new GridLayout(1, 4, 5, 0));

		JLabel jugadas = new JLabel("Jugadas:");
		Font fJugadas = jugadas.getFont();
		jugadas.setFont(fJugadas.deriveFont(fJugadas.getStyle() & ~Font.BOLD));
		this.txtJugadas = new JTextField();

		JLabel jugador = new JLabel("Jugador:");
		Font fJugador = jugadas.getFont();
		jugador.setFont(fJugador.deriveFont(fJugador.getStyle() & ~Font.BOLD));
		this.txtJugador = new JTextField();

		txtJugadas.setEditable(false);
		txtJugador.setEditable(false);
		
		add(jugadas);
		add(txtJugadas);
		add(jugador);
		add(txtJugador);
	}

	// ===============================================================
	// MÉTODOS
	// ===============================================================
	public String getJugadas()
	{
		return txtJugadas.getText();
	}
	
	public String getJugador()
	{
		return txtJugador.getText();
	}
	
	public void setJugador(String nombre)
	{
		String nombreJugador = nombre;
		txtJugador.setText(nombreJugador);
	}
	
	public void setJugadas(int jugadas)
	{
		txtJugadas.setText(Integer.toString(jugadas));
	}

}
