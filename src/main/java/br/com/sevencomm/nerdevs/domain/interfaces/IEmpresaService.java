package br.com.sevencomm.nerdevs.domain.interfaces;

import br.com.sevencomm.nerdevs.domain.models.Empresa;

import java.util.List;

public interface IEmpresaService {
    Empresa insertEmpresa (Empresa empresa);

    List<Empresa> listEmpresas();

    Empresa getEmpresaById(Integer empresaId);

    Empresa updateEmpresa (Empresa empresa);

    Boolean deleteEmpresa(Integer empresaId);
    
}