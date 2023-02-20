package design.panel;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

import design.panel.shadow.ShadowType;


public class CustomSidePanel extends JPanel {
	
	private ShadowType shadowType = ShadowType.CENTER;
	private int shadowSize = 6;
	private float shadowOpacity = 0.5f;
	private Color shadowColor = Color.BLACK;
	private int cornerRadius = 25;
	
	public CustomSidePanel() {
		
		init();
		
	}
	
	
	private void init() {
		
		setOpaque( false );
		setBackground( new Color( 245 , 245 , 245 ) );
		setForeground( new Color( 118 , 118 , 118 ) );
		
	}
	
	
	@Override
	protected void paintComponent( Graphics g ) {
		
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );
		int x = 0;
		int y = 40;
		int width = getWidth();
		int height = getHeight();
		int iconSpace = 3;
		int totalIconSpace = iconSpace * 2;
		int iconSize = y * 3;
		int iconX = (width - (iconSize + totalIconSpace)) / 2;
		int iconY = 0;
		Dimension arcs = new Dimension( 20 , 20 );
		
		Area area = new Area( new RoundRectangle2D.Double( x , y , width , height - y , arcs.width , arcs.height ) );
		area.subtract( new Area( new Ellipse2D.Double( iconX , iconY - iconSpace , iconSize + totalIconSpace ,
				iconSize + totalIconSpace ) ) );
		area.add( new Area( new Ellipse2D.Double( iconX + iconSpace , 0 , iconSize , iconSize ) ) );
		g2d.setColor( getBackground() );
		g2d.fill( area );
		g2d.dispose();
		super.paintComponent( g );
		
	}
	
}
