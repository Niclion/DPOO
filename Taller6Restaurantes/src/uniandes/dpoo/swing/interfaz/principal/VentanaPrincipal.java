package uniandes.dpoo.swing.interfaz.principal;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import uniandes.dpoo.swing.interfaz.agregar.VentanaAgregarRestaurante;
import uniandes.dpoo.swing.interfaz.mapa.VentanaMapa;
import uniandes.dpoo.swing.mundo.Diario;
import uniandes.dpoo.swing.mundo.Restaurante;
@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {
    private final Diario mundo;
    private final PanelBotones pBotones;
    private final PanelDetallesRestaurante pDetalles;
    private final PanelLista pLista;
    private VentanaMapa ventanaMapa;
    private VentanaAgregarRestaurante ventanaAgregar;
    public VentanaPrincipal(Diario elDiario){
        mundo=elDiario; setLayout(new BorderLayout());
        pBotones=new PanelBotones(this); add(pBotones,BorderLayout.NORTH);
        pLista=new PanelLista(this); add(pLista,BorderLayout.CENTER);
        pDetalles=new PanelDetallesRestaurante(); add(pDetalles,BorderLayout.SOUTH);
        actualizarRestaurantes();
        setTitle("Restaurantes"); setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,600); setLocationRelativeTo(null); setVisible(true);
    }
    public void mostrarVetanaNuevoRestaurante(){
        if(ventanaAgregar==null || !ventanaAgregar.isVisible()){
            ventanaAgregar=new VentanaAgregarRestaurante(this); ventanaAgregar.setVisible(true);
        } else ventanaAgregar.toFront();
    }
    public void mostrarVentanaMapa(){
        if(ventanaMapa==null || !ventanaMapa.isVisible()){
            ventanaMapa=new VentanaMapa(this,mundo.getRestaurantes(true)); ventanaMapa.setVisible(true);
        } else { ventanaMapa.actualizarRestaurantes(); ventanaMapa.toFront(); }
    }
    public void agregarRestaurante(String nombre,int calificacion,int x,int y,boolean visitado){
        Restaurante nuevo=new Restaurante(nombre,calificacion,x,y,visitado);
        mundo.agregarRestaurante(nuevo); actualizarRestaurantes(); pLista.seleccionarRestaurante(nuevo);
        if(ventanaMapa!=null && ventanaMapa.isVisible()) ventanaMapa.actualizarRestaurantes();
    }
    public List<Restaurante> getRestaurantes(boolean completos){ return mundo.getRestaurantes(completos); }
    private void actualizarRestaurantes(){
        List<Restaurante> todos=mundo.getRestaurantes(true);
        pLista.actualizarRestaurantes(todos);
        if(!todos.isEmpty()) pLista.seleccionarRestaurante(todos.get(0));
        else pDetalles.actualizarRestaurante(null);
    }
    public void cambiarRestauranteSeleccionado(Restaurante seleccionado){ pDetalles.actualizarRestaurante(seleccionado); }
    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{
            Diario diario=new Diario();
            diario.agregarRestaurante(new Restaurante("Pita Pan",4,30,30,true));
            diario.agregarRestaurante(new Restaurante("Lord of the Wings",5,170,210,true));
            diario.agregarRestaurante(new Restaurante("Nacho Business",2,350,170,false));
            diario.agregarRestaurante(new Restaurante("Thai Tanic",1,110,100,false));
            diario.agregarRestaurante(new Restaurante("Planet of the Creppes",3,400,400,true));
            new VentanaPrincipal(diario);
        });
    }
}
