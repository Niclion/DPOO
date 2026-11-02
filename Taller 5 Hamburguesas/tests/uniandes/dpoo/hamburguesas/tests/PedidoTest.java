package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest
{
    private Pedido pedido;

    @BeforeEach
    void setUp( )
    {
        pedido = new Pedido( "Juan", "Calle 1" );
        pedido.agregarProducto( new ProductoMenu( "corral", 14000 ) );
        pedido.agregarProducto( new ProductoMenu( "gaseosa", 5000 ) );
    }

    @Test
    void testGetNombreCliente( )
    {
        assertEquals( "Juan", pedido.getNombreCliente( ) );
    }

    @Test
    void testGetIdPedido( )
    {
        Pedido otro = new Pedido( "Ana", "Calle 2" );
        assertEquals( pedido.getIdPedido( ) + 1, otro.getIdPedido( ) );
    }

    @Test
    void testGetPrecioTotalPedido( )
    {
        // Neto 19000 + IVA 3610 = 22610
        assertEquals( 22610, pedido.getPrecioTotalPedido( ) );
    }

    @Test
    void testGenerarTextoFactura( )
    {
        String esperado = "Cliente: Juan\n" + "Dirección: Calle 1\n" + "----------------\n" + "corral\n            14000\n"
                + "gaseosa\n            5000\n" + "----------------\n" + "Precio Neto:  19000\n" + "IVA:          3610\n"
                + "Precio Total: 22610\n";
        assertEquals( esperado, pedido.generarTextoFactura( ) );
    }

    @Test
    void testGuardarFactura( ) throws Exception
    {
        File archivo = new File( "./data/pruebas/factura_prueba.txt" );
        pedido.guardarFactura( archivo );

        String contenido = new String( Files.readAllBytes( archivo.toPath( ) ) );
        assertEquals( pedido.generarTextoFactura( ), contenido );
        archivo.delete( );
    }

    @Test
    void testGuardarFacturaRutaInvalida( )
    {
        File archivo = new File( "./carpeta_que_no_existe/factura.txt" );
        assertThrows( FileNotFoundException.class, ( ) -> pedido.guardarFactura( archivo ) );
    }
}