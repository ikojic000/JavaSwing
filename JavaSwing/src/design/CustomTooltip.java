package design;


import java.awt.Color;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JToolTip;


public class CustomTooltip extends JToolTip {
	
	public CustomTooltip( JComponent component ) {
		
		super();
		
		setComponent( component );
		setBackground( Color.white );
		setForeground( new Color( 121 , 118 , 118 ) );
		setFont( new Font( "Century Gothic" , Font.PLAIN , 14 ) );
		setBorder( null );
		setOpaque( false );
		
	}
	
}
