package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas
{
    protected static final int COSTO_POR_KM_NATURAL = 600;
    protected static final int COSTO_POR_KM_CORPORATIVO = 900;

    protected static final double DESCUENTO_PEQUENA = 0.02;
    protected static final double DESCUENTO_MEDIANA = 0.10;
    protected static final double DESCUENTO_GRANDE = 0.20;

    @Override
    protected int calcularCostoBase(Vuelo vuelo, Cliente cliente)
    {
        int distancia = calcularDistanciaVuelo(vuelo.getRuta());

        if (cliente instanceof ClienteCorporativo)
        {
            return distancia * COSTO_POR_KM_CORPORATIVO;
        }
        else
        {
            return distancia * COSTO_POR_KM_NATURAL;
        }
    }

    @Override
    protected double calcularPorcentajeDescuento(Cliente cliente)
    {
        if (cliente instanceof ClienteCorporativo)
        {
            ClienteCorporativo corporativo = (ClienteCorporativo) cliente;

            if (corporativo.getTamanoEmpresa() == ClienteCorporativo.GRANDE)
            {
                return DESCUENTO_GRANDE;
            }
            else if (corporativo.getTamanoEmpresa() == ClienteCorporativo.MEDIANA)
            {
                return DESCUENTO_MEDIANA;
            }
            else if (corporativo.getTamanoEmpresa() == ClienteCorporativo.PEQUENA)
            {
                return DESCUENTO_PEQUENA;
            }
        }

        return 0;
    }
}