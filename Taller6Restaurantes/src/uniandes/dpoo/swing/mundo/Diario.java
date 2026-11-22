package uniandes.dpoo.swing.mundo;
import java.util.ArrayList;
import java.util.List;
public class Diario {
    private final List<Restaurante> restaurantes = new ArrayList<>();
    public List<Restaurante> getRestaurantes(boolean completos){
        List<Restaurante> seleccion=new ArrayList<>();
        for(Restaurante r: restaurantes) if(completos || r.isVisitado()) seleccion.add(r);
        return seleccion;
    }
    public void agregarRestaurante(Restaurante restaurante){ restaurantes.add(restaurante); }
}
