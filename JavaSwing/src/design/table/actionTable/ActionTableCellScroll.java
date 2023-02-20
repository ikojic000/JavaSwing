package design.table.actionTable;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import design.scroll.ScrollBarCustom;


public class ActionTableCellScroll extends JScrollPane {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6340126815518775895L;
	private JTextArea txtPregled;
	private ScrollBarCustom scrollBarCustom;
	private boolean selected;
	
	public ActionTableCellScroll( Object obj , boolean selected ) {
		
		this.selected = selected;
		setOpaque( false );
		setBorder( BorderFactory.createEmptyBorder() );
		setBackground( getBackground() );
		getViewport().setBackground( Color.white );
		
		txtPregled = new JTextArea();
		txtPregled.setText( obj.toString() );
		txtPregled.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txtPregled.setSelectionColor( new Color( 189 , 239 , 230 ) );
		txtPregled.setCaretColor( new Color( 46 , 191 , 65 ) );
		txtPregled.setBackground( Color.white );
		txtPregled.setLineWrap( true );
		txtPregled.setWrapStyleWord( true );
		txtPregled.setEditable( false );
		
		setViewportView( txtPregled );
		
		scrollBarCustom = new ScrollBarCustom( 20 );
		setVerticalScrollBar( scrollBarCustom );
		setVerticalScrollBarPolicy( VERTICAL_SCROLLBAR_ALWAYS );
		
	}
	
	
	@Override
	protected void paintComponent( Graphics grphcs ) {
		
		int width = getWidth() + 5;
		int height = getHeight() - 4;
		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );
		g2.setColor( Color.WHITE );
		
		g2.fillRoundRect( -5 , 2 , width , height , 6 , 6 );
		
		if ( selected ) {
			
			g2.setColor( new Color( 46 , 191 , 165 ) );
			g2.setStroke( new BasicStroke( 2f ) );
			g2.drawRoundRect( -5 , 2 , width - 2 , height , 6 , 6 );
			
		}
		
		super.paintComponent( grphcs );
		
	}
	
}
