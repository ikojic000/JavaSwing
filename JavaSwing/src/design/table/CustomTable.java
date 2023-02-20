package design.table;


import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import design.table.actionTable.PanelAction;
import design.table.actionTable.TableActionCellEditor;
import design.table.actionTable.TableActionEvent;


public class CustomTable extends JTable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 324611443046347438L;
	private boolean isFirstBold = false;
	private boolean isLastAction = false;
	private TableActionEvent actionEvent;
	
	public CustomTable() {
		
		setBackground( new Color( 244 , 244 , 249 ) );
		setRowHeight( 40 );
		setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		getTableHeader().setDefaultRenderer( new DefaultTableCellRenderer() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -6857759854827520041L;
			
			@Override
			public Component getTableCellRendererComponent( JTable jtable , Object o , boolean bln , boolean bln1 ,
															int i , int i1 ) {
				
				return new TableCell( o );
				
			}
			
		} );
		
	}
	
	
	@SuppressWarnings( "exports" )
	@Override
	public Component prepareRenderer( TableCellRenderer tcr , int i , int i1 ) {
		
		TableCell.CellType celLType = TableCell.CellType.CENTER;
		
		if ( i1 == 0 ) {
			
			if ( isFirstBold ) {
				
				isFirstBold = true;
				
			}
			
			celLType = TableCell.CellType.LEFT;
			
		} else if ( i1 == getColumnCount() - 1 ) {
			
			celLType = TableCell.CellType.RIGHT;
			
		}
		
		if ( isLastAction ) {
			
			if ( i1 == getColumnCount() - 2 ) {
				
				celLType = TableCell.CellType.RIGHT;
				
			} else if ( i1 == getColumnCount() - 1 ) {
				
				return new PanelAction();
				
			}
			
			getColumnModel().getColumn( getColumnCount() - 1 )
					.setCellEditor( new TableActionCellEditor( actionEvent ) );
			
		}
		
		return new TableCell( getValueAt( i , i1 ) , isCellSelected( i , i1 ) , celLType , isFirstBold );
		
	}
	
	
	public boolean isFirstBold() {
		
		return isFirstBold;
		
	}
	
	
	public void setFirstBold( boolean isFirstBold ) {
		
		this.isFirstBold = isFirstBold;
		
	}
	
	
	public boolean isLastAction() {
		
		return isLastAction;
		
	}
	
	
	public void setLastAction( boolean isLastAction ) {
		
		this.isLastAction = isLastAction;
		
	}
	
	
	public void setActionEvent( TableActionEvent actionEvent ) {
		
		this.actionEvent = actionEvent;
		
	}
	
}
