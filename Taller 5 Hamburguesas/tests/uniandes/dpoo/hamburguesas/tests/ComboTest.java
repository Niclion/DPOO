package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest
{
    private Combo combo;

    @BeforeEach
    void setUp( )
    {
        ArrayList<ProductoMenu> items = new ArrayList<ProductoMenu>( );
        items.add( new ProductoMenu( "corral", 14000 ) );
        items.add( new ProductoMenu( "papas medianas", 5500 ) );
        items.add( new ProductoMenu( "gaseosa", 5000 ) );
        combo = new Combo( "combo corral", 0.1, items );
    }

    @Test
    void testGetNombre( )
    {
        assertEquals( "combo corral", combo.getNombre( ) );
    }

    @Test
    void testGetPrecio( )
    {
        // (14000 + 5500 + 5000) con 10% de descuento = 22050
        assertEquals( 22050, combo.getPrecio( ) );
    }

    @Test
    void testGenerarTextoFactura( )
    {
        assertEquals( "Combo combo corral\n Descuento: 0.1\n            22050\n", combo.generarTextoFactura( ) );
    }
}