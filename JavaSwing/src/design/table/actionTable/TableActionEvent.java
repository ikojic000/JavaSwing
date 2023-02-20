package design.table.actionTable;


public interface TableActionEvent {
	
	public void onEdit( int row );
	
	public void onDelete( int row );
	
	public void onView( int row );
	
}
