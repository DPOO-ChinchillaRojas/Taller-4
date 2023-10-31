package uniandes.dpoo.taller4.interfaz;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import uniandes.dpoo.taller4.modelo.*;

@SuppressWarnings("serial")
public class PanelTablero extends JPanel implements MouseListener
{
	// ===============================================================
	// ATRIBUTOS
	// ===============================================================
	private Tablero tableroDeJuego;
	private Image imagenLuz;
	private boolean completado;
	private PanelPrincipal padre;

	// ===============================================================
	// CONSTRUCTOR
	// ===============================================================
	public PanelTablero(PanelPrincipal padre, int tamanioTablero)
	{
		setLayout(new BorderLayout());
		this.addMouseListener(this);
		this.tableroDeJuego = new Tablero(tamanioTablero);
		try
		{
			this.imagenLuz = ImageIO.read(new File("data/luz.png"));
		} catch (IOException e)
		{
		}
		this.completado = false;
		this.padre = padre;
	}

	// ===============================================================
	// MÉTODOS
	// ===============================================================
	public Tablero getTablero()
	{
		return tableroDeJuego;
	}
	
	public boolean estaCompletado()
	{
		if (tableroDeJuego.tableroIluminado() == true)
		{
			completado = true;
		}
		return completado;
	}
	
	public int getJugadas()
	{
		return tableroDeJuego.darJugadas();
	}
	
	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		// g2d.setFont(getFont().deriveFont(9f));
		// Recorrido de una matriz como en IP - primero filas y luego columnas
		int contadorFilas = 0;
		for (boolean[] i : tableroDeJuego.darTablero())
		{
			int contadorColumnas = 0;
			for (boolean j : i)
			{
				double a = getWidth() / tableroDeJuego.darTablero().length;
				double b = getHeight() / tableroDeJuego.darTablero().length;
				g2d.setColor(Color.BLACK);
				RoundRectangle2D.Double rect = new RoundRectangle2D.Double((contadorColumnas * a) + 1,
						(contadorFilas * b) + 1, a - 1, b - 1, 5, 5);
				g2d.draw(rect);
				if (j == true)
				{
					g2d.setPaint(new GradientPaint((int) (contadorColumnas * a) + 1, (int) (contadorFilas * b) + 1,
							Color.YELLOW, (int) ((contadorColumnas * a) + 1 + a), (int) ((contadorFilas * b) + 1 + b),
							Color.WHITE));
					g2d.fill(rect);
				} else
				{
					g2d.setPaint(new GradientPaint((int) (contadorColumnas * a) + 1, (int) (contadorFilas * b) + 1,
							Color.BLACK, (int) ((contadorColumnas * a) + 1 + a), (int) ((contadorFilas * b) + 1 + b),
							Color.DARK_GRAY));
					;
					g2d.fill(rect);
				}
				g2d.drawImage(imagenLuz, (int) Math.round((contadorColumnas * a) + a / 6),
						(int) Math.round((contadorFilas * b) + b / 6), (int) (a / 1.5), (int) (b / 1.5), this);
				contadorColumnas++;
			}
			contadorFilas++;
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (!isEnabled())
		{
			return ;
		}
		int click_x = e.getX();
		int click_y = e.getY();
		int[] casilla = convertirCoordenadasACasilla(click_x, click_y);
		tableroDeJuego.jugar(casilla[0], casilla[1]);
		repaint();
		padre.actualizar();
	}

	private int[] convertirCoordenadasACasilla(int x, int y)
	{
		int ladoTablero = tableroDeJuego.darTablero().length;
		int altoPanelTablero = getHeight();
		int anchoPanelTablero = getWidth();
		int altoCasilla = altoPanelTablero / ladoTablero;
		int anchoCasilla = anchoPanelTablero / ladoTablero;
		int fila = (int) (y / altoCasilla);
		int columna = (int) (x / anchoCasilla);
		return new int[] {fila, columna};
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// No realiza nada
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// No realiza nada
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// No realiza nada
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// No realiza nada
	}
}