package uniandes.dpoo.swing.interfaz.agregar;
import java.awt.GridLayout;
import javax.swing.*;
@SuppressWarnings("serial")
public class PanelEditarRestaurante extends JPanel {
    private final JTextField txtNombre;
    private final JComboBox<String> cbbCalificacion, cbbVisitado;
    public PanelEditarRestaurante(){
        setLayout(new GridLayout(3,2,5,5));
        add(new JLabel("Nombre")); txtNombre=new JTextField(18); add(txtNombre);
        add(new JLabel("Calificación")); cbbCalificacion=new JComboBox<>(new String[]{"1","2","3","4","5"}); add(cbbCalificacion);
        add(new JLabel("Visitado")); cbbVisitado=new JComboBox<>(new String[]{"No","Sí"}); add(cbbVisitado);
    }
    public boolean getVisitado(){ return "Sí".equals(cbbVisitado.getSelectedItem()); }
    public int getCalificacion(){ return Integer.parseInt((String)cbbCalificacion.getSelectedItem()); }
    public String getNombre(){ return txtNombre.getText().trim(); }
}
