package design.table.actionTable;


import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


@SuppressWarnings( "serial" )
public class TableActionCellRender extends DefaultTableCellRenderer {
	
	@SuppressWarnings( "exports" )
	@Override
	public Component getTableCellRendererComponent( JTable jtable , Object o , boolean isSeleted , boolean bln1 ,
													int row , int column ) {
		
//		Component com = super.getTableCellRendererComponent( jtable , o , isSeleted , bln1 , row , column );
		PanelAction action = new PanelAction();
		
		if ( isSeleted == false ) {
			
			action.setBackground( Color.WHITE );
			
		}
		
		return action;
		
	}
	
}
