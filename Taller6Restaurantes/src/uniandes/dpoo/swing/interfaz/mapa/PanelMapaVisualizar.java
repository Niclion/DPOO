package uniandes.dpoo.swing.interfaz.mapa;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;
import uniandes.dpoo.swing.mundo.Restaurante;
@SuppressWarnings("serial")
public class PanelMapaVisualizar extends JPanel {
    private final JLabel labMapa;
    private List<Restaurante> restaurantes=new ArrayList<>();
    public PanelMapaVisualizar(){
        setLayout(new BorderLayout());
        labMapa=new JLabel(new ImageIcon("./imagenes/mapa.png"));
        labMapa.setBorder(new LineBorder(Color.DARK_GRAY));
        add(labMapa,BorderLayout.CENTER);
    }
    @Override protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D)g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Point origin=SwingUtilities.convertPoint(labMapa,0,0,this);
        for(Restaurante r:restaurantes){
            int x=origin.x+r.getX(),y=origin.y+r.getY();
            g2d.setColor(Color.RED);g2d.fillOval(x-4,y-4,9,9);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,11f));
            g2d.drawString(r.getNombre(),x+6,y-6);
        }
        g2d.dispose();
    }
    public void actualizarMapa(List<Restaurante> nuevosRestaurantes){
        restaurantes=new ArrayList<>(nuevosRestaurantes);repaint();
    }
}
