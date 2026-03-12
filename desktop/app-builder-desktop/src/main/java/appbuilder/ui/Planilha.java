package appbuilder.ui;

import javax.swing.table.AbstractTableModel;

public class Planilha extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private static char[] letras = new char[] {
		'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G' , 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
		'P' , 'Q' , 'R' , 'S' , 'T' , 'U' , 'V' , 'W', 'X', 'Y', 'Z'			
	};
	
	
	@Override
	public String getColumnName(int columnIndex) {
		String colName = null;
		
		if (columnIndex < 26) {
			colName	= String.valueOf(letras[columnIndex]);
		} else {
			int result  = (int)(columnIndex / 26);
			int resto	= columnIndex % 26;
			
			colName	= resto > 0 ?
					  String.valueOf(letras[resto]) + result :
					  String.valueOf(letras[0]) + result;
		}		
		return colName;
	}	
	
	@Override
	public int getRowCount() {
		return 5000;
	}

	@Override
	public int getColumnCount() {
		return 234;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}

	public static void main(String[] args) {
		Planilha p = new Planilha();
		
		for (int i = 0; i < 234; i++) {
			String cName = p.getColumnName(i);
			System.out.println(cName);
		}
	}
	
}






