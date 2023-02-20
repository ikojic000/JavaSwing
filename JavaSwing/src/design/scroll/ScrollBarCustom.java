package design.scroll;


import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollBar;


public class ScrollBarCustom extends JScrollBar {
	
	private int thumbSize = 40;
	
	/**
	 * @return the thumbSize
	 */
	public int getThumbSize() {
		
		return thumbSize;
		
	}
	
	
	/**
	 * @param thumbSize the thumbSize to set
	 */
	public void setThumbSize( int thumbSize ) {
		
		this.thumbSize = thumbSize;
		
	}
	
	
	public ScrollBarCustom( int thumbSize ) {
		
		setUI( new ModernScrollBarUI( thumbSize ) );
		
		setPreferredSize( new Dimension( 8 , 8 ) );
		setForeground( new Color( 46 , 191 , 165 ) );
		setBackground( Color.WHITE );
		
	}
	
}
