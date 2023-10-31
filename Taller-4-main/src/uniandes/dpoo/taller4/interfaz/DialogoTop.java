package uniandes.dpoo.taller4.interfaz;

import java.awt.*;
import java.io.File;
import java.util.Collection;

import javax.swing.*;

import uniandes.dpoo.taller4.modelo.*;

@SuppressWarnings("serial")
public class DialogoTop extends JDialog
{
	// ===============================================================
	// ATRIBUTOS
	// ===============================================================
	private Top10 top10;
	private String[] display;
	
	// ===============================================================
	// CONSTRUCTOR
	// ===============================================================
	public DialogoTop(Top10 top10)
	{
		setModal(true);
		setTitle("Top 10");
		setResizable(false);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
	    setBounds(440,10,410,210);
	    setVisible(true);
	    
	    String[] imprimible = {};
	    this.display = imprimible;	
	    this.top10 = top10;
	}
	
	// ===============================================================
	// MÉTODOS
	// ===============================================================
	public void generarListaTop10()
	{
		for (RegistroTop10 i : top10.darRegistros())
		{
			System.out.println(i.toString());
		}
		
	}
}
