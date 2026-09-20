package uniandes.dpoo.aerolinea.persistencia;

/**
 * Esta clase cumple el rol de una fábrica de componentes que se encargan de
 * manejar la persistencia de una aerolínea y de sus tiquetes.
 */
public class CentralPersistencia
{
    /**
     * La cadena utilizada para identificar a los archivos en formato JSON
     */
    public static final String JSON = "JSON";

    /**
     * La cadena utilizada para identificar a los archivos en texto plano
     */
    public static final String PLAIN = "PlainText";

    /**
     * Este método retorna una nueva instancia de una clase capaz de cargar y
     * salvar los datos de una aerolínea.
     */
    public static IPersistenciaAerolinea getPersistenciaAerolinea(String tipoArchivo)
            throws TipoInvalidoException
    {
        if (JSON.equals(tipoArchivo))
        {
            return new PersistenciaAerolineaJson();
        }
        else if (PLAIN.equals(tipoArchivo))
        {
            return new PersistenciaAerolineaPlaintext();
        }
        else
        {
            throw new TipoInvalidoException(tipoArchivo);
        }
    }

    /**
     * Este método retorna una nueva instancia de una clase capaz de cargar y
     * salvar los datos de los tiquetes de una aerolínea.
     */
    public static IPersistenciaTiquetes getPersistenciaTiquetes(String tipoArchivo)
            throws TipoInvalidoException
    {
        if (JSON.equals(tipoArchivo))
        {
            return new PersistenciaTiquetesJson();
        }
        else
        {
            throw new TipoInvalidoException(tipoArchivo);
        }
    }
}