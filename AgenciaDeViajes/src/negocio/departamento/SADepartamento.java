package negocio.departamento;

import java.util.List;

public interface SADepartamento {
	
    public int createDepartamento(TDepartamento departamento);
	
	public boolean updateDepartamento(TDepartamento departamento);
	
	public boolean deleteDepartamento(int id);
	
	public TDepartamento readDepartamento(int id);
	
	public List<TDepartamento> readAllDepartamento();
}
