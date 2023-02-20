package design.messageDialog;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import design.button.ButtonShadow;
import design.panel.glass.Glass;
import design.txtInput.TextField;


public class VerifyCode extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9090225194150742992L;
	private final JFrame frame;
	private Animator animator;
	private Glass glass;
	private boolean show;
	private Background background1;
	private ButtonShadow btnCancel;
	private ButtonShadow btnOK;
	private JLabel lbIcon;
	private JLabel lbTitle;
	private TextField txt;
	
	public VerifyCode( JFrame frame ) {
		
		super( frame , true );
		this.frame = frame;
		initComponents();
		init();
		
	}
	
	
	private void init() {
		
		setBackground( new Color( 0 , 0 , 0 , 0 ) );
		setDefaultCloseOperation( JDialog.DO_NOTHING_ON_CLOSE );
		addWindowListener( new WindowAdapter() {
			
			@Override
			public void windowClosing( WindowEvent e ) {
				
				closeMessage();
				
			}
			
		} );
		animator = new Animator( 150 , new TimingTargetAdapter() {
			
			@Override
			public void timingEvent( float fraction ) {
				
				float f = show ? fraction : 1f - fraction;
				glass.setAlpha( f - f * 0.3f );
				setOpacity( f );
				
			}
			
			
			@Override
			public void end() {
				
				if ( show == false ) {
					
					dispose();
					glass.setVisible( false );
					
				}
				
			}
			
		} );
		animator.setResolution( 0 );
		animator.setAcceleration( .5f );
		animator.setDeceleration( .5f );
		setOpacity( 0f );
		glass = new Glass();
		
	}
	
	
	private void startAnimator( boolean show ) {
		
		if ( animator.isRunning() ) {
			
			float f = animator.getTimingFraction();
			animator.stop();
			animator.setStartFraction( 1f - f );
			
		} else {
			
			animator.setStartFraction( 0f );
			
		}
		
		this.show = show;
		animator.start();
		
	}
	
	
	public void showMessage() {
		
		frame.setGlassPane( glass );
		glass.setVisible( true );
		setLocationRelativeTo( frame );
		startAnimator( true );
		setVisible( true );
		
	}
	
	
	private void initComponents() {
		
		background1 = new Background();
		btnCancel = new ButtonShadow();
		btnOK = new ButtonShadow();
		lbIcon = new JLabel();
		lbTitle = new JLabel();
		txt = new TextField();
		
		setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		setUndecorated( true );
		
		background1.setBorder( BorderFactory.createEmptyBorder( 3 , 3 , 3 , 3 ) );
		
		btnCancel.setBackground( new Color( 146 , 20 , 12 ) );
		btnCancel.setForeground( new Color( 244 , 244 , 249 ) );
		btnCancel.setText( "Cancel" );
		btnCancel.setRound( 15 );
		btnCancel.setFocusPainted( false );
		btnCancel.setFont( new Font( "Century Gothic" , Font.BOLD , 15 ) );
		btnCancel.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent evt ) {
				
				btnCancelActionPerformed( evt );
				
			}
			
		} );
		
		btnOK.setRound( 15 );
		btnOK.setForeground( new Color( 44 , 51 , 51 ) );
		btnOK.setFocusPainted( false );
		btnOK.setText( "OK" );
		btnOK.setFont( new Font( "Century Gothic" , Font.BOLD , 15 ) );
		btnOK.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent evt ) {
				
				btnOKActionPerformed( evt );
				
			}
			
		} );
		
		lbIcon.setHorizontalAlignment( SwingConstants.CENTER );
		lbIcon.setIcon( new ImageIcon( getClass().getResource( "/design/messageDialog/msgDia.png" ) ) );
		
		lbTitle.setFont( new Font( "Century Gothic" , Font.BOLD , 18 ) );
		lbTitle.setForeground( new Color( 146 , 20 , 12 ) );
		lbTitle.setHorizontalAlignment( SwingConstants.CENTER );
		lbTitle.setText( "Kod" );
		
		txt.setFont( new Font( "Century Gothic" , Font.PLAIN , 15 ) );
		txt.setForeground( new Color( 44 , 51 , 51 ) );
		txt.setBackground( new Color( 244 , 244 , 249 ) );
		txt.setLabelText( "Unesite kod iz mail-a" );
		txt.addKeyListener( new KeyAdapter() {
			
			@Override
			public void keyReleased( KeyEvent e ) {
				
				checkCode( txt.getText() );
				
			}
			
		} );
		
		txt.addFocusListener( new FocusAdapter() {
			
			@Override
			public void focusLost( FocusEvent e ) {
				
				if ( !txt.getText().equals( "" ) ) {
					
					if ( !checkCode( txt.getText() ) ) {
						
						txt.requestFocus();
						txt.selectAll();
						txt.setHelperText( "Unesite valjan kod ..." );
						
					} else {
						
						txt.setHelperText( "" );
						
					}
					
				} else {
					
					txt.setText( "" );
					
				}
				
			}
			
		} );
		
		GroupLayout background1Layout = new GroupLayout( background1 );
		background1.setLayout( background1Layout );
		background1Layout.setHorizontalGroup( background1Layout.createParallelGroup( GroupLayout.Alignment.LEADING )
				.addGroup( background1Layout.createSequentialGroup()
						.addComponent( btnCancel , GroupLayout.DEFAULT_SIZE , 195 , Short.MAX_VALUE )
						.addGap( 3 , 3 , 3 ).addComponent( btnOK , GroupLayout.DEFAULT_SIZE , 196 , Short.MAX_VALUE ) )
				.addComponent( lbIcon , GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE , Short.MAX_VALUE )
				.addComponent( lbTitle , GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE , Short.MAX_VALUE )
				.addComponent( txt ) );
		background1Layout.setVerticalGroup( background1Layout.createParallelGroup( GroupLayout.Alignment.LEADING )
				.addGroup( GroupLayout.Alignment.TRAILING , background1Layout.createSequentialGroup()
						.addComponent( lbIcon , GroupLayout.PREFERRED_SIZE , 74 , GroupLayout.PREFERRED_SIZE )
						.addGap( 20 , 20 , 20 ).addComponent( lbTitle )
						.addPreferredGap( LayoutStyle.ComponentPlacement.UNRELATED )
						.addComponent( txt , GroupLayout.PREFERRED_SIZE , 60 , GroupLayout.PREFERRED_SIZE )
						.addGap( 18 , 18 , 18 )
						.addGroup( background1Layout.createParallelGroup( GroupLayout.Alignment.BASELINE )
								.addComponent( btnCancel , GroupLayout.PREFERRED_SIZE , 50 ,
										GroupLayout.PREFERRED_SIZE )
								.addComponent( btnOK , GroupLayout.PREFERRED_SIZE , 50 ,
										GroupLayout.PREFERRED_SIZE ) ) ) );
		
		GroupLayout layout = new GroupLayout( getContentPane() );
		getContentPane().setLayout( layout );
		layout.setHorizontalGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING )
				.addComponent( background1 , GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE , Short.MAX_VALUE ) );
		layout.setVerticalGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addComponent( background1 ,
				GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE , Short.MAX_VALUE ) );
		
		pack();
		
	}
	
	
	private void btnCancelActionPerformed( ActionEvent evt ) {
		
		closeMessage();
		
	}
	
	
	private void btnOKActionPerformed( ActionEvent evt ) {
		
		if ( checkCode( txt.getText() ) ) {
			
			System.out.println( txt.getText().trim() );
			closeMessage();
			
		} else {
			
			txt.requestFocus();
			txt.selectAll();
			txt.setHelperText( "Unesite valjan kod ..." );
			
		}
		
	}
	
	
	public void closeMessage() {
		
		startAnimator( false );
		
	}
	
	
	private boolean checkCode( String code ) {
		
		boolean isCodeValid = true;
		String regex = "[0-9]+";
		Pattern pattern = Pattern.compile( regex );
		Matcher matcher = pattern.matcher( code );
		
		if ( !matcher.matches() ) {
			
			isCodeValid = false;
			
		}
		
		if ( !isCodeValid ) {
			
			txt.setHelperText( "Kod mora sadr�avati samo brojeve .. " );
			
		} else {
			
			txt.setHelperText( null );
			
		}
		
		return isCodeValid;
		
	}
	
}
