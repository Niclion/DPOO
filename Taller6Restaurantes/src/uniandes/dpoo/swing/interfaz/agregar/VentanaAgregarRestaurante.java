package uniandes.dpoo.swing.interfaz.agregar;
import java.awt.BorderLayout;
import javax.swing.*;
import uniandes.dpoo.swing.interfaz.principal.VentanaPrincipal;
@SuppressWarnings("serial")
public class VentanaAgregarRestaurante extends JFrame {
    private final PanelEditarRestaurante panelDetalles;
    private final PanelBotonesAgregar panelBotones;
    private final PanelMapaAgregar panelMapa;
    private final VentanaPrincipal ventanaPrincipal;
    public VentanaAgregarRestaurante(VentanaPrincipal principal){
        ventanaPrincipal=principal; setTitle("Nuevo restaurante"); setLayout(new BorderLayout());
        panelMapa=new PanelMapaAgregar(); add(panelMapa,BorderLayout.CENTER);
        panelDetalles=new PanelEditarRestaurante(); panelBotones=new PanelBotonesAgregar(this);
        JPanel sur=new JPanel(new BorderLayout()); sur.add(panelDetalles,BorderLayout.CENTER); sur.add(panelBotones,BorderLayout.SOUTH);
        add(sur,BorderLayout.SOUTH);
        pack();setLocationRelativeTo(null);setDefaultCloseOperation(DISPOSE_ON_CLOSE);setResizable(false);
    }
    public void agregarRestaurante(){
        String nombre=panelDetalles.getNombre();
        if(nombre.isEmpty()){
            JOptionPane.showMessageDialog(this,"Escribe el nombre del restaurante.","Dato faltante",JOptionPane.WARNING_MESSAGE);return;
        }
        int[] xy=panelMapa.getCoordenadas();
        if(xy[0]<0 || xy[1]<0){
            JOptionPane.showMessageDialog(this,"Haz clic en el mapa para marcar la ubicación.","Ubicación faltante",JOptionPane.WARNING_MESSAGE);return;
        }
        ventanaPrincipal.agregarRestaurante(nombre,panelDetalles.getCalificacion(),xy[0],xy[1],panelDetalles.getVisitado());
        dispose();
    }
    public void cerrarVentana(){ dispose(); }
}
