package uniandes.dpoo.swing.interfaz.agregar;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
@SuppressWarnings("serial")
public class PanelBotonesAgregar extends JPanel implements ActionListener {
    private static final String CREAR="nuevo", CERRAR="ver";
    private final JButton butNuevo,butCerrar;
    private final VentanaAgregarRestaurante ventanaPrincipal;
    public PanelBotonesAgregar(VentanaAgregarRestaurante ventana){
        ventanaPrincipal=ventana; setLayout(new FlowLayout());
        butNuevo=new JButton("Crear restaurante"); butNuevo.setActionCommand(CREAR); butNuevo.addActionListener(this); add(butNuevo);
        butCerrar=new JButton("Cerrar"); butCerrar.setActionCommand(CERRAR); butCerrar.addActionListener(this); add(butCerrar);
    }
    @Override public void actionPerformed(ActionEvent e){
        if(CREAR.equals(e.getActionCommand())) ventanaPrincipal.agregarRestaurante();
        else if(CERRAR.equals(e.getActionCommand())) ventanaPrincipal.cerrarVentana();
    }
}
