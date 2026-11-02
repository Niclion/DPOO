package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest
{
    private ProductoAjustado ajustado;

    @BeforeEach
    void setUp( ) throws Exception
    {
        ajustado = new ProductoAjustado( new ProductoMenu( "corral", 14000 ) );

        
        Field agregados = ProductoAjustado.class.getDeclaredField( "agregados" );
        agregados.setAccessible( true );
        ( ( ArrayList<Ingrediente> )agregados.get( ajustado ) ).add( new Ingrediente( "queso", 2500 ) );

        Field eliminados = ProductoAjustado.class.getDeclaredField( "eliminados" );
        eliminados.setAccessible( true );
        ( ( ArrayList<Ingrediente> )eliminados.get( ajustado ) ).add( new Ingrediente( "cebolla", 1000 ) );
    }

    @Test
    void testGetNombre( )
    {
        assertEquals( "corral", ajustado.getNombre( ) );
    }

    @Test
    void testGetPrecio( )
    {
        
        assertEquals( 16500, ajustado.getPrecio( ) );
    }

    @Test
    void testGenerarTextoFactura( )
    {
        String esperado = "corral\n" + "    +queso                2500\n" + "    -cebolla\n" + "            16500\n";
        assertEquals( esperado, ajustado.generarTextoFactura( ) );
    }
}