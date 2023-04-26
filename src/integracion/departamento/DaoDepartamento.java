package integracion.departamento;

import java.util.List;

import negocio.departamento.TDepartamento;

public interface DaoDepartamento {
	
	public int createDepartamento(TDepartamento departamento);
	
	public int updateDepartamento(TDepartamento departamento);
	
	public int deleteDepartamento(TDepartamento departamento);
	
	public TDepartamento readDepartamento(TDepartamento departamento);
	
	public List<TDepartamento> readAllDepartamento(TDepartamento departamento);
}
