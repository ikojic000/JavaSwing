package app;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import design.button.Button;
import design.button.ButtonCustom;
import design.button.ButtonCustom.ButtonStyle;
import design.button.ButtonShadow;
import design.button.ButtonTable;
import design.button.RadioButton;
import design.comboBox.ComboBox;
import design.notification.Notification;
import design.notification.Notification.Location;
import design.notification.Notification.NotificationType;
import design.panel.CustomSidePanel;
import design.panel.HalfRoundShadowPanel;
import design.panel.ImageAvatar;
import design.panel.RoundedPanel;
import design.panel.RoundedShadowPanel;
import design.txtInput.PasswordField;
import design.txtInput.TextArea;
import design.txtInput.TextAreaScroll;
import design.txtInput.TextField;


public class TestFrame extends JFrame {
	
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main( String[] args ) {
		
		EventQueue.invokeLater( new Runnable() {
			
			public void run() {
				
				try {
					
					TestFrame frame = new TestFrame();
					frame.setVisible( true );
					
				} catch ( Exception e ) {
					
					e.printStackTrace();
					
				}
				
			}
			
		} );
		
	}
	
	
	/**
	 * Create the frame.
	 */
	/**
	 * 
	 */
	public TestFrame() {
		
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setBounds( 100 , 100 , 1500 , 1000 );
		setLocationRelativeTo( null );
		contentPane = new JPanel();
		contentPane.setBackground( new Color( 255 , 255 , 255 ) );
		contentPane.setBorder( new EmptyBorder( 5 , 5 , 5 , 5 ) );
		
		setContentPane( contentPane );
		contentPane.setLayout( null );
		
		Notification notification = new Notification( this , NotificationType.SUCCESS , Location.TOP_CENTER ,
				"messageBody" , "" );
		
		RoundedPanel roundedPanel = new RoundedPanel( 50 );
		roundedPanel.setOpaque( false );
		roundedPanel.setBackground( new Color( 128 , 255 , 128 ) );
		roundedPanel.setForeground( new Color( 128 , 255 , 128 ) );
		roundedPanel.setBounds( 10 , 11 , 300 , 200 );
		contentPane.add( roundedPanel );
		roundedPanel.setLayout( null );
		
		JLabel lblNewLabel = new JLabel( "RoundedPanel" );
		lblNewLabel.setHorizontalTextPosition( SwingConstants.CENTER );
		lblNewLabel.setHorizontalAlignment( SwingConstants.CENTER );
		lblNewLabel.setBounds( 10 , 11 , 280 , 178 );
		roundedPanel.add( lblNewLabel );
		
		ButtonShadow btnshdwButtonshadow = new ButtonShadow();
		btnshdwButtonshadow.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent e ) {
				
				notification.setLbMessageText( "Sucess msg body" );
				notification.setType( NotificationType.SUCCESS );
				notification.showNotification();
				
			}
			
		} );
		btnshdwButtonshadow.setBackground( new Color( 192 , 192 , 192 ) );
		btnshdwButtonshadow.setRound( 20 );
		btnshdwButtonshadow.setText( "ButtonShadow" );
		btnshdwButtonshadow.setBounds( 10 , 229 , 221 , 53 );
		contentPane.add( btnshdwButtonshadow );
		
		RoundedShadowPanel roundedShadowPanel = new RoundedShadowPanel( 50 );
		roundedShadowPanel.setBackground( new Color( 128 , 255 , 255 ) );
		roundedShadowPanel.setBounds( 320 , 11 , 300 , 200 );
		contentPane.add( roundedShadowPanel );
		roundedShadowPanel.setLayout( null );
		
		JLabel lblRoundedshadowpanel = new JLabel( "RoundedShadowPanel" );
		lblRoundedshadowpanel.setBounds( 10 , 11 , 280 , 178 );
		lblRoundedshadowpanel.setHorizontalTextPosition( SwingConstants.CENTER );
		lblRoundedshadowpanel.setHorizontalAlignment( SwingConstants.CENTER );
		roundedShadowPanel.add( lblRoundedshadowpanel );
		
		HalfRoundShadowPanel halfRoundShadowPanel = new HalfRoundShadowPanel( 50 );
		halfRoundShadowPanel.setBackground( new Color( 255 , 128 , 192 ) );
		halfRoundShadowPanel.setBounds( 630 , 11 , 300 , 200 );
		contentPane.add( halfRoundShadowPanel );
		halfRoundShadowPanel.setLayout( null );
		
		JLabel lblHalfroundshadowpanel = new JLabel( "HalfRoundShadowPanel" );
		lblHalfroundshadowpanel.setHorizontalTextPosition( SwingConstants.CENTER );
		lblHalfroundshadowpanel.setHorizontalAlignment( SwingConstants.CENTER );
		lblHalfroundshadowpanel.setBounds( 10 , 11 , 280 , 178 );
		halfRoundShadowPanel.add( lblHalfroundshadowpanel );
		
		CustomSidePanel customSidePanel = new CustomSidePanel();
		customSidePanel.setBackground( new Color( 255 , 128 , 64 ) );
		customSidePanel.setBounds( 1174 , 11 , 300 , 939 );
		contentPane.add( customSidePanel );
		customSidePanel.setLayout( null );
		
		JLabel lblCustomsidepanelUseful = new JLabel( "CustomSidePanel - useful for menus" );
		lblCustomsidepanelUseful.setHorizontalTextPosition( SwingConstants.CENTER );
		lblCustomsidepanelUseful.setHorizontalAlignment( SwingConstants.CENTER );
		lblCustomsidepanelUseful.setBounds( 10 , 132 , 280 , 300 );
		customSidePanel.add( lblCustomsidepanelUseful );
		
		ImageAvatar imageAvatar = new ImageAvatar();
		imageAvatar.setBackground( new Color( 255 , 0 , 0 ) );
		imageAvatar.setForeground( new Color( 128 , 128 , 128 ) );
		imageAvatar.setBorderSize( 2 );
		imageAvatar.setIcon( new ImageIcon( this.getClass().getResource( "/MPDLogo_Transparent.png" ) ) );
		imageAvatar.setBounds( 940 , 11 , 153 , 153 );
		contentPane.add( imageAvatar );
		
		Button btnButton = new Button();
		btnButton.setText( "Button" );
		btnButton.setBackground( new Color( 128 , 255 , 255 ) );
		btnButton.setBounds( 10 , 293 , 221 , 50 );
		contentPane.add( btnButton );
		
		ButtonCustom btncstmButtoncustom = new ButtonCustom();
		btncstmButtoncustom.setText( "ButtonCustom - PRIMARY" );
		btncstmButtoncustom.setBounds( 10 , 354 , 221 , 50 );
		contentPane.add( btncstmButtoncustom );
		
		ButtonCustom btncstmButtoncustomSecondary = new ButtonCustom();
		btncstmButtoncustomSecondary.setStyle( ButtonStyle.SECONDARY );
		btncstmButtoncustomSecondary.setText( "ButtonCustom - SECONDARY" );
		btncstmButtoncustomSecondary.setBounds( 10 , 415 , 221 , 50 );
		contentPane.add( btncstmButtoncustomSecondary );
		
		ButtonCustom btncstmButtoncustomDestructive = new ButtonCustom();
		btncstmButtoncustomDestructive.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent e ) {
				
				notification.setLbMessageText( "warning msg body" );
				notification.setType( NotificationType.WARNING );
				notification.showNotification();
				
			}
			
		} );
		btncstmButtoncustomDestructive.setStyle( ButtonStyle.DESTRUCTIVE );
		btncstmButtoncustomDestructive.setText( "ButtonCustom - DESTRUCTIVE" );
		btncstmButtoncustomDestructive.setBounds( 10 , 476 , 221 , 50 );
		contentPane.add( btncstmButtoncustomDestructive );
		
		RadioButton rdbtnRadiobutton = new RadioButton();
		rdbtnRadiobutton.setText( "RadioButton" );
		rdbtnRadiobutton.setBounds( 10 , 533 , 221 , 21 );
		contentPane.add( rdbtnRadiobutton );
		
		ButtonTable btntblButtontable = new ButtonTable();
		btntblButtontable.setIcon( new ImageIcon( getClass().getResource( "/actionTable/delete.png" ) ) );
		btntblButtontable.setBounds( 10 , 561 , 35 , 35 );
		contentPane.add( btntblButtontable );
		
		JLabel lblNewLabel_1 = new JLabel( "ButtonTable" );
		lblNewLabel_1.setBounds( 145 , 561 , 86 , 35 );
		contentPane.add( lblNewLabel_1 );
		
		ButtonTable btntblButtontable_1 = new ButtonTable();
		btntblButtontable_1.setIcon( new ImageIcon( getClass().getResource( "/actionTable/edit.png" ) ) );
		btntblButtontable_1.setBounds( 55 , 561 , 35 , 35 );
		contentPane.add( btntblButtontable_1 );
		
		ButtonTable btntblButtontable_2 = new ButtonTable();
		btntblButtontable_2.setIcon( new ImageIcon( getClass().getResource( "/actionTable/view.png" ) ) );
		btntblButtontable_2.setBounds( 100 , 561 , 35 , 35 );
		contentPane.add( btntblButtontable_2 );
		
		ComboBox comboBox = new ComboBox();
		comboBox.setBounds( 10 , 607 , 141 , 35 );
		contentPane.add( comboBox );
		
		JLabel lblNewLabel_1_1 = new JLabel( "ComboBox" );
		lblNewLabel_1_1.setBounds( 161 , 607 , 70 , 35 );
		contentPane.add( lblNewLabel_1_1 );
		
		TextField txtfldTextfield = new TextField();
		txtfldTextfield.setLabelText( "LabelText" );
		txtfldTextfield.setText( "TextField" );
		txtfldTextfield.setHelperText( "Helper Text" );
		txtfldTextfield.setBounds( 10 , 653 , 221 , 80 );
		contentPane.add( txtfldTextfield );
		
		PasswordField passwordField = new PasswordField();
		passwordField.setShowAndHide( true );
		passwordField.setBounds( 10 , 744 , 221 , 80 );
		contentPane.add( passwordField );
		
		TextAreaScroll textAreaScroll = new TextAreaScroll();
		textAreaScroll.setBackground( new Color( 255 , 255 , 255 ) );
		textAreaScroll.setBounds( 10 , 822 , 221 , 128 );
		contentPane.add( textAreaScroll );
		
		TextArea textArea = new TextArea();
		textAreaScroll.setViewportView( textArea );
		
	}
	
}
