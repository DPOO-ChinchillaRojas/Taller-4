package uniandes.dpoo.taller4.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.dpoo.taller4.modelo.*;

@SuppressWarnings("serial")
public class PanelPrincipal extends JPanel implements ActionListener
{
	// ===============================================================
	// ATRIBUTOS
	// ===============================================================
	// Constantes asociadas a eventos
	public final static String NUEVO = "NUEVO";
	public final static String REINICIAR = "REINICIAR";
	public final static String TOP10 = "TOP-10";
	public final static String CAMBIAR_JUGADOR = "CAMBIAR JUGADOR";

	// Atributos asociados a botones de interacción
	private JButton btnNuevo;
	private JButton btnReiniciar;
	private JButton btnTop10;
	private JButton btnCambiarJugador;

	// Atributos asociados a subpaneles dentro del panel
	private PanelTablero panelTablero;
	private PanelConfiguracion panelConfiguracion;
	private PanelDetalles panelDetalles;
	private InterfazLightsOut padre;

	// ===============================================================
	// CONSTRUCTOR
	// ===============================================================
	public PanelPrincipal(InterfazLightsOut padre)
	{
		// Establecer el layout general del panel central de la interfaz
		setLayout(new BorderLayout());
		this.padre = padre;

		// Establecer los subpaneles dentro del panel para visualización adecuada
		JPanel arriba = new JPanel();
		arriba.setPreferredSize(new Dimension(150, 150));
		JPanel abajo = new JPanel();
		abajo.setPreferredSize(new Dimension(150, 150));
		JPanel centro = new JPanel();
		centro.setLayout(new GridLayout(4, 1, 0, 10));

		// Definir las propiedades de los botones
		this.btnNuevo = new JButton(NUEVO);
		btnNuevo.addActionListener(this);
		btnNuevo.setActionCommand(NUEVO);
		btnNuevo.setBackground(new Color(42, 137, 224));
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setFont(btnNuevo.getFont().deriveFont(btnNuevo.getFont().getStyle() & ~Font.BOLD));
		centro.add(btnNuevo);

		this.btnReiniciar = new JButton(REINICIAR);
		btnReiniciar.addActionListener(this);
		btnReiniciar.setActionCommand(REINICIAR);
		btnReiniciar.setBackground(new Color(42, 137, 224));
		btnReiniciar.setForeground(Color.WHITE);
		btnReiniciar.setFont(btnReiniciar.getFont().deriveFont(btnReiniciar.getFont().getStyle() & ~Font.BOLD));
		centro.add(btnReiniciar);

		this.btnTop10 = new JButton(TOP10);
		btnTop10.addActionListener(this);
		btnTop10.setActionCommand(TOP10);
		btnTop10.setBackground(new Color(42, 137, 224));
		btnTop10.setForeground(Color.WHITE);
		btnTop10.setFont(btnTop10.getFont().deriveFont(btnTop10.getFont().getStyle() & ~Font.BOLD));
		centro.add(btnTop10);

		this.btnCambiarJugador = new JButton(CAMBIAR_JUGADOR);
		btnCambiarJugador.addActionListener(this);
		btnCambiarJugador.setActionCommand(CAMBIAR_JUGADOR);
		btnCambiarJugador.setBackground(new Color(42, 137, 224));
		btnCambiarJugador.setForeground(Color.WHITE);
		btnCambiarJugador
				.setFont(btnCambiarJugador.getFont().deriveFont(btnCambiarJugador.getFont().getStyle() & ~Font.BOLD));
		centro.add(btnCambiarJugador);

		// Definir los action command de cada botón
		btnNuevo.setActionCommand(NUEVO);
		btnReiniciar.setActionCommand(REINICIAR);
		btnTop10.setActionCommand(TOP10);
		btnCambiarJugador.setActionCommand(CAMBIAR_JUGADOR);

		// Establecer los paneles que conforman el panel principal
		this.panelConfiguracion = new PanelConfiguracion();
		add(panelConfiguracion, BorderLayout.NORTH);

		this.panelTablero = new PanelTablero(this, 3); 
		this.panelTablero.setEnabled(false);
		JPanel panelMenu = new JPanel();
		panelMenu.setLayout(new BorderLayout());
		panelMenu.setBackground(Color.WHITE);

		this.panelDetalles = new PanelDetalles();
		add(panelDetalles, BorderLayout.SOUTH);

		// Agregar los elementos al panel de navegación por el menu
		panelMenu.add(arriba, BorderLayout.NORTH);
		panelMenu.add(abajo, BorderLayout.SOUTH);
		panelMenu.add(centro, BorderLayout.CENTER);

		// Agregar los subpaneles al panel principal
		add(panelTablero, BorderLayout.CENTER);
		add(panelMenu, BorderLayout.EAST);
	}

	// ===============================================================
	// MÉTODOS
	// ===============================================================
	public int getDimensiones()
	{
		return panelConfiguracion.getTamanio();
	}

	public int getDificultad()
	{
		return panelConfiguracion.getDificultad();
	}

	public PanelTablero getPanelTablero()
	{
		return panelTablero;
	}

	public PanelDetalles getPanelDetalles()
	{
		return panelDetalles;
	}

	public void actualizar()
	{
		PanelDetalles detalles = getPanelDetalles();
		detalles.setJugadas(panelTablero.getTablero().darJugadas());
		boolean finJuego = panelTablero.estaCompletado();

		if (finJuego)
		{
			String jugadas = getPanelDetalles().getJugadas();
			int puntaje = panelTablero.getTablero().calcularPuntaje();

			JOptionPane.showMessageDialog(this, "¡Ganaste en " + jugadas + " jugadas!", "Fin del juego",
					JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(this, "¡Has obtenido " + Integer.toString(puntaje) + " puntos!", "Puntaje",
					JOptionPane.INFORMATION_MESSAGE);
			if (panelDetalles.getJugador().length() > 0)
			{
				boolean entra = padre.puntajeTop10(puntaje);
				if (entra == true)
				{
					RegistroTop10 registro = new RegistroTop10(panelDetalles.getJugador(), puntaje);
					padre.actualizarTop10(registro);
				}
			}
			int reiniciar = JOptionPane.showConfirmDialog(this, "¿Quieres iniciar un nuevo juego?", "Volver a jugar",
					JOptionPane.YES_NO_OPTION);

			if (reiniciar == JOptionPane.YES_OPTION)
			{
				remove(panelTablero);
				panelTablero = new PanelTablero(this, 3);
				add(panelTablero, BorderLayout.CENTER);
				updateUI();
				this.panelTablero.setEnabled(false);
			}
			else
			{
				this.setEnabled(false);
				this.panelTablero.setEnabled(!finJuego);
				JOptionPane.showMessageDialog(this, "Puede cerrar la ventana", "Fin del juego",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}


	public void actionPerformed(ActionEvent pEvento)
	{
		String comando = pEvento.getActionCommand();
		if (comando.equals(NUEVO))
		{
			// Definir las constantes del panel
			int nuevoTamanio = getDimensiones();
			int nuevaDificultad = getDificultad();
			
			this.panelTablero.setEnabled(true);
			remove(panelTablero);
			panelTablero = new PanelTablero(this, nuevoTamanio);
			add(panelTablero, BorderLayout.CENTER);
			updateUI();
			Tablero tablero = getPanelTablero().getTablero();
			tablero.desordenar(nuevaDificultad);
			panelDetalles.setJugadas(0);
		} 
		else if (comando.equals(REINICIAR))
		{
			Tablero tablero = panelTablero.getTablero();
			tablero.reiniciar();
			panelDetalles.setJugadas(0);
		} 
		else if (comando.equals(TOP10))
		{
			Top10 top10 = padre.getTop10();
			DialogoTop dialog = new DialogoTop(top10);
			dialog.generarListaTop10();
			dialog.setLocationRelativeTo(padre);
			
		} 
		else if (comando.equals(CAMBIAR_JUGADOR))
		{
			int cambiar = JOptionPane.showConfirmDialog(this, "¿Quieres cambiar de jugador?", "Cambiar Jugador",
					JOptionPane.YES_NO_OPTION);

			if (cambiar == JOptionPane.YES_OPTION)
			{
				String strJugador = JOptionPane.showInputDialog(this, "Introduce el nombre del jugador:",
						"Cambiar Jugador", JOptionPane.QUESTION_MESSAGE);
				if (strJugador != null)
				{
					panelDetalles.setJugador(strJugador);
					Tablero tablero = panelTablero.getTablero();
					tablero.reiniciar();
				}
			}
		}
	}
}
