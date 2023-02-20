package design.comboBox;


import javax.swing.JComboBox;


public class ComboBox<E> extends JComboBox<E> {
	
	public ComboBox() {
		
		setUI( new ComboSuggestionUI() );
		
	}
	
}
