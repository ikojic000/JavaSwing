package design.table.actionTable;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;


@SuppressWarnings( "serial" )
public class CustomPanelAction extends JPanel {
	
	public CustomPanelAction() {
		
		setOpaque( false );
		
	};
	
	
	@Override
	protected void paintComponent( Graphics g ) {
		
		super.paintComponent( g );
		Dimension arcs = new Dimension( 6 , 6 );
		int width = getWidth();
		int height = getHeight() - 1;
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );
		
		graphics.setColor( getBackground() );
		graphics.fillRoundRect( 0 , 2 , width - 5 , height - 4 , arcs.width , arcs.height );
		graphics.drawRoundRect( 0 , 2 , width - 5 , height - 4 , arcs.width , arcs.height );
		
	}
	
}
