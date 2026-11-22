package uniandes.dpoo.swing.interfaz.principal;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
@SuppressWarnings("serial")
public class PanelBotones extends JPanel implements ActionListener {
    private static final String NUEVO="nuevo", VER="ver";
    private final JButton butNuevo, butVerTodos;
    private final VentanaPrincipal ventanaPrincipal;
    public PanelBotones(VentanaPrincipal principal){
        ventanaPrincipal=principal; setLayout(new FlowLayout());
        butNuevo=new JButton("Nuevo"); butNuevo.setActionCommand(NUEVO); butNuevo.addActionListener(this); add(butNuevo);
        butVerTodos=new JButton("Ver todos"); butVerTodos.setActionCommand(VER); butVerTodos.addActionListener(this); add(butVerTodos);
    }
    @Override public void actionPerformed(ActionEvent e){
        if(NUEVO.equals(e.getActionCommand())) ventanaPrincipal.mostrarVetanaNuevoRestaurante();
        else if(VER.equals(e.getActionCommand())) ventanaPrincipal.mostrarVentanaMapa();
    }
}
