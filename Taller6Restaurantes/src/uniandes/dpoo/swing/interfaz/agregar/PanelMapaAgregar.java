package uniandes.dpoo.swing.interfaz.agregar;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
@SuppressWarnings("serial")
public class PanelMapaAgregar extends JPanel implements MouseListener {
    private final JLabel labMapa;
    private int x=-1,y=-1;
    public PanelMapaAgregar(){
        setLayout(new BorderLayout());
        labMapa=new JLabel(new ImageIcon("./imagenes/mapa.png"));
        labMapa.setBorder(new LineBorder(Color.DARK_GRAY));
        labMapa.addMouseListener(this); add(labMapa,BorderLayout.CENTER);
    }
    public int[] getCoordenadas(){ return new int[]{x,y}; }
    @Override protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(x>=0 && y>=0){
            Point at=SwingUtilities.convertPoint(labMapa,x,y,this);
            g.setColor(Color.RED); g.fillOval(at.x-4,at.y-4,9,9);
        }
    }
    @Override public void mouseClicked(MouseEvent e){ x=e.getX();y=e.getY(); repaint(); }
    @Override public void mousePressed(MouseEvent e){}
    @Override public void mouseReleased(MouseEvent e){}
    @Override public void mouseEntered(MouseEvent e){}
    @Override public void mouseExited(MouseEvent e){}
}
