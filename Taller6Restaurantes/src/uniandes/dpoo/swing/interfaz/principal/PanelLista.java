package uniandes.dpoo.swing.interfaz.principal;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import uniandes.dpoo.swing.mundo.Restaurante;
@SuppressWarnings("serial")
public class PanelLista extends JPanel implements ListSelectionListener {
    private final JList<Restaurante> listaDeRestaurantes;
    private final DefaultListModel<Restaurante> dataModel;
    private final VentanaPrincipal ventanaPrincipal;
    public PanelLista(VentanaPrincipal principal){
        ventanaPrincipal=principal; setBorder(new TitledBorder("Restaurantes")); setLayout(new BorderLayout());
        dataModel=new DefaultListModel<>(); listaDeRestaurantes=new JList<>(dataModel);
        listaDeRestaurantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaDeRestaurantes.addListSelectionListener(this);
        add(new JScrollPane(listaDeRestaurantes), BorderLayout.CENTER);
    }
    public void actualizarRestaurantes(List<Restaurante> restaurantes){
        Restaurante actual=listaDeRestaurantes.getSelectedValue();
        dataModel.clear(); for(Restaurante r:restaurantes) dataModel.addElement(r);
        if(actual!=null && restaurantes.contains(actual)) seleccionarRestaurante(actual);
    }
    @Override public void valueChanged(ListSelectionEvent e){
        if(!e.getValueIsAdjusting()) ventanaPrincipal.cambiarRestauranteSeleccionado(listaDeRestaurantes.getSelectedValue());
    }
    public void seleccionarRestaurante(Restaurante restaurante){ listaDeRestaurantes.setSelectedValue(restaurante,true); }
}
