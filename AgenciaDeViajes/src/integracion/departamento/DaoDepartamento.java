package integracion.departamento;

import java.util.List;

import negocio.departamento.TDepartamento;

public interface DaoDepartamento {
	
	public boolean createDepartamento(TDepartamento departamento);
	
	public boolean updateDepartamento(TDepartamento departamento);
	
	public boolean deleteDepartamento(int id);
	
	public TDepartamento readDepartamento(int id);
	
	public List<TDepartamento> readAllDepartamento();
}
