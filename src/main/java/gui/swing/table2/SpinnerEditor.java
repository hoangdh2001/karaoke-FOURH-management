package gui.swing.table2;

import gui.swing.table.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.util.EventObject;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

public class SpinnerEditor extends DefaultCellEditor {
        JSpinner spinner;
        DefaultEditor editor;
        JTextField textField;
        boolean valueSet;
        int min = 0;
        int step = 1;
        int i = 1;
        // Initializes the spinner.
        public SpinnerEditor(int max) {
            super(new JTextField());
            
            SpinnerModel value = new SpinnerNumberModel(i, min, max, step);
            spinner = new JSpinner(value);
            editor = ((JSpinner.DefaultEditor)spinner.getEditor());
            textField = editor.getTextField();
            textField.addFocusListener( new FocusAdapter() {
                @Override
                public void focusGained( FocusEvent fe ) {
                    SwingUtilities.invokeLater( new Runnable() {
                        @Override
                        public void run() {
                            if ( valueSet ) {
                                textField.setCaretPosition(1);
                            }
                        }
                    });
                }
            });
            textField.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed( ActionEvent ae ) {
                    stopCellEditing();
                }
            });
        }
        
        
        // Prepares the spinner component and returns it.
        @Override
        public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column
        ) {
            if ( !valueSet ) {
                spinner.setValue(value);
            }
            SwingUtilities.invokeLater( new Runnable() {
                @Override
                public void run() {
                    textField.requestFocus();
                }
            });
            return spinner;
        }

        @Override
        public boolean isCellEditable( EventObject eo ) {
            System.err.println("isCellEditable");
            if ( eo instanceof KeyEvent ) {
                KeyEvent ke = (KeyEvent)eo;
                System.err.println("key event: "+ke.getKeyChar());
                textField.setText(String.valueOf(ke.getKeyChar()));
                textField.select(1,1);
                textField.setCaretPosition(1);
                textField.moveCaretPosition(1);
                valueSet = true;
            } else {
                valueSet = false;
            }
            return true;
        }

        // Returns the spinners current value.
        @Override
        public Object getCellEditorValue() {
            return spinner.getValue();
        }

        @Override
        public boolean stopCellEditing() {
            try {
                editor.commitEdit();
                spinner.commitEdit();
            } catch ( java.text.ParseException e ) {
                JOptionPane.showMessageDialog(null,
                    "Invalid value, discarding.");
            }
            return super.stopCellEditing();
        }
    
}
