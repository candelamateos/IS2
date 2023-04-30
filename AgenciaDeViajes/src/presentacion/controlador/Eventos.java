package presentacion.controlador;

public class Eventos {
	//Todos los codigos deben ser diferentes
	
	//Departamento 0xx
	public static final int ALTA_DEPARTAMENTO = 001;
	public static final int BAJA_DEPARTAMENTO = 002;
	public static final int BUSCAR_DEPARTAMENTO = 003;
	public static final int MODIFICAR_DEPARTAMENTO = 004;
	public static final int LISTAR_DEPARTAMENTO = 005;
	
	//Factura 1xx
	public static final int FACTURAS = 100;
	public static final int ABRIR_VENTA = 101;
	public static final int CERRAR_VENTA = 102;
	public static final int AÑADIR_VENTA = 103;
	public static final int MODIFICAR_FACTURA = 104;
	public static final int BUSCAR_FACTURA = 105;
	public static final int LISTAR_FACTURA = 106;
	
	//Servicio 2xx

	public static final int SERVICIOS = 200;
	public static final int ALTA_SERVICIO = 201;
	public static final int BAJA_SERVICIO = 202;
	public static final int BUSCAR_SERVICIO = 203;
	public static final int MODIFICAR_SERVICIO = 204;
	public static final int LISTAR_SERVICIO = 205;
	
	//Trabajador 3xx
	
	//Viaje 4xx
	public static final int VIAJES = 400;
	public static final int ALTA_VIAJE = 401;
	public static final int BAJA_VIAJE = 402;
	public static final int BUSCAR_VIAJE = 403;
	public static final int MODIFICAR_VIAJE = 404;
	public static final int LISTAR_VIAJE = 405;
	public static final int RES_ALTA_VIAJE_OK = 406;
	public static final int RES_ALTA_VIAJE_ERROR = 407;
	public static final int RES_BAJA_VIAJE_OK = 408;
	public static final int RES_BAJA_VIAJE_ERROR = 409;
	public static final int RES_BUSCAR_VIAJE_OK = 410;
	public static final int RES_BUSCAR_VIAJE_ERROR = 411;
	public static final int RES_MODIFICAR_VIAJE_OK = 412;
	public static final int RES_MODIFICAR_VIAJE_ERROR = 413;
	public static final int RES_LISTAR_VIAJE_OK = 414;
	public static final int RES_LISTAR_VIAJE_ERROR = 415;
	
	
	//Cliente 5xx
	public static final int CLIENTES = 500;
	public static final int ALTA_CLIENTE = 501;
	public static final int BAJA_CLIENTE = 502;
	public static final int BUSCAR_CLIENTE = 503;
	public static final int MODIFICAR_CLIENTE = 504;
	public static final int LISTAR_CLIENTE = 505;
	public static final int RES_ALTA_DEPARTAMENTO_OK = 506;
	public static final int RES_ALTA_DEPARTAMENTO_ERROR = 507;
	
	
}
