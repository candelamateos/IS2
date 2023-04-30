package integracion.departamento;

import java.util.List;

import negocio.departamento.TDepartamento;

public interface DaoDepartamento {
	
	public int createDepartamento(TDepartamento departamento);
	
	public boolean deleteDepartamento(int id);
	
	public boolean updateDepartamento(TDepartamento departamento);
	
	public TDepartamento readDepartamento(int id);
	
	public List<TDepartamento> readAllDepartamento();
}
