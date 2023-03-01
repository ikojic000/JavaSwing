package design.messageDialog;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import design.button.ButtonShadow;
import raven.glasspanepopup.GlassPanePopup;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class MessageInfo extends JPanel {
	
	private ButtonShadow cmdCancel;
	private JLabel lblTitle;
	private JTextPane txt;
	
	public MessageInfo() {
		
		initComponents();
		setOpaque( false );
		txt.setBackground( new Color( 0 , 0 , 0 , 0 ) );
		txt.setSelectionColor( new Color( 48 , 170 , 63 , 200 ) );
		txt.setOpaque( false );
		
	}
	
	
	@Override
	protected void paintComponent( Graphics grphcs ) {
		
		Graphics2D g2 = (Graphics2D) grphcs.create();
		g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );
		g2.setColor( getBackground() );
		g2.fill( new RoundRectangle2D.Double( 0 , 0 , getWidth() , getHeight() , 20 , 20 ) );
		g2.dispose();
		super.paintComponent( grphcs );
		
	}
	
	
	private void initComponents() {
		
		lblTitle = new JLabel();
		txt = new JTextPane();
		txt.setFont( new Font( "Century Gothic" , Font.PLAIN , 13 ) );
		cmdCancel = new ButtonShadow();
		cmdCancel.setMinimumSize( new Dimension( 200 , 45 ) );
		cmdCancel.setMaximumSize( new Dimension( 200 , 45 ) );
		cmdCancel.setPreferredSize( new Dimension( 200 , 45 ) );
		
		setBackground( new Color( 244 , 244 , 249 ) );
		setBorder( BorderFactory.createEmptyBorder( 25 , 25 , 25 , 25 ) );
		
		lblTitle.setText( "" );
		lblTitle.setFont( new Font( "Century Gothic" , Font.BOLD , 18 ) );
		lblTitle.setForeground( new Color( 146 , 20 , 12 ) );
		lblTitle.setHorizontalAlignment( SwingConstants.CENTER );
		
		txt.setForeground( new Color( 66 , 76 , 76 ) );
		txt.setText( " " );
		
		cmdCancel.setBackground( new Color( 146 , 20 , 12 ) );
		cmdCancel.setForeground( new Color( 244 , 244 , 249 ) );
		cmdCancel.setForegroundColorIN( cmdCancel.getForeground() );
		cmdCancel.setForegroundColorOUT( cmdCancel.getForeground() );
		cmdCancel.setText( "Zatvori" );
		cmdCancel.setRound( 15 );
		cmdCancel.setFocusPainted( false );
		cmdCancel.setFont( new Font( "Century Gothic" , Font.BOLD , 15 ) );
		cmdCancel.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent evt ) {
				
				cmdCancelActionPerformed( evt );
				
			}
			
		} );
		
		GroupLayout layout = new GroupLayout( this );
		layout.setHorizontalGroup( layout.createParallelGroup( Alignment.LEADING ).addGroup( layout
				.createSequentialGroup().addContainerGap()
				.addGroup( layout.createParallelGroup( Alignment.TRAILING )
						.addGroup( layout.createSequentialGroup().addComponent( lblTitle ).addGap( 0 , 390 ,
								Short.MAX_VALUE ) )
						.addComponent( txt , GroupLayout.PREFERRED_SIZE , 0 , Short.MAX_VALUE ).addComponent(
								cmdCancel , GroupLayout.PREFERRED_SIZE , 110 , GroupLayout.PREFERRED_SIZE ) ) ) );
		layout.setVerticalGroup( layout.createParallelGroup( Alignment.LEADING )
				.addGroup( layout.createSequentialGroup().addContainerGap().addComponent( lblTitle )
						.addPreferredGap( ComponentPlacement.UNRELATED )
						.addComponent( txt , GroupLayout.PREFERRED_SIZE , GroupLayout.DEFAULT_SIZE ,
								GroupLayout.PREFERRED_SIZE )
						.addPreferredGap( ComponentPlacement.UNRELATED ).addComponent( cmdCancel ,
								GroupLayout.PREFERRED_SIZE , GroupLayout.DEFAULT_SIZE , GroupLayout.PREFERRED_SIZE )
						.addContainerGap() ) );
		this.setLayout( layout );
		
	}
	
	
	private void cmdCancelActionPerformed( ActionEvent evt ) {
		
		GlassPanePopup.closePopupLast();
		
	}
	
	
	public void setMessageTitle( String title ) {
		
		lblTitle.setText( title );
		
	}
	
	
	public void setMessageText( String text ) {
		
		txt.setText( text );
		
	}
	
}
