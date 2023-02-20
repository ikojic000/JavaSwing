package design.table.actionTable;


import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;


public class TableActionCellEditor extends DefaultCellEditor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3237877697846425792L;
	private TableActionEvent event;
	
	public TableActionCellEditor( TableActionEvent event ) {
		
		super( new JCheckBox() );
		this.event = event;
		
	}
	
	
	@SuppressWarnings( "exports" )
	@Override
	public Component getTableCellEditorComponent( JTable jtable , Object o , boolean bln , int row , int column ) {
		
		PanelAction action = new PanelAction();
		action.initEvent( event , row );
		action.setBackground( Color.white );
		return action;
		
	}
	
}
