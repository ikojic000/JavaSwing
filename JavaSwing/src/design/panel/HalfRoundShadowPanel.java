package design.panel;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import design.panel.shadow.ShadowRenderer;
import design.panel.shadow.ShadowType;


public class HalfRoundShadowPanel extends JPanel {
	
	private ShadowType shadowType = ShadowType.CENTER;
	private int shadowSize = 6;
	private float shadowOpacity = 0.5f;
	private Color shadowColor = Color.BLACK;
	private int cornerRadius = 15;
	
	/**
	 * @return the cornerRadius
	 */
	public int getCornerRadius() {
		
		return cornerRadius;
		
	}
	
	
	/**
	 * @param cornerRadius the cornerRadius to set
	 */
	public void setCornerRadius( int cornerRadius ) {
		
		this.cornerRadius = cornerRadius;
		
	}
	
	
	/**
	 * @return the shadowType
	 */
	public ShadowType getShadowType() {
		
		return shadowType;
		
	}
	
	
	/**
	 * @param shadowType the shadowType to set
	 */
	public void setShadowType( ShadowType shadowType ) {
		
		this.shadowType = shadowType;
		
	}
	
	
	/**
	 * @return the shadowSize
	 */
	public int getShadowSize() {
		
		return shadowSize;
		
	}
	
	
	/**
	 * @param shadowSize the shadowSize to set
	 */
	public void setShadowSize( int shadowSize ) {
		
		this.shadowSize = shadowSize;
		
	}
	
	
	/**
	 * @return the shadowOpacity
	 */
	public float getShadowOpacity() {
		
		return shadowOpacity;
		
	}
	
	
	/**
	 * @param shadowOpacity the shadowOpacity to set
	 */
	public void setShadowOpacity( float shadowOpacity ) {
		
		this.shadowOpacity = shadowOpacity;
		
	}
	
	
	/**
	 * @return the shadowColor
	 */
	public Color getShadowColor() {
		
		return shadowColor;
		
	}
	
	
	/**
	 * @param shadowColor the shadowColor to set
	 */
	public void setShadowColor( Color shadowColor ) {
		
		this.shadowColor = shadowColor;
		
	}
	
	
	public HalfRoundShadowPanel( int radius ) {
		
		super();
		cornerRadius = radius;
		setOpaque( false );
		
	}
	
	
	@Override
	public void paintComponent( Graphics g ) {
		
		createShadow( g );
		super.paintComponent( g );
		
	}
	
	
	private void createShadow( Graphics g ) {
		
		Graphics2D g2d = (Graphics2D) g;
		int size = shadowSize * 2;
		int x = 0;
		int y = 0;
		int width = getWidth() - size;
		int height = getHeight() - size;
		Dimension arcs = new Dimension( cornerRadius , cornerRadius );
		
		if ( shadowType == ShadowType.TOP ) {
			
			x = shadowSize;
			y = size;
			
		} else if ( shadowType == ShadowType.BOT ) {
			
			x = shadowSize;
			y = 0;
			
		} else if ( shadowType == ShadowType.TOP_LEFT ) {
			
			x = size;
			y = size;
			
		} else if ( shadowType == ShadowType.TOP_RIGHT ) {
			
			x = 0;
			y = size;
			
		} else if ( shadowType == ShadowType.BOT_LEFT ) {
			
			x = size;
			y = 0;
			
		} else if ( shadowType == ShadowType.BOT_RIGHT ) {
			
			x = 0;
			y = 0;
			
		} else {
			
			// Center
			x = shadowSize;
			y = shadowSize;
			
		}
		
		BufferedImage img = new BufferedImage( width , height , BufferedImage.TYPE_INT_ARGB );
		Graphics2D g2 = img.createGraphics();
		g2.setColor( getBackground() );
		g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );
		g2.fillRoundRect( 0 , 0 , width - 1 , height - 1 , arcs.width , arcs.height ); // paint background
		g2.setColor( getBackground() );
		g2.fillRect( 0 , 0 , width / 2 , height - 1 );
		
		// Shadow
		
		ShadowRenderer render = new ShadowRenderer( shadowSize , shadowOpacity , shadowColor );
		g2d.drawImage( render.createShadow( img ) , 0 , 0 , null );
		g2d.drawImage( img , x , y , null );
		
	}
	
}
