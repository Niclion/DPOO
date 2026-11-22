package uniandes.dpoo.swing.interfaz.mapa;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import uniandes.dpoo.swing.interfaz.principal.VentanaPrincipal;
import uniandes.dpoo.swing.mundo.Restaurante;
@SuppressWarnings("serial")
public class VentanaMapa extends JFrame implements ActionListener {
    private static final String VISITADOS="VISITADOS",TODOS="TODOS";
    private final PanelMapaVisualizar panelMapa;
    private final JRadioButton radioTodos,radioVisitados;
    private final VentanaPrincipal ventanaPrincipal;
    public VentanaMapa(VentanaPrincipal ventanaPrincipal,List<Restaurante> restaurantes){
        this.ventanaPrincipal=ventanaPrincipal;setTitle("Mapa de restaurantes");setLayout(new BorderLayout());
        panelMapa=new PanelMapaVisualizar();panelMapa.actualizarMapa(restaurantes);add(panelMapa,BorderLayout.CENTER);
        JPanel sur=new JPanel(new FlowLayout());
        radioTodos=new JRadioButton("Todos",true);radioTodos.setActionCommand(TODOS);radioTodos.addActionListener(this);
        radioVisitados=new JRadioButton("Visitados");radioVisitados.setActionCommand(VISITADOS);radioVisitados.addActionListener(this);
        ButtonGroup grupo=new ButtonGroup();grupo.add(radioTodos);grupo.add(radioVisitados);
        sur.add(radioTodos);sur.add(radioVisitados);add(sur,BorderLayout.SOUTH);
        pack();setResizable(false);setDefaultCloseOperation(DISPOSE_ON_CLOSE);setLocationRelativeTo(null);
    }
    public void actualizarRestaurantes(){ panelMapa.actualizarMapa(ventanaPrincipal.getRestaurantes(radioTodos.isSelected())); }
    @Override public void actionPerformed(ActionEvent e){ actualizarRestaurantes(); }
}
