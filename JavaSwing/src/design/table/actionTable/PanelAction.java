package design.table.actionTable;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;


@SuppressWarnings( "serial" )
public class PanelAction extends CustomPanelAction {
	
	public PanelAction() {
		
		setBackground( new Color( 255 , 255 , 255 ) );
		
		initComponents();
		
	}
	
	
	public void initEvent( TableActionEvent event , int row ) {
		
		cmdEdit.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent ae ) {
				
				event.onEdit( row );
				
			}
			
		} );
		cmdDelete.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent ae ) {
				
				event.onDelete( row );
				
			}
			
		} );
		cmdView.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent ae ) {
				
				event.onView( row );
				
			}
			
		} );
		
	}
	
	
	private void initComponents() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 24 , 24 , 24 , 0 };
		gridBagLayout.rowHeights = new int[] { 24 , 0 };
		gridBagLayout.columnWeights = new double[] { 1.0 , 0.0 , 1.0 , Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0 , Double.MIN_VALUE };
		setLayout( gridBagLayout );
		
		cmdEdit = new ActionButton();
		cmdEdit.setFocusPainted( false );
		
		cmdEdit.setIcon( new ImageIcon( getClass().getResource( "/actionTable/edit.png" ) ) );
		GridBagConstraints gbc_cmdEdit = new GridBagConstraints();
		gbc_cmdEdit.fill = GridBagConstraints.VERTICAL;
		gbc_cmdEdit.anchor = GridBagConstraints.EAST;
		gbc_cmdEdit.insets = new Insets( 0 , 0 , 0 , 5 );
		gbc_cmdEdit.gridx = 0;
		gbc_cmdEdit.gridy = 0;
		add( cmdEdit , gbc_cmdEdit );
		cmdDelete = new ActionButton();
		cmdDelete.setFocusPainted( false );
		
		cmdDelete.setIcon( new ImageIcon( getClass().getResource( "/actionTable/delete.png" ) ) );
		GridBagConstraints gbc_cmdDelete = new GridBagConstraints();
		gbc_cmdDelete.fill = GridBagConstraints.VERTICAL;
		gbc_cmdDelete.insets = new Insets( 0 , 0 , 0 , 5 );
		gbc_cmdDelete.gridx = 1;
		gbc_cmdDelete.gridy = 0;
		add( cmdDelete , gbc_cmdDelete );
		cmdView = new ActionButton();
		cmdView.setFocusPainted( false );
		
		cmdView.setIcon( new ImageIcon( getClass().getResource( "/actionTable/view.png" ) ) );
		GridBagConstraints gbc_cmdView = new GridBagConstraints();
		gbc_cmdView.fill = GridBagConstraints.VERTICAL;
		gbc_cmdView.anchor = GridBagConstraints.WEST;
		gbc_cmdView.gridx = 2;
		gbc_cmdView.gridy = 0;
		add( cmdView , gbc_cmdView );
		
	}
	
	private ActionButton cmdDelete;
	private ActionButton cmdEdit;
	private ActionButton cmdView;
	
}
