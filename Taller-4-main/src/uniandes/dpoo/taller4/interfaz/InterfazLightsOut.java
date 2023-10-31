package uniandes.dpoo.taller4.interfaz;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;

import uniandes.dpoo.taller4.modelo.*;

@SuppressWarnings("serial")
public class InterfazLightsOut extends JFrame
{
	// ===============================================================
	// ATRIBUTOS
	// ===============================================================
	// Atributos asociados al mundo del problema
	private Tablero tableroJuego;
	private static Top10 top10;
	private File archivoTop;

	// Atributos asociados a elementos gráficos
	private PanelPrincipal panelPrincipal;
	
	// ===============================================================
	// CONSTRUCTOR
	// ===============================================================
	public InterfazLightsOut()
	{
		setTitle("LightsOut"); // Título de la ventana
		setSize(600, 500); // Tamaño de 500px x 500px
		setResizable(false); // Evitar que se le cambie el tamaño (*puede cambiarse)
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Cierre de la ventana habitual
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		setLayout(new BorderLayout()); // Establece el layout como BorderLayout

		this.panelPrincipal = new PanelPrincipal(this);
		add(panelPrincipal, BorderLayout.CENTER);

		// Creación del mundo del problema
		this.tableroJuego = new Tablero(panelPrincipal.getDimensiones());
		this.top10 = new Top10();
		this.archivoTop = new File("data/top10.csv");
		top10.cargarRecords(archivoTop);
		
		// Código para la persistencia de los puntajes
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				try 
				{
					salvarTop10();
				} 
				catch (FileNotFoundException | UnsupportedEncodingException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}
	
	// ===============================================================
	// MÉTODOS
	// ===============================================================	
	public static void main(String[] args)
	{
		InterfazLightsOut ventana = new InterfazLightsOut();		
		ventana.setVisible(true);
		FlatLightLaf.install();
	}
	
	public Tablero getTablero() 
	{
		return tableroJuego;
	}
	
	public Top10 getTop10()
	{
		return top10;
	}
	
	public boolean puntajeTop10(int puntaje)
	{
		return top10.esTop10(puntaje);
	}
	
	public void actualizarTop10(RegistroTop10 registro)
	{
		top10.agregarRegistro(registro.darNombre(), registro.darPuntos());
	}
	
	public void salvarTop10() throws FileNotFoundException, UnsupportedEncodingException
	{
		top10.salvarRecords(archivoTop);
	}
	
}
