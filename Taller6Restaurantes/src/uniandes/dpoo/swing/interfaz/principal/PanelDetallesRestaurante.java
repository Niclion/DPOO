package uniandes.dpoo.swing.interfaz.principal;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import uniandes.dpoo.swing.mundo.Restaurante;
@SuppressWarnings("serial")
public class PanelDetallesRestaurante extends JPanel {
    private final JLabel labNombre, labCalificacion;
    private final JCheckBox chkVisitado;
    public PanelDetallesRestaurante(){
        setLayout(new GridLayout(3,1));
        labNombre=new JLabel("Nombre: "); labCalificacion=new JLabel("Calificación: ");
        chkVisitado=new JCheckBox("Visitado"); chkVisitado.setEnabled(false);
        JPanel p1=new JPanel(new FlowLayout(FlowLayout.LEFT)); p1.add(labNombre); add(p1);
        JPanel p2=new JPanel(new FlowLayout(FlowLayout.LEFT)); p2.add(labCalificacion); add(p2);
        JPanel p3=new JPanel(new FlowLayout(FlowLayout.LEFT)); p3.add(chkVisitado); add(p3);
    }
    private void actualizarRestaurante(String nombre,int calificacion,boolean visitado){
        labNombre.setText("Nombre: "+nombre);
        labCalificacion.setText("Calificación: ");
        labCalificacion.setIcon(calificacion>=1 && calificacion<=5 ? buscarIconoCalificacion(calificacion):null);
        chkVisitado.setSelected(visitado);
    }
    public void actualizarRestaurante(Restaurante r){
        if(r==null) actualizarRestaurante("",0,false);
        else actualizarRestaurante(r.getNombre(),r.getCalificacion(),r.isVisitado());
    }
    private ImageIcon buscarIconoCalificacion(int calificacion){
        return new ImageIcon("./imagenes/stars"+calificacion+".png");
    }
}
