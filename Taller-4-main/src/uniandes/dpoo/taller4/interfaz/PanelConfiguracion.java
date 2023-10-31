package uniandes.dpoo.taller4.interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class PanelConfiguracion extends JPanel implements ActionListener
{
	// ===============================================================
	// ATRIBUTOS
	// ===============================================================
	// Constantes asociadas a la dificultad
	public final static String FACIL = "2";
	public final static String MEDIO = "5";
	public final static String DIFICIL = "10";

	// Atributos asociados al mundo del problema por defecto
	private int valorDificultad = 2;
	private int valorTamanio = 3;

	// ===============================================================
	// CONSTRUCTOR
	// ===============================================================
	public PanelConfiguracion()
	{
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(0, 35));
		setBackground(new Color(42, 137, 224));

		JLabel tamanio = new JLabel("Tamaño:");
		tamanio.setForeground(Color.WHITE);
		JLabel dificultad = new JLabel("Dificultad:");
		dificultad.setForeground(Color.WHITE);

		String[] opciones =
		{ "3x3", "4x4", "5x5", "6x6", "7x7", "8x8", "9x9", "10x10" };
		JComboBox<String> listaTamanios = new JComboBox<String>(opciones);
		listaTamanios.setFont(listaTamanios.getFont().deriveFont(listaTamanios.getFont().getStyle() & ~Font.BOLD));
		listaTamanios.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				valorTamanio = Integer.valueOf(listaTamanios.getSelectedItem().toString().split("x")[0]);
			}
		});

		JRadioButton rbFacil = new JRadioButton("Fácil", true);
		rbFacil.addActionListener(this);
		rbFacil.setActionCommand(FACIL);
		rbFacil.setBackground(new Color(42, 137, 224));
		rbFacil.setForeground(Color.WHITE);
		rbFacil.setFont(rbFacil.getFont().deriveFont(rbFacil.getFont().getStyle() & ~Font.BOLD));

		JRadioButton rbMedio = new JRadioButton("Medio", true);
		rbMedio.addActionListener(this);
		rbMedio.setActionCommand(MEDIO);
		rbMedio.setBackground(new Color(42, 137, 224));
		rbMedio.setForeground(Color.WHITE);
		rbMedio.setFont(rbMedio.getFont().deriveFont(rbMedio.getFont().getStyle() & ~Font.BOLD));

		JRadioButton rbDificil = new JRadioButton("Difícil", true);
		rbDificil.addActionListener(this);
		rbDificil.setActionCommand(DIFICIL);
		rbDificil.setBackground(new Color(42, 137, 224));
		rbDificil.setForeground(Color.WHITE);
		rbDificil.setFont(rbDificil.getFont().deriveFont(rbDificil.getFont().getStyle() & ~Font.BOLD));

		ButtonGroup btnGp = new ButtonGroup();
		btnGp.add(rbFacil);
		btnGp.add(rbMedio);
		btnGp.add(rbDificil);

		add(tamanio);
		add(listaTamanios);
		add(dificultad);
		add(rbFacil);
		add(rbMedio);
		add(rbDificil);
	}

	// ===============================================================
	// MÉTODOS
	// ===============================================================
	public int getDificultad()
	{
		return valorDificultad;
	}

	public int getTamanio()
	{
		return valorTamanio;
	}

	public void actionPerformed(ActionEvent pEvento)
	{
		String comando = pEvento.getActionCommand();
		if (comando.equals(FACIL))
		{
			valorDificultad = Integer.parseInt(FACIL);
		} 
		else if (comando.equals(MEDIO))
		{
			valorDificultad = Integer.parseInt(MEDIO);
		} 
		else if (comando.equals(DIFICIL))
		{
			valorDificultad = Integer.parseInt(DIFICIL);
		}
	}

}
