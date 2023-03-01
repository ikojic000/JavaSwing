package design.notification;


import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import design.panel.shadow.ShadowRenderer;


public class Notification extends JComponent {
	
	private JButton cmdClose;
	private JLabel lbIcon;
	private JLabel lblTitle;
	private String title = "Uspijeh";
	private JLabel lbMessageText;
	private JPanel panel;
	private JDialog dialog;
	private Animator animator;
	private final Frame fram;
	private boolean showing;
	private Thread thread;
	private int animate = 10;
	private BufferedImage imageShadow;
	private int shadowSize = 6;
	private NotificationType type;
	private Location location;
	
	public Notification( Frame fram , NotificationType type , Location location , String message , String lblTitle ) {
		
		this.fram = fram;
		this.type = type;
		this.location = location;
		initComponents();
		init( message , lblTitle );
		initAnimator();
		
	}
	
	
	private void init( String message , String title ) {
		
		setBackground( Color.WHITE );
		dialog = new JDialog( fram );
		dialog.setUndecorated( true );
		dialog.setFocusableWindowState( false );
		dialog.setBackground( new Color( 0 , 0 , 0 , 0 ) );
		dialog.getContentPane().add( this );
		dialog.setSize( getPreferredSize() );
		
		if ( type == NotificationType.SUCCESS ) {
			
			if ( title== "" || title == null ) {
				
				lblTitle.setText( "Uspijeh" );
				
			} else {
				
				lblTitle.setText( title );
				
			}
			
		} else if ( type == NotificationType.INFO ) {
			
			lblTitle.setText( "Info" );
			
		} else if ( type == NotificationType.WARNING ) {
			
			lblTitle.setText( "Upozorenje" );
			
		}
		
		if ( type == NotificationType.SUCCESS ) {
			
			lbIcon.setIcon( new ImageIcon( getClass().getResource( "/notification/sucess.png" ) ) );
			
//			lbMessage.setText(lblMessageStr);
		} else if ( type == NotificationType.INFO ) {
			
			lbIcon.setIcon( new ImageIcon( getClass().getResource( "/notification/info.png" ) ) );
			
//			lbMessage.setText("Info");
		} else {
			
			lbIcon.setIcon( new ImageIcon( getClass().getResource( "/notification/warning.png" ) ) );
			
//			lbMessage.setText("Upozorenje");
		}
		
		lbMessageText.setText( message );
		
	}
	
	
	private void initAnimator() {
		
		TimingTarget target = new TimingTargetAdapter() {
			
			private int x;
			private int top;
			private boolean top_to_bot;
			
			@Override
			public void timingEvent( float fraction ) {
				
				if ( showing ) {
					
					float alpha = 1f - fraction;
					int y = (int) ((1f - fraction) * animate);
					
					if ( top_to_bot ) {
						
						dialog.setLocation( x , top + y );
						
					} else {
						
						dialog.setLocation( x , top - y );
						
					}
					
					dialog.setOpacity( alpha );
					
				} else {
					
					float alpha = fraction;
					int y = (int) (fraction * animate);
					
					if ( top_to_bot ) {
						
						dialog.setLocation( x , top + y );
						
					} else {
						
						dialog.setLocation( x , top - y );
						
					}
					
					dialog.setOpacity( alpha );
					
				}
				
			}
			
			
			@Override
			public void begin() {
				
				if ( !showing ) {
					
					dialog.setOpacity( 0f );
					int margin = 10;
					int y = 0;
					
					if ( location == Location.TOP_CENTER ) {
						
						x = fram.getX() + ((fram.getWidth() - dialog.getWidth()) / 2);
						y = fram.getY() + 20;
						top_to_bot = true;
						
					} else if ( location == Location.TOP_RIGHT ) {
						
						x = fram.getX() + fram.getWidth() - dialog.getWidth() - margin;
						y = fram.getY();
						top_to_bot = true;
						
					} else if ( location == Location.TOP_LEFT ) {
						
						x = fram.getX() + margin;
						y = fram.getY();
						top_to_bot = true;
						
					} else if ( location == Location.BOTTOM_CENTER ) {
						
						x = fram.getX() + ((fram.getWidth() - dialog.getWidth()) / 2);
						y = fram.getY() + fram.getHeight() - dialog.getHeight();
						top_to_bot = false;
						
					} else if ( location == Location.BOTTOM_RIGHT ) {
						
						x = fram.getX() + fram.getWidth() - dialog.getWidth() - margin;
						y = fram.getY() + fram.getHeight() - dialog.getHeight();
						top_to_bot = false;
						
					} else if ( location == Location.BOTTOM_LEFT ) {
						
						x = fram.getX() + margin;
						y = fram.getY() + fram.getHeight() - dialog.getHeight();
						top_to_bot = false;
						
					} else {
						
						x = fram.getX() + ((fram.getWidth() - dialog.getWidth()) / 2);
						y = fram.getY() + ((fram.getHeight() - dialog.getHeight()) / 2);
						top_to_bot = true;
						
					}
					
					top = y;
					dialog.setLocation( x , y );
					dialog.setVisible( true );
					
				}
				
			}
			
			
			@Override
			public void end() {
				
				showing = !showing;
				
				if ( showing ) {
					
					thread = new Thread( new Runnable() {
						
						@Override
						public void run() {
							
							sleep();
							closeNotification();
							
						}
						
					} );
					thread.start();
					
				} else {
					
					dialog.dispose();
					
				}
				
			}
			
		};
		animator = new Animator( 500 , target );
		animator.setResolution( 5 );
		
	}
	
	
	public void showNotification() {
		
		animator.start();
		
	}
	
	
	private void closeNotification() {
		
		if ( thread != null && thread.isAlive() ) {
			
			thread.interrupt();
			
		}
		
		if ( animator.isRunning() ) {
			
			if ( !showing ) {
				
				animator.stop();
				showing = true;
				animator.start();
				
			}
			
		} else {
			
			showing = true;
			animator.start();
			
		}
		
	}
	
	
	private void sleep() {
		
		try {
			
			Thread.sleep( 3500 );
			
		} catch ( InterruptedException e ) {
			
		}
		
	}
	
	
	@Override
	public void paint( Graphics grphcs ) {
		
		Graphics2D g2 = (Graphics2D) grphcs.create();
		g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );
		g2.setColor( getBackground() );
		g2.drawImage( imageShadow , 0 , 0 , null );
		int x = shadowSize;
		int y = shadowSize;
		int width = getWidth() - shadowSize * 2;
		int height = getHeight() - shadowSize * 2;
		g2.fillRect( x , y , width , height );
		
		if ( type == NotificationType.SUCCESS ) {
			
			g2.setColor( new Color( 18 , 163 , 24 ) );
			
		} else if ( type == NotificationType.INFO ) {
			
			g2.setColor( new Color( 28 , 139 , 206 ) );
			
		} else {
			
			g2.setColor( new Color( 241 , 196 , 15 ) );
			
		}
		
		g2.fillRect( 6 , 5 , 5 , getHeight() - shadowSize * 2 + 1 );
		g2.dispose();
		super.paint( grphcs );
		
	}
	
	
	@Override
	public void setBounds( int i , int i1 , int i2 , int i3 ) {
		
		super.setBounds( i , i1 , i2 , i3 );
		createImageShadow();
		
	}
	
	
	private void createImageShadow() {
		
		imageShadow = new BufferedImage( getWidth() , getHeight() , BufferedImage.TYPE_INT_ARGB );
		Graphics2D g2 = imageShadow.createGraphics();
		g2.drawImage( createShadow() , 0 , 0 , null );
		g2.dispose();
		
	}
	
	
	private BufferedImage createShadow() {
		
		BufferedImage img = new BufferedImage( getWidth() - shadowSize * 2 , getHeight() - shadowSize * 2 ,
				BufferedImage.TYPE_INT_ARGB );
		Graphics2D g2 = img.createGraphics();
		g2.fillRect( 0 , 0 , img.getWidth() , img.getHeight() );
		g2.dispose();
		return new ShadowRenderer( shadowSize , 0.3f , new Color( 100 , 100 , 100 ) ).createShadow( img );
		
	}
	
	
	private void initComponents() {
		
		lbIcon = new JLabel();
		panel = new JPanel();
		lblTitle = new JLabel();
		lbMessageText = new JLabel();
		cmdClose = new JButton();
		
		lbIcon.setHorizontalAlignment( SwingConstants.CENTER );
		lbIcon.setIcon( new ImageIcon( this.getClass().getResource( "/notification/sucess.png" ) ) );
		
		panel.setOpaque( false );
		
		lblTitle.setFont( new Font( "Century Gothic" , Font.BOLD , 15 ) );
		lblTitle.setForeground( new Color( 38 , 38 , 38 ) );
		lblTitle.setText( "Message" );
		
		lbMessageText.setForeground( new Color( 127 , 127 , 127 ) );
		lbMessageText.setFont( new Font( "Century Gothic" , Font.PLAIN , 14 ) );
		lbMessageText.setText( "Message Text" );
		
		GroupLayout panelLayout = new GroupLayout( panel );
		panel.setLayout( panelLayout );
		panelLayout.setHorizontalGroup( panelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
				.addGroup( panelLayout.createSequentialGroup().addContainerGap()
						.addGroup( panelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
								.addComponent( lblTitle ).addComponent( lbMessageText ) )
						.addContainerGap( 217 , Short.MAX_VALUE ) ) );
		panelLayout.setVerticalGroup( panelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
				.addGroup( panelLayout.createSequentialGroup().addContainerGap().addComponent( lblTitle )
						.addGap( 3 , 3 , 3 ).addComponent( lbMessageText ).addContainerGap() ) );
		
		cmdClose.setIcon( new ImageIcon( this.getClass().getResource( "/notification/close.png" ) ) ); // NOI18N
		cmdClose.setBorder( null );
		cmdClose.setContentAreaFilled( false );
		cmdClose.setFocusable( false );
		cmdClose.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent evt ) {
				
				cmdCloseActionPerformed( evt );
				
			}
			
		} );
		
		GroupLayout layout = new GroupLayout( this );
		this.setLayout( layout );
		layout.setHorizontalGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING )
				.addGroup( layout.createSequentialGroup().addGap( 20 , 20 , 20 ).addComponent( lbIcon )
						.addGap( 10 , 10 , 10 )
						.addComponent( panel , GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE , Short.MAX_VALUE )
						.addPreferredGap( LayoutStyle.ComponentPlacement.RELATED ).addComponent( cmdClose )
						.addGap( 15 , 15 , 15 ) ) );
		layout.setVerticalGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING ).addGroup( layout
				.createSequentialGroup().addGap( 10 , 10 , 10 )
				.addGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING )
						.addComponent( cmdClose , GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE ,
								Short.MAX_VALUE )
						.addComponent( panel , GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE , Short.MAX_VALUE )
						.addComponent( lbIcon , GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE ,
								Short.MAX_VALUE ) )
				.addGap( 10 , 10 , 10 ) ) );
		
	}
	
	
	private void cmdCloseActionPerformed( ActionEvent evt ) {
		
		closeNotification();
		
	}
	
	
	public void setLblTitle( String title ) {
		
		this.title = title;
		this.lblTitle.setText( title );
		
	}
	
	
	
	/**
	 * @param lbMessageText the lbMessageText to set
	 */
	public void setLbMessageText( String lbMessageText ) {
		
		this.lbMessageText.setText( lbMessageText );;
		
	}


	/**
	 * @param type the type to set
	 */
	public void setType( NotificationType type ) {
		
		this.type = type;
		
	}
	
	
	/**
	 * @param location the location to set
	 */
	public void setLocation( Location location ) {
		
		this.location = location;
		
	}
	
	public static enum NotificationType {
							 SUCCESS , INFO , WARNING
	}
	
	public static enum Location {
								 TOP_CENTER , TOP_RIGHT , TOP_LEFT , BOTTOM_CENTER , BOTTOM_RIGHT , BOTTOM_LEFT , CENTER
	}
	
}
