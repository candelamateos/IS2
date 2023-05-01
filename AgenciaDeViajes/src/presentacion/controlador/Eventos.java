package presentacion.controlador;

public class Eventos {
	//Todos los codigos deben ser diferentes
	
	//Departamento 0xx
	public static final int ALTA_DEPARTAMENTO = 001;
	public static final int BAJA_DEPARTAMENTO = 002;
	public static final int BUSCAR_DEPARTAMENTO = 003;
	public static final int MODIFICAR_DEPARTAMENTO = 004;
	public static final int LISTAR_DEPARTAMENTO = 005;
	public static final int RES_ALTA_DEPARTAMENTO_OK = 016;
	public static final int RES_ALTA_DEPARTAMENTO_ERROR = 017;
	public static final int RES_BAJA_DEPARTAMENTO_OK = 006;
	public static final int RES_BAJA_DEPARTAMENTO_ERROR = 006;
	public static final int RES_BUSCAR_DEPARTAMENTO_OK = 010;
	public static final int RES_BUSCAR_DEPARTAMENTO_ERROR = 011;
	public static final int RES_MODIFICAR_DEPARTAMENTO_OK = 012;
	public static final int RES_MODIFICAR_DEPARTAMENTO_ERROR = 013;
	public static final int RES_LISTAR_DEPARTAMENTO_OK = 014;
	public static final int RES_LISTAR_DEPARTAMENTO_ERROR = 015;
	
	//Factura 1xx
	public static final int FACTURAS = 100;
	public static final int ABRIR_VENTA = 101;
	public static final int CERRAR_VENTA = 102;
	public static final int ANIADIR_VIAJE_A_FACTURA = 103;
	public static final int MODIFICAR_FACTURA = 104;
	public static final int BUSCAR_FACTURA = 105;
	public static final int LISTAR_FACTURAS = 106;
	public static final int RES_ABRIR_VENTA_OK = 107;
	public static final int RES_ABRIR_VENTA_ERROR = 108;
	public static final int RES_CERRAR_VENTA_OK = 109;
	public static final int RES_CERRAR_VENTA_ERROR = 110;
	public static final int RES_ANIADIR_VIAJE_A_FACTURA_OK = 111;
	public static final int RES_ANIADIR_VIAJE_A_FACTURA_ERROR = 112;
	public static final int RES_MODIFICAR_FACTURA_OK = 113;
	public static final int RES_MODIFICAR_FACTURA_ERROR = 114;
	public static final int RES_BUSCAR_FACTURA_OK = 115;
	public static final int RES_BUSCAR_FACTURA_ERROR = 116;
	public static final int RES_LISTAR_FACTURAS_OK = 117;
	public static final int RES_LISTAR_FACTURAS_ERROR = 118;
	
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
	public static final int RES_ALTA_CLIENTE_OK = 506;
	public static final int RES_ALTA_CLIENTE_ERROR = 507;
	public static final int RES_BAJA_CLIENTE_OK = 508;
	public static final int RES_BAJA_CLIENTE_ERROR = 509;
	public static final int RES_BUSCAR_CLIENTE_OK = 510;
	public static final int RES_BUSCAR_CLIENTE_ERROR = 511;
	public static final int RES_MODIFICAR_CLIENTE_OK = 512;
	public static final int RES_MODIFICAR_CLIENTE_ERROR = 513;
	public static final int RES_LISTAR_CLIENTE_OK = 514;
	public static final int RES_LISTAR_CLIENTE_ERROR = 515;
}
