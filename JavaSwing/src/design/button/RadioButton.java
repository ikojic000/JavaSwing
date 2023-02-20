package design.button;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JRadioButton;


public class RadioButton extends JRadioButton {
	
	public RadioButton() {
		
		setOpaque( false );
		setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
		setBackground( new Color( 46 , 191 , 165 ) );
		setFocusPainted( false );
		setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		
	}
	
	
	@Override
	public void paint( Graphics grphcs ) {
		
		super.paint( grphcs );
		
		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );
		int ly = (getHeight() - 16) / 2;
		
		if ( isSelected() ) {
			
			if ( isEnabled() ) {
				
				g2.setColor( getBackground() );
				
			} else {
				
				g2.setColor( Color.GRAY );
				
			}
			
			g2.fillOval( 1 , ly , 16 , 16 );
			g2.setColor( Color.WHITE );
			g2.fillOval( 2 , ly + 1 , 14 , 14 );
			
			if ( isEnabled() ) {
				
				g2.setColor( getBackground() );
				
			} else {
				
				g2.setColor( Color.GRAY );
				
			}
			
			g2.fillOval( 5 , ly + 4 , 8 , 8 );
			
		} else {
			
			if ( isFocusOwner() ) {
				
				g2.setColor( getBackground() );
				
			} else {
				
				g2.setColor( Color.GRAY );
				
			}
			
			g2.fillOval( 1 , ly , 16 , 16 );
			g2.setColor( Color.WHITE );
			g2.fillOval( 2 , ly + 1 , 14 , 14 );
			
		}
		
		g2.dispose();
		
	}
	
}
