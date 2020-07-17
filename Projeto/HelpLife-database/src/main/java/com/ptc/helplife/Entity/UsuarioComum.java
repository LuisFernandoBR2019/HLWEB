package com.ptc.helplife.Entity;

public class UsuarioComum extends Usuario{


    private TipoSanguineo tipoSanguineo;
    private String sexo;
    private String dataNascimento;
   

    public TipoSanguineo getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

   

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

	@Override
	public String toString() {
		return "UsuarioComum [tipoSanguineo=" + tipoSanguineo + ", sexo=" + sexo + ", dataNascimento=" + dataNascimento
				+ "]";
	}

    
}
