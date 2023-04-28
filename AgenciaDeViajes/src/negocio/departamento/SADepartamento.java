package negocio.departamento;

import java.util.List;

public interface SADepartamento {
    public boolean createDepartamento(TDepartamento departamento);
	
	public boolean updateDepartamento(TDepartamento departamento);
	
	public boolean deleteDepartamento(TDepartamento departamento);
	
	public TDepartamento readDepartamento(int id);
	
	public List<TDepartamento> readAllDepartamento();
}
