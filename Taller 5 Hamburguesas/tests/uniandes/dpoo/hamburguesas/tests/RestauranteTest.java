package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

public class RestauranteTest
{
    private Restaurante restaurante;

    @BeforeEach
    void setUp( )
    {
        restaurante = new Restaurante( );
    }

    // Ayuda para no repetir las rutas en cada prueba
    private void cargar( String ingredientes, String menu, String combos ) throws Exception
    {
        restaurante.cargarInformacionRestaurante( new File( "./data/pruebas/" + ingredientes ), new File( "./data/pruebas/" + menu ),
                new File( "./data/pruebas/" + combos ) );
    }

    @Test
    void testCargarInformacion( ) throws Exception
    {
        cargar( "ingredientes_ok.txt", "menu_ok.txt", "combos_ok.txt" );
        assertEquals( 3, restaurante.getIngredientes( ).size( ) );
        assertEquals( 3, restaurante.getMenuBase( ).size( ) );
        assertEquals( 2, restaurante.getMenuCombos( ).size( ) );
        assertEquals( 22050, restaurante.getMenuCombos( ).get( 0 ).getPrecio( ) );
    }

    @Test
    void testIngredienteRepetido( )
    {
        assertThrows( IngredienteRepetidoException.class, ( ) -> cargar( "ingredientes_repetidos.txt", "menu_ok.txt", "combos_ok.txt" ) );
    }

    @Test
    void testIngredienteFormatoInvalido( )
    {
        assertThrows( NumberFormatException.class, ( ) -> cargar( "ingredientes_formato_invalido.txt", "menu_ok.txt", "combos_ok.txt" ) );
    }

    @Test
    void testProductoRepetido( )
    {
        assertThrows( ProductoRepetidoException.class, ( ) -> cargar( "ingredientes_ok.txt", "menu_repetido.txt", "combos_ok.txt" ) );
    }

    @Test
    void testComboRepetido( )
    {
        assertThrows( ProductoRepetidoException.class, ( ) -> cargar( "ingredientes_ok.txt", "menu_ok.txt", "combos_repetidos.txt" ) );
    }

    @Test
    void testComboConProductoFaltante( )
    {
        assertThrows( ProductoFaltanteException.class, ( ) -> cargar( "ingredientes_ok.txt", "menu_ok.txt", "combos_producto_faltante.txt" ) );
    }

    @Test
    void testArchivoNoExiste( )
    {
        assertThrows( FileNotFoundException.class, ( ) -> cargar( "no_existe.txt", "menu_ok.txt", "combos_ok.txt" ) );
    }

    @Test
    void testIniciarPedido( ) throws Exception
    {
        restaurante.iniciarPedido( "Juan", "Calle 1" );
        assertEquals( "Juan", restaurante.getPedidoEnCurso( ).getNombreCliente( ) );
    }

    @Test
    void testYaHayUnPedidoEnCurso( ) throws Exception
    {
        restaurante.iniciarPedido( "Juan", "Calle 1" );
        assertThrows( YaHayUnPedidoEnCursoException.class, ( ) -> restaurante.iniciarPedido( "Ana", "Calle 2" ) );
    }

    @Test
    void testCerrarSinPedido( )
    {
        assertThrows( NoHayPedidoEnCursoException.class, ( ) -> restaurante.cerrarYGuardarPedido( ) );
    }

    @Test
    void testCerrarYGuardarPedido( ) throws Exception
    {
        restaurante.iniciarPedido( "Juan", "Calle 1" );
        Pedido pedido = restaurante.getPedidoEnCurso( );
        restaurante.cerrarYGuardarPedido( );

        assertNull( restaurante.getPedidoEnCurso( ) );
        assertEquals( 1, restaurante.getPedidos( ).size( ) );

        File factura = new File( "./facturas/factura_" + pedido.getIdPedido( ) + ".txt" );
        assertTrue( factura.exists( ) );
        factura.delete( );
    }
}